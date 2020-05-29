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
            <input type="text" id="name" name="name" placeholder="学生姓名" value="${data.name}" class="col-xs-10 col-sm-5"/>
        </div>
        <label class="col-sm-3 control-label no-padding-right" for="birthday">出生日期</label>
        <div class="col-sm-9">
            <input class="col-xs-10 col-sm-5 date-picker" id="birthday" value="${data.birthday}" name="birthday" type="text"
                   data-date-format="yyyy-mm-dd"/>
        </div>
        <label class="col-sm-3 control-label no-padding-right" for="sex">性别</label>

        <div class="col-sm-9">
            <select data-placeholder="选择性别" class="col-xs-10 col-sm-5 chosen-select"  name="sex" id="sex">
                <option ${data.sex=="false"?'selected':''} value="0">男</option>
                <option ${data.sex=="true"?'selected':''} value="1">女</option>
            </select>
        </div>
        <label class="col-sm-3 control-label no-padding-right"></label>
        <div class="col-sm-9">
            <a class="btn btn-info" onclick="submit()">确定</a>
            <a class="btn btn-info" onclick="operate.closeIframe()">取消</a>
        </div>
    </div>
</form>

<script type="text/javascript">
    var id = "${data.id}"
    function submit() {
        var dataFormJson=$("#form-add").serialize();
        operate.add(dataFormJson,'/student/save_edit.do?id='+id,"学生编辑")
    }
    $('.date-picker').datepicker({
        autoclose: true,
        todayHighlight: true
    })

</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
