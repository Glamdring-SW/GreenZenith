<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito - Green Zenith</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="src/stylescart.css">
</head>
<body>
    <%@include file="navbar.jsp"%>

    <main>
        <div class="container">
            <h2 class="mb-4">Carrito de Compra</h2>    
            
            <div class="cart-items-container">
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
                <%@include file="cartitem.jsp"%>
            </div>
            
            <!-- Cart Summary -->
            <div class="cart-summary-wrapper">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Resumen de Compra:</h5>
                        <div class="d-flex justify-content-between">
                            <span>Total de Productos:</span>
                            <span id="total-items">1</span>
                        </div>
                        <div class="d-flex justify-content-between mt-2">
                            <span>Precio Total:</span>
                            <span id="total-price">$29.99</span>
                        </div>
                        <button class="btn btn-success w-100 mt-3">Proceder al Pago</button>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <div class="modal fade" id="deleteConfirmModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Eliminar Articulo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro de que quieres eliminar este artículo?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" onclick="removeItem()" id="confirmDelete">Eliminar</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        let currentItemToRemove = null;
        const deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));

        function updateQuantity(itemId, action) {
            const quantityElement = document.getElementById(`quantity-${itemId}`);
            let currentQuantity = parseInt(quantityElement.textContent);
            
            if (action === 'increase') {
                currentQuantity++;
                quantityElement.textContent = currentQuantity;
            } else if (action === 'decrease') {
                if (currentQuantity > 1) {
                    currentQuantity--;
                    quantityElement.textContent = currentQuantity;
                } else if (currentQuantity === 1) {
                    confirmRemove(itemId);
                }
            }
            
            updateCartSummary();
        }

        function confirmRemove(itemId) {
            currentItemToRemove = itemId;
            deleteModal.show();
        }

        function removeItem() {
            if (currentItemToRemove) {
                const itemElement = document.querySelector(`[data-item-id="${currentItemToRemove}"]`);
                if (itemElement) {
                    itemElement.remove();
                    updateCartSummary();
                }
                deleteModal.hide();
                currentItemToRemove = null;
            }
        }

        function updateCartSummary() {
            // Placeholder - you'll need to implement the actual calculation
            let totalItems = 0;
            let totalPrice = 0;
            
            document.getElementById('total-items').textContent = totalItems;
            document.getElementById('total-price').textContent = `$${totalPrice.toFixed(2)}`;
        }
    </script>
</body>
</html>