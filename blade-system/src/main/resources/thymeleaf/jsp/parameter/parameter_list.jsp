<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>
<style>
    .layui-upload-img {
        width: 60px;
        height: 60px;
    }

    .layui-table-cell {
        height: auto;
    }
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">参数编码：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="paramCode" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">参数名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="paramName" autocomplete="off" class="layui-input">
	            </div>
	            <div class="layui-inline">
	                <button class="layui-btn" id="search">
	                    <i class="layui-icon">&#x1002;</i>查询
	                </button>
	            </div>
            </blockquote>
        </div>
        <hr/>
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-sm layui-btn-danger" id="addParameter"><i
                    class="layui-icon">&#xe654;</i>新增
            </button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" id="edit"><i
                    class="layui-icon">&#xe654;</i>修改
            </button>
            <button class="layui-btn layui-btn-sm layui-btn-danger" id="del"><i
                    class="layui-icon">&#xe654;</i>删除
            </button>
        </div>
        <table class="layui-hide" id="parameterTable" lay-filter="parameterTable"></table>

        <script>
            var table = layui.table;
            var $ = layui.jquery;
            var tableInit = table.render({
                elem: '#parameterTable'
                , url: '${baseURL}/parameter/list'
                , request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
                }
                , where: {
                    productNumber: $('#paramCode').val()
                    , productName: $('#paramName').val()
                }
                , cols: [[
                    {type: 'checkbox', width: '5%'}
                    , {field: 'paramCode', width: '15%', title: '参数编码'}
                    , {field: 'paramName', width: '30%', title: '参数名称'}
                    , {field: 'paramValue', title: '参数值'}
                ]]
                , page: true
                , id: "parameterTableId"
            });

            //刷新table，带上搜索框条件
            function reloadTable(page) {
                var loadData = {
                    where: {
                        productNumber: $('#paramCode').val()
                        , productName: $('#paramName').val()
                    }
                };
                if (page) {
                    loadData.page = {
                        curr: page //重新从第page页开始
                    }
                }

                tableInit.reload(loadData);
            }


            $('#search').on('click', function () {
                reloadTable(1);
            });


            //新增
            $('#addParameter').click(function () {
                openWin('添加参数', '${baseURL}/parameter/addUI/', function () {
                    reloadTable(1);
                });
            })


            //修改
            $('#edit').click(function () {
                var checkStatus = table.checkStatus('parameterTableId')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.alert('请选择一条数据');
                    return;
                } else {
                    layer.open({
                        type: 2 //iframe
                        , title: '修改参数'
                        , area: ['100%', '100%']
                        , offset: '0px'
                        , shade: 0
                        , content: '${baseURL}/parameter/editUI?parameterId=' + data[0].id
                        , zIndex: layer.zIndex //重点1
                        , success: function (layero) {
                            layer.setTop(layero); 	//弹窗置顶
                        }
                        , end: function (index, layero) {
                            reloadTable(1);
                        }
                    });
                }
            })


            //删除
            $('#del').click(function () {
                var checkStatus = table.checkStatus('parameterTableId')
                    , data = checkStatus.data;
                if (checkStatus.data.length < 1) {
                    layer.msg('请选择参数！');
                    return;
                }
                var parameterIds = [];
                $.each(checkStatus.data, function (i, o) {
                    parameterIds.push(o.id);
                })

                var param = {
                    parameterIds: parameterIds.join(",")
                };
                post("${baseURL}/parameter/delete", param, function (data) {
                	parent.parent.layer.msg('删除成功', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        reloadTable(1);
                    });
                }, function (data) {
                	parent.parent.layer.msg(data.msg, {
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    })
                });

            })


        </script>
    </div>
</div>