<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${gateway.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-inline">
				<input type="text" name="name" value="${gateway.name }" required
					lay-verify="required" placeholder="请输入名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">服务器IP：</label>
			<div class="layui-input-inline">
				<input type="text" name="sipServerIp"
					value="${gateway.sipServerIp }" required lay-verify="required"
					placeholder="请输入服务器IP" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">端口：</label>
			<div class="layui-input-inline">
				<input type="text" name="sipServerPort"
					value="${gateway.sipServerPort }" required lay-verify="required"
					placeholder="请输入服务器端口" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名：</label>
			<div class="layui-input-inline">
				<input type="text" name="username" value="${gateway.username }"
					required lay-verify="required" placeholder="请输入用户名"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码：</label>
			<div class="layui-input-inline">
				<input type="text" name="password" value="${gateway.password }"
					required lay-verify="required" placeholder="请输入密码"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否注册：</label>
			<div class="layui-input-inline">
				<select id="isRegister" name="isRegister" required lay-verify="required">
					<option value="0"
						<c:if test="${gateway.isRegister eq '0'}">selected</c:if>>否</option>
					<option value="1"
						<c:if test="${gateway.isRegister eq '1'}">selected</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否专线：</label>
			<div class="layui-input-inline">
				<select id="isSpecialLine" name="isSpecialLine" required lay-verify="required">
					<option value="0"
						<c:if test="${gateway.isSpecialLine eq '0'}">selected</c:if>>否</option>
					<option value="1"
						<c:if test="${gateway.isSpecialLine eq '1'}">selected</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
		    <!-- <label class="layui-input-block" style="color: red;">提示：线路共享后，别的机构将能使用线路！</label> -->
		    <label class="layui-input-block" style="color: red;">提示：专线用于预处理任务，不可以分配到分机！</label>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">呼叫类型：</label>
			<div class="layui-input-inline" >
				<select id="gatewayCallType" name="gatewayCallType" required lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="item" items="${callTypeList }">
						<option value="${item.value }" <c:if test="${gateway.gatewayCallType eq item.value}">selected</c:if>>${item.desc }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">外显号码：</label>
			<div class="layui-input-inline" >
				<select id="numberRealityId" name="numberRealityId" required lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${numberRealityList }">
						<option value="${item.id }" <c:if test="${gateway.numberRealityId eq item.id}">selected</c:if>>${item.areaCode }${item.realityNum }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">拨号规则：</label>
			<div class="layui-input-inline" >
				<select id="dialplanGatewayId" name="dialplanGatewayId" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${dialplanList }">
						<option value="${item.id }" <c:if test="${gateway.dialplanGatewayId eq item.id}">selected</c:if>>${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="gatewayEditForm">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(gatewayEditForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = "${baseURL}/gateway/edit";

		post(url, data.field, function(data) {
			layer.msg('修改成功', {
				icon : 1,
				time : 2000
			//2秒关闭（如果不配置，默认是3秒）
			}, function() {
				parent.table.reload();
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			});
		}, function(data) {
			layer.msg(data.msg, {
				icon : 2,
				time : 2000
			//2秒关闭（如果不配置，默认是3秒）
			});
		});
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
</script>
