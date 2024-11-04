<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.glamdring.greenZenith.exceptions.database.GZDBResultException"%>
<%@page import="com.glamdring.greenZenith.exceptions.application.user.InvalidUserException"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%
    String username = (String) request.getParameter("userName");
    String email = (String) request.getParameter("email");
    String password = (String) request.getParameter("password");
    int age = Integer.parseInt(request.getParameter("age"));

    try {
        User user = new User(username, email, password, age);
        session.setAttribute("User", user);
    } catch (InvalidUserException e) {

    } catch (GZDBResultException e) {

    }

%>