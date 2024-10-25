<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.*" %>
<%
    
    String username = request.getParameter("firstName");
    String email = request.getParameter("email");
    String password = request.getParameter("password"); 
    int age = 0;

    Class.forName("com.glamdring.greenZenith.controllers.UserController");
    UserController controller = new UserController();

    boolean created = controller.createUser(username, email, password, age);

    if (created==true){
        out.println("<script>alert('Usuario creado con exito');</script>");
        session.setAttribute("email", email);
        response.sendRedirect("../test.jsp");
    } else {
        out.println("<script>alert('datos invalidos');</script>");
        response.sendRedirect("../registro.jsp");
    }

%>