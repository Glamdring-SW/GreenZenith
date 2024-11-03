<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/stylesregister.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer/>
        <script src="JS/FormatValidation/RegisterValidation.js" defer/>
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
                            <form class="centered-form needs-validation" method="post" action="Handlers/register_controller.jsp" novalidate="true">
                                <div class="mb-3">
                                    <label class="form-label color-title" for="userName">Nombre de Usuario</label>
                                    <input class="form-control" type="text" id="userName" name="userName" placeholder="Ingresa tu nombre"
                                           pattern="^.{4,49}$" required="true">
                                    <div class="invalid-feedback">
                                        Ingresa un nombre de usuario valido.
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="email">Correo Electronico</label>
                                    <input class="form-control" type="email" id="email" name="email" placeholder="Ingresa tu correo electronico"
                                           pattern="^(?=.{1,99}$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" required="true">
                                    <div class="invalid-feedback">
                                        Ingresa un correo electronico valido
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="age">Edad</label>
                                    <input class="form-control" type="number" id="age" name="age" placeholder="Ingresa tu edad"
                                           required="true">
                                    <div class="invalid-feedback">
                                        Ingresa una edad valida.
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label color-title" for="password">Contraseña</label>
                                    <input class="form-control" type="password" id="password" name="password" placeholder="Ingresa una contraseña segura"
                                           pattern="^(?=.+[A-Z])(?=.*[a-z])(?=.+\\d)(?=.+[!-\\/\\[-`{-~])[!-~]{12,}$" required="true">
                                    <div class="invalid-feedback">
                                        Ingresa una contraseña valida.
                                    </div>
                                </div>
                                <div class="text-end">
                                    <button type="submit" class="btn button-color" disabled="true">Registrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
