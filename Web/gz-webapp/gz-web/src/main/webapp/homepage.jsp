<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/styleshomepage.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body class="body-2">
        <div class="navbar">
            <jsp:include page="navbar.jsp" />
        </div>
        <div class="container my-4">
            <div class="card custom-navbar">
                <div class="row g-0">
                    <div class="col-md-6 image-container">
                        <img src="img/Cropped_Image.png" class="img-fluid responsive-img" alt="Homepage">
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <div class="card-body body-3">
                            <a class="nav-link" href="index.jsp"><h1><b class="body-4 color-title">Green Zenith</b></h1></a>
                            <div class="d-flex flex-column mb-4">
                                <div class="p-2 body-3">Descubre</div>
                                <div class="p-2 body-3" >Mantiene</div>
                                <div class="p-2 body-3">Mejora</div>
                            </div>
                            <%
                                if (user == null) {
                            %>
                            <a class="btn btn-primary btn-lg button-color" href="registro.jsp" role="button"><b>R E G I S T R A R S E</b></a>
                            <%
                                } else {
                            %>
                            <a class="btn btn-primary btn-lg button-color" href="plantsexplore.jsp" role="button"><b>MIS PLANTAS</b></a>
                             <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="scrollArrow" class="scroll-arrow">
           <img src="img/arrowpoint.png" class="pointer">
        </div>
    </body>
    <script>
        window.onscroll = function() {
        var distanciaScroll = window.scrollY;
        var navbar = document.querySelector(".custom-navbar");
        var body = document.body;
        
        if (distanciaScroll > 350) {
            body.style.backgroundColor = "white";
            navbar.classList.add("custom-navbar-scrolled");
        } else {
            body.style.backgroundColor = ""; 
            navbar.classList.remove("custom-navbar-scrolled");
        }if (distanciaScroll > 350) { // Cambia el valor a lo que necesites
            scrollArrow.style.display = 'none'; // Oculta la flecha después de hacer scroll
        } else {
            scrollArrow.style.display = 'block'; // Muestra la flecha si no se ha hecho scroll
        }
    };
    </script>

</html>
