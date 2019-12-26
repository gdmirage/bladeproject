<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">被呼号码：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="destNum" autocomplete="off" class="layui-input">
	            </div>
	            
	            <label class="layui-form-label">任务名称：</label>
	             <div class="layui-input-inline" layui-form>
	                <select id="callTaskId" name="callTaskId" lay-search>
						<option value="">请选择</option>
						<c:forEach var="item" items="${callTaskList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
	            </div>
	            
	            <label class="layui-form-label">执行状态：</label>
	            <div class="layui-input-inline">
	                <select name="status" id="status">
						<option value="">请选择</option>
						<option value="RUNNING">执行中</option>
						<option value="COMPLETION">已完成</option>
						<option value="FAILD">失败</option>
					</select>
	            </div>
	            
	            <label class="layui-form-label">是否呼叫：</label>
	            <div class="layui-input-inline">
	                <select name="isCall" id="isCall">
						<option value="">请选择</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
	            </div>
	
	            <button class="layui-btn" id="searchCallTaskElement">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="callTaskElementTable" lay-filter="callTaskElementTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="add{objectName}"><i class="layui-icon">&#xe654;</i>增加</button>
    		<button class="layui-btn layui-btn-sm" lay-event="edit{objectName}"><i class="layui-icon">&#xe642;</i>修改</button>
    		<button class="layui-btn layui-btn-sm" lay-event="del{objectName}"><i class="layui-icon">&#xe640;</i>删除</button>
  		</div>
	</script>
	
    <script type="text/html" id="startAtTem">
        {{ timestampToTime(d.startAt) }}
    </script>
    
    <script type="text/html" id="acceptAtTem">
        {{ timestampToTime(d.acceptAt) }}
    </script>
    
    <script type="text/html" id="hangupAtTem">
        {{ timestampToTime(d.hangupAt) }}
    </script>
    
    <script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>
    
    <script type="text/html" id="showCallTask">
		{{# if (d.callTask != null) { }}
			{{d.callTask.name}}
		{{# } else { }}
			{{}}
		{{# } }}
	</script>
	
	<script type="text/html" id="isCallTem">
		{{# if (d.isCall === 1) { }}
			<span class="layui-badge layui-bg-blue">是</span>
		{{# } else { }}
			<span class="layui-badge">否</span>
		{{# } }}
	</script>
	
	<script type="text/html" id="statusTem">
			{{# if (d.status === 'RUNNING') { }}
            	<span class="layui-badge layui-bg-green">执行中</span>
            {{# } else if (d.status === 'COMPLETION') { }}
            	<span class="layui-badge layui-bg-black">结束</span>
            {{# } else if (d.status === 'FAILD') { }}
            	<span class="layui-badge layui-bg-red">失败</span>
            {{# }}}
	</script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
    	form.render();
        
        var tableInit = table.render({
            elem: '#callTaskElementTable'
            , url: '${baseURL}/callTaskElement/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	destNum: $('#destNum').val(),
            	isCall: $('#isCall').val(),
            	status: $('#status').val(),
            	callTaskId: $('#callTaskId').val()
            	
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'id', width: '1%', title: 'id', hide: true }
                , {field: 'callTaskName', width: '6%', title: '任务名称',templet:'#showCallTask'}
                , {field: 'destNum', width: '8%', title: '被叫号码'}
                , {field: 'isCall', width: '6%', title: '是否呼叫', templet:'#isCallTem'}
                , {field: 'status', width: '8%', title: '执行状态', templet:'#statusTem'}
                , {field: 'description', width: '10%', title: '描述'}
                , {field: 'startAt', width: '9%', title: '呼入时间', templet:'#startAtTem'}
                , {field: 'acceptAt', width: '9%', title: '接通时间', templet:'#acceptAtTem'}
                , {field: 'hangupAt', width: '9%', title: '挂机时间', templet:'#hangupAtTem'}
                , {field: 'createdAt', width: '9%', title: '创建时间', templet:'#createdAtTem'}
                , {field: 'property', width: '15%', title: '业务参数'}
                , {field: 'cdrUrl', title: '录音文件路径'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'callTaskElementTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	destNum: $('#destNum').val(),
                	isCall: $('#isCall').val(),
                	status: $('#status').val(),
                	callTaskId: $('#callTaskId').val()
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

        $('#searchCallTaskElement').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(callTaskElementTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'add{objectName}') {
        		openWin('新增', '${baseURL}/callTaskElement/addUI', function () {
                	reloadTable(1);
            	});
            } else if (obj.event === 'edit{objectName}') {
		        var checkStatus = table.checkStatus('callTaskElementTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editCallTaskElement(data[0])
       	 		}
            } else if (obj.event === 'del{objectName}') {
	            var checkStatus = table.checkStatus('callTaskElementTableId'), data = checkStatus.data;
            	if (data.length == 0) {
                	parent.parent.layer.alert('请选择一条或多条数据!');
                	return;
            	}
            	
            	var ids = [data.length];
            	for (var i = 0; i < data.length; i++) {
            		ids[i] = data[i].id;
            	}
            	
            	delCallTaskElement(ids)
            } 
        });

      	function editCallTaskElement(data){
      		openWin('修改', '${baseURL}/callTaskElement/editUI?id=' + data.id, function () {
                reloadTable(1);
            });
      	}

        //删除申请
        function delCallTaskElement(data) {
            var deleteData = {
                "ids" : data
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
            	$.ajax({
                    url : "${baseURL}/callTaskElement/delete",
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
                 });
            });
        }


    </script>
</div>
