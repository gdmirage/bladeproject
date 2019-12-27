<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">座席组：</label> 
				<div class="layui-input-inline" layui-form>
					<select id="groupId" name="groupId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${userGroupList}">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>

				<button class="layui-btn" id="searchUserGroupMapping">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

<table class="layui-hide" id="userGroupMappingTable"
	lay-filter="userGroupMappingTable"></table>

<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="add{objectName}"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="edit{objectName}"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="del{objectName}"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>

<script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>

<script type="text/html" id="showUserTem">
		{{# if (d.seat != null) { }}
			{{d.seat.name}}
		{{# } else { }}
			{{}}
		{{# } }}
</script>

<script type="text/html" id="showUserTypeTem">
		{{# if (d.userType === 'leader') { }}
			<span class="layui-badge layui-bg-blue">班长坐席</span>
		{{# } else if (d.userType === 'employee') { }}
			<span class="layui-badge layui-bg-orange">普通座席</span>
		{{# } else { }}
			<span class="layui-badge">备用坐席</span>
		{{# } }}
	</script>

<script type="text/html" id="showUserGroupTem">
		{{# if (d.seatGroup != null) { }}
			{{d.seatGroup.name}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>


<script>
	var table = layui.table;
	var $ = layui.jquery;
	
	//表单下拉框
    var form = layui.form;
    form.render();
	
	var tableInit = table.render({
		elem : '#userGroupMappingTable',
		url : '${baseURL}/seatGroupMapping/list',
		toolbar : '#tableToolbar',
		defaultToolbar : [ 'filter', 'print' ],
		request : {
			pageName : 'pageNum' //页码的参数名称，默认：page
			,
			limitName : 'numPerPage' //每页数据量的参数名，默认：limit
		},
		where : {
			userId : $('#userId').val(),
			groupId : $('#groupId').val()
		},
		cols : [[ 
			{ type : 'checkbox', width : '5%' }
			, { field : 'name', width : '30%', title : '座席组名', templet : '#showUserGroupTem' } 
			, { field : 'username', width : '30%', title : '坐席名', templet : '#showUserTem' }
			, { field : 'userType', title : '坐席类型', templet : '#showUserTypeTem' }
		]],
		limits : [ 5, 10, 20, 50 ]//每页数据选择项
		,
		limit : 10 //默认采用10
		,
		page : true,
		id : 'userGroupMappingTableId'
	});

	//刷新table，带上搜索框条件
	function reloadTable(page) {
		//定义重载table参数
		var loadData = {
			where : {
				userId : $('#userId').val(),
				groupId : $('#groupId').val()
			}
		};
		//组装分页参数,page为空时，取当前页数
		if (page) {
			loadData.page = {
				curr : page
			//重新从第page页开始
			}
		}

		tableInit.reload(loadData);
	}

	$('#searchUserGroupMapping').on('click', function() {
		reloadTable(1);
	});

	table
			.on(
					'toolbar(userGroupMappingTable)',
					function(obj) {
						var checkStatus = table.checkStatus(obj.config.id);
						var data = checkStatus.data;
						if (obj.event === 'add{objectName}') {
							openWin('新增', '${baseURL}/seatGroupMapping/addUI',
									function() {
										reloadTable(1);
									});
						} else if (obj.event === 'edit{objectName}') {
							var checkStatus = table
									.checkStatus('userGroupMappingTableId'), data = checkStatus.data;
							if (data.length != 1) {
								parent.parent.layer.alert('请选择一条编辑数据');
							} else {
								editUserGroupMapping(data[0])
							}
						} else if (obj.event === 'del{objectName}') {
							var checkStatus = table
									.checkStatus('userGroupMappingTableId'), data = checkStatus.data;
							if (data.length == 0) {
								parent.parent.layer.alert('请选择一条或多条数据!');
								return;
							}

							var ids = [ data.length ];
							for (var i = 0; i < data.length; i++) {
								ids[i] = data[i].id;
							}

							delUserGroupMapping(ids)
						}
					});

	function editUserGroupMapping(data) {
		openWin('修改', '${baseURL}/seatGroupMapping/editUI?id=' + data.id,
				function() {
					reloadTable(1);
				});
	}

	//删除申请
	function delUserGroupMapping(ids) {
		var deleteData = {
				"ids" : ids
			}
			parent.parent.layer.confirm('确认删除么', function(index) {
				$.ajax({
					url : "${baseURL}/seatGroupMapping/delete",
					type : "POST",
					data : deleteData,
					traditional : true,
					dataType : "json",
					async : false,
					success : function(data) {
						reloadTable();
						parent.parent.layer.close(index);
						if (data.code == -999) {
							parent.parent.layer.alert(data.msg, {
								icon : 2
							});
						} else {
							parent.parent.layer.msg("删除成功！", {
								icon : 1,
								time : 2000
							//2秒关闭（如果不配置，默认是3秒）
							});
						}
					},
					error : function(data) {
						parent.parent.layer.alert(data.msg, {
							icon : 2
						});
					}
				});
			});
	}
</script>
</div>
