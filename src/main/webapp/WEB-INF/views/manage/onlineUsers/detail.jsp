<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="page-content">

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">

                    <div class="form-group">
                        <label class="col-sm-6 control-label no-padding-right">
                            <spring:message
                                    code="name.zh"/>:<c:out value="${user.name}"/> </label>
                        <label class="col-sm-6 control-label no-padding-right "> <spring:message
                                code="name.en"/>:<c:out value="${user.nameEn}"/> </label>

                    </div>

                    <div class="form-group">
                        <label class="col-sm-6 control-label no-padding-right">
                            <spring:message
                                    code="user.gender"/>:<c:out value="${user.gender}"/> </label>

                    </div>

                    <div class="form-group">
                        <label class="col-sm-6 control-label no-padding-right">
                            <spring:message
                                    code="user.phone"/>:<c:out value="${user.tel}"/> </label>

                        <label class="col-sm-6 control-label no-padding-right"> <spring:message
                                code="mobile"/>:<c:out value="${user.mobile}"/> </label>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-6 control-label no-padding-right">
                            <spring:message
                                    code="fax"/>:<c:out value="${user.fax}"/> </label>

                        <label class="col-sm-6 control-label no-padding-right"> <spring:message
                                code="user.email"/>:<a href="mailto:${user.email}">${user.email}</a> </label>

                    </div>

                </div><!-- /.span -->
            </div><!-- /.row -->
            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div>
