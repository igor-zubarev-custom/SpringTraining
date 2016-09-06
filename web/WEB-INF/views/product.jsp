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
<html>
<head>
  <title>Product page</title>
</head>
<body>
  <c:import url="header.jsp"/>
  <a href="/products">Return to product list</a><br>
  <b>${product.model}</b>
  <table>
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
  </table>

</body>
</html>
