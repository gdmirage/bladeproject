<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">查询字段：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="查询字段" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchCallCenterAuth">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="callCenterAuthTable" lay-filter="callCenterAuthTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addCallCenterAuth"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editCallCenterAuth"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delCallCenterAuth"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createAt) }}
    </script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#callCenterAuthTable'
            , url: '${baseURL}/callCenterAuth/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
                test: $('#test').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'userName', width: '15%', title: '账户名称'}
                , {field: 'description', width: '15%', title: '账户描述'}
                , {field: 'code', width: '10%', title: '区号'}
                , {field: 'ip', width: '10%', title: '本局IP'}
                , {field: 'port', width: '10%', title: '本局端口'}
                , {field: 'authKey', width: '20%', title: '授权码'}
                , {field: 'createAt', title: '创建时间',templet:'#dateTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'callCenterAuthTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                    test: $('#test').val()
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

        $('#searchCallCenterAuth').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(callCenterAuthTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addCallCenterAuth') {
        		openWin('新增', '${baseURL}/callCenterAuth/addUI', function () {
                	reloadTable(1);
            	});
            } else if (obj.event === 'editCallCenterAuth') {
		        var checkStatus = table.checkStatus('callCenterAuthTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editCallCenterAuth(data[0])
       	 		}
            } else if (obj.event === 'delCallCenterAuth') {
	            var checkStatus = table.checkStatus('callCenterAuthTableId'), data = checkStatus.data;
            	if (data.length != 1) {
                	parent.parent.layer.alert('请选择一条数据');
            	} else {
                	delCallCenterAuth(data[0])
            	}
            } 
        });

      	function editCallCenterAuth(data){
      		openWin('修改', '${baseURL}/callCenterAuth/editUI?id=' + data.id, function () {
                reloadTable(1);
            });
      	}

        //删除申请
        function delCallCenterAuth(data) {
            var deleteData = {
                id : data.id
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/callCenterAuth/delete";
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
                });
            });
        }


    </script>
</div>
