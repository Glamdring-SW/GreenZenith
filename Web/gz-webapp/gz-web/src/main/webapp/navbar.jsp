<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
%>
<nav class="navbar navbar-expand-lg custom-navbar">
    <div class="container-fluid">
        <!-- Brand -->
        <a class="sizing-brand nav-link" href="homepage.jsp">Green Zenith</a>
        
        <!-- Toggler -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <!-- Navbar content -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Left-aligned items -->
            <ul class="navbar-nav me-auto">
                <% if (user == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="login.jsp">LogIn</a>
                    </li>
                <% } %>
            </ul>
            
            <!-- Right-aligned items -->
            <ul class="navbar-nav ms-auto align-items-center gap-3">
                <!-- Cart Icon -->
                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" 
                             class="bi bi-cart-fill" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                        </svg>
                    </a>
                </li>
                
                <!-- Search Bar -->
                <li class="nav-item">
                    <form class="d-flex" role="search">
                        <div class="input-group">
                            <input class="form-control" type="search" placeholder="Buscar" 
                                   aria-label="Search" style="width: 200px;">
                            <button class="search-btn btn btn-outline-dark" type="submit">Buscar</button>
                        </div>
                    </form>
                </li>
                
                <!-- Profile Picture -->
                <li class="nav-item">
                    <a href="profile.jsp">
                        <% if (user == null) { %>
                            <img src="img/default_profilePicture.png" 
                                 class="img-fluid profile-photo-preview rounded-circle" 
                                 style="width: 40px; height: 40px;" alt="DefaultUser">
                        <% } else { %>
                            <img src="data:image/png;base64, <%= user.getBase64Picture()%>" 
                                 class="img-fluid profile-photo-preview rounded-circle" 
                                 style="width: 40px; height: 40px;" alt="User">
                        <% } %>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<style>
/* Previous styles */
@media (max-width: 991.98px) {
    .navbar-nav {
        gap: 1rem !important;
    }
    
    .navbar-nav .nav-item form {
        width: 100%;
    }
    
    .navbar-nav .nav-item form .input-group {
        width: 100%;
    }
    
    .navbar-nav .nav-item form .form-control {
        width: 100% !important;
    }
}

.navbar .container-fluid {
    padding: 0.5rem 1rem;
}

.gap-3 {
    gap: 1rem !important;
}

/* New search button styles */
.search-btn.btn-outline-dark {
    color: black;
    border-color: black;
    transition: all 0.3s ease;
}

.search-btn.btn-outline-dark:hover {
    background-color: #f8f9fa;
    color: black;
    border-color: black;
}

/* Optional: Add focus state styling */
.search-btn.btn-outline-dark:focus {
    box-shadow: 0 0 0 0.2rem rgba(0, 0, 0, 0.1);
}
</style>