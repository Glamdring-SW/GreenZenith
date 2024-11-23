<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-4 mb-4">
    <div class="card h-100 shadow-sm hover-card">
        <div class="card-header p-0 position-relative">
            <!-- Edit Form -->
            <form action="editplant.jsp" method="post" class="m-0">
                <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                <input type="text" id="oldName" name="oldName" hidden value="<%=request.getAttribute("name")%>">
                <button type="submit" class="btn p-0 w-100 image-container">
                    <img src="data:image/png;base64, <%= request.getAttribute("plantPicture")%>" 
                         class="card-img-top plant-image" 
                         alt="<%= request.getAttribute("name")%>">
                </button>
            </form>
            
            <!-- Action Buttons Container -->
            <div class="position-absolute top-0 end-0 m-2 d-flex gap-2 action-buttons">
                <!-- Edit Button -->
                <a href="editplant.jsp?id=<%=request.getAttribute("id")%>" 
                   class="btn btn-success btn-sm edit-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                    </svg>
                </a>
                
                <!-- Delete Button -->
                <button class="btn btn-danger btn-sm delete-btn" 
                        onclick="confirmDelete(<%=request.getAttribute("id")%>, '<%=request.getAttribute("name")%>')">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                    </svg>
                </button>
            </div>
        </div>

        <div class="card-body">
            <h5 class="card-title fw-bold mb-3"><%=request.getAttribute("name")%></h5>
            <p class="card-text text-muted"><%=request.getAttribute("description")%></p>
            
            <div class="mt-3">
                <div class="d-flex align-items-center mb-3">
                    <span class="fw-semibold me-2">Cantidad:</span>
                    <span class="badge bg-success"><%=request.getAttribute("quantity")%> unidades</span>
                </div>
                
                <div class="watering-schedule">
                    <h6 class="fw-semibold mb-2">Horario de Riego:</h6>
                    <div class="schedule-times">
                        <%
                            SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
                            for (Time waterTime : (ArrayList<Time>) request.getAttribute("schedule")) {
                        %>  
                        <span class="badge bg-info text-dark mb-1">
                            <%= formatTime.format(waterTime)%>
                        </span>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="card-footer bg-transparent">
            <small class="text-muted">
                Plantado en: <%= request.getAttribute("plantingDate")%>
            </small>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmModalLabel">Confirm Deletion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete <span id="plantNameToDelete" class="fw-bold"></span>?
                This action cannot be undone.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <form id="deleteForm" action="deleteplant" method="POST">
                    <input type="hidden" id="plantIdToDelete" name="plantId">
                    <button type="submit" class="btn btn-danger">Delete Plant</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmDelete(plantId, plantName) {
        document.getElementById('plantNameToDelete').textContent = plantName;
        document.getElementById('plantIdToDelete').value = plantId;
        const deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
        deleteModal.show();
    }
</script>