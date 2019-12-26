<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<div class="layui-form">
		<div class="layui-form-item">
		      <label class="layui-form-label">用户编号：</label>
		      <div class="layui-input-inline">
		        <input type="text" id="userNum" autocomplete="off" class="layui-input">
		      </div>
			<div class="layui-inline">
				<label class="layui-form-label">开始时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="createTimeStart" placeholder="">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">结束时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="createTimeEnd" placeholder="">
				</div>
			</div>
		      <button class="layui-btn"  id="search">
				  <i class="layui-icon">&#x1002;</i>查询
			  </button>
		</div>
	</div>
	
	<hr>

	<div class="layui-btn-group">
		<%--<button class="layui-btn layui-btn-sm layui-btn-danger" id="findInventory">库存查看</button>--%>
	</div>
	
	<table class="layui-hide" id="inventoryDetailTable" lay-filter="inventoryDetailTable"></table>

<script type="text/html" id="permissionBar">
  <a class="layui-btn layui-btn-xs" lay-event="details">查看详情</a>
 <%-- <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除权限</a>--%>
</script>

	<script type="text/html" id="agentRole">
		{{# if (d.role === 'ROLE_USER') { }}
		<span class="layui-badge layui-bg-blue">会员</span>
		{{# } else if(d.role === 'ROLE_AGENT') { }}
		<span class="layui-badge layui-bg-blue">代理商</span>
		{{# } }}
	</script>

<script type="text/html" id="dateTem">
	{{ timestampToTime(d.createTime) }}
</script>
<script type="text/html" id="type">
	{{#  if(d.inOrOut === 'IN'){ }}
		转入
	{{#  } else { }}
		转出
	{{#  } }}
</script>
<script type="text/html" id="operationType">
	{{#  if(d.operationType === 'TRANSFER'){ }}
	   库存转移
	{{#  } }}
</script>

<script>
  var table = layui.table;
  var $ = layui.jquery;
  var date = layui.laydate;

  var createTimeStart = "";
  var createTimeEnd = "";
  date.render({
      elem: '#createTimeStart'
      ,done: function(value, date, endDate){
          console.log(value); //得到日期生成的值，如：2017-08-18
          createTimeStart = value;
      }
  });
  date.render({
      elem: '#createTimeEnd'
      ,done: function(value, date, endDate){
          console.log(value); //得到日期生成的值，如：2017-08-18
          createTimeEnd = value;
      }
  });
  var tableInit = table.render({
	    elem: '#inventoryDetailTable'
	    ,url:'${baseURL}/agent/selectInventoryDetailList/'
	    ,request: {
	    	pageName: 'pageNum' //页码的参数名称，默认：page
	    	,limitName: 'numPerPage' //每页数据量的参数名，默认：limit
	    }
	    ,where : {
          agentNum : $('#userNum').val(),
          createTimeStart: createTimeStart,
          createTimeEnd: createTimeEnd
	    }
	    ,cols: [[
          {field:'nickName', width:'10%', title: '昵称'}
	      ,{field:'login',width:'12%', title: '账号'}
	      ,{field:'productName', width:'15%', title: '产品名称'}
	      ,{field:'agentNum', width:'12%', title: '用户编号'}
	      ,{field:'inOrOut', width:'10%', title: '类型',templet:'#type'}
          ,{field:'amount', width:'5%', title: '库存'}
          ,{field:'operationType', width:'10%', title: '操作类型',templet:'#operationType'}
	      ,{field:'createTime', title: '创建时间', templet: '#dateTem'}
	    ]]
	    ,limits: [5,10,20,50]//每页数据选择项
        ,limit: 10 //默认采用10
	    ,page: true
	    ,id : 'inventoryDetailTableId'
	});
  
  //刷新table，带上搜索框条件
  function reloadTable(page){
	  //定义重载table参数
	  var loadData = {
			  where: {
                  agentNum : $('#userNum').val(),
                  createTimeStart: createTimeStart,
                  createTimeEnd: createTimeEnd
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

	
	$('#search').on('click', function(){
		reloadTable(1);
	});
	
</script>
</div>
