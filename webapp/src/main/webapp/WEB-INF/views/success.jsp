<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Success</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/script.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4"><a href="/products" class="pull-center">Thank you for your purchase, return to products</a></div>
  </div>
  <div class="row">
    <div class="col-md-4 col-md-offset-4"><div class="pull-center">Your order number is ${orderId}</div></div>
  </div>
</div>
</body>
</html>
