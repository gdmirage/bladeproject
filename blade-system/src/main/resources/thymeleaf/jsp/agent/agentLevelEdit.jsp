<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="updateLevelForm">
    	<input type="hidden" name="id" value="${agent.id }">
        <div class="layui-form-item">
            <label class="layui-form-label">会员等级：</label>
            <div class="layui-input-inline">
				<select name="levelCode">
					<option value="">请选择</option>
					<c:forEach var="item" items="${memberLevelList }">
						<option value="${item.levelCode }" <c:if test="${item.levelCode eq agent.grade}">selected</c:if>>${item.levelName }</option>
					</c:forEach>
				</select> 
            </div>
        </div>
        
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="updateLevelForm">立即提交</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form;
    form.render();
    

    form.on('submit(updateLevelForm)', function (data) {
        var url = "${baseURL}/agent/updateLevel";

        post(url, data.field, function () {
        	parent.parent.layer.msg('修改成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
        }, function (data) {
        	parent.parent.layer.msg(data.msg, {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    
</script>
