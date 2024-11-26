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
                <form action="createproduct.jsp" method="post" class="btn btn-success btn-sm edit-btn">
                    <button type="submit" class="btn p-0 w-100 image-container">
                        <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                        <input type="text" id="name" name="name" hidden value="<%=request.getAttribute("name")%>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-dollar" viewBox="0 0 16 16">
                        <path d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73z"/>
                        </svg>
                    </button>
                </form>


                <!-- Delete Button -->
                <button class="btn btn-danger btn-sm delete-btn"
                        onclick="confirmDelete(<%= request.getAttribute("id")%>, '<%= request.getAttribute("name")%>')">
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
