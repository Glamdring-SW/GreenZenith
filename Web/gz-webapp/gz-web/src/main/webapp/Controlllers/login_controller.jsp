<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.*" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    Class.forName("com.glamdring.greenZenith.controllers.UserController");
    UserController controller = new UserController();

    boolean created = controller.summonUser(email, password);

    if (created==true){
        session.setAttribute("email", email);
        response.sendRedirect("../test.jsp");
    } else {
        out.println("<script>alert('datos invalidos');</script>");
        response.sendRedirect("../login.jsp");
    }

%>