<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${fsmStackConfig.id }">
			<div class="layui-form-item">
			    <label class="layui-form-label">Key：</label>
			    <div class="layui-input-inline">
					<input type="text" name="configKey" value="${fsmStackConfig.configKey }" required  lay-verify="required" placeholder="请输入Key" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">Value：</label>
			    <div class="layui-input-inline">
					<input type="text" name="configValue" value="${fsmStackConfig.configValue }" required  lay-verify="required" placeholder="请输入Value" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">电话局：</label>
				<div class="layui-input-block" style="width: 40%" >
					<select id="lanId" name="lanId" required lay-verify="required">
						<option value="">请选择</option>
						<c:forEach var="item" items="${authList }">
							<option value="${item.id }" <c:if test="${fsmStackConfig.lanId eq item.id}">selected</c:if>>${item.userName }--${item.code }(${item.ip }:${item.port })</option>
						</c:forEach>
					</select>
				</div>
			</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="fsmStackConfigEditForm">修改</button>
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
	
	
    form.on('submit(fsmStackConfigEditForm)', function(data){
        var url = "${baseURL}/fsmStackConfig/edit";

        post(url,data.field,function(data){
            layer.msg('修改成功',{
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
