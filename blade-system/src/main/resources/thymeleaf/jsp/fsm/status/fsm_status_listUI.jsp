<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/common/taglib/taglib.jsp" %>

<style>
.layui-table-cell{
	height: auto;
}
.red{
	color: red !important;
}
.grey{
	color:#D3D3D3 !important;
}
.green{
	color:#32CD32 !important;
}
.layui-text h3 {
    font-size: 13px !important;
}
.layui-btn{
	width:100px
}
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    
    <table class="layui-hide" id="gatewayTable" lay-filter="gatewayTable" lay-size="sm"></table>
	
	<script type="text/html" id="pidTem">
		{{
            d.pid.replace("<","&lt;").replace(">","&gt;")
        }}
	</script>
	<script type="text/html" id="fsmStatusTem">
		<ul>
		{{#
            
			var list = [
					{name:'channel_is_on_ready',text:"待使用"}
					,{name:'wating_for_right_leg_answer',text:"等待被叫方响应"}
					,{name:'waiting_left_leg_answer',text:"等待主叫方响应"}
					,{name:'waiting_bridge',text:"通话中"}
					,{name:'merge_session',text:"通话结束"}
					];
			if(d.status && d.status.right_leg_call_num != 'undefined'){
				list[1].text = list[1].text + '(' + d.status.right_leg_call_num + ')';
			}
			if(d.status && d.status.left_leg_call_num != 'undefined'){
				list[2].text = list[2].text + '(' + d.status.left_leg_call_num + ')';
			}
			var isEnd = false;
		}}
		{{#	for(var i in list){	}}
			{{#	
				var clazz = '';
				if(d.status.state ==list[i].name){
					clazz = 'green';
					isEnd = true;
				}else if(!isEnd){
					clazz = 'grey';
				}else{
					clazz = 'red';
				}
			}}
			<li class="layui-timeline-item">
				{{# if(clazz == 'green'){	}}
    				<i class="layui-icon layui-timeline-axis">&#xe65b;</i>
				{{#	}else{	}}
					<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
				{{#	}	}}
    			<div class="layui-timeline-content layui-text">
      				<h3 class="layui-timeline-title {{clazz}}">{{list[i].text}}</h3>
    			</div>
  			</li>
		{{#	}	}}
		</ul>
	</script>
	<script type="text/html" id="showFsmStatusTem">
		{{d.machine_status}}
	</script>
	<script type="text/html" id="butTem">
		{{# if(d.status.state && d.status.state =='waiting_bridge'){	}}
			<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="gotoAudio">在线试听</a>
			<p style="height:30px;"></p>
		{{#	}	}}
		{{# if(d.machine_status =='up'){	}}
			<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="stopBut">下线</a>
			<p style="height:30px;"></p>
		{{#	}	}}
		{{# if(d.machine_status =='down'){	}}
			<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="startBut">上线</a>
			<p style="height:30px;"></p>
		{{#	}	}}
		<a class="layui-btn  layui-btn-sm" lay-event="view">SIP状态查看</a>
	</script>

    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        var laytpl = layui.laytpl;
        form.render();
        
        
        var tableInit = table.render({
            elem: '#gatewayTable'
            , url: '${baseURL}/fsmStatus/list'
            , toolbar: true
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            }
            , cols: [[
                {field: 'description', width: '10%', title: '线路名称'}
                ,{field: 'arguments', width: '15%', title: '网关'}
                ,{field: 'group_name', width: '10%', title: '网关组'}
                , {field: 'node', width: '20%', title: 'node'}
                , {field: 'showFsmStatus', width: '15%', title: '网关状态',templet:'#showFsmStatusTem'}
                , {field: 'fsmStatus', width: '20%', title: '状态机实时状态',templet:'#fsmStatusTem'}
                , {field: '',  title: '操作',templet:'#butTem'}
            ]]
            , limits: [2, 5, 10, 20, 50]//每页数据选择项
            , limit: 2 //默认采用10
            , page: true
            , id: 'gatewayTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
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
        
        
        function reload(){
        	var param = {
        		pageNum : $('.layui-laypage-curr').find('em:eq(1)').html(),
        		numPerPage : $('.layui-laypage-limits select').val()
        	};
        	$.ajax({ 
        		url: '${baseURL}/fsmStatus/list', 
        		type : 'GET',
        		data: param, 
        		success: function(data){
        			var list = data.data;
        			var i = 0;
        			$('tbody > tr').each(function(){
        				$(this).find('td > div').eq(0).html(list[i].description);
        				$(this).find('td > div').eq(1).html(list[i].arguments);
        				$(this).find('td > div').eq(2).html(list[i].group_name);
        				$(this).find('td > div').eq(3).html(list[i].node);
        				$(this).find('td > div').eq(4).html(list[i].machine_status);
        				var _this = $(this);
        				var getTpl = fsmStatusTem.innerHTML;
        				laytpl(getTpl).render(list[i], function(html){
        					_this.find('td > div').eq(5).html(html);
        				});
        				
        				var butTpl = butTem.innerHTML;
        				laytpl(butTpl).render(list[i], function(html){
        					_this.find('td > div').eq(6).html(html);
        				});
        				
        				i++;
        			});
        		}, 
        		error: function(data){
        			console.log(data);
        		}
        	});
        }
        
        
      var t1 = setInterval(function(){
        	reload()
        },2000);
        
        $('#searchGateway').on('click', function () {
            reloadTable(1);
        });


        table.on('tool(gatewayTable)', function (obj) {
        	var data = obj.data;
        	if (obj.event === 'view') {
        		viewSipStatus(data);
            } else if (obj.event === 'gotoAudio') {
            	gotoAudio(data);
            } else if (obj.event === 'stopBut') {
            	stop(data);
            } else if (obj.event === 'startBut') {
            	start(data);
            }  
        });
        
        function viewSipStatus(data){
        	parent.parent.layui.index.openTabsPage('${baseURL}/fsmStatus/sip/info?id=' + data.id,'【' + data.description + '】的SIP状态');
        	/* openWin('SIP状态', '${baseURL}/gateway/status/sip/info?id=' + data.id, function () {
            }); */
        }
        
        function start(data){
        	var url = '${baseURL}/fsmStatus/start';
        	var param = {
           		id : data.id
           	};
        	post(url,param,function(data){
         	  	parent.parent.layer.msg('上线成功');
         	  	reload()
     		});
        }
        
        function stop(data){
        	var url = '${baseURL}/fsmStatus/end';
        	var param = {
           		id : data.id
           	};
        	post(url,param,function(data){
         	   	parent.parent.layer.msg('下线成功');
         	   	reload()
     		});
        }
        
        function gotoAudio(data){
        	parent.parent.layer.open({
       			type: 1,
       			title:'在线试听',
       		  	skin: 'layui-layer-rim', //加上边框
       		  	area: ['420px', '240px'], //宽高
       		 	content: 
       		 		'<audio controls="controls" style="width: 80%;margin-top: 56px;margin-left: 10%;">' + 
       	  				'<source src=' + data.status.recording_url + ' type="audio/mp3" />' + 
       	  	        '</audio>'
           		 ,zIndex: parent.parent.layer.zIndex 
                 ,success: function(layero){
                	 parent.parent.layer.setTop(layero); 
                 }
                 ,end: function (index, layero) {
                 }
       		});
        }


    </script>
</div>
