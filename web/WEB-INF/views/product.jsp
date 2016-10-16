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
<html>
<head>
  <title>Product page</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script>
    $(document).ready(function () {
      $('#productsForm_${product.id}').submit(function (event) {
        event.preventDefault();
        var quantity = $('#quantity_${product.id}').val();
        var id = $('#id_${product.id}').val();
        var json = {"id" : id, "quantity" : quantity};
        var url = $("#productsForm_${product.id}").attr("action");
        sendJson(id, quantity, url);
      });
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

  <div class="navigation">
    <div class="btn1"><a href="/products">Return to products</a></div>
  </div>
  <div class="productTable2">
  <table>
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
    <tr>
    <form:form id = "productsForm_${product.id}" action="/addToCart">
      <input type="hidden" id="id_${product.id}" value="${product.id}"/>
      <td><input id="quantity_${product.id}" type="text" value="1"/></td>
      <td><input type="submit" value="Add to cart"></td>
    </form:form>
    </tr>
  </table>
  </div>
  <div id="info"></div>
</body>
</html>
