<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="../../../static/common/taglib/taglib.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>boss系统</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css?time=${systemTime}"/>
    <link rel="stylesheet" type="text/css" href="common/css/admin.css?time=${systemTime}"/>

    <script src="layui/layui.all.js?time=${systemTime}" type="text/javascript" charset="utf-8"></script>
    
    <style type="text/css">
    	/* .layui-side-scroll{
    		width: 200px;
    	} */
    </style>
</head>
<body>
<div class="main-layout" id='main-layout'>
    <!--侧边栏-->
    <div class="main-layout-side ">
        <div class="m-logo"></div>
        <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="leftNav" id="leftNav">
            <c:if test="${not empty menu.children}">
                <c:if test="${not empty menu.children[0]}">
                    <c:if test="${not empty menu.children[0].children}">
                        <c:forEach items="${menu.children[0].children}" var="menu" varStatus="i">
                            <li class="layui-nav-item layui-nav-itemed" data-url="${menu.menuHref }" data-id='${menu.id }'
                                data-name='${menu.name }'>
                                <a href="javascript:;">${menu.name }</a>
                                <!-- 3级菜单 -->
                                <c:if test="${not empty menu.children}">
                                    <dl class="layui-nav-child">
                                        <c:forEach items="${menu.children}" var="child" varStatus="k">
                                            <dd data-url="${child.menuHref }" data-id='${child.id }'
                                                data-name='${child.name }'><a href="javascript:;">${child.name }</a>
                                            </dd>
                                        </c:forEach>
                                    </dl>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:if>
                </c:if>
            </c:if>
        </ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container">
        <!--头部-->
        <div class="layui-header">
            <!-- <div class="menu-btn" id="hideBtn">
                <a href="javascript:;">
                    <span class="iconfont">&#xe60e;</span>
                </a>
            </div> -->
            <ul class="layui-nav" lay-filter="rightNav">
                <c:if test="${not empty menu.children}">
                <c:forEach items="${menu.children}" var="item" varStatus="i">
                <li class="layui-nav-item " data-id='${item.id }' data-text="${item.id }">
                    <a href="javascript:;">${item.name }</a></li>
                <!-- 2级菜单 -->
                <c:if test="${not empty item.children}">
                    <div id="menu${item.id }" style="display: none;">
                        <c:forEach items="${item.children}" var="menu" varStatus="j">
                            <li class="layui-nav-item layui-nav-itemed" data-url="${menu.menuHref }" data-id='${menu.id }'
                                data-name='${menu.name }'>
                                <a href="javascript:;">${menu.name }</a>
                                <!-- 3级菜单 -->
                                <c:if test="${not empty menu.children}">
                                    <dl class="layui-nav-child">
                                        <c:forEach items="${menu.children}" var="child" varStatus="k">
                                            <dd data-url="${child.menuHref }" data-id='${child.id }'
                                                data-name='${child.name }'><a href="javascript:;">${child.name }</a>
                                            </dd>
                                        </c:forEach>
                                    </dl>
                                </c:if>
                            </li>
                        </c:forEach>
                    </div>
                </c:if>
                <li class="layui-nav-item">
                    </c:forEach>
                    </c:if>
            </ul>
            <ul class="layui-nav layui-layout-right">
            	<li class="layui-nav-item" id="userInfo" url="${baseURL}/pms/operator/resetMyPwdUI"><a>修改登陆密码</a></li>
                <li class="layui-nav-item" id="logoutBut" url="${baseURL}/logout"><a>退出登录</a></li>
                <span class="layui-nav-bar" style="left: 165.891px; top: 55px; width: 0px; opacity: 0;"></span>
            </ul>
        </div>
        <!--主体内容-->
        <div class="main-layout-body">
            <!--tab 切换-->
            <div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this welcome">主页</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
                        <!--1-->
                        <iframe src="main" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe"
                                framborder="0"></iframe>
                        <!--1end-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--遮罩-->
    <div class="main-mask">

    </div>
</div>
<!-- 页面前后各应用一次layui/layui.all.js，兼容手风琴和树形菜单 -->
<script src="layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script src="common/js/core/main.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
