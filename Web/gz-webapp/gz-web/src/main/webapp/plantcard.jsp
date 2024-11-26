<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-12 mb-4">
    <div class="card">
        <div class="row g-0">
            <div class="col-md-3">
                <img src="data:image/png;base64, <%= request.getAttribute("productPicture")%>" class="img-fluid" alt="Plant Image">
            </div>
            <div class="col-md-8 d-flex align-items-center">
                <div class="card-body flex-fill">
                    <h5 class="card-title"><%=request.getAttribute("title")%></h5>
                    <p class="card-text"><%=request.getAttribute("seller")%></p>
                    <p class="card-text"><%=request.getAttribute("description")%></p>
                    <p class="card-text">$<span><%=request.getAttribute("price").toString().substring(0, request.getAttribute("price").toString().length() - 2)%></span></p>
                </div>
                <div class="d-grid gap-3 justify-content-end">
                    <form action="Handlers/cart_controller.jsp" method="post" class="m-0">
                        <input type="number" id="id" name="id" hidden value="<%=request.getAttribute("id")%>">
                        <input type="text" id="title" name="title" hidden value="<%=request.getAttribute("title")%>">
                        <div class="quantity-control">
                            <button type="button" class="quantity-btn" onclick="updateQuantity(-1, '<%=request.getAttribute("title")%>')" id="<%=request.getAttribute("title")%>decreaseBtn">
                                <i class="fas fa-minus"></i>
                            </button>
                            <input type="number" class="form-control" id="<%=request.getAttribute("title")%>" name="quantity" 
                                   min="1" max="<%=request.getAttribute("quantity")%>" value="1" readonly>
                            <button type="button" class="quantity-btn" onclick="updateQuantity(1, '<%=request.getAttribute("title")%>')" id="<%=request.getAttribute("title")%>increaseBtn">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                                <button class="btn btn-outline-success" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" 
                                 class="bi bi-cart-fill" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                            </svg>
                        </button>
                    </form>
                </div>
            </div>                    
        </div>
    </div>
    <script>
        function updateQuantity(change, name) {
            const input = document.getElementById(name);
            const currentValue = parseInt(input.value) || 0;
            const newValue = currentValue + change;

            if (newValue >= 1 && newValue <= input.max) {
                input.value = newValue;
            }
            document.getElementById(name + 'decreaseBtn').disabled = newValue <= 1;
            document.getElementById(name + 'increaseBtn').disabled = newValue >= input.max;
        }
    </script>
</div>
