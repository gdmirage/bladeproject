<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">名称：</label>
				<div class="layui-input-inline">
					<input type="text" id="name" autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label">服务器IP：</label>
				<div class="layui-input-inline">
					<input type="text" id="sipServerIp" autocomplete="off"
						class="layui-input">
				</div>

				<label class="layui-form-label">端口：</label>
				<div class="layui-input-inline">
					<input type="text" id="sipServerPort" autocomplete="off"
						class="layui-input">
				</div>
				
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-inline">
					<input type="text" id="username" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">呼叫类别：</label>
				<div class="layui-input-inline">
					<select name="gatewayCallType" id="gatewayCallType">
						<option value="">请选择</option>
						<c:forEach var="item" items="${callTypeList }">
							<option value="${item.value }">${item.desc }</option>
						</c:forEach>
					</select>
				</div>

				<label class="layui-form-label">是否注册：</label>
				<div class="layui-input-inline">
					<select id="isRegister">
						<option value="">请选择</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</div>

				<button class="layui-btn" id="searchGateway">
					<i class="layui-icon">&#xe615;</i>查询
				</button>
			</div>
		</div>
	</blockquote>

	<table class="layui-hide" id="gatewayTable" lay-filter="gatewayTable"></table>

	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="edit"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</button>
				<button class="layui-btn layui-btn-sm" lay-event="synGateway"><i class="layui-icon">&#xe9aa;</i>更新同步交换机状态</button>
			</div>
  		</div>
	</script>

	<script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>

	<script type="text/html" id="isRegisterTem">
		{{# if (d.isRegister === 1) { }}
			<span class="layui-badge layui-bg-blue">可以</span>
		{{# } else { }}
			<span class="layui-badge" layui-bg-cyan>不可以</span>
		{{# } }}
	</script>

	<script type="text/html" id="enabledTem">
		{{# if (d.enabled === 1) { }}
			<span class="layui-badge layui-bg-green">可用</span>
		{{# } else { }}
			<span class="layui-badge layui-bg-red">不可用</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="isSpecialLineTem">
		{{# if (d.isSpecialLine === 1) { }}
			<span class="layui-badge layui-bg-green">是</span>
		{{# } else { }}
			<span class="layui-badge layui-bg-red">否</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="gatewayCallTypeTem">
		{{# if (d.gatewayCallType === 'outbound') { }}
			<span class="layui-badge layui-bg-green">呼出</span>
		{{# } else if (d.gatewayCallType === 'inbound'){ }}
			<span class="layui-badge ">呼入</span>
		{{# } }}
	</script>

	<script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>


	<script>
		var table = layui.table;
		var $ = layui.jquery;
		//表单下拉框
		var form = layui.form;
		form.render();

		var tableInit = table.render({
			elem : '#gatewayTable',
			url : '${baseURL}/gateway/list',
			toolbar : '#tableToolbar',
			defaultToolbar : [ 'filter', 'print' ],
			request : {
				pageName : 'pageNum' //页码的参数名称，默认：page
				,
				limitName : 'numPerPage' //每页数据量的参数名，默认：limit
			},
			where : {
				name : $('#name').val(),
				sipServerIp : $('#sipServerIp').val(),
				sipServerPort : $('#sipServerPort').val(),
				username : $('#username').val(),
				isRegister : $('#isRegister').val(),
				enabled : $('#enabled').val(),
				gatewayCallType : $('#gatewayCallType').val()
			},
			cols : [[ 
				{ type : 'checkbox', width : '3%' }
				, { field : 'name', width : '10%', title : '名称' }
				, { field : 'sipServerIp', width : '10%', title : '服务器IP' }
				, { field : 'sipServerPort', width : '8%', title : '端口' }
				, { field : 'username', width : '10%', title : '用户名' }
				, { field : 'password', width : '7%', title : '密码' }
				, { field : 'realityNum', width : '10%', title : '外显号码' }
				, { field : 'gatewayCallType', width : '10%', title : '呼叫类型', templet : '#gatewayCallTypeTem' }
				, { field : 'isSpecialLine', width : '10%', title : '是否专线', templet : '#isSpecialLineTem' }
				, { field : 'isRegister', width : '10%', title : '是否可注册', templet : '#isRegisterTem' }
				, { field : 'createdAt', title : '创建时间', templet : '#createdAtTem' } 
			]]
			,limits : [ 5, 10, 20, 50 ]//每页数据选择项
			,limit : 10 //默认采用10
			,page : true
			,id : 'gatewayTableId'
		});

		//刷新table，带上搜索框条件
		function reloadTable(page) {
			//定义重载table参数
			var loadData = {
				where : {
					name : $('#name').val(),
					sipServerIp : $('#sipServerIp').val(),
					sipServerPort : $('#sipServerPort').val(),
					username : $('#username').val(),
					isRegister : $('#isRegister').val(),
					enabled : $('#enabled').val(),
					gatewayCallType : $('#gatewayCallType').val()
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

		$('#searchGateway').on('click', function() {
			reloadTable(1);
		});

		table.on('toolbar(gatewayTable)',function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			var data = checkStatus.data;
			if (obj.event === 'add') {
				openWin('新增', '${baseURL}/gateway/addUI',function() {
					reloadTable(1);
				});
			} else if (obj.event === 'edit') {
				var checkStatus = table.checkStatus('gatewayTableId'), data = checkStatus.data;
				if (data.length != 1) {
					parent.parent.layer.alert('请选择一条编辑数据');
				} else {
					editGateway(data[0])
				}
			} else if (obj.event === 'del') {
				var checkStatus = table.checkStatus('gatewayTableId'), data = checkStatus.data;
				if (data.length == 0) {
					parent.parent.layer.alert('请选择一条或多条数据!');
					return;
				}

				var ids = [ data.length ];
				for (var i = 0; i < data.length; i++) {
					ids[i] = data[i].id;
				}

				delGateway(ids)
			} else if (obj.event === 'synGateway') {
				synGateway()
			}
		});

		function editGateway(data) {
			openWin('修改', '${baseURL}/gateway/editUI?id=' + data.id,function() {
				$("button.layui-laypage-btn").click();
			});
		}

		function synGateway() {
			 var deleteData = {
			}
			parent.parent.layer.confirm('确认同步交换机状态？', function(index) {
				$.ajax({
					url : "${baseURL}/gateway/synGateway",
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
							parent.parent.layer.msg("同步成功！", {
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

		//删除申请
		function delGateway(ids) {
			var deleteData = {
					"ids" : ids
				}
				parent.parent.layer.confirm('确认删除么', function(index) {
					$.ajax({
						url : "${baseURL}/gateway/delete",
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

		function listAllStatus(data) {
			conlog(data.length());
			for (var i = 0; i < data.length(); i++) {
				conlog(d.status)
			}
		}
	</script>
</div>
