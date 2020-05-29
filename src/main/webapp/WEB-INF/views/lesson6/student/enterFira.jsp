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
        <c:forEach items="${subjects}" var="sub">
            <label class="col-sm-3 control-label no-padding-right">${sub.name}</label>

            <div class="col-sm-9">
                <input type="text" id="${sub.id}" value="" class="col-xs-10 col-sm-5"/>
            </div>
        </c:forEach>

    </div>
</form>
<div class="col-sm-9">
    <a class="btn btn-info" onclick="submit()">确定</a>
    <a class="btn btn-info" onclick="operate.closeIframe()">取消</a>
</div>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    var id = "${data.id}",temp=[],rest = {};
    function submit() {
        layer.load()
        let dom = $('.col-sm-9 input');
        for (let i = 0; i <dom.length ; i++) {
            let jqdom = $(dom[i])
            temp[i] = {'subId':jqdom.attr("id"),'score':jqdom.val()};
        }
        console.log(temp)
        rest = {'id':id,'stu':JSON.stringify(temp)}
        console.log(rest)
        operate.add(rest,'/student/enterFraction.do',"班级编辑")
    }
</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
