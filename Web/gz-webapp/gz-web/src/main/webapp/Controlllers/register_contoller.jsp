<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.controllers.UserController" %>
<%
    String username = (String) request.getParameter("firstName") + " " + request.getParameter("lastName");
    String email = (String) request.getParameter("email");
    String password = (String) request.getParameter("password");
    int age = 18;

    Class.forName("com.glamdring.greenZenith.controllers.UserController");
    UserController controller = new UserController();

    boolean created = controller.createUser(username, email, password, age);

    if (created){
        out.println("<script>alert('Usuario creado con exito');</script>");
        session.setAttribute("email", email);
        response.sendRedirect("../homepage.jsp");
    } else {
        out.println("<script>alert('datos invalidos');</script>");
        response.sendRedirect("../register.jsp");
    }

%>