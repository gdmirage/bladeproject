<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">座席姓名：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="name" autocomplete="off" class="layui-input">
	            </div>
	            
	            <label class="layui-form-label">工号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="cno" autocomplete="off" class="layui-input">
	            </div>
	            
	            <%-- <label class="layui-form-label">座席类型：</label>
			    <div class="layui-input-inline">
					<select id="userType">
						<option value="">请选择</option>
						<c:forEach var="item" items="${seatTypeList }">
							<option value="${item.value }">${item.desc }</option>
						</c:forEach>
					</select> 
	            </div> --%>
	            
	            <label class="layui-form-label">座席状态：</label>
			    <div class="layui-input-inline">
					<select id="status">
						<option value="">请选择</option>
						<c:forEach var="item" items="${statusList }">
							<option value="${item.value }">${item.desc }</option>
						</c:forEach>
					</select> 
	            </div>
	
	            <button class="layui-btn" id="searchUser">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
	
    <table class="layui-hide" id="userTable" lay-filter="userTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
			<div class="layui-btn-group">
    			<button class="layui-btn layui-btn-sm" lay-event="addSeat"><i class="layui-icon">&#xe654;</i>增加</button>
    			<button class="layui-btn layui-btn-sm" lay-event="editSeat"><i class="layui-icon">&#xe642;</i>修改</button>
    			<button class="layui-btn layui-btn-sm" lay-event="delSeat"><i class="layui-icon">&#xe640;</i>删除</button> 
			</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
	
	<script type="text/html" id="enabledTem">
		{{# if (d.enabled === 1) { }}
			<span class="layui-badge">已激活</span>
		{{# } else { }}
			<span class="layui-badge" style="background:#d2d2d2;">未激活</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="statusTem">
		{{# if (d.status === 'up') { }}
			<i class="iconfont" style="color:#1afa29;">&#xe641;</i>
			<span class="layui-badge" style="background:#1afa29;">上线</span>
		{{# } else { }}
			<i class="iconfont" style="color:#d2d2d2;">&#xe641;</i>
			<span class="layui-badge" style="background:#d2d2d2;">下线</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="userTypeTem">
		{{# if (d.userType === 'leader') { }}
			<span class="layui-badge layui-bg-blue">班长坐席</span>
		{{# } else if (d.userType === 'employee') { }}
			<span class="layui-badge layui-bg-orange">普通座席</span>
		{{# } else { }}
			<span class="layui-badge">备用坐席</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="callNumTem">
		{{# if (d.callNum) { }}
			{{d.callNum}}
		{{# } else { }}
			<font style="color:red">未绑定分机</font>
		{{# } }}
	</script>
	
	<script type="text/html" id="butTem">
		{{# if(d.status =='up'){	}}
			<a class="layui-btn layui-btn-xs" lay-event="setStatusDown"><i class="iconfont">&#xe65f;</i>下线</a>
		{{#	}else{	}} 
			<a class="layui-btn layui-btn-xs" lay-event="setStatusUp"><i class="layui-icon" style="margin-right: 5px; margin-left: 3px;">&#xe67c;</i>上线</a>
		{{#	}	}}

		{{# if(d.enabled == 1){	}}
			<a class="layui-btn layui-btn-xs" lay-event="editUserByUnEnabled"><i class="layui-icon">&#x1006;</i>停用</a>
		{{#	}else{	}} 
			<a class="layui-btn layui-btn-xs" lay-event="editUserByEnabled"><i class="layui-icon">&#xe605;</i>激活</a>
		{{#	}	}}
		
		<a class="layui-btn layui-btn-xs" lay-event="bindNumber"><i class="layui-icon">&#xe64d;</i>绑定分机</a>
	</script>
	
    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
    	form.render();
        
        var tableInit = table.render({
            elem: '#userTable'
            , url: '${baseURL}/seat/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	name: $('#name').val()
            	, cno: $('#cno').val()
            	, userType: $('#userType').val()
            	, status: $('#status').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'cno', width: '15%', title: '工号'}
                , {field: 'name', width: '20%', title: '座席姓名'} 
                , {field: 'callNum', width: '15%', title: '分机',templet:'#callNumTem'}
                , {field: 'status', width: '15%', title: '当前状态',templet:'#statusTem'}
                //, {field: 'userType', width: '10%', title: '座席类型',templet:'#userTypeTem'}
                , {field: 'enabled', width: '10%', title: '是否激活',templet:'#enabledTem'}
                , {field: '',  title: '操作',templet:'#butTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'userTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	name: $('#name').val()
                	, cno: $('#cno').val()
                	, userType: $('#userType').val()
                	, status: $('#status').val()
                }
            };
            //组装分页参数,page为空时，取当前页数
            if (page) {
                loadData.page = {
                    curr: page //重新从第page页开始
                }
            }

            tableInit.reload(loadData);
        }

        $('#searchUser').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(userTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addSeat') {
        		openWin('新增', '${baseURL}/seat/addUI', function () {
                	reloadTable(1);
            	});
            } else if (obj.event === 'editSeat') {
		        var checkStatus = table.checkStatus('userTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editUser(data[0])
       	 		}
            } else if (obj.event === 'delSeat') {
	            var checkStatus = table.checkStatus('userTableId'), data = checkStatus.data;
	            if (data.length == 0) {
					parent.parent.layer.alert('请选择一条或多条数据!');
					return;
				}

				var ids = [ data.length ];
				for (var i = 0; i < data.length; i++) {
					ids[i] = data[i].id;
				}

				delUser(ids)
            } 
        });

      	function editUser(data){
      		openWin('修改', '${baseURL}/seat/editUI?id=' + data.id, function () {
                reloadTable(1);
            });
      	}

        //删除申请
        function delUser(ids) {
        	var deleteData = {
					"ids" : ids
				}
				parent.parent.layer.confirm('确认删除么', function(index) {
					$.ajax({
						url : "${baseURL}/seat/delete",
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
        table.on('tool(userTable)', function (obj) {
        	var data = obj.data;
            if (obj.event === 'setStatusUp') {
            	setStatusUp(data);
            } else if (obj.event === 'setStatusDown') {
            	setStatusDown(data);
            } else if (obj.event === 'setStatusDown') {
            	setStatusDown(data);
            } else if (obj.event === 'setStatusDown') {
            	setStatusDown(data);
            }  else if (obj.event === 'editUserByUnEnabled') {
            	editUserByUnEnabled(data);
            } else if (obj.event === 'editUserByEnabled') {
            	editUserByEnabled(data);
            } else if(obj.event === 'bindNumber') {
            	bindNumber(data);
            }
        });
        
        function bindNumber(data){
        	layer.open({
       			type: 2,
       			title:'绑定号码',
       		  	skin: 'layui-layer-rim', //加上边框
       		  	area: ['680px', '680px'], //宽高
       		 	content: '${baseURL}/seat/bindNumberUI?id=' + data.id
           		 ,zIndex: layer.zIndex
                 ,success: function(layero){
                   layer.setTop(layero);
                 }
                 ,end: function (index, layero) {
                	 reloadTable();
                 }
       		});
        }
        
      	//停用
		function editUserByUnEnabled(data){
			parent.parent.layer.confirm('确认停用坐席【' + data.name + '】么', function (index) {
				var paramData = {
						id : data.id
			    }
				var url = "${baseURL}/seat/editUserByUnEnabled";
				post(url,paramData,function(data){
					reloadTable();
					parent.parent.layer.msg('停用成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				parent.parent.layer.msg(data.msg,{
					  icon: 2,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					});
	  			});
			});
		}
      
		//激活
		function editUserByEnabled(data){
			parent.parent.layer.confirm('确认激活坐席【' + data.name + '】么', function (index) {
				var paramData = {
						id : data.id
			    }
				var url = "${baseURL}/seat/editUserByEnabled";
				post(url,paramData,function(data){
					reloadTable();
					parent.parent.layer.msg('激活成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				parent.parent.layer.msg(data.msg,{
					  icon: 2,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					});
	  			});
			});
		}
        
      	//上线
		function setStatusUp(data){
			parent.parent.layer.confirm('确认上线坐席【' + data.name + '】么', function (index) {
				var paramData = {
						id : data.id
			    }
				var url = "${baseURL}/seat/setStatusUp";
				post(url,paramData,function(data){
					reloadTable();
					parent.parent.layer.msg('上线成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				parent.parent.layer.msg(data.msg,{
					  icon: 2,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					});
	  			});
			});
		}
      	
		//下线
		function setStatusDown(data){
			parent.parent.layer.confirm('确认下线坐席【' + data.name + '】么', function (index) {
				var paramData = {
						id : data.id
			    }
				var url = "${baseURL}/seat/setStatusDown";
				post(url,paramData,function(data){
					reloadTable();
					parent.parent.layer.msg('下线成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				parent.parent.layer.msg(data.msg,{
					  icon: 2,
					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
					});
	  			});
			});
		}


    </script>
</div>
