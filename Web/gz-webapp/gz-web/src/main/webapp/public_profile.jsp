<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendedor Destacado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesselpro.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <div class="card">

        <img src="img/profile_ph.png" alt="Profile Picture">

        <div class="card-content">
            <h2>Elena Guzmán</h2>
            <p>FAN DE LAS CACTACEAS</p>

            <div class="btn-container">
                <button class="btn-custom">PLANTAS EN VENTA</button>
                <button class="btn-custom">INICIAR CHAT</button>
            </div>
            
            <!--Removable, only needed if we want to manage social media links on the database-->
            <div class="social-icons mt-3">
                <a href="#" class="me-3"><i class="fab fa-instagram"></i></a>
                <a href="#" class="me-3"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
