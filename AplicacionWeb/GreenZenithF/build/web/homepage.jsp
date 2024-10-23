<%-- 
    Document   : homepage
    Created on : Oct 22, 2024, 11:15:53 PM
    Author     : senor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styleshomepage.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="container my-5">
        <div class="card">
            <div class="row g-0">
                <div class="col-md-6 image-container">
                    <img src="res/mountain.png" class="img-fluid responsive-img" alt="Beautiful Plants">
                </div>
                <div class="col-md-6 d-flex align-items-center">
                    <div class="card-body text-center">
                        <a class="nav-link" href="index.html"><h1>Green Zenith</h1></a>
                        <div class="d-flex flex-column mb-4">
                            <div class="p-2">Descubre</div>
                            <div class="p-2">Mantiene</div>
                            <div class="p-2">Mejora</div>
                        </div>
                        <a class="btn btn-primary btn-lg" href="registrarse.html" role="button">Registrarse</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

