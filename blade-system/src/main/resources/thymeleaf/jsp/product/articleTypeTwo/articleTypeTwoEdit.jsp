<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../static/common/taglib/taglib.jsp"%>

<style>
    .layui-upload-img {
        width: 120px;
        height: 120px;
        margin: 0px 10px 10px 110px;
    }
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" name="id" value="${articleTypeTwo.id }">
		
		<div class="layui-form-item">
            <label class="layui-form-label">分类图标</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="pictrue" style="width: 120px">选择一张图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic" src="${articleTypeTwo.typeImg}" >
                    <p id="demoText"></p>
                    <input type="hidden" name="typeImg" id="typeImg" value="${articleTypeTwo.typeImg}" />
                </div>
            </div>
        </div>
        
        <div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：图标大小建议48*48（px）</label>
		</div>
        
        <div class="layui-form-item">
		    <label class="layui-form-label">一级分类：</label>
		    <div class="layui-input-block">
				<select name="typeOneId">
					<option value="">请选择</option>
					<c:forEach var="item" items="${oneTypeList }">
						<option value="${item.id }" <c:if test="${item.id eq articleTypeTwo.typeOneId}">selected</c:if>  >${item.oneTypeName }</option>
					</c:forEach>
				</select> 
		    </div>
		</div>
        
		<div class="layui-form-item">
		    <label class="layui-form-label">分类名称：</label>
		    <div class="layui-input-block">
				<input type="text" name="twoTypeName" required value="${articleTypeTwo.twoTypeName }" lay-verify="required" placeholder="请输入分类名称" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">排序号：</label>
		    <div class="layui-input-block">
				<input type="text" name="typeIndex" required value="${articleTypeTwo.typeIndex }" lay-verify="required" placeholder="请输入分类的排序号" autocomplete="off" class="layui-input">
		    </div>
		</div>
		
		<div class="layui-form-item">
        	<div class="layui-inline">
			    <label class="layui-form-label">是否显示：</label>
			    <div class="layui-input-block">
					<input type="checkbox"  <c:if test="${articleTypeTwo.isShow eq 'YES'}">checked=""</c:if> value="YES" name="isShow" lay-skin="switch" lay-filter="switchIsShow" lay-text="是|否">
			    </div>
		    </div>
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：是否显示标识会控制分类是否在APP端显示</label>
		</div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="articleTypeTwoEditForm">立即提交</button>
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
	form.on('submit(articleTypeTwoEditForm)', function(data){
       var url = "${baseURL}/articleTypeTwo/edit";
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
            $('#typeImg').val(res.data);
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
