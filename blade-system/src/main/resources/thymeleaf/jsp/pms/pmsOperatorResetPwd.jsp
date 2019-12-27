<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
	<form class="layui-form" method="post" id="operatorForm">
		<input type="hidden" name="id" value="${operator.id}" />
		<div class="layui-form-item">
			<label class="layui-form-label">登录账号：</label>
			<div class="layui-input-block">
				<input type="text" name="realName" value="${operator.loginName }" required lay-verify="required"
					   autocomplete="off" class="layui-input" readonly="readonly">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">旧密码：</label>
			<div class="layui-input-inline">
				<input type="password" name="password" id="password" lay-verify="required"
					   placeholder="请输入新密码" autocomplete="off"
					   class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">新密码：</label>
			<div class="layui-input-inline">
				<input type="password" name="newPwd" id="newPwd" lay-verify="required"
					   placeholder="请输入新密码" autocomplete="off"
					   class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">确认密码：</label>
			<div class="layui-input-inline">
				<input type="password" name="newPwd2" id="newPwd2" lay-verify="required"
					   placeholder="请输入新密码" autocomplete="off"
					   class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="operatorForm">立即提交</button>
			</div>
		</div>
	</form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, laydate = layui.laydate, upload = layui.upload, layedit = layui.layedit, table = layui.table;

    form.on('submit(operatorForm)', function (data) {
        var url = "${baseURL}/pms/operator/resetPwd";
        post(url, data.field, function () {
            layer.msg('修改成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
            	$('#password').val('');
            	$('#newPwd').val('');
            	$('#newPwd2').val('');
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
        }, function (data) {
            layer.msg(data.msg, {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

</script>
