<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../../static/taglib/taglib.jsp"%>
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
				<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
					<c:if test="${not empty menu.children}">
						<c:if test="${not empty menu.children[0]}">
							<c:if test="${not empty menu.children[0].children}">
								<c:forEach items="${menu.children[0].children}" var="menu" varStatus="i">
									<li class="layui-nav-item">
									    <a href="javascript:;">${menu.name }</a>
									    <!-- 3级菜单 -->
									    <c:if test="${not empty menu.children}">
									    	<dl class="layui-nav-child">
									    		<c:forEach items="${menu.children}" var="child" varStatus="k">
											      <dd><a href="javascript:;" data-url="menu1.html" data-id='1' data-text="后台菜单"><span class="l-line"></span>${child.name }</a></dd>
										    	</c:forEach>
										     </dl>
									    </c:if>
									 </li>
								</c:forEach>
							</c:if>
						</c:if>
					</c:if>
				</ul>
				
				<%-- 
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
				</div> --%>
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
								<li class="layui-nav-item">
								  	<a href="javascript:;" data-id='${item.id }' data-text="${item.id }">${item.name }</a></li>
									<!-- 2级菜单 -->
									<c:if test="${not empty item.children}">
										<div id="menu${item.id }" style="display: none;">
											<c:forEach items="${item.children}" var="menu" varStatus="j">
												<li class="layui-nav-item layui-nav-itemed">
												    <a href="javascript:;">${menu.name }</a>
												    <!-- 3级菜单 -->
												    <c:if test="${not empty menu.children}">
												    	<dl class="layui-nav-child">
												    		<c:forEach items="${menu.children}" var="child" varStatus="k">
														      <dd><a href="javascript:;" data-url="menu1.html" data-id='1' data-text="后台菜单"><span class="l-line"></span>${child.name }</a></dd>
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
