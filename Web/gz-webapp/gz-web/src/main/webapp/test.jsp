<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultados</title>
    </head>
    <body>
        <%

            UserController controller = session.getAttribute("controller");

        %>
        <h1>Datos</h1>

        <span>ID:</span>
        <span>nombre:</span>
        <span>email:</span>
        <span>edad:</span>

        <span><%= controller.getUserId() %></span>

        <a href="">
            <button>Menu</button>
        </a>

        <a href="">
            <button>no</button>
        </a>

        <a href="">
            <button>si</button>
        </a>

    </body>
</html>