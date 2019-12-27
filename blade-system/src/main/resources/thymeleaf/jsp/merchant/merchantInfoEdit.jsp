<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>

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
        width: 120px;
        height: 120px;
        margin: 0px; 
    }
     .layui-upload-img-desc{
        width: 180px;
        height: 120px;
    }
    #pictrue1,#pictrue2,#pictrue3,#pictrue4,#pictrue5{
    	width: 180px;
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
            <label class="layui-form-label">置顶图片：</label>
            <div class="layui-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="pictrue1">
                   		<i class="layui-icon">&#xe67c;</i>描述图1
                    </button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img-desc" id="productPic1" src="${merchant.pic1 }">
                        <p id="demoText1"></p>
                        <input type="hidden" name="pic1" id="pic1" value="${merchant.pic1 }"/>
                    </div>
                </div>
            </div>
            <!-- 产品描述图 -->
            <div class="layui-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="pictrue2">
                   		<i class="layui-icon">&#xe67c;</i>描述图2
                    </button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img-desc" id="productPic2" src="${merchant.pic2 }">
                        <p id="demoText2"></p>
                        <input type="hidden" name="pic2" id="pic2" value="${merchant.pic2 }"/>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="pictrue3">
                   		<i class="layui-icon">&#xe67c;</i>描述图3
                    </button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img-desc" id="productPic3" src="${merchant.pic3 }">
                        <p id="demoText3"></p>
                        <input type="hidden" name="pic3" id="pic3" value="${merchant.pic3 }" />
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="pictrue4">
                   		<i class="layui-icon">&#xe67c;</i>描述图4
                    </button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img-desc" id="productPic4" src="${merchant.pic4 }" >
                        <p id="demoText4"></p>
                        <input type="hidden" name="pic4" id="pic4" value="${merchant.pic4 }" />
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-primary" id="pictrue5">
                  		<i class="layui-icon">&#xe67c;</i>描述图5
                    </button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img-desc" id="productPic5" src="${merchant.pic5 }" >
                        <p id="demoText5"></p>
                        <input type="hidden" name="pic5" id="pic5" value="${merchant.pic5 }" />
                    </div>
                </div>
            </div>
        </div>
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：置顶图片推荐大小640*480</label>
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">商家介绍：</label>
	            <div class="layui-input-block">
	                <script id="merchantDescPicEditor" type="text/plain" style="width:480px;height: 500px">${merchant.merchantDescPic}</script>
	            </div>
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
	
	var merchantDescPicUe = UE.getEditor('merchantDescPicEditor');
	
	form.on('submit(merchantEditForm)', function(data){
       var url = "${baseURL}/merchant/edit";
       
     	//获取富文本信息
       data.field.merchantDescPic = UE.getEditor('merchantDescPicEditor').getContent();
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
	
  //普通图片上传
    var uploadInst1 = upload.render({
        elem: '#pictrue1'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#productPic1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#productPic1').attr('src', res.data);
            $('#pic1').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText1');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });


    //普通图片上传
    var uploadInst2 = upload.render({
        elem: '#pictrue2'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#productPic2').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#productPic2').attr('src', res.data);
            $('#pic2').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText2');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });

    //普通图片上传
    var uploadInst3 = upload.render({
        elem: '#pictrue3'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#productPic3').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#productPic3').attr('src', res.data);
            $('#pic3').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText3');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });

    //普通图片上传
    var uploadInst4 = upload.render({
        elem: '#pictrue4'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#productPic4').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#productPic4').attr('src', res.data);
            $('#pic4').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText4');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });


    //普通图片上传
    var uploadInst5 = upload.render({
        elem: '#pictrue5'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#productPic5').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#productPic5').attr('src', res.data);
            $('#pic5').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText5');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });
	
</script>
