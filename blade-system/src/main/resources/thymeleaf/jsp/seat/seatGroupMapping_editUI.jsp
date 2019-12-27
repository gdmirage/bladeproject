<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${userGroupMapping.id }">
		<div class="layui-form-item">
		    <label class="layui-form-label">坐席：</label>
		    <div class="layui-input-block" style="width: 30%" layui-form>
			    <select id="userId" name="userId" required  lay-verify="required" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${userList }">
							<option value="${item.id }" <c:if test="${item.id eq userGroupMapping.userId}">selected</c:if>>${item.name }</option>
						</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">坐席组：</label>
		    <div class="layui-input-block" style="width: 30%" layui-form>
		    	<select id="groupId" name="groupId" required  lay-verify="required" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${userGroupList }">
							<option value="${item.id }" <c:if test="${item.id eq userGroupMapping.groupId}">selected</c:if>>${item.name }</option>
						</c:forEach>
				</select>
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">座席类型：</label>
		    <div class="layui-input-inline">
				<select name="userType">
					<c:forEach var="item" items="${seatTypeList }">
						<option value="${item.value }" <c:if test="${item.value eq userGroupMapping.userType}">selected</c:if>>${item.desc }</option>
					</c:forEach>
				</select> 
            </div>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="userGroupMappingEditForm">修改</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
    form.on('submit(userGroupMappingEditForm)', function(data){
    	$(".layui-btn").attr('disabled',true);  //防止重复提交 
        var url = "${baseURL}/seatGroupMapping/edit";

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
