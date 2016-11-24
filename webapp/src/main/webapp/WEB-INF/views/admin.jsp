<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Admin</title>
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
  <h3>Order list</h3>
</div>
<div class="container">
  <c:forEach var="order" items="${orders}">
    <table class="table">
      <tr>
        <td>Order ID</td><td>${order.id}</td>
      </tr>
      <tr>
        <td>Order STATUS</td><td id="status_${order.id}">${order.status}</td>
      </tr>
      <tr>
        <td>Order Info</td>
        <td>
          <table>
            <col width="130">
            <tr>
              <td>Cart price: </td><td>${order.cartPrice}</td>
            </tr>
            <tr>
              <td>Delivery price: </td><td>${order.deliveryPrice}</td>
            </tr>
            <tr>
              <td>Total price: </td><td>${order.totalPrice}</td>
            </tr>
            <tr>
              <td>Total quantity: </td><td>${order.totalQuantity}</td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>Delivery Info</td>
        <td>
          <table>
            <col width="130">
            <tr>
              <td>First name: </td><td>${order.firstName}</td>
            </tr>
            <tr>
              <td>Last name: </td><td>${order.lastName}</td>
            </tr>
            <tr>
              <td>Delivery address: </td><td>${order.deliveryAddress}</td>
            </tr>
            <tr>
              <td>Contact phone: </td><td>${order.contactPhone}</td>
            </tr>
            <tr>
              <td>Comments: </td><td>${order.comment}</td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>Order items</td>
        <td>
          <table>
            <col width="130">
            <c:forEach var="orderItem" items="${order.cartItems}">
              <tr>
                <td>${orderItem.phone.model}  </td>
                <td>${orderItem.quantity}</td>
              </tr>
            </c:forEach>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2"><button id="${order.id}" class="btn btn-warning" onclick="SpringTraining.Admin.sendChangeStatusJson(event, this)">Change order status</button></td>
      </tr>
    </table>
    <hr>
    <br>
  </c:forEach>
</div>
</body>
</html>
