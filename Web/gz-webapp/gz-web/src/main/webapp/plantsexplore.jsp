<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cuidado de Plantas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/stylesplantexplore.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js defer"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container my-5">
            <h3 class="text-center mb-4">Plantas</h3>
            <div class="row g-4">
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
                <jsp:include page="plantinfo.jsp" />
            </div>
        </div>
    </body>
</html>

