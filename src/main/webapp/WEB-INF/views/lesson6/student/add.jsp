<%--
  Created by IntelliJ IDEA.
  User: lub_lenovo
  Date: 2017/8/7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/lesson6/public/header.jsp" %>
<script type="text/javascript">
</script>
<form class="form-horizontal" role="form"  id="form-add">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="name">姓名</label>

        <div class="col-sm-9">
            <input type="text" id="name" placeholder="学生姓名" name="name" class="col-xs-10 col-sm-5"/>
        </div>
        <label class="col-sm-3 control-label no-padding-right" for="birthday">出生日期</label>
        <div class="col-sm-9">
            <input class="col-xs-10 col-sm-5 date-picker" id="birthday" name="birthday" type="text"
                   data-date-format="yyyy-mm-dd"/>
        </div>
        <label class="col-sm-3 control-label no-padding-right" for="sex">性别</label>
        <div class="col-sm-9">
            <select data-placeholder="选择性别" class="col-xs-10 col-sm-5 chosen-select" name="sex" id="sex">
                <option value="0">男</option>
                <option value="1">女</option>
            </select>
        </div>

        <div class="col-sm-9">
            <a class="btn btn-info" onclick="submit()">确定</a>
            <a class="btn btn-info" onclick="operate.closeIframe()">取消</a>
        </div>
    </div>
</form>


<!-- inline scripts related to this page -->
<script type="text/javascript">
    function submit() {
        var dataFormJson = $("#form-add").serialize();
        operate.add(dataFormJson,'/student/save_add.do','学生添加')
    }
    $('.date-picker').datepicker({
        autoclose: true,
        todayHighlight: true
    })

</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
