<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesregister.css">
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container my-5">
        <div class="row justify-content-center"> <!-- Centering the row -->
            <div class="col-md-12"> <!-- Increased column width -->
                <div class="card">
                    <div class="card-header text-center">
                        <h4 class="mb-0 color-registro">Registro</h4>
                    </div>
                    <div class="card-body">
                        <form class="centered-form">
                            <div class="mb-3">
                                <label for="firstName" class="form-label color-title">Nombre</label>
                                <input type="text" class="form-control" id="firstName" placeholder="Introduce nombre">
                            </div>
                            <div class="mb-3">
                                <label for="lastName" class="form-label color-title">Apellido</label>
                                <input type="text" class="form-control" id="lastName" placeholder="Introduce apellido">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label color-title">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Introduce email">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label color-title">Contraseña</label>
                                <input type="password" class="form-control" id="password" placeholder="Introduce contraseña">
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
