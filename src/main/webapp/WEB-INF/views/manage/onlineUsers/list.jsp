<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="onlineUser.title">
    <!-- /.breadcrumb -->
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li class="active">
                <spring:message code="onlineUser.title"/>
            </li>
        </ul>
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="onlineUser.title"/>

                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="onlineUser.user.userId"/></th>
                                <th class="hidden-480"><spring:message code="onlineUser.user.name"/></th>
                                <th class="hidden-480"><spring:message code="onlineUser.user.nameEn"/></th>
                                <th><spring:message code="onlineUser.lastActiveTime"/></th>
                                <th class="hidden-480"><spring:message code="onlineUser.loginIP"/></th>
                                <th><spring:message code="onlineUser.lastVisit"/></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${onlineUsers}" var="onlineUser">
                                <tr id="tr-${onlineUser.userId}">
                                    <td><a class="ajax-contact-btn" data-id="${onlineUser.userId}" style="cursor: pointer"
                                           data-url="/manage/onlineUser/detail.do?id=${onlineUser.userId}"
                                           data-title="${onlineUser.userId}"
                                           data-modal="#modal-table">
                                        <c:out value="${onlineUser.userId}"/>
                                    </a></td>
                                    <td class="hidden-480"><c:out value="${onlineUser.name}"/></td>
                                    <td class="hidden-480"><c:out value="${onlineUser.nameEn}"/></td>
                                    <td><fmt:formatDate value="${userRequestMap[onlineUser.userId].lastActiveTime}"
                                                                           pattern="yyyy-MM-dd HH:mm"
                                                                           timeZone="${sessionScope.user.timeZone}"/></td>
                                    <td class="hidden-480"><c:out value="${userRequestMap[onlineUser.userId].loginIP}"/></td>
                                    <td><c:out value="${userRequestMap[onlineUser.userId].lastRequest}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>
