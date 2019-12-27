<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>
<div class="page" style="margin: 10px 10px">
    <form class="layui-form" method="post" id="productForm">
        <div class="layui-form-item">
            <label class="layui-form-label">产品名称：</label>
            <div class="layui-input-block">
                <input type="text" name="productName" required lay-verify="required" placeholder="请输入产品名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">产品价格：</label>
            <div class="layui-input-inline">
                <input type="text" name="productPrice" lay-verify="required" placeholder="请输入产品价格" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">产品邮费：</label>
            <div class="layui-input-inline">
                <input type="text" name="productPostage" lay-verify="required" placeholder="请输入产品邮费" autocomplete="off"
                       class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">上架时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="shelvesDate"  lay-verify="datetime" id="shelvesDate" placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">下架时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="shelfDate" lay-verify="datetime" id="shelfDate" placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">产品主要描述</label>
            <div class="layui-input-block">
                <textarea placeholder="产品主要描述" class="layui-textarea" name="productMainDes"
                          id="productMainDes"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品主图</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="pictrue">产品主图</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="pic">
                    <p id="demoText"></p>
                    <input type="hidden" name="picPath" id="picPath"/>
                </div>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>添加产品SKU</legend>
        </fieldset>
        <div class="layui-btn-group searchSKU" style="margin-bottom: 10px;">
            <i class="layui-icon" style="font-size:30px">&#xe61f;</i>



        </div>

        <table lay-filter="skuTable" id="skuTable">
        </table>

        <div class="skuInput"></div>
        <div class="skuBaseNum"></div>
        <input type="hidden" id="des" name="productDess"/>
        <div class="layui-form-item">
            <textarea id="productDes" name="productDes"></textarea>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="productForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>


</script>

<script type="text/javascript">
    var $ = layui.jquery;
    var form = layui.form, laydate = layui.laydate, upload = layui.upload, layedit = layui.layedit, table = layui.table;
    form.render();

    //日期
    laydate.render({
        elem: '#shelvesDate'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#shelfDate'
        ,type: 'datetime'
    });

    layedit.set({
        uploadImage: {
            url: '${baseURL}/layEdit/fileUpload/' //接口url
            , type: 'POST' //默认post
        }
    });

    var editIndex = layedit.build('productDes', {
        height: 180, //设置编辑器高度
        tool: [
            'strong' //加粗
            , 'italic' //斜体
            , 'underline' //下划线
            , 'del' //删除线

            , '|' //分割线

            , 'left' //左对齐
            , 'center' //居中对齐
            , 'right' //右对齐
            , 'link' //超链接
            , 'unlink' //清除链接
            , 'face' //表情
            , 'image' //插入图片
            , 'help' //帮助
        ],
    });

    form.on('submit(productForm)', function (data) {
        var url = "${baseURL}/product/add";
        //获取富文本信息
        data.field.productDes = layedit.getContent(editIndex);
        var skuIds = new Array();
        $('input[name="skuIdList"]').each(function () {
            skuIds[skuIds.length] = $(this).val();
        })

        data.field.skuIdList = skuIds.toString();
        var skuBaseNums = new Array();
        $('input[name="skuBaseNumList"]').each(function () {
            skuBaseNums[skuBaseNums.length] = $(this).val();
        })
        data.field.skuBaseNumList = skuBaseNums.toString();
        post(url, data.field, function () {
            layer.msg('添加成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                parent.table.reload();
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
        }, function (data) {
            layer.msg(data.msg, {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#pictrue'
        , url: '${baseURL}/fileUpload/fileUpload/'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#pic').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功
            $('#pic').attr('src', res.data);
            $('#picPath').val(res.data);
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });


    //监听+ icon 事件
    $('.searchSKU .layui-icon').on('click', function () {
        layer.open({
            type: 2 //iframe
            , title: "添加SKU"
            , area: ['70%', '60%']
            , fixed: false  //不固定
            , offset: '0px'
            , shade: 0
            , content: "${baseURL}/sku/show/enable"
            , zIndex: layer.zIndex //重点1
            , success: function (layero) {

            }
            , end: function (index, layero) {

            }
        });
    });


    function showSku(data) {
        table.render({
            elem: '#skuTable'
            , cols: [[ //标题栏
                {field: 'skuNum', title: 'SKU编码'}
                , {field: 'skuName', title: 'SKU名称'}
                , {field: 'skuBaseNum', title: 'SKU数量', edit: 'text'}
                , {field: 'skuUnit', title: 'SKU单位'}
            ]]
            , data: getTableJson(data)
        });

        $('.skuInput').html('');
        $('.skuBaseNum').html('');
        var skuArr = new Array();
        for (var i in data) {
            skuArr[skuArr.length] = data[i].id;
            $('.skuInput').append('<input type="hidden" name="skuIdList" value="' + data[i].id + '" />')
            $('.skuBaseNum').append('<input type="hidden" name="skuBaseNumList" id="skuId' + data[i].id + '" value="0"/>')
        }

    }

    //监听单元格编辑
    table.on('edit(skuTable)', function (obj) {
        console.log(obj);
        var data = obj.data; //得到所在行所有键值
        var id = data.id;
        $('#skuId' + id).val(data.skuBaseNum);
    });
</script>
