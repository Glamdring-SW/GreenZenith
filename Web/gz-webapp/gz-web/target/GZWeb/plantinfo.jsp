<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-4">
    <div class="card text-center">
        <a href="editplant.jsp" class="image-hover">
            <img  src="data:image/png;base64, <%= request.getAttribute("plantPicture")%>" class="card-img-top" style="width: 30vh;" alt="<%= request.getAttribute("name")%>">
        </a>
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><%= request.getAttribute("name")%></h5>
                        <p class="card-text"><%= request.getAttribute("description")%></p>
                        <p class="card-text">Cantidad: <%= request.getAttribute("quantity")%></p>
                        <p class="card-text">Horarios:</p>
                        <%
                            for (Time waterTime : (ArrayList<Time>) request.getAttribute("schedule")) {
                        %>  
                        <p class="card-text"><%= waterTime%></p>
                        <%
                            }
                        %>
                        <p class="card-text"><small class="text-body-secondary"><%= request.getAttribute("plantingDate")%></small></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
