<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/styleslogin.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body>
        <jsp:include page="Elements/navbar.jsp"/>
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header text-center">
                            <h4 class="mb-0 color-registro">Iniciar Sesión</h4>
                        </div>
                        <div class="card-body">
                            <form class="centered-form" method="post" action="controlllers/login_controller.jsp">
                                <div class="mb-3">
                                    <label class="form-label color-title" for="email">Email</label>
                                    <input class="form-control" type="email" id="email" name="email" placeholder="Introduce email">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="password">
                                    Contraseña</label>
                                    <input class="form-control" type="password" id="password" name="password" placeholder="Introduce contraseña">
                                </div>
                                <div class="text-end">
                                    <button class="btn button-color" type="submit">Ingresar</button>
                                </div>
                            </form>
                            <p class="color-title" style="margin-top: 8px;">No tienes cuenta? <a href="register.jsp" class="link-primary">Regístrate</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
