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
</style>
</head>
<body>
<div class="onegoogle noprint">
  <div id="gbar"><nobr>
  <a href="http://www.glgoo.com"id="gb_1"class="gb1 gbz0l">搜索</a>
  <a id="gb_2"class="gb1"href="http://192.168.10.15:8080/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=0">课题</a>
  <a href="http://192.168.10.15:8080/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=1"id="gb_8"class="gb1">机构</a>
  <a href="http://192.168.10.15:8080/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=2"id="gb_5"class="gb1">人物</a>
  <a href="http://192.168.10.15:8080/SampleSearch/show?content=<%=(String)request.getAttribute("content")%>&mid=3"id="gb_49"class="gb1">care知道</a>
  
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
   		 <p>找到约 10条结果</p>
		</div>
		<div id="search_result">
			
		   
		   <%
		    List<String> que = (List<String>)request.getAttribute("simq");
		    List<String> ans = (List<String>)request.getAttribute("simr");
			String str = (String)request.getAttribute("content");
			//out.println(request.getAttribute("content"));
			for(int i = 0;i < que.size();i ++){
				String tem = "content=" + (String)que.get(i);
				out.println("</br><div class=\"s_r\">");
				out.println("<a class=\"title\" href=\"http://localhost:8080/SampleSearch/show?" + tem + "&mid=3\" target=\"_blank\">" +(String)que.get(i)+"</a>");
				
				out.println("<div class=\"min_content\">Answer:" + ans.get(i) + "</div>");
				out.println("</div>");
			}
			%>
		
		
    </div>

    
  </div>
 </div>
</div>
<script type="text/javascript" src="/public/js/jquery-1.8.3.min.js"></script><script type="text/javascript" src="/public/js/comm.js"></script><script type="text/javascript"src="/public/js/bdsug2.js"></script><script>$(function(){$("#kw").focus();var params={"XOffset":0,"YOffset":0,"fontColor":"#000","fontColorHI":"#000","fontSize":"medium","fontFamily":"Arial","borderColor":"#CCC","bgcolorHI":"#ebebeb","sugSubmit":true};BaiduSuggestion.bind("kw",params);$(".wx").mouseenter(function(){$(this).find("span").css("display","block");});$(".wx").mouseleave(function(){$(this).find("span").css("display","none");});});</script>
</body>
</html>