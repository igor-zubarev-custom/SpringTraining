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
<body>
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
        <form:form action="addToCart" method="post" modelAttribute="orderItem">
          <form:hidden path="phoneId" value =/>
          <td><form:input path="quantity"/></td>
          <td><input type="submit" value="Add to cart"></td>
        </form:form>
      </tr>
    </c:forEach>
  </table>

</body>
</html>
