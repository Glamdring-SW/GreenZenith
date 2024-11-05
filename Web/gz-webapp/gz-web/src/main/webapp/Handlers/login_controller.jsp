<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.controllers.UserController" %>
<%
    String email = (String) request.getParameter("email");
    String password = (String) request.getParameter("password");

    UserController controller = new UserController();

    boolean created = controller.summonUser(email, password);

    if (created){
        int id = controller.getUser().getId();
        String name = controller.getUser().getName();
        session.setAttribute("id", id);
        session.setAttribute("name", name);
        response.sendRedirect("../test.jsp");
    } else {
        out.println("<script>alert('datos invalidos');</script>");
        response.sendRedirect("../login.jsp");
    }
%>