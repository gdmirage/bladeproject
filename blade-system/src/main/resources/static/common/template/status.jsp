<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib/taglib.jsp" %>

<script type="text/html" id="statusTmp">
    {{# if (d.status === 'ACTIVE') { }}
    <span class="layui-badge layui-bg-green">激活</span>
    {{# } else if(d.status === 'UNACTIVE') { }}
    <span class="layui-badge layui-bg-gray">冻结</span>
    {{# } }}
</script>