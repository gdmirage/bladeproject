<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../common/taglib/taglib.jsp" %>
<style>
.layui-form-select dl{
	z-index: 99999 !important;
}
</style>
<script type="text/javascript" charset="utf-8" src="${baseURL}/ueditor/ueditor.config.js?time=${systemTime}"></script>
<script type="text/javascript" charset="utf-8" src="${baseURL}/ueditor/ueditor.all.js?time=${systemTime}"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${baseURL}/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" type="text/css" href="http://xiumi.us/connect/ue/v5/xiumi-ue-v5.css"/>
<script type="text/javascript">
UE.registerUI('dialog', function (editor, uiName) {
    var btn = new UE.ui.Button({
        name   : 'xiumi-connect',
        title  : '秀米',
        onclick: function () {
            var dialog = new UE.ui.Dialog({
                iframeUrl: '${baseURL}/xiumi-ue-dialog-v5.html',
                editor   : editor,
                name     : 'xiumi-connect',
                title    : "秀米图文消息助手",
                cssRules : "width: " + (window.innerWidth - 60) + "px;" + "height: " + (window.innerHeight - 60) + "px;",
            });
            dialog.render();
            dialog.open();
        }
    });

    return btn;
});
</script>
<style>
    .layui-upload-img {
        width: 210px;
        height: 120px;
        margin: 0px 10px 10px 110px;
    }
</style>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="productForm">
    	<input type="hidden" name="id" value="${article.id }">
    	<div class="layui-form-item">
    		<div class="layui-row">
    			<div class="layui-col-md4">
    				<div class="layui-upload">
		                <button type="button" class="layui-btn layui-btn-primary" id="mainPathPictrue" style="width: 280px;;margin-left: 110px;">
		                	<i class="layui-icon">&#xe67c;</i>设置活动主图
		                </button>
		                <div class="layui-upload-list">
		                    <img class="layui-upload-img" id="mainPathPic"  src="${article.mainPath }" style="width: 280px;height: 210px">
		                    <p id="mainPathErrorText"></p>
		                    <input type="hidden" name="mainPath" id="mainPath"  value="${article.mainPath }" />
		                </div>
		            </div>
		            <div class="layui-form-item">
					    <label class="layui-input-block" style="color: red;">提示：文章主图大小建议640*480</label>
					</div>
				</div>
				<div class="layui-col-md4">
		            <div class="layui-upload">
		                <button type="button" class="layui-btn layui-btn-primary" id="pictrue" style="width: 210px;margin-left: 110px;">
		                	<i class="layui-icon">&#xe67c;</i>设置分享图片
		                </button>
		                <div class="layui-upload-list">
		                    <img class="layui-upload-img" id="pic" style="width: 210px;height: 210px"  src="${article.picPath }">
		                    <p id="demoText"></p>
		                    <input type="hidden" name="picPath" id="picPath"  value="${article.picPath }"/>
		                </div>
		            </div>
		            <div class="layui-form-item">
					    <label class="layui-input-block" style="color: red;">提示：置顶图片大小建议840*480</label>
					</div>
	            </div>
			</div>
        </div>
    	
        
        <div class="layui-form-item">
            <label class="layui-form-label">标题：</label>
            <div class="layui-input-block">
                <input type="text" name="articleTitle" required lay-verify="required" placeholder="请输入标题"
                       autocomplete="off" class="layui-input" value="${article.articleTitle }" >
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">简要描述：</label>
            <div class="layui-input-block">
                <input type="text" name="articleSubtitle" required lay-verify="required" placeholder="请输入简要描述"
                       autocomplete="off" class="layui-input" value="${article.articleSubtitle }" >
            </div>
        </div>
		
		<div class="layui-form-item ">
        	<div class="layui-inline">
	            <label class="layui-form-label">支付金额：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="costPrice"  placeholder="请输入支付金额" autocomplete="off"
	                       class="layui-input">
	            </div>
            </div>
            <div class="layui-inline isSell">
	            <label class="layui-form-label">获得积分：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="onePrice"  placeholder="请输入获得积分" autocomplete="off"
	                       class="layui-input">
	            </div>
            </div>
        </div>
        
        <div class="layui-form-item">
			    <label class="layui-form-label">活动内容：</label>
	            <div class="layui-input-block">
	                <script id="freeEditor" type="text/plain" style="width:480px;height: 500px">${article.free}</script>
	            </div>
		</div>
        
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, upload = layui.upload, layedit = layui.layedit, table = layui.table;
    form.render();
    
    var freeUe = UE.getEditor('freeEditor');

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#pictrue'
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
            $('#picPath').val(res.data);
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
    
  //文章主图上传
    var mainPathUploadInst = upload.render({
        elem: '#mainPathPictrue'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#mainPathPic').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#mainPathPic').attr('src', res.data);
            $('#mainPath').val(res.data);
        }
        , error: function () {
            //失败状态，并实现重传
            var mainPathErrorText = $('#mainPathErrorText');
            mainPathErrorText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            mainPathErrorText.find('.demo-reload').on('click', function () {
            	mainPathUploadInst.upload();
            });
        }
    });
    
</script>
