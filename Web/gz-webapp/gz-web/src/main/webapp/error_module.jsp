<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../src/stylesprofile.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
        <style>
            @media (max-width: 991.98px) {
                .navbar-nav {
                    gap: 1rem !important;
                }

                .navbar-nav .nav-item form {
                    width: 100%;
                }

                .navbar-nav .nav-item form .input-group {
                    width: 100%;
                }

                .navbar-nav .nav-item form .form-control {
                    width: 100% !important;
                }
            }

            .navbar .container-fluid {
                padding: 0.5rem 1rem;
            }

            .gap-3 {
                gap: 1rem !important;
            }

            .search-btn.btn-outline-dark {
                color: black;
                border-color: black;
                transition: all 0.3s ease;
            }

            .search-btn.btn-outline-dark:hover {
                background-color: #f8f9fa;
                color: black;
                border-color: black;
            }

            .search-btn.btn-outline-dark:focus {
                box-shadow: 0 0 0 0.2rem rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("User");
        %>
        <nav class="navbar navbar-expand-lg custom-navbar">
            <div class="container-fluid">
                <a class="sizing-brand nav-link" href="../homepage.jsp">Green Zenith</a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">

                    <ul class="navbar-nav">
                        <% if (user == null) { %>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="../login.jsp">LogIn</a>
                        </li>
                        <% } %>

                        <% if (user != null) { %>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="../plantsexplore.jsp">Mis Plantas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="../productsexplore.jsp">Mis Productos</a>
                        </li>
                        <% } %>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="../marketplace.jsp">Marketplace</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav ms-auto align-items-center gap-3">
                        <form class="d-flex" role="search">
                            <li class="nav-item">
                                <div class="input-group">
                                    <input class="form-control" type="search" placeholder="Buscar Plantas" 
                                           aria-label="Search" style="width: 200px;">
                                    <button class="search-btn btn btn-outline-dark" type="submit">Buscar</button>
                                </div>
                            </li>
                        </form>
                        <li class="nav-item">
                            <a class="nav-link" href="../cart.jsp">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" 
                                     class="bi bi-cart-fill" viewBox="0 0 16 16">
                                <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                                </svg>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="../profile.jsp">
                                <% if (user == null) { %>
                                <img src="img/default_profilePicture.png" 
                                     class="img-fluid profile-photo-preview rounded-circle" 
                                     style="width: 40px; height: 40px;" alt="DefaultUser">
                                <% } else {%>
                                <img src="data:image/png;base64, <%= user.getBase64Picture()%>" 
                                     class="img-fluid profile-photo-preview rounded-circle" 
                                     style="width: 40px; height: 40px;" alt="User">
                                <% }%>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container my-5 d-flex justify-content-center">
            <div class="text-center">
                <div class="alert alert-success">
                    <%
                        if (request.getAttribute("jspErrorRegister") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorRegister")%></p>
                    <a href="../registro.jsp" class="btn btn-success">REGISTRAR</a>
                    <%
                    } else if (request.getAttribute("jspErrorLogin") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorLogin")%></p>
                    <a href="../login.jsp" class="btn btn-success">INICIAR SESION</a>
                    <%
                    } else if (request.getAttribute("jspErrorPlantCreate") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorPlantCreate")%></p>
                    <a href="../plantsexplore.jsp" class="btn btn-success">PLANTAS</a>
                    <%
                    } else if (request.getAttribute("jspErrorUserUpdate") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorUserUpdate")%></p>
                    <a href="../profile.jsp" class="btn btn-success">PERFIL</a>
                    <%
                    } else if (request.getAttribute("jspErrorProduct") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorProduct")%></p>
                    <a href="../productsexplore.jsp" class="btn btn-success">PRODUCTOS</a>
                    <%
                    } else if (request.getAttribute("jspErrorProduct") != null) {
                    %>
                    <p class="alert-success"><%= request.getAttribute("jspErrorCart")%></p>
                    <a href="../cart.jsp" class="btn btn-success">CARRITO</a>
                    <%}%>            
                </div>
            </div>
        </div>
    </body>
</html>
