<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/common/taglib/taglib.jsp"%>

<!-- <style>
.searchResult {
	border-bottom: 1 px solid #e4e7ee;
	line-height: 30 px;
	padding-left: 4 px;
	width: 176 px;
	list-style: none;
	position: absolute;
	top: 60 px;
	right: 70 px;
	width: 180 px;
	border: 1 px solid #e4e7ee;
	border-top: 0;
	border-bottom: 0;
	background: #fff;
	max-height: 279 px;
	overflow-y: auto;
	overflow-x: hidden;
	z-index: 2;
}
</style> -->



<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label">启动模块：</label>
				<div class="layui-input-inline">
					<input type="text" id="module" name="module" autocomplete="off"
						class="layui-input">
				</div>

				<label class="layui-form-label">是否激活：</label>
				<div class="layui-input-inline">
					<select name="enabled" id="enabled">
						<option value="">请选择</option>
						<option value="0">未激活</option>
						<option value="1">已激活</option>
					</select>
				</div>

				<label class="layui-form-label">呼叫类别：</label>
				<div class="layui-input-inline">
					<select name="type" id="type">
						<option value="">请选择</option>
						<option value="inbound">呼入</option>
						<option value="outbound">呼出</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">服务器IP：</label>
				<div class="layui-input-inline" layui-form>
					<select id="serverId" name="serverId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${serverList }">
							<option value="${item.id }">${item.ip }</option>
						</c:forEach>
					</select>
				</div>
				<ul id="searchResult" class="searchResult layui-bg-gray"></ul>
				<label class="layui-form-label">网关名：</label>
				<div class="layui-input-inline" layui-form>
					<select id="gatewayId" name="gatewayId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${gatewayList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>

				<button class="layui-btn" id="searchFsm">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
				<!-- <button class="layui-btn" id="reportToExcelByFsm">
					<i class="layui-icon">&#xe601;</i>导出
				</button> -->
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="fsmTable" lay-filter="fsmTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
			<div class="layui-btn-group">
    			<button class="layui-btn layui-btn-sm" lay-event="addFsm"><i class="layui-icon">&#xe654;</i>增加</button>
    			<button class="layui-btn layui-btn-sm" lay-event="editFsm"><i class="layui-icon">&#xe642;</i>修改</button>
    			<button class="layui-btn layui-btn-sm" lay-event="delFsm"><i class="layui-icon">&#xe640;</i>删除</button>
				
			</div>
  		</div>
	</script>


	<script type="text/html" id="showServer">
		{{# if (d.server != null) { }}
			{{d.server.ip}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>

	<script type="text/html" id="showGateway">
		{{# if (d.gateway != null) { }}
			{{d.gateway.name}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>

	<script type="text/html" id="enabledTem">
		{{# if (d.enabled === 1) { }}
			<span class="layui-badge layui-bg-blue">已激活</span>
		{{# } else { }}
			<span class="layui-badge">未激活</span>
		{{# } }}
	</script>

	<script type="text/html" id="typeTem">
		{{# if (d.type === 'inbound') { }}
			<span class="layui-badge layui-bg-blue">呼入</span>
		{{# } else { }}
			<span class="layui-badge layui-bg-green">呼出</span>
		{{# } }}
	</script>


	<script>
		var table = layui.table;
		var $ = layui.jquery;
		//表单下拉框
		var form = layui.form;
		form.render();

		$('#reportToExcelByFsm').click(function() {
			var param = {
				module : $('#module').val(),
				enabled : $('#enabled').val(),
				name : $('#name').val(),
				type : $('#type').val(),
				serverId : $('#serverId').val(),
				gatewayId : $('#gatewayId').val()
			};
			var url = "${baseURL}/fsm/reportToExcelByFsm";
			downByUrlAndParamsPost(url, param);
		});

		var tableInit = table.render({
			elem : '#fsmTable',
			url : '${baseURL}/fsm/list/',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				module : $('#module').val(),
				enabled : $('#enabled').val(),
				name : $('#name').val(),
				type : $('#type').val(),
				serverId : $('#serverId').val(),
				gatewayId : $('#gatewayId').val()
			},
			cols : [ [ {
				type : 'checkbox',
				width : '5%'
			}, {
				field : 'id',
				width : '4%',
				title : 'id',
				hide : true
			}, {
				field : 'ip',
				width : '12%',
				title : '服务器IP',
				templet : '#showServer'
			}, {
				field : 'gatewayName',
				width : '6%',
				title : '网关名',
				templet : '#showGateway'
			}, {
				field : 'module',
				width : '6%',
				title : '启动模块'
			}, {
				field : 'function',
				width : '6%',
				title : '启动函数'
			}, {
				field : 'arguments',
				width : '12%',
				title : '函数参数'
			}, {
				field : 'name',
				width : '10%',
				title : '名称'
			}, {
				field : 'commands',
				width : '10%',
				title : '命令集'
			}, {
				field : 'erlangCookie',
				width : '8%',
				title : 'Erlang Cookie'
			}, {
				field : 'description',
				width : '10%',
				title : '描述'
			}, {
				field : 'type',
				width : '6%',
				title : '呼叫类别',
				templet : '#typeTem'
			}, {
				field : 'enabled',
				title : '是否激活',
				templet : '#enabledTem'
			} ] ],
			limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,
			limit : 10 //默认采用10
			,
			page : true,
			id : 'fsmTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					module : $('#module').val(),
					enabled : $('#enabled').val(),
					name : $('#name').val(),
					serverId : $('#serverId').val(),
					gatewayId : $('#gatewayId').val(),
					type : $('#type').val()
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

		$('#searchFsm').on('click', function() {
			$('#searchResult').hide();
			reloadTable(1);
		});

		table
				.on(
						'toolbar(fsmTable)',
						function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							var data = checkStatus.data;
							if (obj.event === 'addFsm') {
								openWin('新增', '${baseURL}/fsm/addUI',
										function() {
											reloadTable(1);
										});
							} else if (obj.event === 'editFsm') {
								var checkStatus = table
										.checkStatus('fsmTableId'), data = checkStatus.data;
								if (data.length != 1) {
									layer.alert('请选择一条编辑数据');
								} else {
									editFsm(data[0])
								}
							} else if (obj.event === 'delFsm') {
								var checkStatus = table
										.checkStatus('fsmTableId'), data = checkStatus.data;
								if (data.length == 0) {
									parent.parent.layer.alert('请选择一条或多条数据!');
									return;
								}

								var ids = [ data.length ];
								for (var i = 0; i < data.length; i++) {
									ids[i] = data[i].id;
								}

								delFsm(ids)
							}
						});

		function editFsm(data) {
			openWin('修改', '${baseURL}/fsm/editUI?id=' + data.id, function() {
				reloadTable(1);
			});
		}

		//删除申请
		function delFsm(ids) {
			var deleteData = {
				"ids" : ids
			}
			parent.parent.layer.confirm('确认删除么', function(index) {
				$.ajax({
					url : "${baseURL}/fsm/delete",
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
