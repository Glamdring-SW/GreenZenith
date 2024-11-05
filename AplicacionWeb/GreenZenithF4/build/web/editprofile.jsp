<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylesprofile.css">
    <style>
        /* Make profile container wider and add padding */
        .profile-container {
            width: 600px; /* Adjust as needed */
            padding: 30px;
            border-radius: 10px;
        }
        /* Circular profile photo preview */
        .profile-photo-preview {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container my-5 d-flex justify-content-center">
    <div class="profile-container text-center">
        <h2 class="profile-name text-white">Editar Perfil</h2>
        <img src="https://via.placeholder.com/150" alt="Profile Photo" class="profile-photo-preview">
        
        <form action="updateProfile.jsp" method="post" enctype="multipart/form-data" class="mt-4">
            <div class="mb-3">
                <label for="name" class="form-label text-white">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Nuevo Nombre">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label text-white">Apellido</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Nuevo Apellido">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label text-white">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Nuevo Email">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label text-white">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Nueva Contraseña">
            </div>
            <div class="mb-3">
                <label for="photo" class="form-label text-white">Foto de Perfil</label>
                <input type="file" class="form-control" id="photo" name="photo" accept="image/*">
            </div>
            <div class="mb-3">
                <label for="dob" class="form-label text-white">Fecha de Nacimiento</label>
                <input type="date" class="form-control" id="dob" name="dob">
            </div>
            <hr class="text-white">
            <h5 class="text-white">Redes Sociales</h5>
            <div class="mb-3">
                <label for="instagram" class="form-label text-white">Instagram</label>
                <input type="url" class="form-control" id="instagram" name="instagram" placeholder="Enter your Instagram URL">
            </div>
            <div class="mb-3">
                <label for="facebook" class="form-label text-white">Facebook</label>
                <input type="url" class="form-control" id="facebook" name="facebook" placeholder="Enter your Facebook URL">
            </div>
            <div class="mb-3">
                <label for="twitter" class="form-label text-white">Twitter</label>
                <input type="url" class="form-control" id="twitter" name="twitter" placeholder="Enter your Twitter URL">
            </div>
            <button type="submit" class="btn btn-outline-light btn-outline-light-custom">Save Changes</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
