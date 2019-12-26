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
    	<input type="hidden" name="id" value="${course.id }">
    	<div class="layui-form-item">
            <label class="layui-form-label">课程封面</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="pictrue" style="width: 210px">选择一张图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic" src="${course.picPath }" >
                    <p id="demoText"></p>
                    <input type="hidden" name="picPath" id="picPath" value="${course.picPath }" />
                </div>
            </div>
        </div>
        
        <div class="layui-form-item">
		    <label class="layui-input-block" style="color: red;">提示：封面图片大小建议840*480</label>
		</div>
		
		<div class="layui-form-item">
            <label class="layui-form-label">课程标题：</label>
            <div class="layui-input-block">
                <input type="text" name="courseTitle" required lay-verify="required" placeholder="请输入课程标题"
                       autocomplete="off" class="layui-input" value="${course.courseTitle }" >
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">课程描述：</label>
            <div class="layui-input-block">
                <textarea name="courseSubtitle" required lay-verify="required" placeholder="请输入课程描述" class="layui-textarea">${course.courseSubtitle }</textarea>
            </div>
        </div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
            	<label class="layui-form-label">开课时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" lay-verify="datetime" id="startDate"
                           placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input" value="<fmt:formatDate value="${course.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
            </div>
        </div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
	            <label class="layui-form-label">课程分类：</label>
	            <div class="layui-input-inline">
					<select name="courseTypeId">
						<option value="">请选择</option>
						<c:forEach var="item" items="${productTypeList }">
							<option value="${item.id }" <c:if test="${item.id eq course.courseTypeId}">selected</c:if>>${item.oneTypeName } -> ${item.typeName }</option>
						</c:forEach>
					</select> 
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">所属专栏：</label>
	            <div class="layui-input-inline">
					<select name="columnId" lay-search="">
						<option value="">请选择</option>
						<c:forEach var="item" items="${courseColumnList }">
							<option value="${item.id }" <c:if test="${item.id eq course.columnId}">selected</c:if>>${item.columnTitle }</option>
						</c:forEach>
					</select> 
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">课程等级：</label>
	            <div class="layui-input-inline">
					<select name="productLevelCode">
						<option value="">请选择</option>
						<c:forEach var="item" items="${productLevelList }">
							<option value="${item.levelCode }" <c:if test="${item.levelCode eq course.productLevelCode}">selected</c:if>>${item.levelName }</option>
						</c:forEach>
					</select> 
	            </div>
            </div>
        </div>
        
        <div class="layui-form-item">
        	<div class="layui-inline">
			    <label class="layui-form-label">是否收费：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" <c:if test="${course.isSell eq 'YES'}">checked=""</c:if> name="isSell" lay-skin="switch" lay-filter="switchIsSell" lay-text="是|否">
			    </div>
		    </div>
        	<div class="layui-inline">
	        	<label class="layui-form-label">现场课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isHomeShow" <c:if test="${course.isHomeShow eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsHomeShow" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">专业课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isMajor" <c:if test="${course.isMajor eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsMajor" lay-text="是|否">
			    </div>
		    </div>
		    
		</div>
		
		
        
        
        <div class="layui-form-item">
        	<div class="layui-inline">
	        	<label class="layui-form-label">确认收货：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isConfirmReceipt" <c:if test="${course.isConfirmReceipt eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsConfirmReceipt" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
			    <label class="layui-form-label">收货地址：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" <c:if test="${course.isOpenAddress eq 'YES'}">checked=""</c:if> name="isOpenAddress" lay-skin="switch" lay-filter="switchIsOpenAddress" lay-text="是|否">
			    </div>
		    </div>
        	<div class="layui-inline">
	        	<label class="layui-form-label">站内课程：</label>
			    <div class="layui-input-block">
					<input type="checkbox" value="YES" name="isSelfSite" <c:if test="${course.isSelfSite eq 'YES'}">checked=""</c:if> lay-skin="switch" lay-filter="switchIsSelfSite" lay-text="是|否">
			    </div>
		    </div>
		    <div class="layui-inline">
		    	<label class="layui-input-block" style="color: red;">提示：第三方的收费课程需要提供优惠券才能上架，站内课程可以直接上架！</label>
		    </div>
        </div>
        
        <div class="layui-form-item " >
        	<div class="layui-inline">
	            <label class="layui-form-label">原价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="originalPrice"  placeholder="请输入原价" autocomplete="off"
	                       class="layui-input" value="${course.originalPrice }" >
	            </div>
            </div>
            
            <div class="layui-inline isSell" <c:if test="${course.isSell eq 'NO'}">style="display: none;"</c:if> >
            	<label class="layui-form-label">售价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="productPrice"  placeholder="请输入售价" autocomplete="off"
	                       class="layui-input" value="${course.productPrice }" >
	            </div>
            </div>
            
            <div class="layui-inline isSell" <c:if test="${course.isSell eq 'NO'}">style="display: none;"</c:if> >
            	<label class="layui-form-label">成本价：</label>
	            <div class="layui-input-inline">
	                <input type="number" name="costPrice" placeholder="请输入成本价" autocomplete="off"
	                       class="layui-input" value="${course.costPrice }" >
	            </div>
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">链接地址：</label>
            <div class="layui-input-block">
                <input type="text" name="courseUrl" lay-verify="required" placeholder="请输入第课程的链接地址" autocomplete="off"
                       class="layui-input" value="${course.courseUrl }" >
            </div>
        </div>
		
		<div class="layui-form-item">
            <label class="layui-form-label">课程详情：</label>
            <div class="layui-input-block">
                <script id="courseEditor" type="text/plain" style="width:480px;height: 200px">${course.courseDes }</script>
            </div>
        </div>
        
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="courseEditForm">立即提交</button>
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
    
    var courseEditorUE = UE.getEditor('courseEditor');
    
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
    
    
</script>
