<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../static/common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${lecturer.id }">
		<div class="layui-form-item">
		    <label class="layui-form-label">讲师姓名：</label>
		    <div class="layui-input-block">
				<input type="text" name="lecturerName" required value="${lecturer.lecturerName }" lay-verify="required" placeholder="请输入讲师姓名" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">性别：</label>
		    <div class="layui-input-block">
				<select name="sex">
					<option value="">请选择</option>
					<option value="MALE" <c:if test="${'MALE' eq lecturer.sex}">selected</c:if>>男</option>
					<option value="FEMALE" <c:if test="${'FEMALE' eq lecturer.sex}">selected</c:if>>女</option>
				</select> 
		    </div>
		</div>
		
		<div class="layui-form-item">
        	<div class="layui-inline">
			    <label class="layui-form-label">名师：</label>
			    <div class="layui-input-block">
					<input type="checkbox" <c:if test="${lecturer.isTop eq 'YES'}">checked=""</c:if>  value="YES" name="isTop" lay-skin="switch" lay-filter="switchIsTop" lay-text="是|否">
			    </div>
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="lecturerEditForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	form.on('submit(lecturerEditForm)', function(data){
       var url = "${baseURL}/lecturer/edit";
       console.log(data.field);
       post(url,data.field,function(data){
    	   parent.parent.layer.msg('修改成功',{
               icon: 1,
               time: 2000 //2秒关闭（如果不配置，默认是3秒）
           },function(){
               parent.table.reload();
               var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
               parent.layer.close(index); //再执行关闭
           });
       	},function(data){
       		parent.parent.layer.msg(data.msg,{
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
			});
		});
	  	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
</script>
