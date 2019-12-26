<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="parameterForm">
        <input type="hidden" value="${parameter.id}" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">参数编码：</label>
            <div class="layui-input-block">
                <input type="text" name="paramCode" value="${parameter.paramCode}" required lay-verify="required"
                       placeholder="请输入参数编码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">参数名称：</label>
            <div class="layui-input-block">
                <input type="text" name="paramName" value="${parameter.paramName}" lay-verify="required"
                       placeholder="请输入参数名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">参数值</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入参数值" class="layui-textarea" name="paramValue"
                          id="paramValue">${parameter.paramValue}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="parameterForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, laydate = layui.laydate, layedit = layui.layedit, table = layui.table;
    form.render();


    form.on('submit(parameterForm)', function (data) {
        var url = "${baseURL}/parameter/update";
        post(url, data.field, function () {
        	parent.parent.layer.msg('添加成功', {
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
