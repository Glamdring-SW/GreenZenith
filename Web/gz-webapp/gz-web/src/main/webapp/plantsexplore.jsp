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
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Explore Plants</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplantexplore.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container my-5">
            <h3 class="text-center mb-4">Plantas</h3>
            <%
                if (user.getPlants().isEmpty()) {
            %>
            <div class="alert alert-info text-center empty-list-container" role="alert">
                Ingresa tu primera planta!
            </div>
            <% } else { %>
            <div class="row g-4">
                <% for (Plant plant : user.getPlants()) { %>
                <%
                    request.setAttribute("name", plant.getName());
                    request.setAttribute("description", plant.getDescription());
                    request.setAttribute("quantity", plant.getQuantity());
                    request.setAttribute("schedule", plant.getSchedule());
                    request.setAttribute("plantingDate", plant.getPlantingDate());
                    request.setAttribute("plantPicture", user.getBase64PictureFromExternalSource(plant.getPicture()));
                %>
                <jsp:include page="plantinfo.jsp"/>
                <% } %>
            </div>
            <% }%>
            <div class="text-center my-3">
                <a href="createplant.jsp" class="create-plant-button">
                    Añadir planta.
                </a>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
