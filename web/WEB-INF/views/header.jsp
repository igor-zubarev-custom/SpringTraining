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
<style type="text/css">
  .cart{
    float:right;
    padding: 10px;
    padding-left: 15px;
    margin: 10px;
    border: 1px solid gray;
    border-radius: 10px;
    background: #E6DEEA;
  }
  .phone_img{
    float: left;
  }
</style>
  <jsp:useBean id="orderService" scope="page" class="home.zubarev.service.OrderService">
    <jsp:setProperty name="orderService" property="order" value="${order}"/>
  </jsp:useBean>

  <div>
    <div class="phone_img">
      <img src="/resources/img/phone.png">
    </div>
    <div class="cart">
     My cart: ${orderService.allItems} items, ${orderService.orderPrice}$
    </div>
  </div>
  <div style="clear: both">
    <hr>
  </div>
</html>
