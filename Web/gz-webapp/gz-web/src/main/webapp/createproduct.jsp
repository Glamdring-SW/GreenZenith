<%@page import="com.glamdring.greenZenith.userInteractions.plants.Plant"%>
<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
    Plant plant = user.getPlant(Integer.parseInt((String) request.getParameter("id")), request.getParameter("name"));
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nuevo Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplant.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <h2 class="text-center">CREAR NUEVO PRODUCTO</h2>
            <div class="form-section">
                <form action="Handlers/product_controller.jsp" method="post" enctype="multipart/form-data" class="form-input">
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <div class="image-upload-container" onclick="document.getElementById('productPicture').click()">
                                <input type="file" id="productPicture" name="productPicture" accept=".png, .jpg, .jpeg" 
                                       onchange="previewImage(event)" style="display: none;">
                                <i class="fas fa-camera upload-icon" id="uploadIcon"></i>
                                <img class="image-preview" id="preview" src="data:image/png;base64, <%= plant.getPictureBase64()%>" alt="" style="display: none;">
                            </div>
                        </div>
                        <div class="col-md-8 d-flex flex-column justify-content-between">
                            <div class="mb-6">
                                <label for="name" class="form-label">Titulo de venta</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Titulo del producto" required="true">
                            </div>
                            <div class="mb-6">
                                <label for="description" class="form-label">Descripción</label>
                                <textarea class="form-control" id="description" name="description" rows="3" 
                                          placeholder="Descripcion de la planta" maxlength="500" onkeyup="updateCharCounter()"></textarea>
                                <div class="char-counter">
                                    <span id="charCount">0</span>/500 caracteres
                                </div>
                            </div>
                            <div class="mb-6">
                                <label for="name" class="form-label">Precio</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="" required="true">
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
                                   min="1" max="<%=plant.getQuantity()%>" value="1" readonly required="true">
                            <button type="button" class="quantity-btn" onclick="updateQuantity(1)" id="increaseBtn">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <input type="number" id="id" name="id" hidden value="<%=request.getParameter("id")%>" required="true">
                    <input type="text" id="name" name="name" hidden value="<%=request.getParameter("name")%>" required="true">
                    <button type="submit" class="btn btn-outline-light">Añadir Producto</button>
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

                document.getElementById('decreaseBtn').disabled = newValue <= 1;
                document.getElementById('increaseBtn').disabled = newValue >= input.max;
            }

            function updateCharCounter() {
                const textarea = document.getElementById('description');
                const counter = document.getElementById('charCount');
                const currentLength = textarea.value.length;
                counter.textContent = currentLength;
            }

            window.onload = function () {
                setDateLimits();
                updateCharCounter();
                document.getElementById('decreaseBtn').disabled = true;
            };
        </script>
    </body>
</html>
