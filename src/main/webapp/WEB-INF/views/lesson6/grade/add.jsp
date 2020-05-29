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
<style>
    shuttle_box{width:800px;zoom: 1;margin: 100px auto;}
    #shuttle_box:after{
        content: ".";
        clear: both;
        display: block;
        height: 0;
        overflow: hidden;
        visibility: hidden;
    }
    .shuttle_box_li{min-height: 240px;float: left;}
    .shuttle_box_near{width:300px;background-color:#ffffff;overflow-y: scroll;overflow-x:hidden;border-radius: 10px;}
    .shuttle_box_li_act{color:#ffffff !important;background-color: #1890ff !important;border-bottom: 1px solid #ffffff;transition: all .01s;}
    .shuttle_box_near::-webkit-scrollbar {/*滚动条整体样式*/
        width: 6px;     /*高宽分别对应横竖滚动条的尺寸*/
        height: 1px;
    }
    .shuttle_box_near::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
        border-radius: 20px;
        background-color: rgba(0,0,0,0.5);
    }
    .shuttle_box_near::-webkit-scrollbar-track {/*滚动条里面轨道*/
        background-color: rgba(0,0,0,0.2);
        border-radius: 20px;
    }
    .shuttle_box_near li{
        padding:8px;
        border-bottom: 1px solid #ffffff;
        background-color: #f4f4f4;
        cursor: pointer;
        transition: all .5s;
    }
    .shuttle_box_li_act:hover{opacity: 0.7;transition: all .01s;}
    #shuttle_box_mid{width:80px;text-align: center;}
    #shuttle_box_mid input{
        width: 75px;
        height:30px;
        display: block;
        margin:20px auto;
        line-height: 30px;
        color:white;
        cursor: pointer;
        background-color: #1890ff;
        border-radius: 5px;
        transition: all .5s;
        border:none;
    }
    li{
        list-style:none;
    }
    #shuttle_box_mid button:hover{opacity: 0.7;transition: all .5s;}
    /*#shuttle_box_toRight{margin-top:225px !important;}*/
</style>
<script type="text/javascript">
</script>
<body>
<form class="form-horizontal" role="form" id="form-add">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="name">班级名称</label>

        <div class="col-sm-9" style="margin-bottom: 10px">
            <input type="text" id="name" name="name" placeholder="请输入班级名称" class="col-xs-10 col-sm-5" />
        </div>
    </div>
    <label class="col-sm-3 control-label no-padding-right" for="name"></label>
    <div class="col-sm-9">
        <a class="btn btn-info" onclick="submit()">确定</a>
        <a class="btn btn-info" onclick="operate.closeIframe()">取消</a>
    </div>
</form>


<!-- inline scripts related to this page -->
<script type="text/javascript">
    function submit() {
        var dataFormJson=$("#form-add").serialize();
        operate.add(dataFormJson,'/grade/save_add.do',"班级添加")
    }

</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
