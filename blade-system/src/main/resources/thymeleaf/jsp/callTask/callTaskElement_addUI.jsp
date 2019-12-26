<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">呼叫任务：</label>
			<div class="layui-input-block" style="width: 30%" layui-form>
				<select id="callTaskId" name="callTaskId" required
					lay-verify="required" lay-search>
					<option value="">请选择</option>
					<c:forEach var="item" items="${callTaskList }">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">被叫号码：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="destNum" name="destNum" required
					lay-verify="required" placeholder="请输入被叫号码" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否呼叫：</label>
			<div class="layui-input-block" style="width: 30%">
				<select id="isCall" name="isCall" required lay-verify="required">
					<option value="">请选择</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
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
		<!-- <div class="layui-form-item">
			    <label class="layui-form-label">执行状态：</label>
			    <div class="layui-input-block">
					<input type="text" id="status" name="status" required  lay-verify="required" placeholder="请输入status" autocomplete="off" class="layui-input">
			    </div>
			</div>
			
			<div class="layui-form-item">
			    <label class="layui-form-label">开始呼入时间：</label>
			    <div class="layui-input-block">
					<input type="text" id="startAt" name="startAt" required  lay-verify="required" placeholder="请输入startAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">接通时间：</label>
			    <div class="layui-input-block">
					<input type="text" id="acceptAt" name="acceptAt" required  lay-verify="required" placeholder="请输入acceptAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">挂机时间：</label>
			    <div class="layui-input-block">
					<input type="text" id="hangupAt" name="hangupAt" required  lay-verify="required" placeholder="请输入hangupAt" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">创建时间：</label>
			    <div class="layui-input-block">
					<input type="text" id="createdAt" name="createdAt" required  lay-verify="required" placeholder="请输入createdAt" autocomplete="off" class="layui-input">
			    </div>
			</div> -->
		<div class="layui-form-item">
			<label class="layui-form-label">业务参数：</label>
			<div class="layui-input-block" style="width: 30%">
				<input type="text" id="property" name="property" placeholder="请输入业务参数" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit
					lay-filter="callTaskElementForm">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();

	form.on('submit(callTaskElementForm)', function(data) {
		$(".layui-btn").attr('disabled',true);  //防止重复提交 
		var url = " ${baseURL}/callTaskElement/add";

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
