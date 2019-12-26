<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../common/taglib/taglib.jsp"%>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit">
  		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>网站后台管理模版</title>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="common/css/admin.css"/>
		
		<script src="layui/layui.all.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="main-layout" id='main-layout'>
			<!--侧边栏-->
			<div class="main-layout-side">
				<div class="m-logo"></div>
				
				<div class="layui-collapse" lay-accordion>
					<c:if test="${not empty menu.children}">
						<c:forEach items="${menu.children}" var="item" varStatus="i">
							<div class="layui-colla-item">
							    <h2 class="layui-colla-title">${item.name }</h2>
							    <div class="layui-colla-content">
							    	<ul id="menu${item.id }"></ul>
							    	<script>
										layui.use(['tree', 'layer'], function() {
											var jsonstr = '${item.childrenJson}';
											var json = JSON.parse(jsonstr);
											var layer = layui.layer,$ = layui.jquery; 
											layui.tree({
											    elem: '#menu${item.id}' //指定元素
											   // ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
											    //,skin: 'shihuang'
											    ,click: function(item){ //点击节点回调
											    	var $ = layui.jquery;
											    	var menu = $('#menu' + item.id);
												    console.log(item);
												    openMenu(item);
											    }
											    ,nodes: json
											  });
										});
									</script>
							    </div>
							 </div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<!--右侧内容-->
			<div class="main-layout-container">
				<!--头部-->
				<div class="main-layout-header">
					<div class="menu-btn" id="hideBtn">
						<a href="javascript:;">
							<span class="iconfont">&#xe60e;</span>
						</a>
					</div>
					<span class="layui-breadcrumb mbx" id="mbx">
					  <a href="javascript:void(0);">首页</a>
					  <a href="javascript:void(0);">国际新闻</a>
					  <a href="javascript:void(0);">亚太地区</a>
					  <a><cite>正文</cite></a>
					</span>
					<ul class="layui-nav" lay-filter="rightNav">
					  <li class="layui-nav-item"><a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息">超级管理员</a>
					  </li>
					  <li class="layui-nav-item"><a href="javascript:;">退出</a></li>
					</ul>
				</div>
				<!--主体内容-->
				<div class="main-layout-body">
					<!--tab 切换-->
					<div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
					  <ul class="layui-tab-title">
					    <li class="layui-this welcome">后台主页</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
					    	<!--1-->
					    	<iframe src="main" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
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
