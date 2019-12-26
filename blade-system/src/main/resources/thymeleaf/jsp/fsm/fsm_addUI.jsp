<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">服务ip：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select name="serverId" id="serverId" required lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${serverList }">
						<option value="${item.id }">${item.ip }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">网关名：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select name="gatewayId" id="gatewayId" required lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${gatewayList }">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">启动模块：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="module" name="module" required
					lay-verify="required" placeholder="请输入启动模块" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">启动函数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="function" name="function" required
					lay-verify="required" placeholder="请输入启动函数" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">函数参数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="arguments" name="arguments" required
					lay-verify="required" placeholder="请输入启动函数参数" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="name" name="name" required
					lay-verify="required" placeholder="请输入名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">命令集：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="commands" name="commands" required
					lay-verify="required" placeholder="请输入命令集" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">Erlang Cookie：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="erlangCookie" name="erlangCookie" required
					lay-verify="required" placeholder="请输入Erlang Cookie"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">描述：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="description" name="description" required
					lay-verify="required" placeholder="请输入描述" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">呼叫类别：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="type" name="type" required lay-verify="required">
					<option value="">请选择</option>
					<option value="inbound">呼入</option>
					<option value="outbound">呼出</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">是否激活：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="enabled" name="enabled" required lay-verify="required">
					<option value="">请选择</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="fsmForm">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(fsmForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = " ${baseURL}/fsm/add";

		post(url, data.field, function(data) {
			layer.msg('添加成功', {
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
