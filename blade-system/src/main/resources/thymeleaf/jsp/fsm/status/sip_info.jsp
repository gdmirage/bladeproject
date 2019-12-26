<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/taglib/taglib.jsp" %>

<div class="page" style="margin: 10px 10px">
	<%-- <table class="layui-table">
		<tr>
			<th>事件名稱</th>
			<th>事件值</th>
		</tr>
		<c:forEach var="item" varStatus="i" items="${lineState.innerStatus.current_event }">
			<tr>
				<td>${item.key }</td>
				<td>${item.value }</td>
			</tr>
		</c:forEach>
		<c:if test="${empty lineState.innerStatus.current_event }">
			<tr>
				<td>無</td>
				<td>無</td>
			</tr>
		</c:if>
		
	</table> --%>
	
	<table class="layui-hide" id="sipStatusTable" lay-filter="sipStatusTable"></table>
	
</div>

<script>
    var table = layui.table;
    var $ = layui.jquery;
    
    var id = "${id}";
    
    var tableInit = table.render({
        elem: '#sipStatusTable'
        , url: '${baseURL}/fsmStatus/sip/info/list'
        , request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
        }
        , where: {
        	id: id
        }
        , cols: [[
            {field: 'key', width: '50%', title: '事件名稱'}
            , {field: 'value', width: '50%', title: '事件值'}
        ]]
        , id: 'sipStatusTableId'
    });

    //刷新table，带上搜索框条件
    function reloadTable(page) {
        //定义重载table参数
        var loadData = {
            where: {
            	id: id
            }
        };
        //组装分页参数,page为空时，取当前页数
        if (page) {
            loadData.page = {
                curr: page //重新从第page页开始
            }
        }

        tableInit.reload(loadData);
    }
    
    /* var t1 = setInterval(function(){
    	reload();
    },2000); */
	
    function reload(){
    	var param = {
    			id: id
    	};
    	$.ajax({ 
    		url: '${baseURL}/gateway/status/sip/info/list', 
    		type : 'GET',
    		data: param, 
    		success: function(data){
    			var list = data.data;
    			
    			var i = 0;
    			if(list.length == 0){
    				reloadTable(1);
    			}
    			$('tbody tr').each(function(){
    				$(this).find('td div').eq(0).html(list[i].key);
    				$(this).find('td div').eq(1).html(list[i].value);
    				i++;
    			});
    		}, 
    		error: function(data){
    			console.log(data);
    		}
    	});
    }


</script>
