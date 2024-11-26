<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page import="com.glamdring.greenZenith.userInteractions.plants.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    int id = Integer.parseInt((String) request.getParameter("id"));
    String name = (String) request.getParameter("title");
    try {
        user.deleteProduct(id, name);
        response.sendRedirect("../productsexplore.jsp");
    } catch (InvalidUserException e) {
        request.setAttribute("jspErrorProduct", e.getMessage());
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
    }
%>
