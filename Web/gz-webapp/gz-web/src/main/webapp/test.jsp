<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.glamdring.greenZenith.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuario validado</title>
    </head>
    <body>
        <%

            int id = (int) session.getAttribute("id");
            String name = (String) session.getAttribute("name");

        %>
        <h1>Datos del usuario</h1>

        <span>ID:</span>
        <span>nombre:</span>

        <span><%= out.print(id) %></span>
        <span><%= out.print(name) %></span>

        <br>

        <a href="">
            <button>Regresar</button>
        </a>


    </body>
</html>