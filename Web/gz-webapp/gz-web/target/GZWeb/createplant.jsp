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
        <title>Create New Plant</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplant.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
        <style>
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
                object-fit: cover;
                display: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container my-5">
            <h2 class="text-center">CREAR NUEVA PLANTA</h2>
            <div class="form-section">
                <form action="Handlers/plant_controller.jsp" method="post" enctype="multipart/form-data" class="form-input">
                    <div class="mb-3">
                        <label class="form-label" for="plantPicture">Foto de la planta.</label>            
                        <div  class="form-control">
                        <input type="file" id="plantPicture" name="plantPicture" accept=".png, .jpg, .jpeg" onchange="previewImage(event)">
                        <img class="img-fluid rounded responsive-img mx-auto d-block" style="width: 10vh;" id="preview" src="" alt="">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Nombre de la planta" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Descripción</label>
                        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Descripción de la planta"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="plantingDate" class="form-label">Fecha de plantado</label>
                        <input type="date" class="form-control" id="plantingDate" name="plantingDate" max="2024-11-06" 
                               placeholder="Dia en el que fue plantada." required>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Cantidad</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" min="1" placeholder="Cantidad del mismo tipo." required>
                    </div>
                    <div class="mb-3">
                        <label for="waterTime" class="form-label">Hora de Riego</label>
                        <input type="time" class="form-control" id="waterTime" name="waterTime" placeholder="Hora de regado." required>
                    </div>
                    <button type="submit" class="btn btn-outline-light">Añadir Planta</button>
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
        </script>
    </body>
</html>
