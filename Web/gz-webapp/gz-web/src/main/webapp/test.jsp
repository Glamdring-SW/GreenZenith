<!DOCTYPE html>
<html>
    <head>
        <title>Resultados</title>
    </head>
    <body>
        <%

            String email = session.getAttribute("email");

        %>
        <h1>Datos</h1>

        <span>ID:</span>
        <span>nombre:</span>
        <span>email:</span>
        <span>edad:</span>

        <span><%= rs.getInt("id_usuario") %></span>
        <span><%= rs.getString("usuario_usuario") %></span>
        <span><%= rs.getString("nombre_usuario") %></span>
        <span><%= rs.getString("aMaterno_usuario") %></span>
        <span><%= rs.getString("aPaterno_usuario") %></span>
        <span><%= rs.getString("email_usuario") %></span>
        <span><%= rs.getString("contrasena_usuario") %></span>
        <span><%= rs.getString("labios") %></span>
        <span><%= rs.getString("piel") %></span>
        <span><%= rs.getString("rostro") %></span>
        <span><%= rs.getString("ojos") %></span>

        <a href="../inicio/paginaInicio.html">
            <button>Menu</button>
        </a>

        <a href="alterarCuenta.html">
            <button>Alterar usuario</button>
        </a>

        <a href="eliminarCuenta.html">
            <button>Borrar usuario</button>
        </a>

    </body>
</html>