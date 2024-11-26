<%@page import="com.glamdring.greenZenith.userInteractions.plants.Plant"%>
<%@page import="com.glamdring.greenZenith.userInteractions.products.Product"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
    int productId = Integer.parseInt((String) request.getParameter("id"));
    String productTitle = (String) request.getParameter("oldTitle");
    Product product = user.getProduct(productId, productTitle);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Un Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplant.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <h2 class="text-center">EDITAR PRODUCTO</h2>
            <div class="form-section">
                <form action="Handlers/update_product_controller.jsp" method="post" enctype="multipart/form-data" class="form-input">
                    <input type="number" id="id" name="id" required hidden value="<%=productId%>">
                    <input type="text" id="oldTitle" name="oldTitle" required hidden value="<%=productTitle%>">

                    <div class="row mb-3">
                        <div class="col-md-4">
                            <div class="image-upload-container" onclick="document.getElementById('productPicture').click()">
                                <input type="file" id="productPicture" name="productPicture" accept=".png, .jpg, .jpeg" 
                                       onchange="previewImage(event)" style="display: none;">
                                <i class="fas fa-camera upload-icon" id="uploadIcon"></i>
                                <img class="image-preview" id="preview" src="" alt="" style="display: none;">
                            </div>
                        </div>
                        <div class="col-md-8 d-flex flex-column justify-content-between">
                            <div class="mb-6">
                                <label for="name" class="form-label">Titulo</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="<%=product.getName()%>">
                            </div>
                            <div class="mb-6">
                                <label for="description" class="form-label">Descripción</label>
                                <textarea class="form-control" id="description" name="description" rows="3" 
                                          placeholder="<%=product.getDescription()%>" maxlength="500" 
                                          onkeyup="updateCharCounter()"></textarea>
                                <div class="char-counter">
                                    <span id="charCount">0</span>/500 caracteres
                                </div>
                            </div>
                            <div class="mb-6">
                                <label for="name" class="form-label">Precio</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Cantidad</label>
                        <div class="quantity-control">
                            <button type="button" class="quantity-btn" onclick="updateQuantity(-1)" id="decreaseBtn">
                                <i class="fas fa-minus"></i>
                            </button>
                            <input type="number" class="form-control" id="quantity" name="quantity" 
                                   min="1" max="<%=product.getPlantSale().getQuantity()%>" value="<%=product.getQuantity()%>" readonly>
                            <button type="button" class="quantity-btn" onclick="updateQuantity(1)" id="increaseBtn">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
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
                    reader.onload = function () {
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

                if (newValue >= 1 && newValue <= input.max) {
                    input.value = newValue;
                }

                // Update buttons state
                document.getElementById('decreaseBtn').disabled = newValue <= 1;
                document.getElementById('increaseBtn').disabled = newValue >= input.max;
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

            window.onload = function () {
                setDateLimits();
                updateCharCounter();
                // Initialize quantity buttons state
                document.getElementById('decreaseBtn').disabled = true;
            };
        </script>
    </body>
</html>