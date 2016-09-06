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
<html>
<head>
  <title>Product List</title>
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
  $(document).ready(function () {
  <c:forEach var="product" items="${productList}" varStatus="number">
    $('#productsForm_${product.id}').submit(function (event) {
      var quantity = $('#quantity_${product.id}').val();
      var id = $('#id_${product.id}').val();
      var json = {"id" : id, "quantity" : quantity};

      $.ajax({
        url: $("#productsForm_${product.id}").attr("action"),
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
          xhr.setRequestHeader("Accept", "application/json");
          xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function () {
          location.reload();
        },
        error: function () {
          var content = "";
          content += "<b>ERROR</b>";
          $("#formMessage").html(content);
        }
      });
      event.preventDefault();
    });
  </c:forEach>
  });
</script>
<body>
  <c:import url="header.jsp"/>
  <div id="formMessage"></div>
  Phones
  <table>
    <th>No</th>
    <th>Model</th>
    <th>Color</th>
    <th>Display size</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Action</th>
    <c:forEach var="product" items="${productList}" varStatus="number">
      <tr>
        <td>${number.index + 1}</td>
        <td><a href="/product?id=${product.id}">${product.model}</a></td>
        <td>${product.color}</td>
        <td>${product.displaySize}</td>
        <td>${product.price}</td>
        <form:form id = "productsForm_${product.id}" action="/addToCart">
          <input type="hidden" id="id_${product.id}" value="${product.id}"/>
          <td><input id="quantity_${product.id}" type="text"/></td>
          <td><input type="submit" value="Add to cart"></td>
        </form:form>
      </tr>
    </c:forEach>
  </table>

</body>
</html>
