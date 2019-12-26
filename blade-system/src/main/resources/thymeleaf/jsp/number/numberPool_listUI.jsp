<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">分机号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="callNum" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchNumberPool">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="numberPoolTable" lay-filter="numberPoolTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addNumberPool"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editNumberPool"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delNumberPool"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createdAt) }}
    </script>
    
    <script type="text/html" id="enabledTem">
		{{# if (d.enabled === 1) { }}
			<span class="layui-badge">可用</span>
		{{# } else { }}
			<span class="layui-badge" style="background:#d2d2d2;">不可用</span>
		{{# } }}
	</script>
	
    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#numberPoolTable'
            , url: '${baseURL}/numberPool/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	username: $('#username').val()
            	,callNum: $('#callNum').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                //, {field: 'numberName', width: '15%', title: '分机名称'}
                , {field: 'code', width: '10%', title: '区号'}
                , {field: 'callNum', width: '20%', title: '分机号码'}
                , {field: 'username', width: '20%', title: '分机名称'}
                , {field: 'password', width: '20%', title: '注册密码'}
                // , {field: 'status', width: '20%', title: '状态',templet:'#statusTem'}
                , {field: 'enabled', width: '10%', title: '是否可用',templet:'#enabledTem'}
                , {field: 'createdAt',  title: '创建时间',templet:'#dateTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'numberPoolTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	username: $('#username').val()
                	,callNum: $('#callNum').val()
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

        $('#searchNumberPool').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(numberPoolTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addNumberPool') {
        		openWin('添加分机', '${baseURL}/numberPool/addUI', function () {
        			$("button.layui-laypage-btn").click();
            	});
            } else if (obj.event === 'editNumberPool') {
		        var checkStatus = table.checkStatus('numberPoolTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editNumberPool(data[0])
       	 		}
            } else if (obj.event === 'delNumberPool') {
            	var checkStatus = table.checkStatus('numberPoolTableId'), data = checkStatus.data;
	            if (data.length == 0) {
					parent.parent.layer.alert('请选择一条或多条数据!');
					return;
				}

				var ids = [ data.length ];
				for (var i = 0; i < data.length; i++) {
					ids[i] = data[i].id;
				}
                delNumberPool(ids)
            } 
        });

      	function editNumberPool(data){
      		openWin('修改分机', '${baseURL}/numberPool/editUI?id=' + data.id, function () {
      			$("button.layui-laypage-btn").click();
            });
      	}

        //删除申请
        function delNumberPool(ids) {
            var deleteData = {
            		"ids" : ids
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/numberPool/delete";
                $.ajax({
					url : url,
					type : "POST",
					data : deleteData,
					traditional : true,
					dataType : "json",
					async : false,
					success : function(data) {
						$("button.layui-laypage-btn").click();
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
