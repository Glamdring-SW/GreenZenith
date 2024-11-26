<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  try {
        User user = (User) session.getAttribute("User");
        int id = Integer.parseInt((String) request.getParameter("id"));
        int quantity = Integer.parseInt((String) request.getParameter("quantity"));
        user.addToCart(id, quantity);
        response.sendRedirect("../cart.jsp");
    } catch (InvalidUserException e) {
        request.setAttribute("jspErrorCart", e.getMessage());
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
    }
%>