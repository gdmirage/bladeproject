<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}" scope="request"/>
<c:set var="systemTime" value="${System.currentTimeMillis()}" scope="request"/>

<link rel="stylesheet" type="text/css" href="${baseURL}/layui/css/layui.css?time=${systemTime}"/>
<link rel="stylesheet" type="text/css" href="${baseURL}/common/css/public.css?time=${systemTime}"/>
<link rel="stylesheet" type="text/css" href="${baseURL}/common/css/comment/iconfont.css?time=${systemTime}"/>

<script src="${baseURL}/layui/layui.all.js?time=${systemTime}" type="text/javascript" charset="utf-8"></script>
<script src="${baseURL}/common/js/core/public.js?time=${systemTime}" type="text/javascript" charset="utf-8"></script>
