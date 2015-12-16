<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page 
import="java.util.List"
import="com.search.Create_index"
import="com.search.Unit"
import="com.change.Change"
%>
<html>
<head>
<meta content="text/html; charset=UTF-8"http-equiv="content-type">
<meta content="IE=Edge"http-equiv="X-UA-Compatible">
<title><%=(String)request.getAttribute("content")%> - Icare 搜索</title>
<link type="text/css"href="./public/css/res_style.css?v=1107"rel="stylesheet"/>
<script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?dd1c64642439e8993975d7c03f91df57";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s)})();</script>
<style type="text/css">

div#contentx {width:600px;float:left;}
div#search_result {
	margin-left:124px;
	font-family: arial, sans-serif;
	width:550px;
	float:left;
}
ul#related_kwy_wordsx {
	margin-left:124px;
	font-family: arial, sans-serif;
	float:left;
	}
.s_r_x {
	background-color: #ffffdd;
	margin-bottom:10px;
	word-wrap: break-word;
}
.s_r_x .title {
	color: #1A0DAB;
	font-size: 18px;
	white-space:normal;
	text-decoration: none;
	word-wrap: break-word;
}
.s_r_x .min_content {
	line-height: 1.4;
	width:550px;
	white-space:normal;
	word-wrap: break-word;
	color: #545454;
	font-size:13px;
}
</style>
</head>
<body>
<div class="onegoogle noprint">
  <div id="gbar"><nobr>
  <a href="http://www.glgoo.com"id="gb_1"class="gb1 gbz0l">搜索</a>
  <a id="gb_2"class="gb1"href="http://localhost/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=0">景点</a>
  <a href="http://localhost/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=1"id="gb_8"class="gb1">类型</a>
  <a href="http://localhost/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=2"id="gb_5"class="gb1">地址</a>
  <a href="http://localhost/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=3"id="gb_49"class="gb1">care知道</a>
  
</div>
<div class="clear"></div>
<div class="search_box">
  <div class="logo"><a href="/"><img src="./public/images/logo_mini.png"/></a></div>
  <form action="show" name="f" class="res_form">
    <span class="span_search_in s_ipt_wr">
    
    
    <input id='kw'name="content"class="search_in"type="text"value=<%=(String)request.getAttribute("content") %> />
    
    <script type="text/javascript">
    	var inp = document.getElementById('kw');
    	//inp.value = content;
    </script>
    </span><span class="span_search_btn">
    <input class="search_btn" type="submit" value="搜索" id='su'/>
    </span>
  </form>
</div>
<div class="box">
  <div class="main clearfix">
      <div class="baidu_ad" style="margin:24px auto auto 123px;"></div>
      
    <div id="content">
    	<div class="result_stats">
   		 <p>找到约 <%=(Integer)request.getAttribute("cnt") %> 条结果</p>
		</div>
		<div id="search_result">
			
		   
		   <%
		    List docres = (List)request.getAttribute("docres");
			List srcres = (List)request.getAttribute("srcres");
			List prjres = (List)request.getAttribute("prjres");
			List depres = (List)request.getAttribute("depres");
			List manres = (List)request.getAttribute("manres");
			List url = (List)request.getAttribute("url");
			List knowres = (List)request.getAttribute("knowres");
			Change c = new Change();
			String str = (String)request.getAttribute("content");
			String topq = (String)request.getAttribute("topq");
			String topr = (String)request.getAttribute("topr");
			if(knowres != null && knowres.size() >= 1){
				String answer = "";
				for(Object key : knowres){
					answer += key.toString() + "</br>";
				}
				out.println("</br><div class=\"s_r_x\">");
				out.println("<a class=\"title\" href=\"http://localhost/SampleSearch/\" target=\"_blank\">" + (String)request.getAttribute("content") +"</a>");
				
				out.println("<div class=\"min_content\">Answer:" + answer + "</div>");
				out.println("</div>");
			}
			else if(Double.valueOf((String)request.getAttribute("src")) > 0.5){
				
			
				out.println("</br><div class=\"s_r_x\">");
				out.println("<a class=\"title\" href=\"http://localhost/SampleSearch/\" target=\"_blank\">" + topq +"</a>");
				
				out.println("<div class=\"min_content\">Answer:" + topr + "</div>");
				out.println("</div>");
			}
			
			//out.println(request.getAttribute("content"));
			for(int i = 0;i < docres.size();i ++){
				String tem = "mid=" + (String)docres.get(i);
				out.println("</br><div class=\"s_r\">");
				out.println("<a class=\"title\" href=\"http://localhost:8080/SampleSearch/detail?" + tem + "\" target=\"_blank\">" + c.ChangeSt((String)prjres.get(i), str) +"</a>");
				out.println("<div class=\"visible_url\">" + c.ChangeSt((String)url.get(i), str) +"</div>");
				out.println("<div class=\"min_content\">Src:" + srcres.get(i) + "</br>类型:" + c.ChangeSt((String)manres.get(i),str) + "</br>" + c.ChangeSt((String)depres.get(i), str) +"</div>");
				out.println("</div>");
			}
			%>
		
		
    </div>
    
    <div id = "contentx">
    <h3 class="relation_kw" style="text-align:left">

    <%=(String)request.getAttribute("content")%>的相关的景点类型

	</h3>
    <ul class="related_kwy_words">

  	<%
  		for(int i = 0;i < manres.size() && i < 6;i ++){
  			String tem = (String)manres.get(i);
  			out.print("<li><a href=\"http://localhost:8080/SampleSearch/show?content=" + tem + "\">" + tem + "</a></li>");
  		}
  	%>
    
    </ul>
    </br></br></br></br></br>
    <h3 class="relation_kw" style="text-align:left">

    <%=(String)request.getAttribute("content")%>的相关的景点地址

	</h3>
    <ul class="related_kwy_words">

  	
    <%
  		for(int i = 0;i < manres.size() && i < 6;i ++){
  			String tem = (String)depres.get(i);
  			out.print("<li><a href=\"http://localhost:8080/SampleSearch/show?content=" + tem + "\">" + tem + "</a></li>");
  		}
  	%>
    
    </ul>
      </br></br></br></br></br>
    <h3 class="relation_kw" style="text-align:left">

    <%=(String)request.getAttribute("content")%>的相关搜索

</h3>
<ul id="related_kwy_wordsx">

  <%List simq =  (List)request.getAttribute("simq");%>
    <%
  		for(int i = 0;i < simq.size() && i < 10;i ++){
  			String tem = (String)simq.get(i);
  			out.print("<li><a href=\"http://localhost:8080/SampleSearch/show?content=" + tem + "\">" + tem + "</a></li>");
  		}
  	%>
</ul>
    </div>
    </br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
    
  </div>
 </div>
</div>
<script type="text/javascript" src="/public/js/jquery-1.8.3.min.js"></script><script type="text/javascript" src="/public/js/comm.js"></script><script type="text/javascript"src="/public/js/bdsug2.js"></script><script>$(function(){$("#kw").focus();var params={"XOffset":0,"YOffset":0,"fontColor":"#000","fontColorHI":"#000","fontSize":"medium","fontFamily":"Arial","borderColor":"#CCC","bgcolorHI":"#ebebeb","sugSubmit":true};BaiduSuggestion.bind("kw",params);$(".wx").mouseenter(function(){$(this).find("span").css("display","block");});$(".wx").mouseleave(function(){$(this).find("span").css("display","none");});});</script>
</body>
</html>