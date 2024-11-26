<%@page import="com.glamdring.greenZenith.userInteractions.users.User"%>
<%@page import="com.glamdring.greenZenith.userInteractions.products.CartProduct"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("User");
    if (user == null) {
%>
<jsp:forward page="login.jsp"/>
<%
    }
    int totalProducts = 0;
    BigDecimal totalPrice = new BigDecimal(0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Carrito</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="src/stylescart.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>

        <main>
            <div class="container">
                <h2 class="mb-4">Carrito de Compra</h2>    

                <div class="cart-items-container">
                    <%
                        if (!user.getCart().isEmpty()) {
                            for (CartProduct product : user.getCart().getProductList()) {
                                request.setAttribute("id", product.getProduct().getId());
                                request.setAttribute("title", product.getProduct().getName());
                                request.setAttribute("quantityTotal", product.getProduct().getQuantity());
                                request.setAttribute("quantity", product.getQuantity());
                                request.setAttribute("price", product.getProduct().getPrice());
                                request.setAttribute("productPicture", product.getProduct().getPictureBase64());
                                totalProducts++;
                                totalPrice = totalPrice.add(product.getProduct().getPrice());
                    %>
                    <jsp:include page="cartitem.jsp"/>    
                    <%}%>
                </div>
                <!-- Cart Summary -->
                <div class="cart-summary-wrapper">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Resumen de Compra:</h5>
                            <div class="d-flex justify-content-between">
                                <span>Total de Productos:</span>
                                <span id="total-items"><%=totalProducts%></span>
                            </div>
                            <div class="d-flex justify-content-between mt-2">
                                <span>Precio Total:</span>
                                <span id="total-price">$<%=totalPrice.toString().substring(0, totalPrice.toString().length() - 2)%></span>
                            </div>
                            <form id="deleteForm" action="Handlers/purchase.jsp" method="post">
                                <button class="btn btn-success w-100 mt-3">Proceder al Pago</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%} else {%>
        <div class="text-center my-3">
            <div class="alert alert-info text-center empty-list-container" role="alert">
                <p> No hay ningun elemento en tu carrito </p>
                <a href="marketplace.jsp" class="btn btn-success">
                    Marketplace
                </a>
            </div>
        </div>
        <%}%>
        <!-- Delete Confirmation Modal -->
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmar Accion</h5>
                    </div>
                    <div class="modal-body">
                        Esta seguro que quiere eliminar este producto?
                        Esta accion no se puede deshacer.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <form id="deleteForm" action="Handlers/delete_cart.jsp" method="post">
                            <input type="number" id="idUpdate" name="id" hidden>
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function confirmDelete(id) {
                document.getElementById('idUpdate').defaultValue = id;
                const deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
                deleteModal.show();
            }

            function updateQuantity(change, name) {
                const input = document.getElementById(name);
                const currentValue = parseInt(input.value) || 0;
                const newValue = currentValue + change;
                document.getElementById(name + 'decreaseBtn').disabled = newValue <= 1;
                document.getElementById(name + 'increaseBtn').disabled = newValue >= input.max;
                if (newValue >= 1 && newValue <= input.max) {
                    input.value = newValue;
                }
                document.getElementById(name + 'form').submit();
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </script>
</body>
</html>