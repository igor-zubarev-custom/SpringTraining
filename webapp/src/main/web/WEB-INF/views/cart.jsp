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
<html>
<head>
  <title>Product List</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script>
    $(document).ready(function () {
      <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="number">
      $('#cartItemForm_${cartItem.id}').submit(function (event) {
        event.preventDefault();
        var id = $('#id_${cartItem.id}').val();
        var url = $("#cartItemForm_${cartItem.id}").attr("action");
        sendJson(id, url);
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
    
    function sendJson(id, url) {
      var json = {"id" : id};

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
          $('#info').addClass('success');
          $('#info').html(responseInfo);
          $('#row_' + id).remove();
          getCartInfo();
        },
        error: function (xhr) {
          var response = $.parseJSON(xhr.responseText);
          var messages = response.messageList;
          var responseInfo = "";
          if (xhr.status = 400){
            for(i = 0; i < messages.length; i++){
              responseInfo += messages[i] + ". ";
            }
          };
          if (xhr.status = 406){
            for(i = 0; i < messages.length; i++){
              responseInfo += messages[i].defaultMessage + ". ";
            }
          };
          $('#info').addClass('error');
          $('#info').html(responseInfo);
        }
      });
    }
  </script>
</head>

<body>
<c:import url="header.jsp"/>
<h1>Cart</h1>
<c:choose>
<c:when test="${cart.cartItems.size() != 0 && cart != null}">
<div id="info"></div>
<div class="navigation">
  <div class="btn1"><a href="/products">Return to products</a></div>
  <div class="btn2"><a href="/cart">Order</a></div>
</div>
<div class="productTable">
<table>
  <th>Model</th>
  <th>Color</th>
  <th>Display size</th>
  <th>Price</th>
  <th>Quantity</th>
  <th>Action</th>
  <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="number">
    <tr id="row_${cartItem.id}">
      <td>${cartItem.phone.model}</td>
      <td>${cartItem.phone.color}</td>
      <td>${cartItem.phone.displaySize}</td>
      <td>${cartItem.phone.price}</td>
      <td><input name="cartItemDTOs[${number.index}].quantity" type="text" value="${cartItem.quantity}" form="cartForm"/></td>
      <input type="hidden" name="cartItemDTOs[${number.index}].id" value="${cartItem.id}" form="cartForm"/>
      <form:form id ="cartItemForm_${cartItem.id}" action="/deleteFromCart">
        <input type="hidden" id="id_${cartItem.id}" value="${cartItem.id}"/>
        <td><input type="submit" value="Delete" form="cartItemForm_${cartItem.id}"></td>
      </form:form>
    </tr>
  </c:forEach>
</table>
</div>
<div class="navigation">
  <spring:hasBindErrors name="cartFormData">
    <c:forEach var="error" items="${errors.allErrors}">
      <b><spring:message message="${error}" /></b>
      <br/>
    </c:forEach>
  </spring:hasBindErrors>
  <div class="btn2"><a href="/cart">Order</a></div>
  <form:form id="cartForm" method="post" action="/updateCart" modelAttribute="cartFormData">
    <input type="submit" value="Update" form="cartForm" style="float: right; margin: 10px; padding: 10px;">
  </form:form>
</div>
</c:when>
<c:otherwise>
    <div class="btn1"><a href="/products">Return to products</a></div><br>
  Cart is empty!
</c:otherwise>
</c:choose>
</body>
</html>
