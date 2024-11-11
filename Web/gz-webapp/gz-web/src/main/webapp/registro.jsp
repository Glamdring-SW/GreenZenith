<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesregister.css">
        <style>
            .error {
                color: red;
                font-size: 0.8rem;
            }
            .profile-pic {
                position: relative;
                overflow: hidden;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                background-color: #f8f9fa;
            }

            .profile-pic input[type="file"] {
                display: none;
            }

            .profile-pic img {
                position: absolute;
                width: 100%;
                transform: translate(0%, 50%);
                height: 100%;
                object-fit: cover;
                display: none;
            }

            .profile-pic span {
                font-size: 2rem;
                color: #999;
                position: absolute;
                pointer-events: none;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" defer></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-8 col-md-10 col-sm-12">
                    <div class="card">
                        <div class="card-header text-center">
                            <h4 class="mb-0 color-registro">Registro</h4>
                        </div>
                        <div class="card-body">
                            <form class="centered-form" action="Handlers/register_controller.jsp" method="post" enctype="multipart/form-data" onsubmit="return validateForm()" novalidate>
                                <div class="d-flex flex-column flex-md-row align-items-center mb-3">
                                    <label for="profilePicture">
                                        <div class="profile-pic">
                                            <input type="file" id="profilePicture" name="profilePicture" accept=".png, .jpg, .jpeg" onchange="previewImage(event)">
                                            <span>+</span>
                                            <img id="preview" src="" alt="Profile Picture">                                 
                                        </div>
                                    </label>
                                    <div class="flex-fill ms-md-3"> 
                                        <div class="mb-3">
                                            <label for="userName" class="form-label color-title">Nombre de Usuario</label>
                                            <input type="text" class="form-control" id="userName" name="userName" placeholder="Introduce nombre"
                                                   pattern="^.{4,49}$" required="true">
                                            <div id="userNameError" class="error  small"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="email" class="form-label color-title">Email</label>
                                            <input type="email" class="form-control" id="email" name="email" placeholder="Introduce email"
                                                   pattern="^[a-zA-Z0-9!#$%&’*+\-\/=?^_`{|}~\.]+@{1}(?:gmail|yahoo|hotmail|outlook|msn|aol|live|mail|ymail|comcast|icloud|protonmail|wanadoo|zoho)(?:\.[a-z]{2,3})?(?:\.[a-z]{2}|)$" required="true">
                                            <div id="emailError" class="error  small"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="password" class="form-label color-title">Contraseña</label>
                                            <input type="password" class="form-control" id="password" name="password" placeholder="Introduce contraseña"
                                                   pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.+\d)(?=.+[\\u0021-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E\\u00A1-\\u00BF\\u2000-\\u206F\\u2190-\\u21FF\\u2600-\\u26FF\\u2700-\\u27BF])[^\r\n]{12,100}$" required="true">
                                            <div id="passwordError" class="error  small"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="userAge" class="form-label color-title">Fecha de nacimiento</label>
                                            <input type="date" class="form-control" id="userAge" name="userAge" required="true">
                                            <div id="ageError" class="error  small"></div>
                                        </div>
                                        <label for="age" hidden="true">Edad</label>
                                        <input type="number" id="age" name="age" required="true" hidden="true">
                                    </div>
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

        <script>
            function previewImage(event) {
                const file = event.target.files[0];
                const preview = document.getElementById('preview');
                const profilePic = document.querySelector('.profile-pic');
                const span = profilePic.querySelector('span');

                if (file) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function () {
                        preview.style.display = 'block';
                        span.style.display = 'none';
                        document.getElementById('preview').setAttribute('src', reader.result);
                    };
                } else {
                    preview.style.display = 'none';
                    span.style.display = 'block';
                }
            }

            function validateForm() {
                document.getElementById('userNameError').innerText = '';
                document.getElementById('emailError').innerText = '';
                document.getElementById('passwordError').innerText = '';
                document.getElementById('ageError').innerText = '';

                let valid = true;

                const userName = document.getElementById('userName').value;
                if (!userName.match(/^.{4,49}$/)) {
                    document.getElementById('userNameError').innerText = 'El nombre de no puede ser utilizado.';
                    valid = false;
                }

                const email = document.getElementById('email').value;
                if (!email.match(/^[a-zA-Z0-9!#$%&’*+\-\/=?^_`{|}~\.]+@{1}(?:gmail|yahoo|hotmail|outlook|msn|aol|live|mail|ymail|comcast|icloud|protonmail|wanadoo|zoho)(?:\.[a-z]{2,3})?(?:\.[a-z]{2}|)$/)) {
                    document.getElementById('emailError').innerText = 'El correo electrónico debe ser verdadero.';
                    valid = false;
                }

                const password = document.getElementById('password').value;
                if (!password.match(/^(?=.*[A-Z])(?=.*[a-z])(?=.+\d)(?=.+[\\u0021-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E\\u00A1-\\u00BF\\u2000-\\u206F\\u2190-\\u21FF\\u2600-\\u26FF\\u2700-\\u27BF])[^\r\n]{12,100}$/)) {
                    document.getElementById('passwordError').innerText = 'La contraseña debe tener mayusculas, numeros y caracteres especiales.';
                    valid = false;
                }

                const ageDate = new Date(document.getElementById('userAge').value);
                const age = new Date().getFullYear() - ageDate.getFullYear();
                if (document.getElementById('userAge').value === '') {
                    document.getElementById('ageError').innerText = 'Debes llenar este campo';
                    valid = false;
                } else if (age < 12) {
                    document.getElementById('ageError').innerText = 'Debes ser mayor de edad';
                    valid = false;
                } else if (age > 120) {
                    document.getElementById('ageError').innerText = 'No puedes tener mas de 120 años';
                    valid = false;
                } else {
                    document.getElementById('age').value = age;
                }
                return valid;
            }

            function setDateLimits() {
                const inputDate = document.getElementById("userAge");
                const today = new Date();
                const minDate = new Date(today.getFullYear() - 12, today.getMonth(), today.getDate());
                const maxDate = new Date(today.getFullYear() - 120, today.getMonth(), today.getDate());
                inputDate.min = maxDate.toISOString().split("T")[0];
                inputDate.max = minDate.toISOString().split("T")[0];
            }
            window.onload = setDateLimits;
        </script>
    </body>
</html>
