<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.08.2016
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Product List</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/script.js"></script>
</head>

<body>
<c:import url="header.jsp"/>
<div class="container">
  <h3>Product list</h3>
</div>
<c:choose>
<c:when test="${sessionScope['scopedTarget.cart'].cartItems.size() != 0}">

<div class="container">
<table class="table table-striped">
  <th>Model</th>
  <th>Color</th>
  <th>Display size</th>
  <th>Price</th>
  <th>Quantity</th>
  <th>Action</th>
  <c:forEach var="cartItem" items="${sessionScope['scopedTarget.cart'].cartItems}" varStatus="number">
    <tr id="row_${cartItem.id}">
      <td>${cartItem.phone.model}</td>
      <td>${cartItem.phone.color}</td>
      <td>${cartItem.phone.displaySize}</td>
      <td>${cartItem.phone.price}</td>
      <td><input name="cartItemDTOs[${number.index}].quantity" type="text" value="${cartItem.quantity}" form="cartForm" class="form-control"/></td>
      <input type="hidden" name="cartItemDTOs[${number.index}].id" value="${cartItem.id}" form="cartForm"/>
      <form:form id ="cartItemForm_${cartItem.id}" action="/deleteFromCart">
        <input type="hidden" id="id_${cartItem.id}" value="${cartItem.id}"/>
        <td><button id="${cartItem.id}" type="submit" class="btn btn-warning" onclick="sendDeleteJson(event, this)">Delete</button></td>
      </form:form>
    </tr>
  </c:forEach>
</table>
</div>

  <div class="container">
    <div class="row">
      <div class="col-md-10">
        <div id="info"></div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-10">
        <spring:hasBindErrors name="cartFormData">
          <c:forEach var="error" items="${errors.allErrors}">
            <b><spring:message message="${error}" /></b>
            <br/>
          </c:forEach>
        </spring:hasBindErrors>
      </div>
      <div class="col-md-1">
        <form:form id="cartForm" method="post" action="/updateCart" modelAttribute="cartFormData">
          <button type="submit" class="btn btn-primary" form="cartForm">Update</button>
        </form:form>
      </div>
      <div class="col-md-1">
        <a href="/cart" class="btn btn-success">Order</a>
      </div>
    </div>
  </div>
</c:when>
<c:otherwise>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-5"><a href="/products" class="pull-center">Cart is empty, return to products</a></div>
    </div>

  </div>
</c:otherwise>
</c:choose>
</body>
</html>
