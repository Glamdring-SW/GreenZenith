<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row g-4">
    <div class="col-12">
        <div class="card cart-item">
            <div class="row g-0">
                <!-- Product Image -->
                <div class="col-md-2">
                    <img src="data:image/png;base64, <%= request.getAttribute("productPicture")%>" class="img-fluid rounded-start product-image" alt="<%=request.getAttribute("title")%>">
                </div>

                <!-- Product Details -->
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><%=request.getAttribute("title")%></h5>
                        <p class="card-text"><%=request.getAttribute("price").toString().substring(0, request.getAttribute("price").toString().length() - 2)%></p>
                        <div class="quantity-controls">
                            <form action="Handlers/update_cart_quantity_controller.jsp" method="post" id="<%=request.getAttribute("title")%>form">
                                <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                                <button type="submit" class="quantity-btn" onclick="updateQuantity(-1, '<%=request.getAttribute("title")%>')" id="<%=request.getAttribute("title")%>decreaseBtn">
                                    <i class="fas fa-minus"></i>
                                </button>
                                <input type="number" class="form-control" id="<%=request.getAttribute("title")%>" name="quantity" 
                                       min="1" max="<%=request.getAttribute("quantityTotal")%>" value="<%=request.getAttribute("quantity")%>" readonly>
                                <button type="submit" class="quantity-btn" onclick="updateQuantity(1, '<%=request.getAttribute("title")%>')" id="<%=request.getAttribute("title")%>increaseBtn">
                                    <i class="fas fa-plus"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Remove Button -->
                <div class="col-md-2 d-flex align-items-center justify-content-center">
                    <button class="btn btn-danger btn-sm delete-btn"
                            onclick="confirmDelete(<%= request.getAttribute("id")%>)">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


