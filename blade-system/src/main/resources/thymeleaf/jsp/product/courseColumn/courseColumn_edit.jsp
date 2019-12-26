<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../common/taglib/taglib.jsp" %>

<script type="text/javascript" charset="utf-8" src="${baseURL}/ueditor/ueditor.config.js?time=${systemTime}"></script>
<script type="text/javascript" charset="utf-8" src="${baseURL}/ueditor/ueditor.all.min.js"> </script>
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
    .layui-form-select dl{
		z-index: 99999 !important;
	}
</style>

<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="productForm">
    	<input type="hidden" name="id" value="${courseColumn.id }">
    	<div class="layui-form-item">
            <label class="layui-form-label">专栏封面</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="pictrue" style="width: 210px">选择一张图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic" src="${courseColumn.picPath }" >
                    <p id="demoText"></p>
                    <input type="hidden" name="picPath" id="picPath" value="${courseColumn.picPath }" />
                </div>
            </div>
        </div>
        
        <div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：封面图片大小建议840*480</label>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">专栏标题：</label>
            <div class="layui-input-block">
                <input type="text" name="columnTitle" required lay-verify="required" placeholder="请输入专栏标题"
                       autocomplete="off" class="layui-input" value="${courseColumn.columnTitle }" >
            </div>
		</div>
		
		<div class="layui-form-item">
            <label class="layui-form-label">副标题：</label>
            <div class="layui-input-block">
                <input type="text" name="columnSubtitle" required lay-verify="required" placeholder="请输入专栏副标题"
                       autocomplete="off" class="layui-input" value="${courseColumn.columnSubtitle }" >
            </div>
        </div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
	            <label class="layui-form-label">专栏分类：</label>
	            <div class="layui-input-inline">
					<select name="columnTypeId">
						<option value="">请选择</option>
						<c:forEach var="item" items="${productTypeList }">
							<option value="${item.id }" <c:if test="${item.id eq courseColumn.columnTypeId}">selected</c:if>>${item.oneTypeName } -> ${item.typeName }</option>
						</c:forEach>
					</select> 
	            </div>
            </div>
            <div class="layui-inline">
            	<label class="layui-form-label">专栏等级：</label>
	            <div class="layui-input-block">
					<select name="productLevelCode">
						<option value="">请选择</option>
						<c:forEach var="item" items="${productLevelList }">
							<option value="${item.levelCode }" <c:if test="${item.levelCode eq courseColumn.productLevelCode}">selected</c:if>>${item.levelName }</option>
						</c:forEach>
					</select> 
	            </div>
            </div>
            <div class="layui-inline">
            	<label class="layui-form-label">开课时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" lay-verify="datetime" id="startDate"
                           placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input" value="<fmt:formatDate value="${courseColumn.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
            </div>
        </div>
        
        
        <div class="layui-form-item">
        	<div class="layui-inline">
			    <label class="layui-form-label">核心课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isUp" <c:if test="${courseColumn.isUp eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsUp" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">专业课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isMajor" <c:if test="${courseColumn.isMajor eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsMajor" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
		    	<label class="layui-form-label">自动收货：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isConfirm" <c:if test="${courseColumn.isConfirm eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsConfirm" lay-text="是|否">
			    </div>
		    </div>
		    
		    <div class="layui-inline">
		    	<label class="layui-form-label">推送课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isSend" <c:if test="${courseColumn.isSend eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsSend" lay-text="是|否">
			    </div>
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：</label>
		    <p>
		    <label class="layui-input-block" style="color: red;">1、购买核心课程，会员能够升级！</label>
		    </p>
		    <p>
		    <label class="layui-input-block" style="color: red;">2、自动确认收货代表购买后立即确认收货，不允许退款！</label>
		    </p>
		    <p>
		    <label class="layui-input-block" style="color: red;">3、开启推送课程后，专栏中的课程会每天自动推送给购买专栏的会员！</label>
		    </p>
		</div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
	        	<label class="layui-form-label">首页直推：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isHomeShow" <c:if test="${courseColumn.isHomeShow eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsHomeShow" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
		    	<label class="layui-form-label">是否收费：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" <c:if test="${courseColumn.isSell eq 'YES'}">checked=""</c:if> name="isSell" lay-skin="switch" lay-filter="switchIsSell" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">收货地址：</label>
			    <div class="layui-input-inline">
					<input type="checkbox" value="YES" <c:if test="${courseColumn.isOpenAddress eq 'YES'}">checked=""</c:if> name="isOpenAddress" lay-skin="switch" lay-filter="switchIsOpenAddress" lay-text="是|否">
			    </div>
		    </div>
		</div>
		
		<div class="layui-form-item "  >
			<div class="layui-inline">
	            <label class="layui-form-label">原价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="originalPrice"  placeholder="请输入原价" autocomplete="off"
	                       class="layui-input" value="${courseColumn.originalPrice }" >
	            </div>
	        </div>
	        <div class="layui-inline isSell" <c:if test="${courseColumn.isSell eq 'NO'}">style="display: none;"</c:if>>
	            <label class="layui-form-label">售价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="productPrice" placeholder="请输入售价" autocomplete="off"
	                       class="layui-input" value="${courseColumn.productPrice }" >
	            </div>
	        </div>
	        <div class="layui-inline isSell" <c:if test="${courseColumn.isSell eq 'NO'}">style="display: none;"</c:if>>
	            <label class="layui-form-label">成本价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="costPrice"  placeholder="请输入成本价" autocomplete="off"
	                       class="layui-input" value="${courseColumn.costPrice }" >
	            </div>
	        </div>
		</div>
		
		<div class="layui-form-item isSell" <c:if test="${courseColumn.isSell eq 'NO'}">style="display: none;"</c:if> >
	        <div class="layui-inline " >
	            <label class="layui-form-label">积分：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="point"  placeholder="请输入购买积分" autocomplete="off"
	                       class="layui-input" value="${courseColumn.point }" >
	            </div>
	        </div>
		</div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">链接地址：</label>
            <div class="layui-input-block">
                <input type="text" name="columnUrl" lay-verify="required" placeholder="请输入第三方平台的链接地址" autocomplete="off"
                       class="layui-input" value="${courseColumn.columnUrl }" >
            </div>
        </div>
		
        <div class="layui-form-item">
            <label class="layui-form-label">课程详情：</label>
            <div class="layui-input-block">
                <script id="columnDesEditor" type="text/plain" style="width:480px;height: 200px">${courseColumn.columnDes }</script>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="courseColumnEditForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, laydate = layui.laydate,upload = layui.upload, table = layui.table;
    form.render();
    var columnDesEditorUE = UE.getEditor('columnDesEditor');
    
  	//日期
    laydate.render({
        elem: '#startDate'
        , type: 'datetime'
    });
  	
    form.on('switch(switchIsSell)', function(data){
  		if(data.elem.checked){
  			$('.isSell').show();
  		}else{
  			$('.isSell').hide();
  		}
  	});

    form.on('submit(courseColumnEditForm)', function (data) {
        var url = "${baseURL}/courseColumn/edit";
        //获取富文本信息
        data.field.columnDes = UE.getEditor('columnDesEditor').getContent();

        post(url, data.field, function () {
        	parent.parent.layer.msg('修改成功', {
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
    
</script>
