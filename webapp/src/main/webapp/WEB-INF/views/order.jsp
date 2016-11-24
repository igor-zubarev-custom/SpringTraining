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
<c:set var="cart" scope="page" value="${cartService.getCart()}" />
<div class="container">
  <h3>Order</h3>
</div>
<c:choose>
  <c:when test="${cart.cartItems.size() != 0}">

    <div class="container">
      <table class="table table-striped">
        <th>Model</th>
        <th>Color</th>
        <th>Display size</th>
        <th>Quantity</th>
        <th>Price</th>
        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="number">
          <tr id="row_${cartItem.id}">
            <td>${cartItem.phone.model}</td>
            <td>${cartItem.phone.color}</td>
            <td>${cartItem.phone.displaySize}</td>
            <td>${cartItem.quantity}</td>
            <td>${cartItem.getPrice()}</td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="3"><td>Subtotal</td><td>${cart.totalPrice}</td>
        </tr>
        <tr>
          <td colspan="3"><td>Delivery</td><td>${cart.deliveryInfo.deliveryPrice}</td>
        </tr>
        <tr>
          <td colspan="3"><td>TOTAL</td><td>${cartService.getTotalPrice()}</td>
        </tr>
      </table>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <form:form method="post" modelAttribute="addressFormData" action="/shop/order">
            <table class="table table-striped">
              <tr>
                <td class="col-md-2">First Name</td>
                <td>
                  <form:input path="firstName" type="text" value="${cart.deliveryInfo.firstName}" class="form-control" placeholder="First Name"/>
                  <form:errors path="firstName" cssClass="error"/>
                </td>
              </tr>
              <tr>
                <td class="col-md-2">Last Name</td>
                <td>
                  <form:input path="lastName" type="text" value="${cart.deliveryInfo.lastName}" class="form-control" placeholder="Last Name"/>
                  <form:errors path="lastName" cssClass="error"/>
                </td>
              </tr>
              <tr>
                <td class="col-md-2">Address</td>
                <td>
                  <form:input path="address" type="text" value="${cart.deliveryInfo.deliveryAddress}" class="form-control" placeholder="Delivery Address"/>
                  <form:errors path="address" cssClass="error"/>
                </td>
              </tr>
              <tr>
                <td class="col-md-2">Phone</td>
                <td>
                  <form:input path="phone" type="tel" value="${cart.deliveryInfo.contactPhone}" class="form-control" placeholder="Sample 29555777"/>
                  <form:errors path="phone" cssClass="error"/>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <form:textarea path="comment" rows="3" value="${cart.deliveryInfo.comment}" class="form-control" placeholder="Additional info"/>
                  <form:errors path="comment" cssClass="error"/>
                </td>
              </tr>
              <tr>
                <td><button type="submit" class="btn btn-primary">Order</button></td>
              </tr>
            </table>          
          </form:form>
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
