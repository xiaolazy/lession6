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
            <input type="text" id="name" name="name" value="${data.name}" placeholder="请输入班级名称" class="col-xs-10 col-sm-5" />
        </div>
        <div>
            <label class="col-sm-3 control-label no-padding-right" for="name">选择学生</label>
        </div>
        <div class="col-sm-9">
            <ul id="shuttle_box">
                <li class="shuttle_box_li shuttle_box_near">
                    待选学生
                    <ul id="shuttle_box_left">
                        <c:forEach items="${students}" var="it">
                            <li value="${it.id}">${it.name}</li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="shuttle_box_li" id="shuttle_box_mid">
                    <input type="button" id="shuttle_box_toRight" value="选择此学生"/>
                    <input type="button" id="shuttle_box_toLeft" value="移除此学生"/>
                </li>
                <li class="shuttle_box_li shuttle_box_near">
                    已选学生
                    <ul id="shuttle_box_right">
                        <c:forEach items="${data.student}" var="stu">
                            <li value="${stu.id}">${stu.name}</li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
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
    var id = "${data.id}"
    function submit() {
        layer.load()
        var values = $('#shuttle_box_right li');
        let temp = [];
        for(var i=0;i<values.length;i++){
            temp[i]=($(values[i]).attr("value"))
        }
        var dataFormJson={'name':$('#name').val(),'ids':JSON.stringify(temp)};
        operate.add(dataFormJson,'/grade/save_edit.do?id='+id,"班级编辑")
    }
    //穿梭框左侧选中
    $("#shuttle_box_left").on('click', 'li', function () {
        if ($(this).hasClass('shuttle_box_li_act')) {
            $(this).removeClass('shuttle_box_li_act');
        } else {
            $(this).addClass('shuttle_box_li_act');
        }
    });
    //穿梭框右侧选中
    $("#shuttle_box_right").on('click', 'li', function () {
        if ($(this).hasClass('shuttle_box_li_act')) {
            $(this).removeClass('shuttle_box_li_act');
        } else {
            $(this).addClass('shuttle_box_li_act');
        }
    });
    //向右移动
    $("#shuttle_box_toRight").click(function () {
        if ($("#shuttle_box_left .shuttle_box_li_act").length == 0) return false;

        $("#shuttle_box_left").find('.shuttle_box_li_act').appendTo("#shuttle_box_right");
        $("#shuttle_box_right li").removeClass('shuttle_box_li_act');


    });
    //向左移动
    $("#shuttle_box_toLeft").click(function () {
        if ($("#shuttle_box_right .shuttle_box_li_act").length == 0) return false;

        $("#shuttle_box_right .shuttle_box_li_act").appendTo("#shuttle_box_left");
        $("#shuttle_box_left li").removeClass('shuttle_box_li_act');
    });
</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
