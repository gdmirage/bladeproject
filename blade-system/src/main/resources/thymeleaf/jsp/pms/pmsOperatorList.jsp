<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">登录名：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="loginName" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">姓名：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="realName" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">状态：</label>
	            <div class="layui-input-inline">
	                <select name="status" id="status">
	                    <option value="">全部</option>
	                    <option value="ACTIVE" selected>激活</option>
	                    <option value="UNACTIVE">冻结</option>
	                </select>
	            </div>
	            <button class="layui-btn" id="searchOperator">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
            </blockquote>
        </div>
    </div>

    <hr>

    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-sm " id="addOperator"><i class="layui-icon">&#xe654;</i>添加</button>
        <button class="layui-btn layui-btn-sm " id="editOperator"><i class="layui-icon">&#xe642;</i>修改</button>
    </div>

    <table class="layui-hide" id="operatorTable" lay-filter="operatorTable"></table>

    <script type="text/html" id="stateTpl">
        {{# if (d.status === 'ACTIVE') { }}
        <span class="layui-badge layui-bg-green">激活</span>
        {{# } else if(d.status === 'UNACTIVE') { }}
        <span class="layui-badge">冻结</span>
        {{# } }}
    </script>

    <script type="text/html" id="roleBar">
        <a class="layui-btn layui-btn-xs" lay-event="resetPassword">重置密码</a>
        <a class="layui-btn layui-btn-xs" lay-event="unActiveOperator">冻结</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delOperator">删除</a>
    </script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        form.render();


        var tableInit = table.render({
            elem: '#operatorTable'
            , url: '${baseURL}/pms/operator/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
                loginName: $('#loginName').val()
                , realName: $('#realName').val()
                , status: $('#status').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'id', width: '8%', title: '序号'}
                , {field: 'loginName', width: '10%', title: '操作员登录名'}
                , {field: 'realName', width: '15%', title: '操作员姓名'}
                , {field: 'mobileNo', width: '10%', title: '手机号码'}
                , {field: 'mobileNo', width: '10%', title: '授权账号'}
                , {field: 'status', width: '12%', title: '状态', templet: '#stateTpl'}
                , {field: 'toolbar', title: '操作', templet: '#roleBar'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'operatorTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                    loginName: $('#loginName').val()
                    , realName: $('#realName').val()
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

        $('#searchOperator').on('click', function () {
            reloadTable(1);
        });


        //监听工具条
        table.on('tool(operatorTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'resetPassword') {
                resetPassword(data);
            } else if (obj.event === 'unActiveOperator') {
                editRole(data)
            } else if (obj.event === 'delOperator') {
                assignMenuUI(data);
            }
        });


        //新增
        $('#addOperator').click(function () {
            openWin('添加操作员', '${baseURL}/pms/operator/addUI/', function () {
                reloadTable(1);
            });
        })

        //修改
        $('#editOperator').click(function () {
            var checkStatus = table.checkStatus('operatorTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else if (data[0].id == '1') {
            	parent.parent.layer.msg("操作员：" + data[0].realName + "不能修改！", {
                    icon: 2,
                    time: 4000 //2秒关闭（如果不配置，默认是3秒）
                })
                return;
            } else {
                layer.open({
                    type: 2 //iframe
                    , title: '修改操作员'
                    , area: ['100%', '100%']
                    , offset: '0px'
                    , shade: 0
                    , content: '${baseURL}/pms/operator/editUI?id=' + data[0].id
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        layer.setTop(layero); 	//弹窗置顶
                    }
                    , end: function (index, layero) {
                        reloadTable(1);
                    }
                });
            }
        });


        //删除
        function resetPassword(data) {
            layer.open({
                type: 2 //iframe
                , title: '重置密码'
                , area: ['100%', '100%']
                , offset: '0px'
                , shade: 0
                , content: '${baseURL}/pms/operator/resetPwdUI?id=' + data.id
                , zIndex: layer.zIndex //重点1
                , success: function (layero) {
                    layer.setTop(layero); 	//弹窗置顶
                }
                , end: function (index, layero) {
                    reloadTable(1);
                }
            });
        }

    </script>
</div>
