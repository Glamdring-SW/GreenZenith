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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
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
                                <div class="p-2 body-3">Mantiene</div>
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

        <!-- Nueva Sección Animada -->
        <div class="container custom-spacing animated-section">
            <div class="row align-items-center animated-row">
                <div class="col-md-6">
                    <img src="img/planta_an.png" class="img-fluid rounded animated-img" alt="Dynamic Image">
                </div>
                <div class="col-md-6">
                    <div class="body-3">
                        <h2 class="color-title">¿Qué hace Green Zenith?</h2>
                        <p class="color">
                            Green Zenith es una plataforma diseñada para todos los amantes de las plantas, que facilita la compra, venta y cuidado de todo tipo de cultivos, con el objetivo de contribuir a un mundo más verde.
                        </p>
                        <a href="explore.jsp" class="btn btn-primary btn-lg button-color">Explorar Más</a>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Interactive Featured Sections -->
        <section class="feeatured section">
            <div class="section-tabs">
                <button class="tab-button active" data-tab="products">Productos Destacados</button>
                <button class="tab-button" data-tab="vendors">Vendedores Destacados</button>
            </div>
            
            <div class="tab-content">
                <!-- Products Tab -->
                <div class="tab-pane active" id="products">
                    <div class="productos-container">
                        <% for(int i = 0; i < 11; i++) { %>
                            <jsp:include page="highlighted_product.jsp" />
                        <% } %>
                    </div>
                </div>
                
                <!-- Vendors Tab -->
                <div class="tab-pane" id="vendors">
                    <div class="productos-container">
                        <% for(int i = 0; i < 11; i++) { %>
                            <jsp:include page="highlighted_profile.jsp" />
                        <% } %>
                    </div>
                </div>
            </div>
        </section>
        <%
            if (user != null) {
        %>
        <jsp:include page="message.jsp" />
        <%
            }
        %>
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
            }
            if (distanciaScroll > 350) { 
                scrollArrow.style.display = 'none';
            } else {
                scrollArrow.style.display = 'block';
            }
        };
        
        document.addEventListener("DOMContentLoaded", function () {
            const animatedRow = document.querySelector(".animated-row");

            window.addEventListener("scroll", function () {
                const rect = animatedRow.getBoundingClientRect();
                const inViewport = rect.top < window.innerHeight && rect.bottom >= 0;

                if (inViewport) {
                    animatedRow.classList.add("show");
                }
            });
            
            const tabButtons = document.querySelectorAll('.tab-button');
            const tabPanes = document.querySelectorAll('.tab-pane');

            tabButtons.forEach(button => {
                button.addEventListener('click', () => {
                    // Remove active class from all buttons and panes
                    tabButtons.forEach(btn => btn.classList.remove('active'));
                    tabPanes.forEach(pane => pane.classList.remove('active'));

                    // Add active class to clicked button and corresponding pane
                    button.classList.add('active');
                    const tabId = button.getAttribute('data-tab');
                    document.getElementById(tabId).classList.add('active');
                });
            });
            
        });
        // Smooth scroll on arrow click
        document.addEventListener("DOMContentLoaded", function () {
            const scrollArrow = document.querySelector("#scrollArrow");

            scrollArrow.addEventListener("click", function () {
                window.scrollBy({
                    top: 650, // Scroll down by 350 pixels
                    left: 0,
                    behavior: "smooth" // Smooth scrolling effect
                });
            });
        });
        
        
    </script>
</html>
