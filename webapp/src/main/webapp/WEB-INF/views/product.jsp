<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.08.2016
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Product page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/script.js"></script>
</head>
<body>
  <c:import url="header.jsp"/>
  <div class="container">
    <h3>Product details</h3>
    <div id="info"></div>
  </div>
  <div class="container">
    <table class="table table-striped">
      <tr>
        <td>Model</td><td><b>${product.model}</b></td>
      </tr>
      <tr>
        <td>Display</td><td>${product.displaySize}</td>
      </tr>
      <tr>
        <td>Length</td><td>${product.length}</td>
      </tr>
      <tr>
        <td>Width</td><td>${product.width}</td>
      </tr>
      <tr>
        <td>Color</td><td>${product.color}</td>
      </tr>
      <tr>
        <td>Price</td><td>${product.price}</td>
      </tr>
      <tr>
        <td>Camera</td><td>${product.camera}</td>
      </tr>
      <tr id="row_${product.id}">
        <form:form id = "productsForm_${product.id}" action="/addToCart">
          <input type="hidden" id="id_${product.id}" value="${product.id}"/>
          <td><input id="quantity_${product.id}" type="text" value="1" class="form-control"/></td>
          <td><button id="${product.id}" type="submit" class="btn btn-success" onclick="sendAddToCartJson(event, this)">Add to cart</button></td>
        </form:form>
      </tr>
    </table>
  </div>
</body>
</html>
