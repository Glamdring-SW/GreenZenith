<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Plant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesplant.css">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container my-5">
    <h2 class="text-center">CREAR NUEVA PLANTA</h2>
    <div class="form-section">
        <!-- Image Preview Placeholder -->
        <div class="image-preview">
            <img src="https://via.placeholder.com/150" alt="Plant Image" style="width: 100%; height: auto; border-radius: 10px;">
        </div>

        <!-- Create Form -->
        <form action="addPlant.jsp" method="post" enctype="multipart/form-data" class="form-input">
            <div class="mb-3">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Nombre de la planta" required>
            </div>
            <div class="mb-3">
                <label for="scientificName" class="form-label">Nombre Científico</label>
                <input type="text" class="form-control" id="scientificName" name="scientificName" placeholder="Nombre científico de la planta" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Descripción</label>
                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Descripción de la planta" required></textarea>
            </div>
            <div class="mb-3">
                <label for="photo" class="form-label">Subir Foto</label>
                <input type="file" class="form-control" id="photo" name="photo" accept="image/*" required>
            </div>
            <button type="submit" class="btn btn-outline-light">Añadir Planta</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
