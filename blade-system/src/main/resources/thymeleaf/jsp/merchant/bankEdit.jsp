<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../common/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="updateBankForm">
    	<input type="hidden" name="id" value="${merchant.id }">
        <div class="layui-form-item">
            <label class="layui-form-label">银行名称：</label>
            <div class="layui-input-inline">
				<input type="text" name="bankName" required lay-verify="required" placeholder="请输入银行名称"
                       autocomplete="off" class="layui-input" value="${merchant.bankName }" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">支行名称：</label>
            <div class="layui-input-inline">
				<input type="text" name="bankSubName" required lay-verify="required" placeholder="请输入支行名称"
                       autocomplete="off" class="layui-input" value="${merchant.bankSubName }" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开户人：</label>
            <div class="layui-input-inline">
				<input type="text" name="bankUser" required lay-verify="required" placeholder="请输入开户人"
                       autocomplete="off" class="layui-input" value="${merchant.bankUser }" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">银行卡号：</label>
            <div class="layui-input-inline">
				<input type="text" name="bankNo" required lay-verify="required" placeholder="请输入银行卡号"
                       autocomplete="off" class="layui-input" value="${merchant.bankNo }" >
            </div>
        </div>
        
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="updateBankForm">立即提交</button>
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
    

    form.on('submit(updateBankForm)', function (data) {
        var url = "${baseURL}/merchant/editBank";

        post(url, data.field, function () {
        	parent.parent.layer.msg('设置成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
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
