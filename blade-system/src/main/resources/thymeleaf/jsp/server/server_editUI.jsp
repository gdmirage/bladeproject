<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>
<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${server.id }">
			<div class="layui-form-item">
			    <label class="layui-form-label">ip：</label>
			    <div class="layui-input-block"  style="width: 30%">
					<input type="text" name="ip" value="${server.ip }" required  lay-verify="required" placeholder="请输入ip" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">ssh端口：</label>
			    <div class="layui-input-block" style="width: 30%">
					<input type="text" name="sshPort" value="${server.sshPort }" required  lay-verify="required" placeholder="请输入ssh端口" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">分组名称：</label>
			    <div class="layui-input-block" style="width: 30%" layui-form>
					<select name="groupId" id="groupId" required lay-search>
						<c:forEach var="item" items="${serverGroupList }">
							<option value="${item.id }" <c:if test="${item.id eq server.groupId}">selected</c:if>>${item.groupName }</option>
						</c:forEach>
					</select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">ssh用户名：</label>
			    <div class="layui-input-block" style="width: 30%">
					<input type="text" name="sshUser" value="${server.sshUser }" required  lay-verify="required" placeholder="请输入ssh用户名" autocomplete="off" class="layui-input">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">ssh密码：</label>
			    <div class="layui-input-block" style="width: 30%">
					<input type="text" name="sshPassword" value="${server.sshPassword }" required  lay-verify="required" placeholder="请输入ssh密码" autocomplete="off" class="layui-input">
			    </div>
			</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="serverEditForm">修改</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
	$(function () {
    	$.ajax({
    		type:"post",
    		url: "${baseURL}/serverGroup/lists/",
    		datatype: "json",
    		success: function (data) {
    			$.each(data, function (index, item) {
    				$('#groupId').append(new Option(item.groupName, item.id));// 下拉菜单里添加元素
    				
    			});
    			layui.form.render("select");
    		}, 
    	});
    })
	
    form.on('submit(serverEditForm)', function(data){
    	$(".layui-btn").attr('disabled',true);  //防止重复提交 
        var url = "${baseURL}/server/edit";

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
