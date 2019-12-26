<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="operatorForm">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>提示：登录名不可修改，请确保添加信息的准确性！</legend>
        </fieldset>
        <input type="hidden" name="id" value="${pmsOperator.id }">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-block">
                <input type="text" name="realName" value="${pmsOperator.realName }" required lay-verify="required"
                       placeholder="请输入操作员姓名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">登录名：</label>
            <div class="layui-input-inline">
                <input type="text" name="loginName" value="${pmsOperator.loginName }" lay-verify="required"
                       placeholder="请输入操作员登录名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机：</label>
            <div class="layui-input-inline">
                <input type="text" name="mobileNo" value="${pmsOperator.mobileNo }" lay-verify="required"
                       placeholder="请输入操作员手机" autocomplete="off"
                       class="layui-input">
            </div>
        </div>


        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述：</label>
            <div class="layui-input-block">
                <textarea placeholder="描述" class="layui-textarea" name="remark"
                          id="remark">${pmsOperator.remark }</textarea>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>选择角色</legend>
            <div class="layui-input-block">
                <c:forEach items="${rolesList}" var="item">
                    <input id="roleId${item.id }" type="checkbox" name="selectRole" title="${item.roleName }"
                           value="${item.id}">
                </c:forEach>
            </div>
        </fieldset>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="operatorForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, laydate = layui.laydate, upload = layui.upload, layedit = layui.layedit, table = layui.table;


    $(document).ready(function () {
        var str = "${owenedRoleIds}";
        var array = new Array();
        array = str.split(",");
        console.log(array)
        for (var i = 0; i < array.length; i++) {
            $("#roleId" + array[i]).attr('checked', true);
        }
        form.render();
    })


    form.on('submit(operatorForm)', function (data) {
        var url = "${baseURL}/pms/operator/edit";
        var roleIds = "";
        $("input:checkbox[name='selectRole']:checked").each(function () { // 遍历name=test的多选框
            roleIds += $(this).val() + ",";  // 每一个被选中项的值
        });
        data.field.selectVal = roleIds.toString();
        post(url, data.field, function () {
        	parent.parent.layer.msg('添加成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
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
