<%--
  Created by IntelliJ IDEA.
  User: lub_lenovo
  Date: 2017/8/7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/lesson6/public/header.jsp" %>
<script type="text/javascript">
</script>
<form class="form-horizontal" role="form"  id="form-add">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="name">学科名称</label>

        <div class="col-sm-9">
            <input type="text" id="name" name="name" placeholder="请输入学科名称" class="col-xs-10 col-sm-5" />
        </div>
    </div>
    <div class="col-sm-9">
        <a class="btn btn-info" onclick="submit()">确定</a>
        <a class="btn btn-info" onclick="operate.closeIframe()">取消</a>
    </div>
</form>


<!-- inline scripts related to this page -->
<script type="text/javascript">
    function submit() {
        var dataFormJson=$("#form-add").serialize();
        operate.add(dataFormJson,'/subject/save_add.do',"学科添加")
    }

</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
