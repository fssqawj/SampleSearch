package com.weijie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fenci.Fenci;
import com.knowledge.OwlTest;
import com.search.Create_index;
import com.search.Unit;
import com.sim.Simquery;


/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Create_index t = new Create_index();
	OwlTest knowledge = new OwlTest();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse resp)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");  
        //PrintWriter out = resp.getWriter();  
        String content = req.getParameter("content");
        //out.println("query:" + content);
        int mid = Integer.parseInt(req.getParameter("mid") == null?"0":req.getParameter("mid"));
        
        OwlTest knowledge = new OwlTest();
        Simquery sim = new Simquery();
        try {
        	List<String> simq = sim.simquery(content);
			List<Unit> res = t.create(content);
			List<String> knowres = knowledge.doQuery(content);
			List<String> docres = new ArrayList<String>();
			List<String> srcres = new ArrayList<String>();
			List<String> prjres = new ArrayList<String>();
			List<String> depres = new ArrayList<String>();
			List<String> manres = new ArrayList<String>();
			List<String> url = new ArrayList<String>();
			for(int i = 0;i < res.size();i ++){
				//out.println("</br>DOC:" + res.get(i).getDocId() + " src:" + res.get(i).getNum() + " subname:" + res.get(i).getSubjectname());
				docres.add(res.get(i).getDocId());
				srcres.add(Double.toString(res.get(i).getSrc()));
				prjres.add(res.get(i).getChName());
				depres.add(res.get(i).getAddr());
				manres.add(res.get(i).getType());
				url.add(res.get(i).getURL());
			}
			List<String> sq = new ArrayList<String>();
			List<String> sr = new ArrayList<String>();
			String[] rt = simq.get(0).split("#");
			String src = "0";
			if(rt.length > 2)src = rt[2];
			for(String key:simq){
				System.out.println(key);
				String[] tem = key.split("#");
				//System.out.println(tem[0] + " " + tem[1]);
				sq.add(tem[0]);
				if(tem.length > 1)sr.add(tem[1]);
			}
			req.setAttribute("docres", docres);
			req.setAttribute("url", url);
			req.setAttribute("depres", depres);
			req.setAttribute("manres", manres);
			req.setAttribute("srcres", srcres);
			req.setAttribute("prjres", prjres);
			req.setAttribute("content", content);
			req.setAttribute("knowres", knowres);
			req.setAttribute("simq", sq);
			req.setAttribute("simr", sr);
			req.setAttribute("topq", sq.get(0));
			req.setAttribute("topr", sr.get(0));
			req.setAttribute("src", src);
			req.setAttribute("cnt", docres.size());
			req.setAttribute("test", System.getProperty("user.dir"));
			if(mid == 1){
				req.setAttribute("prjres", depres);
				req.setAttribute("depres", prjres);
			}
			if(mid == 2){
				req.setAttribute("prjres", manres);
				
				req.setAttribute("manres", prjres);
			}
			if(mid == 3){
				RequestDispatcher rd = req.getRequestDispatcher("know.jsp");
				rd.forward(req,resp);
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
				rd.forward(req,resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //out.close();  
  
        //super.doGet(req, resp);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}