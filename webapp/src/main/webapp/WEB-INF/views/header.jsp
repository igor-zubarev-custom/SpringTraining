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
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <a href="/products"><img class="brand-img img-responsive" src="/resources/img/phone.png"></a>
      </div>
      <div class="col-sm-8">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="/products" class="btn btn-info">Products</a></li>
          <li><a href="/cart" class="cartMini btn btn-success"></a></li>
        </ul>
      </div>
    </div>
  </div>
  <hr>
</html>
