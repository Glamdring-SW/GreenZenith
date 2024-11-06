<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesprofile.css">
    <style>
        body {
            color: white; /* White text */
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container my-5 d-flex justify-content-center">
    <div class="profile-container text-center">
        <div class="error-message">
            <div class="error-code">ERROR    :(</div>
            <p>No se encontró un perfil. Registrate.</p>
         <a href="registro.jsp" class="btn btn-outline-light btn-outline-light-custom">REGISTRAR</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
