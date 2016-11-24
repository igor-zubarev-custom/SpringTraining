<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Order</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/script.js"></script>
</head>

<body>
<c:import url="header.jsp"/>
<div class="container">
  <h3>Order confirmation page</h3>
</div>
<c:choose>
  <c:when test="${order != null}">

    <div class="container">
      <table class="table table-striped">
        <th>Model</th>
        <th>Color</th>
        <th>Display size</th>
        <th>Quantity</th>
        <th>Price</th>
        <c:forEach var="cartItem" items="${order.cartItems}" varStatus="number">
          <tr id="row_${cartItem.id}">
            <td>${cartItem.phone.model}</td>
            <td>${cartItem.phone.color}</td>
            <td>${cartItem.phone.displaySize}</td>
            <td>${cartItem.quantity}</td>
            <td>${cartItem.getPrice()}</td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="3"><td>Subtotal</td><td>${order.cartPrice}</td>
        </tr>
        <tr>
          <td colspan="3"><td>Delivery</td><td>${order.deliveryPrice}</td>
        </tr>
        <tr>
          <td colspan="3"><td>TOTAL</td><td>${order.totalPrice}</td>
        </tr>
      </table>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-6">

            <table class="table table-striped">
              <tr>
                <td class="col-md-2">First Name</td><td>${order.firstName}</td>
              </tr>
              <tr>
                <td class="col-md-2">Last Name</td><td>${order.lastName}</td>
              </tr>
              <tr>
                <td class="col-md-2">Address</td><td>${order.deliveryAddress}</td>
              </tr>
              <tr>
                <td class="col-md-2">Phone</td><td>${order.contactPhone}</td>
              </tr>
              <tr>
                <td colspan="2">${order.comment}</td>
              </tr>
              <tr>
                <td><a href="/shop/confirmOrder"><button class="btn btn-primary">Confirm order</button></a></td>
              </tr>
            </table>

        </div>
      </div>
    </div>
  </c:when>
  <c:otherwise>
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-md-offset-5"><a href="/shop/products" class="pull-center">Cart is empty, return to products</a></div>
      </div>
    </div>
  </c:otherwise>
</c:choose>
</body>
</html>
