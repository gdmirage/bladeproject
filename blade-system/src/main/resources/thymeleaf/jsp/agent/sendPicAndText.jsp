<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>
<style>
    .layui-upload-img {
        width: 400px;
        height: 200px;
    }
</style>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post">
    	
    	<input type="hidden" name="idArr" value="${idArr }">
    	
        <div class="layui-form-item">
            <label class="layui-form-label">标题：</label>
            <div class="layui-input-block">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入标题"
                       autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述：</label>
            <div class="layui-input-block">
            	<input type="text" name="desc" required lay-verify="required" placeholder="请输入描述"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">主图：</label>
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic">
                    <p id="demoText"></p>
                    <input type="hidden" name="mainPicture" id="mainPicture"/>
                </div>
            </div>
        </div>

		<div class="layui-form-item">
            <label class="layui-form-label">链接：</label>
            <div class="layui-input-block">
                <input type="text" name="url" required lay-verify="required" placeholder="请输入链接"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="sendPicAndTextForm">发送</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form,  layer = layui.layer, upload = layui.upload;

    

    form.on('submit(sendPicAndTextForm)', function (data) {
        var url = "${baseURL}/agent/sendPicAndText";
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
            $('#mainPicture').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });


</script>
