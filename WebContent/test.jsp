<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page 
import="java.util.List"
import="com.search.Create_index"
import="com.search.Unit"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  type="text/css"  href="CSS/style.css"/>
<title>SampleSearch</title>
</head>
<body>
<%
List docres = (List)request.getAttribute("docres");
List srcres = (List)request.getAttribute("srcres");
List prjres = (List)request.getAttribute("prjres");
List depres = (List)request.getAttribute("depres");
List manres = (List)request.getAttribute("manres");
for(int i = 0;i < docres.size();i ++){
	out.println("DocId:" + docres.get(i));
	out.println("src:" + srcres.get(i));
	out.println("prj:" + prjres.get(i));
	out.println("dep:" + depres.get(i));
	out.println("man:" + manres.get(i));
}
%>
</body>
</html>