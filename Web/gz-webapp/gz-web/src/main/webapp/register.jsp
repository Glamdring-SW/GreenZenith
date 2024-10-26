<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/stylesregister.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body>
        <jsp:include page="Elements/navbar.jsp"/>
        <div class="container my-5">
            <div class="row justify-content-center"> <!-- Centering the row -->
                <div class="col-md-12"> <!-- Increased column width -->
                    <div class="card">
                        <div class="card-header text-center">
                            <h4 class="mb-0 color-registro">Registro</h4>
                        </div>
                        <div class="card-body">
                            <form class="centered-form" method="post" action="Controlllers/register_contoller.jsp">
                                <div class="mb-3">
                                    <label class="form-label color-title" for="firstName">Nombre</label>
                                    <input class="form-control" type="text" id="firstName" name="firstName" placeholder="Ingresa tu nombre">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="lastName">Apellido</label>
                                    <input class="form-control" type="text" id="lastName" name="lastName" placeholder="Ingresa tus apellidos">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="email">Email</label>
                                    <input class="form-control" type="email" id="email" name="email" placeholder="Ingresa tu correo electronico">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="password">Contraseña</label>
                                    <input class="form-control" type="password" id="password" name="password" placeholder="Ingresa una contraseña segura">
                                </div>
                                <div class="text-end">
                                    <button type="submit" class="btn button-color">Registrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
