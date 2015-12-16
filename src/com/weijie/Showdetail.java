package com.weijie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.fenci.Fenci;
import com.search.Create_index;
import com.search.Doc;
import com.search.Getdetail;
import com.search.Unit;
import com.sim.Simquery;


/**
 * Servlet implementation class Test
 */
@WebServlet("/Showdetail")
public class Showdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showdetail() {
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
        //out.println("query:" + content);
        String mid = req.getParameter("mid");
        
        Getdetail detail = new Getdetail();
        
        try {
			Map<String,Doc> dMap = detail.create();
			Doc iDoc = dMap.get(mid);
			PrintWriter out = resp.getWriter(); 
			out.println("中文名" + iDoc.getChName() + "</br>");
			out.println("ID：" + iDoc.getID() + "</br>");
			out.println("地址：" + iDoc.getAddr() + "</br>");
			out.println("评分：" + iDoc.getScore() + "</br>");
			out.println("所在城市：" + iDoc.getCityx() + "</br>");
			out.println("所在省份：" + iDoc.getCountry() + "</br>");
			out.println("景点类型：" + iDoc.getType() + "</br>");
			out.println("简介：" + iDoc.getSummary() + "</br>");
			out.println("URL：" + iDoc.getURL() + "</br>");
			out.println("纬度：" + iDoc.getX() + "</br>");
			out.println("经度：" + iDoc.getY() + "</br>");
			out.println("联系电话：" + iDoc.getTel()+ "</br>");
			out.println("门票信息：" + iDoc.getTicketInfo() + "</br>");
			out.println("建议游玩时间：" + iDoc.getTourTime() + "</br>");
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
