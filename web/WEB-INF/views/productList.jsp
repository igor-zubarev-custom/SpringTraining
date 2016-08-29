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
<html>
<head>
  <title>Product List</title>
</head>
<body>
  Phones

  <table>
    <th>No</th>
    <th>Model</th>
    <th>Color</th>
    <th>Display size</th>
    <th>Price</th>
    <c:forEach var="product" items="${productList}" varStatus="number">
      <tr>
        <td>${number.index + 1}</td>
        <td><a href="/product?id=${product.id}">${product.model}</a></td>
        <td>${product.color}</td>
        <td>${product.displaySize}</td>
        <td>${product.price}</td>
      </tr>
    </c:forEach>
  </table>

</body>
</html>
