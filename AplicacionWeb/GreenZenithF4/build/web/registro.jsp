<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesregister.css">
    <style>
        .error {
            color: red; /* Error message color */
            font-size: 0.9rem; /* Smaller font size for error messages */
        }
    </style>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12"> <!-- Responsive column sizes -->
                <div class="card">
                    <div class="card-header text-center">
                        <h4 class="mb-0 color-registro">Registro</h4>
                    </div>
                    <div class="card-body">
                        <form class="centered-form" enctype="multipart/form-data" onsubmit="return validateForm()">
                            <div class="d-flex flex-column flex-md-row align-items-center mb-3"> <!-- Flex column for mobile, row for larger screens -->
                                <label class="profile-pic" for="profilePhoto">
                                    <input type="file" id="profilePhoto" accept="image/*" onchange="previewImage(event)">
                                    <img id="preview" src="" alt="Profile Photo" style="display:none;">
                                    <span>+</span> <!-- Placeholder for click to upload -->
                                </label>
                                <div class="flex-fill ms-md-3"> <!-- Flex-fill for dynamic width -->
                                    <div class="mb-3">
                                        <label for="firstName" class="form-label color-title">Nombre</label>
                                        <input type="text" class="form-control" id="firstName" placeholder="Introduce nombre">
                                        <div id="firstNameError" class="error"></div> <!-- Error message -->
                                    </div>
                                    <div class="mb-3">
                                        <label for="lastName" class="form-label color-title">Apellido</label>
                                        <input type="text" class="form-control" id="lastName" placeholder="Introduce apellido">
                                        <div id="lastNameError" class="error"></div> <!-- Error message -->
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label color-title">Email</label>
                                        <input type="email" class="form-control" id="email" placeholder="Introduce email">
                                        <div id="emailError" class="error"></div> <!-- Error message -->
                                    </div>
                                    <div class="mb-3">
                                        <label for="password" class="form-label color-title">Contraseña</label>
                                        <input type="password" class="form-control" id="password" placeholder="Introduce contraseña">
                                        <div id="passwordError" class="error"></div> <!-- Error message -->
                                    </div>
                                    <div class="mb-3">
                                        <label for="age" class="form-label color-title">Edad</label>
                                        <input type="date" class="form-control" id="age" placeholder="Introduce tu fecha de nacimiento">
                                        <div id="ageError" class="error"></div> <!-- Error message -->
                                    </div>
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
                const reader = new FileReader();
                reader.onload = function(e) {
                    preview.src = e.target.result;
                    preview.style.display = 'block'; // Show the image
                    span.style.display = 'none'; // Hide the placeholder
                }
                reader.readAsDataURL(file);
            } else {
                preview.style.display = 'none'; // Hide the image if no file
                span.style.display = 'block'; // Show the placeholder
            }
        }

        function validateForm() {
            // Clear previous error messages
            document.getElementById('firstNameError').innerText = '';
            document.getElementById('lastNameError').innerText = '';
            document.getElementById('emailError').innerText = '';
            document.getElementById('passwordError').innerText = '';
            document.getElementById('ageError').innerText = '';

            let valid = true;

            // Validate first name
            const firstName = document.getElementById('firstName').value;
            if (firstName.trim() === '') {
                document.getElementById('firstNameError').innerText = 'El nombre es obligatorio.';
                valid = false;
            }

            // Validate last name
            const lastName = document.getElementById('lastName').value;
            if (lastName.trim() === '') {
                document.getElementById('lastNameError').innerText = 'El apellido es obligatorio.';
                valid = false;
            }

            // Validate email
            const email = document.getElementById('email').value;
            if (!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
                document.getElementById('emailError').innerText = 'El correo electrónico debe ser válido.';
                valid = false;
            }

            // Validate password
            const password = document.getElementById('password').value;
            if (password.length < 6 || password.length > 30) {
                document.getElementById('passwordError').innerText = 'La contraseña debe tener entre 6 y 30 caracteres.';
                valid = false;
            }

            // Validate age
            const ageInput = document.getElementById('age').value;
            const ageDate = new Date(ageInput);
            const age = new Date().getFullYear() - ageDate.getFullYear();
            if (age < 1 || age > 120) {
                document.getElementById('ageError').innerText = 'La edad debe ser un número entre 1 y 120.';
                valid = false;
            }

            return valid; // Only submit if all fields are valid
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
