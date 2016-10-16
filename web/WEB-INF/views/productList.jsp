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
  <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script>
    $(document).ready(function () {
      <c:forEach var="product" items="${productList}" varStatus="number">
      $('#productsForm_${product.id}').submit(function (event) {
        event.preventDefault();
        var quantity = $('#quantity_${product.id}').val();
        var id = $('#id_${product.id}').val();
        var url = $("#productsForm_${product.id}").attr("action");
        sendJson(id, quantity, url);
      });
      </c:forEach>
    });

    function getCartInfo() {
      $.ajax({
        type: "GET",
        url: "/cartInfo",
        dataType: "json",
        success: function (response) {
          $('.cartMini').empty();
          $('.cartMini').html('My cart: ' + response.quantity + ' items, ' + response.price + '$');
        }
      })
    }

    function sendJson(id, quantity, url) {
      var json = {"id" : id, "quantity" : quantity};

      $('.success').empty();
      $('.error').empty();
      $('.success').removeClass('success');
      $('.error').removeClass('error');

      $.ajax({
        url: url,
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
          xhr.setRequestHeader("Accept", "application/json");
          xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (response) {
          var messages = response.messageList;
          var responseInfo = "";
          for(i = 0; i < messages.length; i++){
            responseInfo += messages[i] + ". ";
          }
          $('#quantity_' + id).val('1');
          $('#info').addClass('success');
          $('#info').html(responseInfo);
          getCartInfo();
        },
        error: function (xhr) {
          var response = $.parseJSON(xhr.responseText);
          var messages = response.messageList;
          var responseInfo = "";
          for(i = 0; i < messages.length; i++){
            responseInfo += messages[i].defaultMessage + ". ";
          }
          $('#quantity_' + id).val(quantity);
          $('#info').addClass('error');
          $('#info').html(responseInfo);
        }
      });
    }
  </script>
</head>

<body>
  <c:import url="header.jsp"/>
  <h1>Phones</h1>
  <div id="info"></div>
  <div class="navigation">
    <div class="btn1"><a href="/cart">Cart</a></div>
  </div>
  <div class="productTable">
  <table>
    <th>Model</th>
    <th>Color</th>
    <th>Display size</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Action</th>
    <c:forEach var="product" items="${productList}" varStatus="number">
      <tr>
        <td><a href="/product/${product.id}">${product.model}</a></td>
        <td>${product.color}</td>
        <td>${product.displaySize}</td>
        <td>${product.price}</td>
        <form:form id = "productsForm_${product.id}" action="/addToCart">
          <input type="hidden" id="id_${product.id}" value="${product.id}"/>
          <td><input id="quantity_${product.id}" type="text" value="1"/></td>
          <td><input type="submit" value="Add to cart"></td>
        </form:form>
      </tr>
    </c:forEach>
  </table>
  </div>
</body>
</html>
