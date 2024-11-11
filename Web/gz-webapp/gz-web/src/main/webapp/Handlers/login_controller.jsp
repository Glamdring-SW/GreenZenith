<%@page import="com.glamdring.greenZenith.exceptions.database.GZDBResultException"%>
<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String email = (String) request.getParameter("email");
    String password = (String) request.getParameter("password");

    try {
        User user = new User(email, password);
        session.setAttribute("User", user);
        response.sendRedirect("../profile.jsp");
    } catch (InvalidUserException e) {
        request.setAttribute("jspErrorLogin", e.getMessage());
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
        response.sendRedirect("../error_module.jsp");
    } catch (GZDBResultException e) {
        request.setAttribute("jspErrorLogin", e.getMessage());
        RequestDispatcher dispatch = request.getRequestDispatcher("../error_module.jsp");
        dispatch.forward(request, response);
    }

%>