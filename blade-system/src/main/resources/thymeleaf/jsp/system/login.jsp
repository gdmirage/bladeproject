<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="${baseURL }/common/taglib/taglib.jsp" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>boss系统</title>
<link rel="stylesheet" type="text/css" href="${baseURL }/common/css/index.css?time=${systemTime}" />
<script type="text/javascript" language="javascript" src="${baseURL }/common/js/login/login.js?time=${systemTime}"></script>
<script type="text/javascript" language="javascript" src="${baseURL}/common/js/libs/jquery-3.2.1.min.js?time=${systemTime}"></script>
</head>
<body>
	<header>
		<div class="content"><img src="${baseURL }/common/image/login/logo.png"/></div>
	</header>
	<form id="form1" action="${baseURL }/login" method="post">
		
		<ul class="logindiv">
			<li>
				<div style="color: red; padding-left: 80px; padding-bottom: 10px;">${message}</div>
			</li>
			<li class="username">
				<span id="tip1" class="icontip"></span>
				<input type="text" name="loginName" id="userName" placeholder="账号"/>
			</li>
			<li class="password"> 
				<span id="tip2" class="icontip"></span>
				<input type="password" name="roncooPwd" id="password" placeholder="密码"/>
			</li>
			<li class="verificationCode">
				<input class="codevalue" type="text" name="captchaCode"  id="regcode" placeholder="输入验证码"/>
				<img class="rcCaptcha-btn rcCaptcha-img" src="${baseURL}/rcCaptcha.jpg" alt="点击更换验证码" title="点击更换验证码" width="65" height="24" style="margin: 6px 20px;" />
			</li>
			<li>
				<input class="login" type="submit" value="登&nbsp;&nbsp;录"></input>
			    <!-- <span class="login"  onclick="login()">登&nbsp;&nbsp;录</span> -->
			    <!-- <span class="forgetpassword" onclick="passwordRecover()">忘记密码？</span> -->
			</li>
		</ul>
	</form>
	
	<script>
		$(function() {
			$(".rcCaptcha-btn").click(function() {
				$(".rcCaptcha-img").attr("src",'${baseURL}/rcCaptcha.jpg?'+ new Date().getTime());
			});
		});
		
	</script>
</body>
</html>
