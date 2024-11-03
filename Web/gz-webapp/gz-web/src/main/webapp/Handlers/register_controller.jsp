<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String) request.getParameter("userName");
    String email = (String) request.getParameter("email");
    int age = request.getParameter("age");
    String password = (String) request.getParameter("password");

%>