<%
    
    String usuario = request.getParameter("firstName");
    String email = request.getParameter("email");
    String contrasena = request.getParameter("contrasena"); 

    session.setAttribute("usuario", usuario);
    response.sendRedirect("../test.jsp");

%>