<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesprofile.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5 d-flex justify-content-center">
            <div class="text-center">
                <div class="alert alert-success">
                    <%
                        if (session.getAttribute("jspErrorRegister") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorRegister")%></p>
                    <a href="registro.jsp" class="btn btn-success">REGISTRAR</a>
                    <%
                    } else if (session.getAttribute("jspErrorLogin") != null) {
                    %>
                    <p class="alert-success"><%= session.getAttribute("jspErrorLogin")%></p>
                    <a href="login.jsp" class="btn btn-success">INICIAR SESION</a>
                    <%
                    } else if (session.getAttribute("jspErrorPlantCreate") != null) {
                    %>
                    <p class="alert-success"><%= session.getAttribute("jspErrorPlantCreate")%></p>
                    <a href="createplant.jsp" class="btn btn-success">AÑADIR PLANTA</a>
                    <%
                    } else if (session.getAttribute("jspErrorUserUpdate") != null) {
                    %>
                    <p class="alert-success"><%= session.getAttribute("jspErrorUserUpdate")%></p>
                    <a href="profile.jsp" class="btn btn-success">PERFIL</a>
                    <%}%>         
                </div>
            </div>
        </div>
    </body>
</html>
