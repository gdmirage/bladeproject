<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../static/common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<div class="layui-form">
		<%--<div class="layui-form-item">--%>
		      <%--<label class="layui-form-label">账号名称：</label>--%>
		      <%--<div class="layui-input-inline">--%>
		        <%--<input type="text" id="login" autocomplete="off" class="layui-input">--%>
		      <%--</div>--%>
		      <%--<label class="layui-form-label">编号：</label>--%>
		      <%--<div class="layui-input-inline">--%>
		        <%--<input type="text" id="userNum" autocomplete="off" class="layui-input">--%>
		      <%--</div>--%>
		      <%----%>
		      <%--<button class="layui-btn"  id="searchPermission">--%>
				  <%--<i class="layui-icon">&#x1002;</i>查询--%>
			  <%--</button>--%>
		<%--</div>--%>
	</div>
	
	<hr>
	
	<table class="layui-hide" id="inventoryTable" lay-filter="inventoryTable"></table>

<script type="text/html" id="dateTem">
	{{ timestampToTime(d.createTime) }}
</script>

<script>
  var agentId = "<%=request.getParameter("agentId")%>";
  var table = layui.table;
  var $ = layui.jquery;
  var tableInit = table.render({
	    elem: '#inventoryTable'
	    ,url:'${baseURL}/agent/selectInventoryList/'
	    ,request: {
	    	pageName: 'pageNum' //页码的参数名称，默认：page
	    	,limitName: 'numPerPage' //每页数据量的参数名，默认：limit
	    }
	    ,where : {
          agentId : agentId
	    }
	    ,cols: [[
          {field:'agentNum',width:'10%', title: '代理商编号'}
	      ,{field:'productName',width:'15%', title: '产品名称'}
	      ,{field:'productNumber', width:'15%', title: '产品编号'}
	      ,{field:'amount', title: '库存'}
	      ,{field:'createTime', title: '创建时间', width:'15%', templet: '#dateTem'}
	    ]]
	    ,limits: [5,10,20,50]//每页数据选择项
        ,limit: 10 //默认采用10
	    ,page: true
	    ,id : 'inventoryTableId'
	});
  
  //刷新table，带上搜索框条件
  function reloadTable(page){
	  //定义重载table参数
	  var loadData = {
			  where: {
                  login : $('#login').val()
					,userNum : $('#userNum').val()
				} 
	  };
	  //组装分页参数,page为空时，取当前页数
	  if(page){
		  loadData.page = {
				curr: page //重新从第page页开始
			}
	  }
	  
	  tableInit.reload(loadData);
  }
  
	//监听工具条
	table.on('tool(memberTable)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	    	delPermission(data);
	    } else if(obj.event === 'details'){
            agentDetails(data)
	    }
	});
	
	$('#searchPermission').on('click', function(){
		reloadTable(1);
	});

	
	function agentDetails(data){
		openWin('详情','${baseURL}/agent/details?id=' + data.id,function(){
			// reloadTable(1);
			parent.reloadTable(1);
		});
	}
	
	
</script>
</div>
