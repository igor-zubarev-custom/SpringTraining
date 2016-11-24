<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Product List</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="_csrf" content="${_csrf.token}" />
  <meta name="_csrf_header" content="${_csrf.headerName}" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/script.js"></script>
</head>

<body>
<c:import url="header.jsp"/>
<div class="container">
  <h3>Cart</h3>
</div>
<c:choose>
<c:when test="${cart.cartItems.size() != 0}">

<div class="container">
<table class="table table-striped">
  <th>Model</th>
  <th>Color</th>
  <th>Display size</th>
  <th>Price</th>
  <th>Quantity</th>
  <th>Action</th>
  <form:form id="cartForm" method="post" action="/shop/updateCart" modelAttribute="cartFormData">
    <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="number">
      <tr id="row_${cartItem.id}">
        <td>${cartItem.phone.model}</td>
        <td>${cartItem.phone.color}</td>
        <td>${cartItem.phone.displaySize}</td>
        <td>${cartItem.phone.price}</td>
        <td>
          <form:input path="cartItemDTOs[${number.index}].quantity" type="text" class="form-control"/>
          <form:errors path="cartItemDTOs[${number.index}].quantity" cssClass="error"/>
          <form:hidden path="cartItemDTOs[${number.index}].id" value="${cartItem.id}"/>
        </td>
        <td><button id="${cartItem.id}" type="submit" class="btn btn-warning" onclick="SpringTraining.Cart.sendDeleteJson(event, this)">Delete</button></td>
      </tr>
    </c:forEach>
  </form:form>
</table>
</div>

  <div class="container">
    <div class="row">
      <div class="col-md-10">
      </div>
      <div class="col-md-1">
        <button type="submit" class="btn btn-primary" form="cartForm">Update</button>
      </div>
      <div class="col-md-1">
        <a href="/shop/order" class="btn btn-success">Order</a>
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
