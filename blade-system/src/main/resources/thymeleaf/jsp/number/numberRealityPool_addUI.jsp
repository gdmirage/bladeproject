<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<div class="layui-form-item">
		    <label class="layui-form-label">区号：</label>
		    <div class="layui-input-inline">
				<input type="text" id="areaCode" name="areaCode" required  lay-verify="required" placeholder="请输入区号" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">外显号码：</label>
		    <div class="layui-input-inline">
				<input type="text" id="realityNum" name="realityNum" required  lay-verify="required" placeholder="请输入外显号码" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">运营商：</label>
		    <div class="layui-input-inline">
				<select name="telecomOperatorId" id="telecomOperatorId" required lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="item" items="${telecomOperatorList }">
						<option value="${item.id }">${item.telecomOperatorName }</option>
					</c:forEach>
				</select>
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="numberRealityPoolForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
	
	form.on('submit(numberRealityPoolForm)', function(data){
		var url = " ${baseURL}/numberRealityPool/add";

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
