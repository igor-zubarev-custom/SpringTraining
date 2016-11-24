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
    <h3>Product list</h3>
  </div>
  <div class="container">
  <table class="table table-striped">
    <th>Model</th>
    <th>Color</th>
    <th>Display size</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Action</th>
    <c:forEach var="product" items="${productList}" varStatus="number">
      <tr id="row_${product.id}">
        <td><a href="/shop/product/${product.id}">${product.model}</a></td>
        <td>${product.color}</td>
        <td>${product.displaySize}</td>
        <td>${product.price}</td>
        <form:form id = "productsForm_${product.id}" action="/shop/addToCart">
          <input type="hidden" id="id_${product.id}" value="${product.id}"/>
          <td id="input_${product.id}"><input id="quantity_${product.id}" type="text" value="1" class="form-control"/></td>
          <td><button id="${product.id}" type="submit" class="btn btn-success" onclick="SpringTraining.Product.sendAddToCartJson(event, this)">Add to cart</button></td>
        </form:form>
      </tr>
    </c:forEach>
  </table>
  </div>
</body>
</html>
