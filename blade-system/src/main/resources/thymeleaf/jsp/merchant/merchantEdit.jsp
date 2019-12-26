<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/taglib/taglib.jsp"%>

<style>
    .layui-upload-img {
        width: 120px;
        height: 120px;
        margin: 0px; 
    }
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${merchant.id }" />
		<div class="layui-form-item">
            <label class="layui-form-label">商家logo</label>
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic" src="${merchant.headImg }">
                    <p id="demoText"></p>
                    <input type="hidden" name=headImg id="headImg" value="${merchant.headImg }"/>
                </div>
            </div>
        </div>
        
        <div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：图片大小建议48*48（px）</label>
		</div>
        
		<div class="layui-form-item">
		    <label class="layui-form-label">商家名称：</label>
		    <div class="layui-input-block">
				<input type="text" name="merchantName" value="${merchant.merchantName }" required  lay-verify="required" placeholder="请输入商家名称" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
            <label class="layui-form-label">商家描述：</label>
            <div class="layui-input-block">
                <textarea name="merchantDesc" required lay-verify="required" placeholder="请输入商家描述" class="layui-textarea">${merchant.merchantDesc }</textarea>
            </div>
        </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">联系电话：</label>
		    <div class="layui-input-block">
				<input type="text" name="mobile" value="${merchant.mobile }" required  lay-verify="required" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">详细地址：</label>
		    <div class="layui-input-block">
				<input type="text" name="address" value="${merchant.address }" required  lay-verify="required" placeholder="请输入详细地址" autocomplete="off" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">客服微信：</label>
		    <div class="layui-input-block">
				<input type="text" name="wechat" value="${merchant.wechat }" required  lay-verify="required" placeholder="请输入客服微信" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：置顶图片推荐大小640*480</label>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="merchantEditForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var form = layui.form;
	var upload = layui.upload;
	form.render();
	
	
	form.on('submit(merchantEditForm)', function(data){
       var url = "${baseURL}/merchant/edit";
       console.log(data.field);
       post(url,data.field,function(data){
    	   parent.parent.layer.msg('修改成功',{
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
	
	//普通图片上传
    var uploadInst = upload.render({
        elem: '#pic'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#pic').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#pic').attr('src', res.data);
            $('#headImg').val(res.data);
        }
        , error: function () {
            //失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
	
</script>
