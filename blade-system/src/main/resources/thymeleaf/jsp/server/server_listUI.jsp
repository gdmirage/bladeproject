<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">服务器IP：</label>
				<div class="layui-input-inline">
					<input type="text" id="ip" autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label">端口：</label>
				<div class="layui-input-inline">
					<input type="text" id="sshPort" autocomplete="off"
						class="layui-input">
				</div>

				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-inline">
					<input type="text" id="sshUser" autocomplete="off"
						class="layui-input">
				</div>
				
				<label class="layui-form-label">服务器组：</label>
				<div class="layui-input-inline" layui-form>
					<select name="groupId" id="groupId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${serverGroupList }">
							<option value="${item.id }">${item.groupName }</option>
						</c:forEach>
					</select>
				</div>

				<button class="layui-btn" id="searchServer">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="serverTable" lay-filter="serverTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
			<div class="layui-btn-group">
    			<button class="layui-btn layui-btn-sm" lay-event="addServer"><i class="layui-icon">&#xe654;</i>增加</button>
    			<button class="layui-btn layui-btn-sm" lay-event="editServer"><i class="layui-icon">&#xe642;</i>修改</button>
    			<button class="layui-btn layui-btn-sm" lay-event="delServer"><i class="layui-icon">&#xe640;</i>删除</button>
			</div>
  		</div>
	</script>


	<script type="text/html" id="showServerGroupName">
		{{# if (d.serverGroup != null) { }}
			{{d.serverGroup.groupName}}
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
			elem : '#serverTable',
			url : '${baseURL}/server/list/',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				ip : $('#ip').val(),
				sshPort : $('#sshPort').val(),
				groupId : $('#groupId').val(),
				sshUser : $('#sshUser').val()
			},
			cols : [ [ {
				type : 'checkbox',
				width : '5%'
			}, {
				field : 'id',
				width : '10%',
				title : 'id',
				hide : true
			}, {
				field : 'ip',
				width : '20%',
				title : '服务器IP'
			}, {
				field : 'sshPort',
				width : '12%',
				title : '端口'
			}, {
				field : 'groupName',
				width : '25%',
				title : '服务器组',
				templet : '#showServerGroupName'
			}, {
				field : 'sshUser',
				width : '20%',
				title : '用户名'
			}, {
				field : 'sshPassword',
				title : '密码'
			} ] ],
			limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,
			limit : 10 //默认采用10
			,
			page : true,
			id : 'serverTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					ip : $('#ip').val(),
					sshPort : $('#sshPort').val(),
					groupId : $('#groupId').val(),
					sshUser : $('#sshUser').val()
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

		$('#searchServer').on('click', function() {
			reloadTable(1);
		});

		table
				.on(
						'toolbar(serverTable)',
						function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							var data = checkStatus.data;
							if (obj.event === 'addServer') {
								openWin('新增', '${baseURL}/server/addUI',
										function() {
											reloadTable(1);
										});
							} else if (obj.event === 'editServer') {
								var checkStatus = table
										.checkStatus('serverTableId'), data = checkStatus.data;
								if (data.length != 1) {
									layer.alert('请选择一条编辑数据');
								} else {
									editServer(data[0])
								}
							} else if (obj.event === 'delServer') {
								var checkStatus = table
										.checkStatus('serverTableId'), data = checkStatus.data;
								if (data.length == 0) {
									parent.parent.layer.alert('请选择一条或多条数据!');
									return;
								}

								var ids = [ data.length ];
								for (var i = 0; i < data.length; i++) {
									ids[i] = data[i].id;
								}

								delServer(ids)
							}
						});

		function editServer(data) {
			openWin('修改', '${baseURL}/server/editUI?id=' + data.id, function() {
				reloadTable(1);
			});
		}

		//删除申请
		function delServer(ids) {
			var deleteData = {
					"ids" : ids
				}
				parent.parent.layer.confirm('确认删除么', function(index) {
					$.ajax({
						url : "${baseURL}/server/delete",
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
