<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/styleslogin.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container my-5">
        <div class="row justify-content-center"> 
            <div class="col-md-12"> 
                <div class="card">
                    <div class="card-header text-center">
                        <h4 class="mb-0 color-registro">Iniciar Sesión</h4>
                    </div>
                    <div class="card-body">
                        <form class="centered-form" method="post" action="controllers/login_controller.jsp">
                            <div class="mb-3">
                                <label for="email" class="form-label color-title">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Introduce email">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label color-title">Contraseña</label>
                                <input type="password" class="form-control" id="password" placeholder="Introduce contraseña">
                            </div>
                            <div class="text-end">
                                <button type="submit" class="btn button-color">Ingresar</button>
                            </div>
                        </form>
                        <p class="color-title" style="margin-top: 8px;">No tienes cuenta? <a href="registro.jsp" class="link-primary">Regístrate</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
