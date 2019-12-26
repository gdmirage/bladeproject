<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">名称：</label>
			<div class="layui-input-inline">
				<input type="text" id="name" name="name" required
					lay-verify="required" placeholder="请输入名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">服务器IP：</label>
			<div class="layui-input-inline">
				<input type="text" id="sipServerIp" name="sipServerIp" required
					lay-verify="required" placeholder="请输入服务器IP" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">端口：</label>
			<div class="layui-input-inline">
				<input type="text" id="sipServerPort" name="sipServerPort" required
					lay-verify="required" placeholder="请输入服务器端口" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名：</label>
			<div class="layui-input-inline">
				<input type="text" id="username" name="username" required
					lay-verify="required" placeholder="请输入用户名" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码：</label>
			<div class="layui-input-inline">
				<input type="text" id="password" name="password" required
					lay-verify="required" placeholder="请输入密码" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否注册：</label>
			<div class="layui-input-inline" >
				<select id="isRegister" name="isRegister" required lay-verify="required">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否专线：</label>
			<div class="layui-input-inline" >
				<select id="isSpecialLine" name="isSpecialLine" required lay-verify="required">
					<option value="0">否</option>
					<option value="1">是</option>
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
						<option value="${item.value }">${item.desc }</option>
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
						<option value="${item.id }">${item.areaCode }${item.realityNum }</option>
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
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="gatewayForm">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(gatewayForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = " ${baseURL}/gateway/add";

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
