<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 06.09.2016
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
  <jsp:useBean id="orderService" scope="page" class="home.zubarev.service.CartService">
    <jsp:setProperty name="orderService" property="cart" value="${cart}"/>
  </jsp:useBean>

  <div>
    <div class="phone_img">
      <img src="/resources/img/phone.png">
    </div>
    <a href="/cart">
    <div class="cartMini">
     My cart: ${orderService.allItems} items, ${orderService.orderPrice}$
    </div>
    </a>
  </div>
  <div style="clear: both">
    <hr>
  </div>
</html>
