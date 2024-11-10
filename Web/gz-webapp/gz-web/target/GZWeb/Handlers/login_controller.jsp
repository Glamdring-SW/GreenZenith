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
        session.setAttribute("jspErrorLogin", "Se ocasiono un error, intentalo de nuevo, recuerda ingresar tu correo y contraseña correctos."
                + "\n" + e.getMessage() + "\n" + email + "\n" + password);
        session.setAttribute("jspErrorRegister", null);
        session.setAttribute("jspErrorUserUpdate", null);
        session.setAttribute("jspErrorPlantCreate", null);
        response.sendRedirect("../error_module.jsp");
    } catch (GZDBResultException e) {
        session.setAttribute("jspErrorLogin", "Estamos teniendo problemas en nuestros servidores, intentalo de nuevo mas tarde.");
        session.setAttribute("jspErrorRegister", null);
        session.setAttribute("jspErrorUserUpdate", null);
        session.setAttribute("jspErrorPlantCreate", null);
        response.sendRedirect("../error_module.jsp");
    }

%>