<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
	<meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>Call Center</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css?time=${systemTime}"/>
    <link rel="stylesheet" type="text/css" href="common/css/admin.css?time=${systemTime}"/>

    <script src="layui/layui.js?time=${systemTime}" type="text/javascript" charset="utf-8"></script>
  
</head>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
          
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>${pmsOperator.realName }</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="info.html">基本资料</a></dd>
              <dd><a lay-href="${baseURL}/pms/operator/resetMyPwdUI">修改密码</a></dd>
              <hr>
              <dd layadmin-event="logout" style="text-align: center;" url="${baseURL}/logout"><a>退出</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          
        </ul>
      </div>
      
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo">
            <span>Call-Center</span>
          </div>
          
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
          	<c:forEach items="${menu.children}" var="item" varStatus="i">
          		<c:if test="${i.index == 0 }">
          			<c:set var="item_class" value="layui-nav-itemed"></c:set>
          		</c:if>
          		<c:if test="${i.index != 0 }">
          			<c:set var="item_class" value=""></c:set>
          		</c:if>
          		<li data-name="home" class="layui-nav-item ${item_class }">
					<a href="javascript:;" lay-tips="${item.name }" lay-direction="2">
						<%-- <c:if test="${i.index == 0 }">
							<i class="layui-icon layui-icon-home"></i>
						</c:if>
						<c:if test="${i.index == 1 }">
							<i class="layui-icon layui-icon-app"></i>
						</c:if>
						<c:if test="${i.index == 2 }">
							<i class="layui-icon layui-icon-set"></i>
						</c:if> --%>
						<c:if test="${not empty item.remark }">
							<i class="layui-icon">&#${item.remark };</i>
						</c:if>
	                	<cite>${item.name }</cite>
	              	</a>
	              	<!-- 2级菜单 -->
	              	<c:if test="${not empty item.children}">
	              		<dl class="layui-nav-child">
	              		<c:forEach items="${item.children}" var="menu" varStatus="j">
	              			<dd data-name="${menu.id }">
		                  		<c:if test="${not empty menu.children}">
		                  			<a>
		                  				<c:if test="${not empty menu.remark }">
											<i class="layui-icon">&#${menu.remark };</i>
										</c:if>
		                  				${menu.name }
		                  			</a>
		                  			<dl class="layui-nav-child">
		                  				<c:forEach items="${menu.children}" var="child" varStatus="k">
                                            <dd data-url="${child.menuHref }" data-id='${child.id }'
                                                data-name='${child.name }'>
                                                <a href="javascript:;" lay-href="${child.menuHref }">
                                                	<c:if test="${not empty child.remark }">
														<i class="layui-icon">&#${child.remark };</i>
													</c:if>
                                               	 	${child.name }
                                                </a>
                                            </dd>
                                        </c:forEach>
		                  			</dl>
		                  		</c:if>
		                  		<c:if test="${empty menu.children}">
		                  			<a lay-href="${menu.menuHref }">${menu.name }</a>
		                  		</c:if>
		                	</dd>
	              		</c:forEach>
	              		</dl>
	              	</c:if>
	            </li>
          	</c:forEach>
          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body" style="background: white;">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="main" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
    </div>
  </div>

  <script>
	layui.config({base: 'common/layui-lib/'}).extend({index: 'index'}).use('index');
  </script>
  
</body>
</html>


