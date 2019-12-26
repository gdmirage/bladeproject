<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">线路池名：</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off" class="layui-input">
				</div>
				<label class="layui-form-label">呼叫类型：</label>
				<div class="layui-input-inline" >
					<select id="callType" name="callType" required lay-verify="required">
						<option value="">请选择</option>
						<c:forEach var="item" items="${callTypeList }">
							<option value="${item.value }">${item.desc }</option>
						</c:forEach>
					</select>
				</div>
				<button class="layui-btn" id="searchLinePool">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="linePoolTable" lay-filter="linePoolTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
			<div class="layui-btn-group">
    			<button class="layui-btn layui-btn-sm" lay-event="add{objectName}"><i class="layui-icon">&#xe654;</i>增加</button>
    			<button class="layui-btn layui-btn-sm" lay-event="edit{objectName}"><i class="layui-icon">&#xe642;</i>修改</button>
    			<button class="layui-btn layui-btn-sm" lay-event="del{objectName}"><i class="layui-icon">&#xe640;</i>删除</button>
			</div>
  		</div>
	</script>

	<script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>
	<script type="text/html" id="editTimeTem">
        {{ timestampToTime(d.editTime) }}
    </script>
	<script type="text/html" id="callTypeTem">
		{{# if (d.callType === 'outbound') { }}
			<span class="layui-badge layui-bg-green">呼出</span>
		{{# } else if (d.callType === 'inbound'){ }}
			<span class="layui-badge">呼入</span>
		{{# } }}
	</script>
	
	<script>
		var table = layui.table;
		var $ = layui.jquery;

		var form = layui.form;
		form.render();

		var tableInit = table.render({
			elem : '#linePoolTable',
			url : '${baseURL}/linePool/list',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				name : $('#name').val(),
				callType : $('#callType').val()
			},
			cols : [[
				{type : 'checkbox',width : '5%'}
				, {field : 'name',width : '35%',title : '线路池名称'}
				, {field : 'callType',width : '20%',title : '呼叫类型',templet:'#callTypeTem'}
				, {field : 'createdAt',width : '20%',title : '创建时间',templet : '#createdAtTem'}
				, {field : 'editTime',title : '修改时间',templet : '#editTimeTem'}
			]],
			limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,
			limit : 10 //默认采用10
			,
			page : true,
			id : 'linePoolTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					name : $('#name').val(),
					callType : $('#callType').val()
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

		$('#searchLinePool').on('click', function() {
			reloadTable(1);
		});

		table.on('toolbar(linePoolTable)',function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			var data = checkStatus.data;
			if (obj.event === 'add{objectName}') {
				openWin('新增', '${baseURL}/linePool/addUI',
						function() {
							reloadTable(1);
						});
			} else if (obj.event === 'edit{objectName}') {
				var checkStatus = table
						.checkStatus('linePoolTableId'), data = checkStatus.data;
				if (data.length != 1) {
					parent.parent.layer.alert('请选择一条编辑数据');
				} else {
					editLinePool(data[0])
				}
			} else if (obj.event === 'del{objectName}') {
				var checkStatus = table
						.checkStatus('linePoolTableId'), data = checkStatus.data;
				if (data.length == 0) {
					parent.parent.layer.alert('请选择一条或多条数据!');
					return;
				}

				var ids = [ data.length ];
				for (var i = 0; i < data.length; i++) {
					ids[i] = data[i].id;
				}

				delLinePool(ids)
			}
		});

		function editLinePool(data) {
			openWin('修改', '${baseURL}/linePool/editUI?id=' + data.id,
					function() {
						reloadTable(1);
					});
		}

		//删除申请
		function delLinePool(ids) {

			var deleteData = {
				"ids" : ids
			}
			parent.parent.layer.confirm('确认删除么', function(index) {
				$.ajax({
					url : "${baseURL}/linePool/delete",
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
		
		
		//列按钮监听
        table.on('tool(linePoolTable)', function (obj) {
        	var data = obj.data;
            if(obj.event === 'bind') {
            	openWin('线路池资源管理', '${baseURL}/linePoolElement/listUI',function() {
				});
            }
        });
		
		
	</script>
</div>
