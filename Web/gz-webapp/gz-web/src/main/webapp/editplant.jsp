<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
    String plantId = (String) request.getParameter("id");
    String plantName = (String) request.getParameter("oldName");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Plant Information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplant.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <h2 class="text-center">EDITAR PLANTA</h2>
            <div class="form-section">
                <form action="Handlers/update_plant_controller.jsp" method="post" enctype="multipart/form-data" class="form-input">
                    <input type="number" id="id" name="id" required hidden value="<%=plantId%>">
                    <input type="text" id="oldName" name="oldName" required hidden value="<%=plantName%>">

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <!-- Image Upload -->
                            <div class="image-upload-container" onclick="document.getElementById('plantPicture').click()">
                                <input type="file" id="plantPicture" name="plantPicture" accept=".png, .jpg, .jpeg" 
                                       onchange="previewImage(event)" style="display: none;">
                                <i class="fas fa-camera upload-icon" id="uploadIcon"></i>
                                <img class="image-preview" id="preview" src="" alt="" style="display: none;">
                            </div>
                        </div>
                        <div class="col-md-6 d-flex flex-column justify-content-between">
                            <!-- Cantidad Section -->
                            <div class="mb-3">
                                <label for="quantity" class="form-label">Cantidad</label>
                                <div class="quantity-control">
                                    <button type="button" class="quantity-btn" onclick="updateQuantity(-1)" id="decreaseBtn">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                    <input type="number" class="form-control" id="quantity" name="quantity" 
                                           min="1" max="999" value="1" readonly>
                                    <button type="button" class="quantity-btn" onclick="updateQuantity(1)" id="increaseBtn">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <!-- Fecha de Plantado Section -->
                            <div class="mb-3">
                                <label for="plantingDate" class="form-label">Fecha de plantado</label>
                                <input type="date" class="form-control" id="plantingDate" name="plantingDate">
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Nombre de la planta">
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Descripción</label>
                        <textarea class="form-control" id="description" name="description" rows="3" 
                                placeholder="Descripción de la planta" maxlength="500" 
                                onkeyup="updateCharCounter()"></textarea>
                        <div class="char-counter">
                            <span id="charCount">0</span>/500 caracteres
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="waterTime" class="form-label">Hora de Riego</label>
                        <input type="time" class="form-control" id="waterTime" name="waterTime">
                    </div>

                    <button type="submit" class="btn btn-outline-light">Guardar Cambios</button>
                </form>
            </div>
        </div>

        <script>
            function previewImage(event) {
                const file = event.target.files[0];
                const preview = document.getElementById('preview');
                const uploadIcon = document.getElementById('uploadIcon');

                if (file) {
                    const reader = new FileReader();
                    reader.onload = function() {
                        preview.style.display = 'block';
                        preview.src = reader.result;
                        uploadIcon.style.display = 'none';
                    };
                    reader.readAsDataURL(file);
                } else {
                    preview.style.display = 'none';
                    uploadIcon.style.display = 'block';
                }
            }

            function updateQuantity(change) {
                const input = document.getElementById('quantity');
                const currentValue = parseInt(input.value) || 0;
                const newValue = currentValue + change;
                
                if (newValue >= 1 && newValue <= 999) {
                    input.value = newValue;
                }
                
                // Update buttons state
                document.getElementById('decreaseBtn').disabled = newValue <= 1;
                document.getElementById('increaseBtn').disabled = newValue >= 999;
            }

            function updateCharCounter() {
                const textarea = document.getElementById('description');
                const counter = document.getElementById('charCount');
                const currentLength = textarea.value.length;
                counter.textContent = currentLength;
            }

            function setDateLimits() {
                const inputDate = document.getElementById("plantingDate");
                const today = new Date();
                const minDate = new Date(today.getFullYear() - 100, today.getMonth(), today.getDate());
                const maxDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());
                inputDate.min = minDate.toISOString().split("T")[0];
                inputDate.max = maxDate.toISOString().split("T")[0];
            }

            window.onload = function() {
                setDateLimits();
                updateCharCounter();
                // Initialize quantity buttons state
                document.getElementById('decreaseBtn').disabled = true;
            };
        </script>
    </body>
</html>