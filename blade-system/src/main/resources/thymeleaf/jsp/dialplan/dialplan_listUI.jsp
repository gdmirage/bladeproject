<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">规则名称：</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label">呼入号码：</label>
				<div class="layui-input-inline">
					<input type="text" id="destCallNum" autocomplete="off"
						class="layui-input">
				</div>

				<label class="layui-form-label">状态机名：</label>
				<div class="layui-input-inline" layui-form>
					<select id="fsmId" name="fsmId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${fsmList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</div>

				<button class="layui-btn" id="searchDialplan">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="dialplanTable" lay-filter="dialplanTable"></table>

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

	<script type="text/html" id="showFsm">
		{{# if (d.fsm != null) { }}
			{{d.fsm.name}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>


	<script>
		var table = layui.table;
		var $ = layui.jquery;
		var form = layui.form;
		form.render();

		var tableInit = table.render({
			elem : '#dialplanTable',
			url : '${baseURL}/dialplan/list',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				name : $('#name').val(),
				destCallNum : $('#destCallNum').val(),
				fsmId : $('#fsmId').val()
			},
			cols : [ [ {
				type : 'checkbox',
				width : '5%'
			}, {
				field : 'id',
				width : '20%',
				title : 'id',
				hide : true
			}, {
				field : 'name',
				width : '15%',
				title : '规则名称'
			}, {
				field : 'destCallNum',
				width : '20%',
				title : '呼入号码'
			}, {
				field : 'fsmName',
				width : '20%',
				title : '状态机名',
				templet : '#showFsm'
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
			id : 'dialplanTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					name : $('#name').val(),
					destCallNum : $('#destCallNum').val(),
					fsmId : $('#fsmId').val()
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

		$('#searchDialplan').on('click', function() {
			reloadTable(1);
		});

		table
				.on(
						'toolbar(dialplanTable)',
						function(obj) {
							var checkStatus = table.checkStatus(obj.config.id);
							var data = checkStatus.data;
							if (obj.event === 'add{objectName}') {
								openWin('新增', '${baseURL}/dialplan/addUI',
										function() {
											reloadTable(1);
										});
							} else if (obj.event === 'edit{objectName}') {
								var checkStatus = table
										.checkStatus('dialplanTableId'), data = checkStatus.data;
								if (data.length != 1) {
									parent.parent.layer.alert('请选择一条编辑数据');
								} else {
									editDialplan(data[0])
								}
							} else if (obj.event === 'del{objectName}') {
								var checkStatus = table
										.checkStatus('dialplanTableId'), data = checkStatus.data;
								if (data.length == 0) {
									parent.parent.layer.alert('请选择一条或多条数据!');
									return;
								}

								var ids = [ data.length ];
								for (var i = 0; i < data.length; i++) {
									ids[i] = data[i].id;
								}

								delDialplan(ids)
							}
						});

		function editDialplan(data) {
			openWin('修改', '${baseURL}/dialplan/editUI?id=' + data.id,
					function() {
						reloadTable(1);
					});
		}

		//删除申请
		function delDialplan(data) {
			var deleteData = {
				"ids" : data
			}
			parent.parent.layer.confirm('确认删除么', function(index) {
				$.ajax({
					url : "${baseURL}/dialplan/delete",
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
