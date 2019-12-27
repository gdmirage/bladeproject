<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../static/common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<div class="layui-form">
		<div class="layui-form-item">
			<blockquote class="layui-elem-quote">
			      <label class="layui-form-label">权限名称：</label>
			      <div class="layui-input-inline">
			        <input type="text" id="permissionName" autocomplete="off" class="layui-input">
			      </div>
			      <label class="layui-form-label">权限标识：</label>
			      <div class="layui-input-inline">
			        <input type="text" id="permission" autocomplete="off" class="layui-input">
			      </div>
			      
			      <button class="layui-btn"  id="searchPermission">
					  <i class="layui-icon">&#xe615;</i>查询
				  </button>
			 </blockquote>
		</div>
	</div>
	
	<hr>

	<div class="layui-btn-group">
	    <button class="layui-btn layui-btn-sm " id="addPermission"><i class="layui-icon">&#xe654;</i>增加</button>
	    <button class="layui-btn layui-btn-sm " id="editPermission"><i class="layui-icon">&#xe642;</i>编辑</button>
	    <button class="layui-btn layui-btn-sm " id="delPermission"><i class="layui-icon">&#xe640;</i>删除</button>
	</div>
	
	<table class="layui-hide" id="permissionTable" lay-filter="permissionTable"></table>

<script type="text/html" id="dateTem">
	{{ timestampToTime(d.createTime) }}
</script>

<script>
  var table = layui.table;
  var $ = layui.jquery;
  var tableInit = table.render({
	    elem: '#permissionTable'
	    ,url:'${baseURL}/pms/permission/list/'
	    ,request: {
	    	pageName: 'pageNum' //页码的参数名称，默认：page
	    	,limitName: 'numPerPage' //每页数据量的参数名，默认：limit
	    }
	    ,where : {
	    	permissionName : $('#permissionName').val()
			,permission : $('#permission').val()
	    }
	    ,cols: [[
			{type:'checkbox', width:'5%'}
	      ,{field:'id', width:'10%', title: '序号'}
	      ,{field:'permissionName', width:'20%', title: '权限名称'}
	      ,{field:'permission', width:'20%', title: '权限标识', sort: true}
	      ,{field:'remark', width:'25%', title: '描述'}
	      ,{field:'createTime', title: '创建时间', width: '20%', templet: '#dateTem'}
	    ]]
	    ,limits: [5,10,20,50]//每页数据选择项
        ,limit: 10 //默认采用10
	    ,page: true
	    ,id : 'permissionTableId'
	});
  
  //刷新table，带上搜索框条件
  function reloadTable(page){
	  //定义重载table参数
	  var loadData = {
			  where: {
					permissionName : $('#permissionName').val()
					,permission : $('#permission').val()
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
	table.on('tool(permissionTable)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	    	delPermission(data);
	    } else if(obj.event === 'edit'){
	    	editPermission(data)
	    }
	});
	
	$('#searchPermission').on('click', function(){
		reloadTable(1);
	});
	
	
	//新增
	$('#addPermission').click(function(){
		openWin('添加权限','${baseURL}/pms/permission/addUI/',function(){
			reloadTable(1);
		});
	})
	
	//修改
	$('#editPermission').click(function(){
		var checkStatus = table.checkStatus('permissionTableId')
	      ,data = checkStatus.data;
		if(data.length != 1){
			layer.alert('请选择一条编辑数据');
		}else{
			editPermission(data[0])
		}
	});
	
	function editPermission(data){
		openWin('修改权限','${baseURL}/pms/permission/editUI?id=' + data.id,function(){
			reloadTable(1);
		});
	}
	
	//删除
	$('#delPermission').click(function(){
		var checkStatus = table.checkStatus('permissionTableId')
	      ,data = checkStatus.data;
		if(data.length != 1){
			layer.alert('请选择一条编辑数据');
		}else{
			delPermission(data[0])
		}
	});
	
	//删除权限
	function delPermission(data){
		var deleteData = {
	    		permissionId : data.id
	    	}
		parent.parent.layer.confirm('确认删除【'+data.permissionName+'】么', function(index){
			var url = "${baseURL}/pms/permission/delete";
			post(url,deleteData,function(data){
				reloadTable();
				parent.parent.layer.close(index);
				parent.parent.layer.msg('删除成功',{
  				  icon: 1,
  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
  				});
  			},function(data){
  				parent.parent.layer.alert(data.msg,{
				  icon: 2
				});
  			});
		});
	}
	
	
</script>
</div>
