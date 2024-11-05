<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/styleshomepage.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body class="body-2">
        <div class="navbar">
            <jsp:include page="Elements/navbar.jsp" />
        </div>
        <div class="container my-5">
            <div class="card custom-navbar">
                <div class="row g-0">
                    <div class="col-md-6 image-container">
                        <img src="CSS/IMGs/Cropped_Image.png" class="img-fluid responsive-img" alt="Beautiful Plants">
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <div class="card-body body-3">
                            <a class="nav-link" href="../index.jsp"><h1><b class="body-4 color-title">Green Zenith</b></h1></a>
                            <div class="d-flex flex-column mb-4">
                                <div class="p-2 body-3">Descubre</div>
                                <div class="p-2 body-3">Mantiene</div>
                                <div class="p-2 body-3">Mejora</div>
                            </div>
                            <a class="btn btn-primary btn-lg button-color" href="register.jsp" role="button"><b>R E G I S T R A R S E</b></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
