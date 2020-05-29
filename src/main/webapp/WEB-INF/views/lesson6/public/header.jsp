<%--
  Created by IntelliJ IDEA.
  User: lub_lenovo
  Date: 2017/8/7
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        .ui-jqgrid-sortable{
            text-align:center !important;
        }
        .addDiv{
            position: relative;
            right: 1%;
            float: right;
        }
    </style>
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/jquery.gritter.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/bootstrap-editable.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/common/css/layer.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/fonts.googleapis.com.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/font-awesome/4.5.0/fonts/fontawesome-webfont.woff" />
    <link rel="stylesheet" href="${contextPath}/static-resource/ace/assets/font-awesome/4.5.0/fonts/fontawesome-webfont.ttf" />
    <script src="${contextPath}/static-resource/ace/assets/js/ace-extra.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery-2.1.4.min.js"></script>
    <script src="${contextPath}/static-resource/common/js/layer.js"></script>
    <script src="${contextPath}/static-resource/common/js/base.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.jqGrid.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery-ui.custom.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.mobile.custom.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/excanvas.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.easypiechart.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.sparkline.index.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.flot.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.flot.pie.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.flot.resize.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/ace-elements.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/ace.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.fileupload.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.gritter.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/ace-editable.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/grid.setcolumns.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/grid.locale-en.js"></script>
    <script src="${contextPath}/static-resource/ace/assets/js/jszip.min.js"></script>
</head>