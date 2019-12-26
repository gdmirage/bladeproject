<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${productType.id }">
		
		<div class="layui-form-item">
		    <label class="layui-form-label">一级分类：</label>
		    <div class="layui-input-block">
				<select name="typeOneId">
					<c:forEach var="item" items="${list }">
						<option value="${item.id }" <c:if test="${item.id eq productType.typeOneId}">selected</c:if> >${item.typeName }</option>
					</c:forEach>
				</select> 
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">分类名称：</label>
		    <div class="layui-input-block">
				<input type="text" name="typeName" value="${productType.typeName }" required  lay-verify="required" placeholder="请输入分类名称" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="productTypeEditForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
	form.on('submit(productTypeEditForm)', function(data){
       var url = "${baseURL}/productType/edit";
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
