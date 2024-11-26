<%@page import="com.glamdring.greenZenith.userInteractions.plants.Plant"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-4 mb-4">
    <div class="card h-100 shadow-sm hover-card">
        <div class="card-header p-0 position-relative">
            <!-- Edit Form -->
            <form action="editproduct.jsp" method="post" class="m-0">
                <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                <input type="text" id="oldTitle" name="oldTitle" hidden value="<%=request.getAttribute("title")%>">
                <button type="submit" class="btn p-0 w-100 image-container">
                    <img src="data:image/png;base64, <%= request.getAttribute("productPicture")%>" 
                         class="card-img-top plant-image" 
                         alt="<%= request.getAttribute("title")%>">
                </button>
            </form>

            <!-- Action Buttons Container -->
            <div class="position-absolute top-0 end-0 m-2 d-flex gap-2 action-buttons">
                <button class="btn btn-danger btn-sm delete-btn"
                        onclick="confirmDelete(<%= request.getAttribute("id")%>, '<%= request.getAttribute("title")%>')">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                    </svg>
                </button>
            </div>
        </div>

        <div class="card-body">
            <h5 class="card-title fw-bold mb-3"><%=request.getAttribute("title")%></h5>
            <p class="card-text text-muted"><%=request.getAttribute("description")%></p>

            <div class="mt-3">
                <div class="d-flex align-items-center mb-3">
                    <span class="fw-semibold me-2">Cantidad:</span>
                    <span class="badge bg-success"><%=request.getAttribute("quantity")%> unidades</span>
                </div>
                <div class="d-flex align-items-center mb-3">
                    <span class="fw-semibold me-2">Precio:</span>
                    <span class="badge bg-success">$<%=request.getAttribute("price").toString().substring(0, request.getAttribute("price").toString().length() - 2)%></span>
                </div>
                <div class="d-flex align-items-center mb-3">
                    <span class="fw-semibold me-2">Nombre de la Planta:</span>
                    <span class="badge bg-success"><%=request.getAttribute("plantSale")%></span>
                </div>
            </div>
        </div>
    </div>
</div>