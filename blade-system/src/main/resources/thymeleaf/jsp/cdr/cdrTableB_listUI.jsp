<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">网关：</label>
            <div class="layui-input-inline">
                <input type="text" id="gatewayId" autocomplete="off" class="layui-input">
            </div>

            <button class="layui-btn" id="searchCdrTableB">
                <i class="layui-icon">&#xe615;</i>查询
            </button>
        </div>
    </div>

   <!--  <hr>

    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-sm " id="addCdrTableB"><i class="layui-icon">&#xe654;</i>增加</button>
        <button class="layui-btn layui-btn-sm " id="editCdrTableB"><i class="layui-icon">&#xe642;</i>修改</button>
		<button class="layui-btn layui-btn-sm " id="delCdrTableB"><i class="layui-icon">&#xe640;</i>删除</button>
    </div> -->
    <table class="layui-hide" id="cdrTableBTable" lay-filter="cdrTableBTable"></table>

    <script type="text/html" id="startStampTem">
        {{ timestampToTime(d.startStamp) }}
    </script>
    
    <script type="text/html" id="answerStampTem">
        {{ timestampToTime(d.answerStamp) }}
    </script>
    
    <script type="text/html" id="dest_caller_active_timeTem">
        {{ timestampToTime(d.dest_caller_active_time) }}
    </script>
    
    <script type="text/html" id="source_caller_hungup_timeTem">
        {{ timestampToTime(d.source_caller_hungup_time) }}
    </script>
    
    <script type="text/html" id="dest_caller_hungup_timeTem">
        {{ timestampToTime(d.dest_caller_hungup_time) }}
    </script>
    
    <script type="text/html" id="source_caller_active_timeTem">
        {{ timestampToTime(d.source_caller_active_time) }}
    </script>
    
    <script type="text/html" id="createdAtTem">
        {{ timestampToTime(d.createdAt) }}
    </script>
    
    <script type="text/html" id="tableToolbar">
	</script>

    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#cdrTableBTable'
            , url: '${baseURL}/cdrTableB/list/'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	gatewayId: $('#gatewayId').val()
            }
            , cols: [[
                //{type: 'checkbox', width: '5%'}
                //{field: 'channel_uuid', width: '10%', title: 'channel_uuid'}
                {field: 'dest_caller_gateway', width: '10%', title: '被叫使用网关'}
                , {field: 'dest_caller_number', width: '10%',title: '被叫号码'}
                , {field: 'source_caller_gateway', width: '10%',title: '主叫使用网关'}
                , {field: 'source_caller_number', width: '10%',title: '主叫号码'}
                , {field: 'dest_caller_active_time', width: '10%', title: '被叫接通时间',templet: '#dest_caller_active_timeTem'}
                , {field: 'dest_caller_hungup_time', width: '10%',title: '被叫挂机时间',templet: '#dest_caller_hungup_timeTem'}
                , {field: 'source_caller_active_time', width: '10%',title: '主叫接通时间',templet: '#source_caller_active_timeTem'}
                , {field: 'source_caller_hungup_time', width: '10%',title: '主叫挂机时间',templet: '#source_caller_hungup_timeTem'}
                , {field: 'createdAt', width: '10%',title: '创建时间',templet: '#createdAtTem'}
                , {field: 'who_hangup', width: '10%',title: '挂机人'}
                , {field: 'erlang_node', width: '10%',title: 'erlang_node'}
                , {field: 'raw_recording_file', width: '10%',title: '录音文件地址'}
                , {field: 'raw_recording_url', width: '10%',title: '录音文件URL'}
                , {field: 'is_error', width: '10%',title: 'is_error'}
                , {field: 'err_msg', width: '10%',title: 'err_msg'}
                , {field: 'error_side', width: '10%',title: 'error_side'}
                /* , {field: '', title: 'start_stamp',width: '10%', templet: '#startStampTem'}
                , {field: '', title: 'answer_stamp', width: '10%',templet: '#answerStampTem'}
                , {field: '', title: 'end_stamp', width: '10%',templet: '#endStampTem'}
                , {field: 'uduration',width: '10%', title: 'uduration'}
                , {field: 'billsec',width: '10%', title: 'billsec'}
                , {field: 'hangupCause',width: '10%', title: 'hangup_cause'} */
            ]]
            //, limits: [5, 10, 20, 50]//每页数据选择项
            //, limit: 10 //默认采用10
            , page: false
            , id: 'cdrTableBTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	gatewayId: $('#gatewayId').val()
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

        $('#searchCdrTableB').on('click', function () {
            reloadTable(1);
        });

    </script>
</div>
