<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<div class="layui-form-item">
			<input type="hidden" name="id" value="${callTask.id }">
			<label class="layui-form-label">主叫座席：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select id="callUserId" name="callUserId" required lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${seatList }">
						<option value="${item.id }"
							<c:if test="${item.id eq callTask.callUserId}">selected</c:if>>${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务名称：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="name" value="${callTask.name }" required
					lay-verify="required" placeholder="请输入任务名称" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">描述：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" name="description"
					value="${callTask.description }" required lay-verify="required"
					placeholder="请输入描述" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">并发数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="number" id="lineCount" name="lineCount" required
				value="${callTask.lineCount }"
					lay-verify="required" placeholder="请输入任务并发数"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">开始时间：</label>
			<div class="layui-input-inline">
				<input type="text" name="startTime" lay-verify="datetime"
					id="startTime" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
					class="layui-input"
					value="<fmt:formatDate value="${callTask.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">结束时间：</label>
			<div class="layui-input-inline">
				<input type="text" name="endTime" lay-verify="datetime" id="endTime"
					placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
					class="layui-input"
					value="<fmt:formatDate value="${callTask.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="callTaskEditForm">修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>


<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;
	form.render();

	laydate.render({
		elem : '#startTime' //指定元素
		,
		type : 'datetime'
	});
	laydate.render({
		elem : '#endTime' //指定元素
		,
		type : 'datetime'
	});

	form.on('submit(callTaskEditForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = "${baseURL}/callTask/edit";

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
