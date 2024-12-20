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
        <title>Mis Plantas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesplantexplore.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
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
                <% for (Plant plant : user.getPlants().getPlantList()) {
                        request.setAttribute("id", plant.getId());
                        request.setAttribute("name", plant.getName());
                        request.setAttribute("description", plant.getDescription());
                        request.setAttribute("quantity", plant.getQuantity());
                        request.setAttribute("plantingDate", plant.getPlantingDate());
                        request.setAttribute("schedule", plant.getSchedule());
                        request.setAttribute("plantPicture", plant.getPictureBase64());
                %>
                <jsp:include page="plantinfo.jsp"/>
                <% } %>
            </div>
            <%}%>
            <div class="text-center my-3">
                <a href="createplant.jsp" class="btn btn-success">
                    Añadir planta.
                </a>
            </div>
        </div>

        <!-- Delete Confirmation Modal -->
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmar Accion</h5>
                    </div>
                    <div class="modal-body">
                        Esta seguro que quiere eliminar esta planta?
                        Esta accion no se puede deshacer.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <form id="deleteForm" action="Handlers/delete_plant.jsp" method="post">
                            <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                            <input type="text" id="name" name="name" hidden value="<%=request.getAttribute("name")%>">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function confirmDelete(id, title) {
                document.getElementById('id').value = id;
                document.getElementById('name').value = title;
                const deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
                deleteModal.show();
            }
        </script>
    </body>
</html>
