<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">分组名称：</label>
				<div class="layui-input-inline">
					<input type="text" id="groupName" autocomplete="off"
						class="layui-input">
				</div>

				<button class="layui-btn" id="searchServerGroup">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="serverGroupTable"
		lay-filter="serverGroupTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
			<div class="layui-btn-group">
    			<button class="layui-btn layui-btn-sm" lay-event="addServerGroup"><i class="layui-icon">&#xe654;</i>增加</button>
    			<button class="layui-btn layui-btn-sm" lay-event="editServerGroup"><i class="layui-icon">&#xe642;</i>修改</button>
    			<button class="layui-btn layui-btn-sm" lay-event="delServerGroup"><i class="layui-icon">&#xe640;</i>删除</button>
			</div>
  		</div>
	</script>

	<script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>


	<script>
		var table = layui.table;
		var $ = layui.jquery;
		var tableInit = table.render({
			elem : '#serverGroupTable',
			url : '${baseURL}/serverGroup/list/',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				groupName : $('#groupName').val()
			},
			cols : [ [ {
				type : 'checkbox',
				width : '5%'
			}, {
				field : 'id',
				width : '15%',
				title : 'id',
				hide : true
			}, {
				field : 'groupName',
				width : '40%',
				title : '分组名称'
			}, {
				field : 'createdAt',
				title : '创建时间',
				templet : '#createdAtTem'
			} ] ],
			limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,
			limit : 10 //默认采用10
			,
			page : true,
			id : 'serverGroupTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					groupName : $('#groupName').val()
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

		$('#searchServerGroup').on('click', function() {
			reloadTable(1);
		});

		table
				.on(
						'toolbar(serverGroupTable)',
						function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							var data = checkStatus.data;
							if (obj.event === 'addServerGroup') {
								openWin('新增', '${baseURL}/serverGroup/addUI',
										function() {
											reloadTable(1);
										});
							} else if (obj.event === 'editServerGroup') {
								var checkStatus = table
										.checkStatus('serverGroupTableId'), data = checkStatus.data;
								if (data.length != 1) {
									layer.alert('请选择一条编辑数据');
								} else {
									editServerGroup(data[0])
								}
							} else if (obj.event === 'delServerGroup') {
								var checkStatus = table
										.checkStatus('serverGroupTableId'), data = checkStatus.data;
								if (data.length == 0) {
									parent.parent.layer.alert('请选择一条或多条数据!');
									return;
								}

								var ids = [ data.length ];
								for (var i = 0; i < data.length; i++) {
									ids[i] = data[i].id;
								}

								delServerGroup(ids)
							}
						});

		function editServerGroup(data) {
			openWin('修改', '${baseURL}/serverGroup/editUI?id=' + data.id,
					function() {
						reloadTable(1);
					});
		}

		//删除申请
		function delServerGroup(ids) {
			var deleteData = {
				"ids" : ids
			}
			parent.parent.layer.confirm('确认删除么', function(index) {
				$.ajax({
					url : "${baseURL}/serverGroup/delete",
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
