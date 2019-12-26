<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">

	<form class="layui-form">
		<input type="hidden" id="courseOrColumnId" value="${otherCoupon.courseOrColumnId }" />
		<div class="layui-upload">
		  <button type="button" class="layui-btn layui-btn-normal" id="selectFile"><i class="layui-icon">&#xe65f;</i>选择文件</button>
		  <button type="button" class="layui-btn" id="upload"><i class="layui-icon">&#xe67c;</i>开始上传</button>
		  <label class="layui-input-inline" style="color: red;" id="importMsg"></label>
		</div>
	</form>
	
	<table class="layui-hide" id="courseColumnCouponTable" lay-filter="courseColumnCouponTable"></table>
	
	
	<script type="text/html" id="productTypeBar">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">删除</a>
    </script>

   <script type="text/html" id="usedTimeTem">
        {{ timestampToTime(d.usedTime) }}
   </script>
	<script type="text/html" id="isUsedTem">
        {{# if (d.isUsed === 'YES') { }}
       		<span class="layui-badge layui-bg-green">是</span>
        {{# } else if(d.isUsed === 'NO') { }}
        	<span class="layui-badge">否</span>
        {{# } }}
    </script>
</div>

<script type="text/javascript">
	var $ = layui.jquery;
	var table = layui.table; 
	var form = layui.form;
	var upload = layui.upload;
	form.render();
	var courseOrColumnId = $('#courseOrColumnId').val()
	//指定允许上传的文件类型
	upload.render({
	    elem: '#selectFile'
	    ,url: '${baseURL}/courseColumn/uploadCoupon'
	    ,auto: false
	    ,data: {
	    	id : courseOrColumnId
	    }
	    //,multiple: true
	    ,bindAction: '#upload'
	    ,accept: 'file'
	    ,done: function(res){
	    	if(res.code == 0){
	    		$('#importMsg').html('导入成功，总共导入' + res.data + '条消息');
	    		reloadTable(1);
	    	}
	    }
	    ,error: function(index, upload){
	    	console.log('index = ' + index);
	    	console.log('upload = ' + upload);
	    }
	});
	
	var tableInit = table.render({
        elem: '#courseColumnCouponTable'
        , url: '${baseURL}/courseColumn/otherCouponlist/'
        , request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
        }
        , where: {
        	courseOrColumnId: $('#courseOrColumnId').val()
        }
        , cols: [[
            // ,{field:'id', width:'10%', title: 'id'}
            {field: 'couponCode', width: '10%', title: '优惠码'}
            , {field: 'couponMoney', width: '10%', title: '优惠金额'}
            , {field: 'isUsed',  width: '10%',title: '是否已领取', templet: '#isUsedTem'}
            , {field: 'userName',  width: '10%',title: '领取用户'}
            , {field: 'usedTime',  width: '15%',title: '领取时间', templet: '#usedTimeTem'}
            , {field: 'couponUrl', title: '链接地址'}
            // , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#productTypeBar' , fixed: 'right'}
        ]]
        , limits: [5, 10, 20, 50]//每页数据选择项
        , limit: 10 //默认采用10
        , page: true
        , id: 'courseColumnCouponTableId'
        //, height: 'full-50'
    });
	
	//刷新table，带上搜索框条件
    function reloadTable(page) {
        var loadData = {
            where: {
            	courseOrColumnId: $('#courseOrColumnId').val()
            }
        };
        if (page) {
            loadData.page = {
                curr: page //重新从第page页开始
            }
        }

        tableInit.reload(loadData);
    }
	
</script>
