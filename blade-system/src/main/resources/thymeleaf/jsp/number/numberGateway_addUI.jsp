<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<div class="layui-form-item">
		    <label class="layui-form-label">分机：</label>
		    <div class="layui-input-inline">
				<select name="numberId" id="numberId" required lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="item" items="${numberList }">
						<option value="${item.id }">${item.callNum }</option>
					</c:forEach>
				</select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">线路：</label>
		    <div class="layui-input-inline">
				<select name="gatewayId" id="gatewayId" required lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="item" items="${gatewayList }">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">固定线路：</label>
		    <div class="layui-input-block">
		    	<input type="checkbox" value="1" name="isFixed" lay-skin="switch" lay-filter="switchIsFixed" lay-text="是|否">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：分机绑定固定线路后，该线路将会被锁定，别的分机无法再次绑定！</label>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="numberGatewayForm">立即提交</button>
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
	
	
	form.on('submit(numberGatewayForm)', function(data){
		var url = " ${baseURL}/numberGateway/add";

		post(url,data.field,function(data){
				layer.msg('添加成功',{
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            },function(){
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
		},function(data){
			layer.msg(data.msg,{
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
			});
		});
	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
</script>
