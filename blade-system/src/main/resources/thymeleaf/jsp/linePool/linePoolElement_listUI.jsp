<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">线路池名：</label>
				<div class="layui-input-inline" layui-form>
					<select id="linePoolId" name="linePoolId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${linePoolList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>
				
				<label class="layui-form-label">线路名：</label>
				<div class="layui-input-inline" layui-form>
					<select id="gatewayId" name="gatewayId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${gatewayList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>

				<button class="layui-btn" id="searchLinePoolElement">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="linePoolElementTable"
		lay-filter="linePoolElementTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="add{objectName}"><i class="layui-icon">&#xe654;</i>增加</button>
    		<button class="layui-btn layui-btn-sm" lay-event="edit{objectName}"><i class="layui-icon">&#xe642;</i>修改</button>
    		<button class="layui-btn layui-btn-sm" lay-event="del{objectName}"><i class="layui-icon">&#xe640;</i>删除</button>
  		</div>
	</script>

	<script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>

	<script type="text/html" id="showLinePool">
		{{# if (d.linePool != null) { }}
			{{d.linePool.name}}
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

	<script>
		var table = layui.table;
		var $ = layui.jquery;

		//表单下拉框
		var form = layui.form;
		form.render();

		var tableInit = table.render({
			elem : '#linePoolElementTable',
			url : '${baseURL}/linePoolElement/list',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				gatewayId : $('#gatewayId').val(),
				linePoolId : $('#linePoolId').val()
			},
			cols : [[
				{type : 'checkbox',width : '5%'}
				, {field : 'linePoolId',width : '30%',title : '线路池名',templet : '#showLinePool'}
				, {field : 'gatewayId',width : '30%',title : '线路名',templet : '#showGateway'}
				, {field : 'createdAt',title : '创建时间',templet : '#createdAtTem'}
			]],
			limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,
			limit : 10 //默认采用10
			,
			page : true,
			id : 'linePoolElementTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					gatewayId : $('#gatewayId').val(),
					linePoolId : $('#linePoolId').val()
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

		$('#searchLinePoolElement').on('click', function() {
			reloadTable(1);
		});

		table
				.on(
						'toolbar(linePoolElementTable)',
						function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							var data = checkStatus.data;
							if (obj.event === 'add{objectName}') {
								openWin('新增',
										'${baseURL}/linePoolElement/addUI',
										function() {
											reloadTable(1);
										});
							} else if (obj.event === 'edit{objectName}') {
								var checkStatus = table
										.checkStatus('linePoolElementTableId'), data = checkStatus.data;
								if (data.length != 1) {
									parent.parent.layer.alert('请选择一条编辑数据');
								} else {
									editLinePoolElement(data[0])
								}
							} else if (obj.event === 'del{objectName}') {
								var checkStatus = table
										.checkStatus('linePoolElementTableId'), data = checkStatus.data;
								if (data.length == 0) {
									parent.parent.layer.alert('请选择一条或多条数据!');
									return;
								}

								var ids = [ data.length ];
								for (var i = 0; i < data.length; i++) {
									ids[i] = data[i].id;
								}

								delLinePoolElement(ids)
							}
						});

		function editLinePoolElement(data) {
			openWin('修改', '${baseURL}/linePoolElement/editUI?id=' + data.id,
					function() {
						reloadTable(1);
					});
		}

		//删除申请
		function delLinePoolElement(ids) {
			var deleteData = {
					"ids" : ids
				}
				parent.parent.layer.confirm('确认删除么', function(index) {
					$.ajax({
						url : "${baseURL}/linePoolElement/delete",
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
