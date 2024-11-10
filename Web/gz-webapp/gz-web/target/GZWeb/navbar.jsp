<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
%>
<nav class="navbar navbar-expand-lg custom-navbar">
    <div class="container-fluid">
        <a class="sizing-brand nav-link" href="homepage.jsp">Green Zenith</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="login.jsp">LogIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="registro.jsp.jsp">Registro</a>
                </li>
                <%
                    if (user != null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="plantsexplore.jsp">Mis Plantas</a>
                </li>
                <%
                    }
                %>
                <!--
                <li class="nav-item">
                    <a class="nav-link" href="marketplace.jsp">MarketPlace</a>
                </li>
                -->
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a href="profile.jsp">
                        <%
                            if (user == null) {
                        %>
                        <img src="img/default_profilePicture.png" class="img-fluid responsive-img profile-photo-preview" 
                             style="width: 2.25vw; height: 2.25vw;" alt="DefaultUser">
                        <%
                        } else {
                        %>
                        <img src="data:image/png;base64, <%= user.getBase64Picture()%>" class="img-fluid responsive-img profile-photo-preview" 
                             style="width: 2.25vw; height: 2.25vw;" alt="User">
                        <%
                            }
                        %>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
