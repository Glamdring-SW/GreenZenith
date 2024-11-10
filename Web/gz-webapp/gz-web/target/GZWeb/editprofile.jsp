<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesprofile.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
        <style>

            .profile-container {
                width: 600px;
                padding: 30px;
                border-radius: 10px;
            }
            .profile-photo-preview {
                width: 150px;
                height: 150px;
                object-fit: cover;
                border-radius: 50%;
            }
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
                height: 100%;
                object-fit: cover;
            }

            .profile-pic span {
                font-size: 2rem;
                color: #999;
                position: absolute;
                pointer-events: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5 d-flex justify-content-center">
            <div class="profile-container text-center">
                <form action="Handlers/update_profile_controller.jsp" method="post" enctype="multipart/form-data" class="mt-4 centered-form" onsubmit="return validateForm()" novalidate>
                    <div class="text-center">
                        <label for="profilePicture" class="text-white"> Foto de Perfil
                            <div class="profile-pic">
                                <input type="file" id="profilePicture" name="profilePicture" accept=".png, .jpg, .jpeg" onchange="previewImage(event)">
                                <img id="preview" src="data:image/png;base64, <%= user.getBase64Picture()%>" lass="profile-photo-preview" alt="Profile Picture">                                 
                            </div>
                        </label>
                    </div>
                    <div class="mb-3">
                        <label for="userName" class="form-label color-title text-white">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="Introduce el nombre de usuario nuevo"
                               pattern="^.{4,49}$">
                        <div id="userNameError" class="error"></div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label color-title text-white">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Introduce el correo electronico nuevo"
                               pattern="^(?=.{1,99}$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,100}$">
                        <div id="emailError" class="error"></div>
                    </div>
                    <div class="mb-3">
                        <label for="oldPassword" class="form-label color-title text-white">Contraseña Actual</label>
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Introduce la contraseña actual"
                               pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.+\d)(?=.+[!-\/\[-`{-~])[!-~]{12,100}$" required="true">
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label color-title text-white">Contraseña Nueva</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Introduce una contraseña nueva"
                               pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.+\d)(?=.+[!-\/\[-`{-~])[!-~]{12,100}$">
                        <div id="passwordError" class="error"></div>
                    </div>
                    <div class="mb-3">
                        <label for="userAge" class="form-label color-title text-white">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="userAge" name="userAge" max="2006-11-01">
                        <div id="ageError" class="error"></div>
                    </div>
                    <label for="age" hidden="true">Edad</label>
                    <input type="number" id="age" name="age" required="true" hidden="true">
                    <button type="submit" class="btn btn-outline-light btn-outline-light-custom">Guardar Cambios</button>
                </form>
            </div>
        </div>
        <script>
            function previewImage(event) {
                const file = event.target.files[0];
                const preview = document.getElementById('preview');
                const profilePic = document.querySelector('.profile-pic');
                if (file) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function () {
                        preview.style.display = 'block';
                        document.getElementById('preview').setAttribute('src', reader.result);
                    };
                } else {
                    preview.style.display = 'none';
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
                    document.getElementById('userNameError').innerText = 'El nombre de ususario es obligatorio.';
                    valid = false;
                }

                const email = document.getElementById('email').value;
                if (!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
                    document.getElementById('emailError').innerText = 'El correo electrónico debe ser válido.';
                    valid = false;
                }

                const newPassword = document.getElementById('newPassword').value;
                if (!newPassword.match(/^(?=.*[A-Z])(?=.*[a-z])(?=.+\d)(?=.+[!-\/\[-`{-~])[!-~]{12,100}$/)) {
                    document.getElementById('passwordError').innerText = 'La contraseña no es segura.';
                    valid = false;
                }

                const ageDate = new Date(document.getElementById('userAge').value);
                const age = new Date().getFullYear() - ageDate.getFullYear();
                if (document.getElementById('userAge').value === '') {
                    document.getElementById('ageError').innerText = 'Debes llenar este campo';
                    valid = false;
                } else if (age < 18) {
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
        </script>          
    </body>
</html>
