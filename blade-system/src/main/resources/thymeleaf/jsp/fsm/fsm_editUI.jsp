<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${fsm.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">服务ip：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select name="serverId" id="serverId" required lay-verify="required" lay-search>
					<c:forEach var="item" items="${serverList }">
						<option value="${item.id }"
							<c:if test="${item.id eq fsm.serverId}">selected</c:if>>${item.ip }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">网关名：</label>
			<div class="layui-input-block" style="width: 30%" >
				<select name="gatewayId" id="gatewayId" required lay-verify="required" >
					<c:forEach var="item" items="${gatewayList }">
						<option value="${item.id}" <c:if test="${item.id eq fsm.gatewayId}">selected</c:if>>${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">启动模块：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="module" value="${fsm.module }" required
					lay-verify="required" placeholder="请输入启动模块" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">启动函数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="function" value="${fsm.function }" required
					lay-verify="required" placeholder="请输入启动函数" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">函数参数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="arguments" value="${fsm.arguments }"
					required lay-verify="required" placeholder="请输入启动函数参数"
					autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="name" value="${fsm.name }" required
					lay-verify="required" placeholder="请输入名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">命令集：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="commands" value="${fsm.commands }" required
					lay-verify="required" placeholder="请输入命令集" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">Erlang Cookie：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="erlangCookie" value="${fsm.erlangCookie }"
					required lay-verify="required" placeholder="请输入Erlang Cookie"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">描述：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="description" value="${fsm.description }"
					required lay-verify="required" placeholder="请输入描述"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">呼叫类别：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="type" name="type" required lay-verify="required">
					<option value="inbound"
						<c:if test="${fsm.type eq 'inbound'}">selected</c:if>>呼入</option>
					<option value="outbound"
						<c:if test="${fsm.type eq 'outbound'}">selected</c:if>>呼出</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">是否激活：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="enabled" name="enabled" required lay-verify="required">
					<option value="0"
						<c:if test="${fsm.enabled eq '0'}">selected</c:if>>否</option>
					<option value="1"
						<c:if test="${fsm.enabled eq '1'}">selected</c:if>>是</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="fsmEditForm">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(fsmEditForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = "${baseURL}/fsm/edit";
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
