<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../static/taglib/taglib.jsp" %>

<style>
    .layui-upload-img {
        width: 60px;
        height: 60px;
    }

    .layui-table-cell {
        height: 60px;
        line-height: 60px;
    }
    .layui-table-view .layui-table td{
    	height: 60px;
    }
    
    th div{
    	height:auto !important;
    	line-height: 28px !important;
    }
     .layui-btn-primary {
    	    margin-top: 20px !important;
    }
    
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
        		<label class="layui-form-label">商家名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="merchantName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchMerchant">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addMerchant">
	                <i class="layui-icon">&#xe654;</i>商家入驻
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="merchantTable" lay-filter="merchantTable"></table>

	<script type="text/html" id="merchantBar">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改资料</a>
    </script>

    <script type="text/html" id="dateTem">
        {{ timestampToDate(d.createTime) }}
    </script>
	<script type="text/html" id="iconImgTem">
        <img class="layui-upload-img " src="{{d.headImg}}"> 
   </script>
        
    <script>
        var table = layui.table; 
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        var tableInit = table.render({
            elem: '#merchantTable'
            , url: '${baseURL}/merchant/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	merchantName: $('#merchantName').val()
            }
            , cols: [[
                {field:'', width:'10%', title: '商家logo', templet: '#iconImgTem'}
                , {field: 'merchantName', width: '15%', title: '商家名称'}
                , {field: 'merchantDesc', width: '15%', title: '商家描述'}
                , {field: 'mobile', width: '9%', title: '联系电话'}
                , {field: 'address', width: '20%', title: '地址'}
                , {field: 'wechat', width: '10%', title: '客服微信号'}
                , {field: '', width: '10%', title: '入驻时间', templet: '#dateTem'}
                , {field: 'toolbar', title: '操作',width: '10%',toolbar: '#merchantBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'merchantTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	merchantName: $('#merchantName').val()
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
		
        $('#searchMerchant').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addMerchant').click(function () {
            openWin('商家入驻', '${baseURL}/merchant/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(merchantTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	editMerchant(data);
            }
        });
        
        //修改
		function editMerchant(data){
			openWin('修改资料','${baseURL}/merchant/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
    </script>
</div>
