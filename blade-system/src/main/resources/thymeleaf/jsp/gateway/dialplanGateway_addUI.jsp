<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
					<div class="layui-form-item">
			    <label class="layui-form-label">规则名称：</label>
			    <div class="layui-input-block">
					<input type="text" id="name" name="name" required  lay-verify="required" placeholder="请输入规则名称" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">本地手机：</label>
			    <div class="layui-input-block">
					<input type="text" id="localMobile" name="localMobile" required  lay-verify="required" placeholder="请输入本地手机前缀" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">外地手机：</label>
			    <div class="layui-input-block">
					<input type="text" id="nonlocalMobile" name="nonlocalMobile" required  lay-verify="required" placeholder="请输入外地手机前缀" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">本地座机：</label>
			    <div class="layui-input-block">
					<input type="text" id="localLandline" name="localLandline" required  lay-verify="required" placeholder="请输入本地座机前缀" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">外地座机：</label>
			    <div class="layui-input-block">
					<input type="text" id="nonlocalLandline" name="nonlocalLandline" required  lay-verify="required" placeholder="请输入外地座机前缀" autocomplete="off" class="layui-input">
			    </div>
			</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="dialplanGatewayForm">立即提交</button>
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
	
	
	form.on('submit(dialplanGatewayForm)', function(data){
		var url = " ${baseURL}/dialplanGateway/add";

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
