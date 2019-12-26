<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">姓名：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="lecturerName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchLecturer">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addLecturer">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="lecturerTable" lay-filter="lecturerTable"></table>

	<script type="text/html" id="lecturerBar">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
    <script type="text/html" id="sexTmp">
        {{# if (d.sex === 'MALE') { }}
       		男
        {{# } else if(d.sex === 'FEMALE') { }}
        	女
        {{# } }}
    </script>
	<script type="text/html" id="isTopTmp">
        {{# if (d.isTop === 'YES') { }}
        	<span class="layui-badge layui-bg-green">是</span>
        {{# } else { }}
        	<span class="layui-badge">否</span>
        {{# }  }}
    </script>
    <script>
        var table = layui.table; 
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#lecturerTable'
            , url: '${baseURL}/lecturer/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	lecturerName: $('#lecturerName').val()
            }
            , cols: [[
                // ,{field:'id', width:'10%', title: 'id'}
                {field: 'lecturerName', width: '20%', title: '讲师姓名'}
                , {field: 'sex', width: '20%', title: '性别' , templet: '#sexTmp'}
                , {field: 'isTop', width: '20%', title: '名师' , templet: '#isTopTmp'}
                , {field: 'creater',  width: '20%',title: '创建人'}
                , {field: 'createTime',  width: '20%',title: '创建时间', templet: '#dateTem'}
                , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#lecturerBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'lecturerTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	lecturerName: $('#lecturerName').val()
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

        $('#searchLecturer').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addLecturer').click(function () {
            openWin('添加讲师', '${baseURL}/lecturer/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(lecturerTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	editLecturer(data);
            } else if (obj.event === 'del') {
            	delLecturer(data);
            }
        });
        
        //修改
		function editLecturer(data){
			openWin('修改讲师','${baseURL}/lecturer/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
		//删除
		function delLecturer(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认删除讲师【'+data.lecturerName+'】么', function(index){
				var url = "${baseURL}/lecturer/delete";
				post(url,deleteData,function(data){
					reloadTable();
					layer.close(index);
					parent.parent.layer.msg('删除成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				parent.parent.layer.alert(data.msg,{
					  icon: 2
					});
	  			});
			});
		}

    </script>
</div>
