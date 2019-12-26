<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${callTaskElement.id }">
		<div class="layui-form-item">
			<label class="layui-form-label">任务名称：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select id="callTaskId" name="callTaskId" required
					lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${callTaskList }">
						<option value="${item.id }"
							<c:if test="${item.id eq callTaskElement.callTaskId}">selected</c:if>>${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">被叫号码：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="destNum"
					value="${callTaskElement.destNum }" required lay-verify="required"
					placeholder="请输入被叫号码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否呼叫：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="isCall" name="isCall" required lay-verify="required">
					<option value="0"
						<c:if test="${callTaskElement.isCall eq '0'}">selected</c:if>>否</option>
					<option value="1"
						<c:if test="${callTaskElement.isCall eq '1'}">selected</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">描述：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="description"
					value="${callTaskElement.description }" required
					lay-verify="required" placeholder="请输入描述" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<%-- <div class="layui-form-item">
			    <label class="layui-form-label">执行状态：</label>
			    <div class="layui-input-block">
					<input type="text" name="status" value="${callTaskElement.status }" required  lay-verify="required" placeholder="请输入status" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">呼入时间：</label>
			    <div class="layui-input-block">
					<input type="text" name="startAt" value="${callTaskElement.startAt }" required  lay-verify="required" placeholder="请输入startAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">接通时间：</label>
			    <div class="layui-input-block">
					<input type="text" name="acceptAt" value="${callTaskElement.acceptAt }" required  lay-verify="required" placeholder="请输入acceptAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">挂机时间：</label>
			    <div class="layui-input-block">
					<input type="text" name="hangupAt" value="${callTaskElement.hangupAt }" required  lay-verify="required" placeholder="请输入hangupAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">创建时间：</label>
			    <div class="layui-input-block">
					<input type="text" name="createdAt" value="${callTaskElement.createdAt }" required  lay-verify="required" placeholder="请输入createdAt" autocomplete="off" class="layui-input">
			    </div>
			</div> --%>
		<div class="layui-form-item">
			<label class="layui-form-label">业务参数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="property"
					value="${callTaskElement.property }" placeholder="请输入业务参数"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit
					lay-filter="callTaskElementEditForm">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(callTaskElementEditForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = "${baseURL}/callTaskElement/edit";

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
