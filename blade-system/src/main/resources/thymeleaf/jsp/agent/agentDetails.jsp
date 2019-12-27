<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <table class="layui-table admin-table-info">
        <tbody>
        <tr>
            <td class="admin-table-td-info leftTitle">微信昵称：</td>
            <td >${agentDetails.nickName }</td>
        </tr>
        <tr>
            <td class="admin-table-td-info leftTitle">用户编号：</td>
            <td>${agentDetails.userNum}</td>
        </tr>
        <%--<tr>--%>
        <%--<td class="admin-table-td-info mobile leftTitle">手机：</td>--%>
        <%--<td class="mobile">${agentDetails.mobile}</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="admin-table-td-info  leftTitle">等级：</td>
            <td >${agentDetails.levelName}</td>
        </tr>
        <tr>
            <td class="admin-table-td-info  leftTitle">推荐人编号：</td>
            <td>${agentDetails.recommendUserNum}</td>
        </tr>
        <tr>
            <td class="admin-table-td-info  leftTitle">推荐人姓名：</td>
            <td>${agentDetails.recommendAgentName}</td>
        </tr>
        </tbody>
    </table>
    </fieldset>

</div>
<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form;
    form.on('submit(permissionForm)', function (data) {
        var url = "${baseURL}/pms/permission/add";

        post(url, data.field, function (data) {
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
