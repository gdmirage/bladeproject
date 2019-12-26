<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>
<script type="text/javascript" src="../../../static/js/libs/jquery-3.2.1.min.js"></script>
<div class="page" style="margin: 10px 10px">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>添加菜单</legend>
    </fieldset>
    <form class="layui-form" method="post">
        <input type="hidden" name="parent.id" value="${pmsMenu.parent.id}"/>
        <input type="hidden" name="parent.level" value="${pmsMenu.parent.level}"/>

        <div class="layui-form-item">
            <label class="layui-form-label">上级菜单：</label>
            <div class="layui-input-block">
                <input type="text" name="parent.name" value="${pmsMenu.parent.name}" readonly="true" autocomplete="off"
                       class="layui-input"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="name" value="${pmsMenu.name}" lay-verify="required"
                       placeholder="请输入菜单名称" autocomplete="off"
                       class="layui-input"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">菜单编号：</label>
            <div class="layui-input-inline">
                <input type="text" name="number" value="${pmsMenu.number}" lay-verify="required"
                       placeholder="菜单编号" autocomplete="off"
                       class="layui-input"/>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">请求URL</label>
                <div class="layui-input-inline">
                    <input type="text" name="url" value="${pmsMenu.url}" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="menuSumbit">立即提交</button>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form;
    form.on('submit(menuSumbit)', function (data) {
        var url = "${baseURL}/pms/menu/add";
        post(url, data.field, function () {
        	parent.parent.layer.msg('添加成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.parent.location.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.parent.layer.close(index); //再执行关闭
            });
        }, function () {
        	parent.parent.layer.msg('添加失败', {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
</script>