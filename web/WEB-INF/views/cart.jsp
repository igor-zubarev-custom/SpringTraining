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
      <c:forEach var="orderItem" items="${order.orderItems}" varStatus="number">
      $('#orderItemForm_${orderItem.id}').submit(function (event) {
        var id = $('#id_${orderItem.id}').val();
        var json = {"id" : id};

        $('.success').empty();
        $('.error').empty();
        $('.success').removeClass('success');
        $('.error').removeClass('error');

        $.ajax({
          url: $("#orderItemForm_${orderItem.id}").attr("action"),
          data: JSON.stringify(json),
          type: "POST",
          beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
          },
          success: function (response) {
            var messages = response.messageList;
            var responseInfo = "";
            if(response.status == "SUCCESS"){
              for(i = 0; i < messages.length; i++){
                responseInfo += messages[i] + ". ";
              }
              //$('#quantity_${product.id}').val('');
              $('#info').addClass('success');
              $('#info').html(responseInfo);
              $('#row_${orderItem.id}').remove();
              getCartInfo();
            }else {
              for(i = 0; i < messages.length; i++){
                responseInfo += messages[i] + ". ";
              }
              //$('#quantity_${product.id}').val('');
              $('#info').addClass('error');
              $('#info').html(responseInfo);
            }
            //location.reload();
          },
          error: function () {
            var responseInfo = "INPUT ERROR";
            $('#info').addClass('error');
            $('#info').html(responseInfo);
          }
        });
        event.preventDefault();
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
  </script>
</head>

<body>
<c:import url="header.jsp"/>
<h1>Cart</h1>
<c:choose>
<c:when test="${order.orderItems.size() != 0 && order != null}">
<div id="info"></div>
<div class="navigation">
  <div class="btn1"><a href="/products">Return to products</a></div>
  <div class="btn2"><a href="/order">Order</a></div>
</div>
<div class="productTable">
<table>
  <th>Model</th>
  <th>Color</th>
  <th>Display size</th>
  <th>Price</th>
  <th>Quantity</th>
  <th>Action</th>
  <c:forEach var="orderItem" items="${order.orderItems}" varStatus="number">
    <tr id="row_${orderItem.id}">
      <td>${orderItem.phone.model}</td>
      <td>${orderItem.phone.color}</td>
      <td>${orderItem.phone.displaySize}</td>
      <td>${orderItem.phone.price}</td>
      <td><input name="orderItemDTOs[${number.index}].quantity" type="text" value="${orderItem.quantity}" form="cartForm"/></td>
      <input type="hidden" name="orderItemDTOs[${number.index}].id" value="${orderItem.id}" form="cartForm"/>
      <form:form id ="orderItemForm_${orderItem.id}" action="/deleteFromCart">
        <input type="hidden" id="id_${orderItem.id}" value="${orderItem.id}"/>
        <td><input type="submit" value="Delete" form="orderItemForm_${orderItem.id}"></td>
      </form:form>
    </tr>
  </c:forEach>
</table>
</div>
<div class="navigation">
  <div class="error">${errorMessage}</div>
  <div class="btn2"><a href="/order">Order</a></div>
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
