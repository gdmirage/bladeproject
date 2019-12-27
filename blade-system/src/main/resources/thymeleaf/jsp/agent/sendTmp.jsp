<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
	<input type="hidden" name="idArr" id="idArr" value="${idArr }" />
	
	<table class="layui-hide" id="tmpTable" lay-filter="tmpTable"></table>
    	
</div>
<script type="text/html" id="tmpBar"> 
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="send">发送消息</a>
</script>

<script type="text/javascript">
	var table = layui.table;
    var $ = layui.jquery;
    
    var tableInit = table.render({
        elem: '#tmpTable'
        , url: '${baseURL}/agent/getTmpList/'
        , cols: [[
			{field: 'title', width: '45%', title: '模板名称'}
			, {field: 'template_id', width: '45%', title: '模板ID'}
			, {field: '' , title: '操作',toolbar: '#tmpBar' , fixed: 'right'} 
        ]]
        , id: 'tmpTableId'
    });
    
    table.on('tool(tmpTable)', function (obj) {
        var data = obj.data;
        var url = '${baseURL}/agent/sendTmpBySelectUI?idArr=' + $('#idArr').val() 
        			+ '&template_id=' + data.template_id
        			;
        if (obj.event === 'send') {
        	openWin('【' + data.title + '】',url,function(){
			});
        } 
    });

</script>
