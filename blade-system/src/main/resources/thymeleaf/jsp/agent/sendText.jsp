<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="updateLevelForm">
    	<input type="hidden" name="idArr" value="${idArr }">
    	
        <div class="layui-form-item">
            <textarea name="msg" style="height: 300px" required lay-verify="required" placeholder="请输入文字消息" class="layui-textarea"></textarea>
        </div>
        
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit lay-filter="updateLevelForm">发送</button>
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
        var url = "${baseURL}/agent/sendText";

        post(url, data.field, function () {
        	parent.parent.layer.msg('发送成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                var index = parent.parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.parent.layer.close(index); //再执行关闭
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
