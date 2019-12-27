<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../static/common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${memberLevel.id }">
		<div class="layui-form-item">
		    <label class="layui-form-label">等级名称：</label>
		    <div class="layui-input-block">
				<input type="text" name="levelName" required value="${memberLevel.levelName }" lay-verify="required" placeholder="请输入等级名称" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">等级Code：</label>
		    <div class="layui-input-block">
				<input type="text" name="levelCode" required value="${memberLevel.levelCode }" disabled="disabled" lay-verify="required" placeholder="请输入等级Code" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：等级Code添加后将不可修改，请确保添加信息的准确性！</label>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">等级权重：</label>
		    <div class="layui-input-block">
				<input type="text" name="requireAmount" required value="${memberLevel.requireAmount }" lay-verify="required" placeholder="请输入等级权重" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：等级权重越高，代表等级越高！</label>
		</div>
		
		<%-- <div class="layui-form-item">
		    <label class="layui-form-label">分销权：</label>
		    <div class="layui-input-block">
				<select name="levelType">
				  <option value="LEVEL_01" <c:if test="${memberLevel.levelType eq 'LEVEL_01'}">selected</c:if>>一级分销权</option>
				  <option value="LEVEL_02" <c:if test="${memberLevel.levelType eq 'LEVEL_02'}">selected</c:if>>二级分销权</option>
				  <option value="LEVEL_03" <c:if test="${memberLevel.levelType eq 'LEVEL_03'}">selected</c:if>>三级分销权</option>
				</select> 
		    </div>
		</div> --%>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">对外展示：</label>
		    <div class="layui-input-block">
				<input type="checkbox" <c:if test="${memberLevel.isShow eq 'YES'}">checked=""</c:if>  value="YES" name="isShow" lay-skin="switch" lay-filter="switchTest" lay-text="是|否">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">升级链接：</label>
		    <div class="layui-input-block">
				<input type="text" name="upUrl" value="${memberLevel.upUrl }" placeholder="请输入升级链接" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="memberLevelEditForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	form.on('submit(memberLevelEditForm)', function(data){
       var url = "${baseURL}/memberLevel/edit";
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
