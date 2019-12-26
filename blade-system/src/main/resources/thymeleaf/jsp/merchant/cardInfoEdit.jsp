<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>



<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
     <form class="layui-form">
     	<input type="hidden" name="id" value="${cardInfo.id }" />
     	<input type="hidden" name="merchantId" value="${cardInfo.merchantId }" />
     	
     	
        <div class="layui-form-item">
            <label class="layui-form-label">会员卡：</label>
            <div class="layui-input-block">
                <input type="text" name="cardName" required lay-verify="required" placeholder="请输入会员卡名称"
                       autocomplete="off" class="layui-input" value="${cardInfo.cardName }" >
            </div>
        </div>
        
		<div class="layui-form-item">
            <label class="layui-form-label">特权说明：</label>
            <div class="layui-input-block">
                <textarea name="cardDesc" required lay-verify="required" placeholder="请输入特权说明" class="layui-textarea">${cardInfo.cardDesc }</textarea>
            </div>
        </div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
	            <label class="layui-form-label">有效期：</label>
	            <div class="layui-input-inline">
					<select name="validDateStr">
						<option value="永久有效">永久有效</option>
					</select> 
	            </div>
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">商户服务：</label>
            <div class="layui-input-block">
                <input type="text" name="cardService" required lay-verify="required" placeholder="请输入商户服务，多个服务可用空格分隔"
                       autocomplete="off" class="layui-input" value="${cardInfo.cardService }" >
            </div>
        </div>
        <div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：多个服务可用空格分隔</label>
		</div>
		
		<div class="layui-form-item">
            <label class="layui-form-label">使用须知：</label>
            <div class="layui-input-block">
                <textarea name="userdInfo" required lay-verify="required" placeholder="请输入使用须知" class="layui-textarea">${cardInfo.userdInfo }</textarea>
            </div>
        </div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="cardInfoForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	form.render();
	
	form.on('submit(cardInfoForm)', function(data){
		var url = "${baseURL}/cardInfo/edit";
		
		post(url,data.field,function(data){
			parent.parent.layer.msg('设置成功',{
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            },function(){
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
		},function(data){
			parent.parent.layer.msg(data.msg,{
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
			});
		});
	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	
</script>
