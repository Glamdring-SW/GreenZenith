<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.controllers.Usercontrollers.UserController" %>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UserController controller = new UserController();

    boolean created = controller.summonUser(email, password);

    if (created==true){
        HttpSession session = request.getSession();
        session.setAttribute("controller", controller);
        response.sendRedirect("../test.jsp");
    } else {
        out.println("<script>alert('datos invalidos');</script>");
        response.sendRedirect("../login.jsp");
    }

%>