<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${linePoolElement.id }">
			<div class="layui-form-item">
			    <label class="layui-form-label">名称：</label>
			    <div class="layui-input-block" style="width: 30%">
					<input type="text" name="name" value="${linePoolElement.name }" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">线路池名：</label>
			     <div class="layui-input-block" style="width: 30%" layui-form>
					<select id="linePoolId" name="linePoolId" required  lay-verify="required" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${linePoolList }">
							<option value="${item.id }" <c:if test="${item.id eq linePoolElement.linePoolId}">selected</c:if>>${item.name }</option>
						</c:forEach>
					</select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">状态机名：</label>
			    <div class="layui-input-block" style="width: 30%" layui-form>
					<select id="fsmId" name="fsmId" required  lay-verify="required" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${fsmList }">
							<option value="${item.id }" <c:if test="${item.id eq linePoolElement.fsmId}">selected</c:if>>${item.name }</option>
						</c:forEach>
					</select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">是否可用：</label>
			    <div class="layui-input-block" style="width: 30%">
					<select id="enabled" name="enabled" required  lay-verify="required">
						<option value="0" <c:if test="${linePoolElement.enabled eq '0'}">selected</c:if>>否</option>
						<option value="1" <c:if test="${linePoolElement.enabled eq '1'}">selected</c:if>>是</option>
					</select>
			    </div>
			</div>
			
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="linePoolElementEditForm">修改</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
	
    form.on('submit(linePoolElementEditForm)', function(data){
    	$(".layui-btn").attr('disabled',true);  //防止重复提交 
        var url = "${baseURL}/linePoolElement/edit";

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
