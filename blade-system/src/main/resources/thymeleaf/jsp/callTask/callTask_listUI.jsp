<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">任务名称：</label>
	            <div class="layui-input-inline">
	                <input type="name" id="name" autocomplete="off" class="layui-input">
	            </div>
	            
	            <%-- <label class="layui-form-label">座席组名：</label>
	            <div class="layui-input-inline" layui-form>
	                <select id="groupId" name="groupId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${userGroupList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
	            </div> --%>
	
	            <button class="layui-btn" id="searchCallTask">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="callTaskTable" lay-filter="callTaskTable"></table>
	
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
     <script type="text/html" id="startTimeTem">
        {{ timestampToTime(d.startTime) }}
    </script>
     <script type="text/html" id="endTimeTem">
        {{ timestampToTime(d.endTime) }}
    </script>
    
     <script type="text/html" id="showUserGroup">
		{{# if (d.userGroup != null) { }}
			{{d.userGroup.name}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>
    
    <script type="text/html" id="statusTem">
			{{# if (d.status === 'init') { }}
            	<span class="layui-badge layui-bg-blue">初始</span>
            {{# } else if (d.status === 'start') { }}
            	<span class="layui-badge layui-bg-green">运行中</span>
            {{# } else if (d.status === 'pause') { }}
            	<span class="layui-badge layui-bg-orange">暂停</span>
            {{# } else if (d.status === 'end') { }}
            	<span class="layui-badge layui-bg-red">结束</span>
            {{# }}}
	</script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
    	form.render();
        
        
        var tableInit = table.render({
            elem: '#callTaskTable'
            , url: '${baseURL}/callTask/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	name: $('#name').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'id', width: '2%', title: 'id', hide: true }
                //, {field: 'groupId', width: '10%', title: '座席组', templet:'#showUserGroup'}
                , {field: 'name', width: '10%', title: '任务名称'}
                , {field: 'description', width: '10%', title: '描述'}
                , {field: 'lineCount', width: '10%', title: '并发数'}
                , {field: 'status', width: '10%', title: '状态', templet:'#statusTem'}
                , {field: 'startTime', width: '10%', title: '开始时间', templet:'#startTimeTem'}
                , {field: 'endTime', width: '20%', title: '结束时间', templet:'#endTimeTem'}
                , {field: 'createdAt', title: '创建时间', templet:'#createdAtTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'callTaskTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	name: $('#name').val()
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

        $('#searchCallTask').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(callTaskTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'add{objectName}') {
        		openWin('新增', '${baseURL}/callTask/addUI', function () {
                	reloadTable(1);
            	});
            } else if (obj.event === 'edit{objectName}') {
		        var checkStatus = table.checkStatus('callTaskTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editCallTask(data[0])
       	 		}
            } else if (obj.event === 'del{objectName}') {
	            var checkStatus = table.checkStatus('callTaskTableId'), data = checkStatus.data;
	            if (data.length == 0) {
                	parent.parent.layer.alert('请选择一条或多条数据!');
                	return;
            	}
            	
            	var ids = [data.length];
            	for (var i = 0; i < data.length; i++) {
            		ids[i] = data[i].id;
            	}
            	
            	delCallTask(ids)
            } 
        });

      	function editCallTask(data){
      		openWin('修改', '${baseURL}/callTask/editUI?id=' + data.id, function () {
                reloadTable(1);
            });
      	}

        //删除申请
        function delCallTask(data) {
            var deleteData = {
                "ids" : data
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
            	$.ajax({
                    url : "${baseURL}/callTask/delete",
                    type : "POST",
                    data : deleteData,
                    traditional: true,
                    dataType : "json",
                    async: false,
                    success : function(data) {
                    	reloadTable();
                        parent.parent.layer.close(index);
                        if (data.code == -999) {
                        	parent.parent.layer.alert(data.msg, {
                                icon: 2
                            });
                        } else {
                        	parent.parent.layer.msg("删除成功！", {
                                icon: 1,
                                time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    },
                    error:function(data){
                    	parent.parent.layer.alert(data.msg, {
                            icon: 2
                        });
                    }
                 })
                /* var url = "${baseURL}/callTask/delete";
                post(url, deleteData, function (data) {
                    reloadTable();
                    parent.parent.layer.close(index);
                    parent.parent.layer.msg('删除成功', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }, function (data) {
                    parent.parent.layer.alert(data.msg, {
                        icon: 2
                    });
                }); */
            });
        }


    </script>
</div>
