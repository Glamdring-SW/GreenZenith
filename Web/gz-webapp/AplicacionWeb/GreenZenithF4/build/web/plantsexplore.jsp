<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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

        <!-- Script to check if the list is empty -->
        <%
            List plants = (List) request.getAttribute("plants"); // Assuming "plants" is set in the request scope
            if (plants == null || plants.isEmpty()) {
        %>
            <!-- Message when no plants are available -->
            <div class="alert alert-info text-center empty-list-container" role="alert">
                Create your first plant!
            </div>
            
            <!-- Button to add a new plant -->
            <div class="text-center my-3">
                <a href="createPlant.jsp" class="create-plant-button">
                    Add New Plant
                </a>
            </div>
            
        <% } else { %>
            <div class="row g-4">
                <!-- Loop through plants list and display each plant -->
                <% for (Object plant : plants) { %>
                    <jsp:include page="plantinfo.jsp" />
                <% } %>
                
                <!-- Card to Add New Plant -->
                <div class="col">
                    <div class="card h-100">
                        <a href="createplant.jsp" class="text-decoration-none text-dark text-center py-5">
                            <h5 class="card-title">Add New Plant</h5>
                        </a>
                    </div>
                </div>
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
