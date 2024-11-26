<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page import="com.glamdring.greenZenith.externals.database.GZDBConnector"%>
<%@page import="com.glamdring.greenZenith.userInteractions.products.ProductList"%>
<%@page import="com.glamdring.greenZenith.userInteractions.products.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Marketplace</title>

        <link rel="stylesheet" href="src/stylesplant.css">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="src/stylesmarketplace.css">
    </head>
    <body>
        <div class="navbar">
            <jsp:include page="navbar.jsp" />
        </div>
        <div class="container my-5">
            <div class="row">
                <%
                    GZDBConnector gzdbc = new GZDBConnector();
                    User user = (User) session.getAttribute("User");
                    if (user != null) {
                        for (Product product : new ProductList(gzdbc).getProductList()) {
                            try {
                                if (product.equals(user.getCart().getFromMap(product.getId()).getProduct())) {
                                    request.setAttribute("id", product.getId());
                                    request.setAttribute("title", product.getName());
                                    request.setAttribute("description", product.getDescription());
                                    request.setAttribute("price", product.getPrice());
                                    request.setAttribute("quantity", product.getQuantity());
                                    request.setAttribute("productPicture", product.getPictureBase64());
                                    request.setAttribute("seller", product.getSeller().getName());
                                }
                            } catch (Exception e) {
                                request.setAttribute("id", product.getId());
                                request.setAttribute("title", product.getName());
                                request.setAttribute("description", product.getDescription());
                                request.setAttribute("price", product.getPrice());
                                request.setAttribute("quantity", product.getQuantity());
                                request.setAttribute("productPicture", product.getPictureBase64());
                                request.setAttribute("seller", product.getSeller().getName());
                            }
                        }
                    }
                %> <jsp:include page="plantcard.jsp"/>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
