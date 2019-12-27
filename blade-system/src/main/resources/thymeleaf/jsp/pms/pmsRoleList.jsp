<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>
<script type="text/javascript" src="../../../static/common/js/libs/jquery-3.2.1.min.js"></script>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">角色名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="roleName" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchRole">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	
	            <button class="layui-btn" id="addRole">
	                <i class="layui-icon">&#xe608;</i> 添加角色
	            </button>
            </blockquote>
        </div>
    </div>
    <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>

    <script type="text/html" id="roleBar">
        <a class="layui-btn layui-btn-xs" lay-event="assignMenuUI">分配菜单</a>
        <a class="layui-btn layui-btn-xs" lay-event="assignPermissionUI">分配权限</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>


    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>

    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#roleTable'
            , url: '${baseURL}/pms/role/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
                roleName: $('#roleName').val()
            }
            , cols: [[
                {field: 'id', width: '10%', title: '序号'}
                , {field: 'roleName', width: '15%', title: '角色名称'}
                , {field: 'roleCode', width: '10%', title: '角色编码', sort: true}
                , {field: 'remark', width: '15%', title: '描述'}
                , {field: 'createTime', title: '创建时间', width: '20%', templet: '#dateTem'}
                , {field: 'toolbar', width: '30%', title: '操作', toolbar: '#roleBar'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                    roleName: $('#roleName').val()
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

        //监听工具条
        table.on('tool(roleTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                delRole(data);
            } else if (obj.event === 'edit') {
                editRole(data)
            } else if (obj.event === 'assignMenuUI') {
                assignMenuUI(data);
            } else if(obj.event === 'assignPermissionUI'){
                assignPermissionUI(data)
            }
        });

        $('#searchRole').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addRole').click(function () {
            openWin('添加角色', '${baseURL}/pms/role/addUI', function () {
                reloadTable(1);
            });
        })

        function editRole(data) {
            openWin('修改角色', '${baseURL}/pms/role/editUI?roleId=' + data.id, function () {
                reloadTable(1);
            });
        }

        function assignMenuUI(data) {
            openWin('分配菜单', '${baseURL}/pms/role/assignMenuUI?roleId=' + data.id, function () {
                reloadTable(1);
            });
        }
        function  assignPermissionUI(data) {
            openWin('分配权限', '${baseURL}/pms/role/assignPermissionUI?roleId=' + data.id, function () {
                reloadTable(1);
            });
        }

        //删除
        function delRole(data) {
            var deleteData = {
                roleId: data.id
            }
            parent.parent.layer.confirm('确认删除【' + data.roleName + '】么', function (index) {
                var url = "${baseURL}/pms/role/delete";
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


<%-- [
<a href="${baseURL}/pms/role/assignMenuUI?roleId=${item.id}" title="为角色【${item.roleName}】分配菜单" target="dialog" width="950" style="color: blue">分配菜单</a>
] &nbsp;[
<a href="${baseURL}/pms/role/assignPermissionUI?roleId=${item.id}" title="为角色【${item.roleName}】分配权限" target="dialog" width="950" style="color: blue">分配权限</a>
] &nbsp;[
<a href="${baseURL}/pms/role/editUI?roleId=${item.id}" title="修改角色【${item.roleName}】" target="dialog" width="550" height="300" rel="input" style="color: blue">修改</a>
]
<c:if test="${roleType eq RoleTypeEnum.USER.value}">
    &nbsp;[<a href="${baseURL}/pms/role/delete?roleId=${item.id}" title="删除角色【${item.roleName}】" target="ajaxTodo" style="color: blue">删除</a>]
    </c:if> --%>
