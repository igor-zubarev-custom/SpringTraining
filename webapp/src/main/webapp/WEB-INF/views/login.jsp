<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Product List</title>
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
    <div class="col-md-4 col-md-offset-4">
      <div id="mainWrapper">
        <div class="login-container">
          <div class="login-card">
            <div class="login-form">
              <c:url var="loginUrl" value="/login" />
              <form action="/login" method="post" class="form-horizontal">
                <c:if test="${param.error != null}">
                  <div class="alert alert-danger">
                    <p>Invalid username or password.</p>
                  </div>
                </c:if>
                <c:if test="${param.logout != null}">
                  <div class="alert alert-success">
                    <p>You have been logged out successfully.</p>
                  </div>
                </c:if>
                <div class="input-group input-sm">
                  <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                  <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                </div>
                <div class="input-group input-sm">
                  <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                </div>
                <div class="input-group input-sm">
                  <div class="checkbox">
                    <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                  </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                <div class="form-actions">
                  <input type="submit"
                         class="btn btn-block btn-primary btn-default" value="Log in">
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
