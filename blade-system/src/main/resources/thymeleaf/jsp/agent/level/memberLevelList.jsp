<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">级别名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="levelName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchLevel">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addLevel">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="memberLevelTable" lay-filter="memberLevelTable"></table>

	<script type="text/html" id="memberLevelBar">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="editMemberLevel">编辑</a>
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delMemberLevel">删除</a>
    </script>

    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
    <script type="text/html" id="levelTypeTmp">
        {{# if (d.levelType === 'LEVEL_01') { }}
       		<span class="layui-badge layui-bg-blue">一级分销权</span>
        {{# } else if(d.levelType === 'LEVEL_02') { }}
        	<span class="layui-badge layui-bg-orange">二级分销权</span>
        {{# } else if(d.levelType === 'LEVEL_03') { }}
       	 <span class="layui-badge">三级分销权</span>
        {{# } }}
    </script>
    <script type="text/html" id="isShowTmp">
        {{# if (d.isShow === 'YES') { }}
        	是
        {{# } else { }}
        	<span class="layui-badge">否</span>
        {{# }  }}
    </script>

    <script>
        var table = layui.table; 
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#memberLevelTable'
            , url: '${baseURL}/memberLevel/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
                levelName: $('#levelName').val()
            }
            , cols: [[
                // ,{field:'id', width:'10%', title: 'id'}
                {field: 'levelName', width: '20%', title: '等级名称'}
                //, {field: 'levelType', width: '20%', title: '分销权', templet: '#levelTypeTmp'}
                , {field: 'requireAmount',  width: '12%',title: '等级权重'}
                , {field: 'isShow',  width: '12%',  title: '是否对外展示',templet: '#isShowTmp'}
                , {field: 'upUrl',  width: '35%',  title: '升级链接'}
                , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#memberLevelBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'memberLevelTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                    levelName: $('#levelName').val()
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

        $('#searchLevel').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addLevel').click(function () {
            openWin('添加等级', '${baseURL}/memberLevel/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(memberLevelTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'editMemberLevel') {
            	editMemberLevel(data);
            } else if (obj.event === 'delMemberLevel') {
            	delMemberLevel(data);
            }
        });
        
        //修改
		function editMemberLevel(data){
			openWin('修改等级','${baseURL}/memberLevel/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
		//删除
		function delMemberLevel(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认删除等级【'+data.levelName+'】么', function(index){
				var url = "${baseURL}/memberLevel/delete";
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
