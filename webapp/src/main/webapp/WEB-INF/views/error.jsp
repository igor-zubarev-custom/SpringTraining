<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24.10.2016
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Error</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="error-template">
        <h1>
          Oops!</h1>
        <h2>
          Something not wrong</h2>
        <div class="error-details">
          Sorry, an error has occured, please request page later!
        </div>
        <div class="error-actions">
          <a href="/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
            Take Me Home </a><a href="/products" class="btn btn-default btn-lg">Return to products</a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
