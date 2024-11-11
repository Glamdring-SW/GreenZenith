<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-4">
    <div class="card text-center">
        <form action="editplant.jsp" method="post">
            <label for="id" hidden="true">Id</label>
            <input type="number" id="id" name="id" required="true" hidden="true" value="<%=request.getAttribute("id")%>">
            <label for="oldName" hidden="true">NombreOriginal</label>
            <input type="text" id="oldName" name="oldName" required="true" hidden="true" value="<%=request.getAttribute("name")%>">
            <button type="submit" class="image-hover rounded border-0">
                <img src="data:image/png;base64, <%= request.getAttribute("plantPicture")%>" class="card-img-top rounded" style="width: 30vh;" alt="<%= request.getAttribute("name")%>">
            </button>
            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-8" style="margin: auto;">
                        <div class="card-body">
                            <h5 class="card-title"><%=request.getAttribute("name")%></h5>
                            <p class="card-text"><%=request.getAttribute("description")%></p>
                            <p class="card-text">Cantidad: <%=request.getAttribute("quantity")%></p>
                            <p class="card-text">Horarios:</p>
                            <%
                                SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
                                for (Time waterTime : (ArrayList<Time>) request.getAttribute("schedule")) {
                            %>  
                            <p class="card-text"><%= formatTime.format(waterTime)%></p>
                            <%
                                }
                            %>

                            <p class="card-text"><small class="text-body-secondary"><%= request.getAttribute("plantingDate")%></small></p>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
