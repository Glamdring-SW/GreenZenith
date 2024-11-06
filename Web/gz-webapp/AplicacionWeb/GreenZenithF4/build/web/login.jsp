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
                        <form class="centered-form" onsubmit="return validateForm(event)">
                            <div class="mb-3">
                                <label for="email" class="form-label color-title">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Introduce email">
                                <div id="emailError" class="text-danger" style="display: none;">Email inválido</div>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label color-title">Contraseña</label>
                                <input type="password" class="form-control" id="password" placeholder="Introduce contraseña">
                                <div id="passwordError" class="text-danger" style="display: none;">Contraseña inválida</div>
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

    <!-- Modal -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="errorModalLabel">Error</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Credenciales incorrectas. Por favor, verifica tu email y contraseña.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function validateForm(event) {
            event.preventDefault(); // Prevent form submission
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            // Reset error messages
            document.getElementById('emailError').style.display = 'none';
            document.getElementById('passwordError').style.display = 'none';

            let isValid = true;

            // Basic validation
            if (!email.includes('@') || !email.includes('.')) {
                document.getElementById('emailError').style.display = 'block';
                isValid = false;
            }
            if (password.length < 3) {
                document.getElementById('passwordError').style.display = 'block';
                isValid = false;
            }

            // Simulate login validation
            if (isValid) {
                if (email === "j@j.j" && password === "jjj") {
                    // Show the modal if credentials are incorrect
                    const modal = new bootstrap.Modal(document.getElementById('errorModal'));
                    modal.show();
                } else {
                    // Handle successful login here (e.g., redirect)
                    alert('Login successful!'); // Replace with actual logic
                }
            }
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
