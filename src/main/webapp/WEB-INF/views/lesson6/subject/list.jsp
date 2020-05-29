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
<%@include file="/WEB-INF/views/lesson6/public/nav.jsp" %>
<script type="text/javascript">
</script>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="space-6"></div>
                            <form id="formSearch" class="form-horizontal">
                                <div class="form-group" style="margin-top:15px">
                                    <label class="control-label col-sm-1" style="margin-left: 20px;" for="searchName">学科名称</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="searchName" name="searchName">
                                    </div>
                                    <div class="col-sm-1" style="text-align:center;">
                                        <button type="button"  id="find_btn" class="btn btn-primary" onclick="operate.search()">查询</button>
                                    </div>
                                </div>
                            </form>
                            <div class="addDiv">
                                <a class="btn btn-primary btn-xs" onclick="operate.openUrl('/subject/add.do')">新增</a>
                            </div>
                            <div class="col-sm-12 infobox-container">
                                <div class="row trip4-container">
                                    <div class="col-xs-12" id="trip4-container">
                                        <table id="grid-table"></table>
                                        <div id="grid-pager"></div>
                                    </div>
                                </div>
                            </div>

                            <div class="vspace-12-sm"></div>

                        </div><!-- /.row -->

                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

</div><!-- /.main-container -->
<script type="text/javascript">
    $("body").on("focus", "#grid-table input[name='name']", function(){
    });
    $(document).keyup(function(e){
        console.log(e);
        console.log(e.keyCode);
        var $selectedGridTr = $(".ui-jqgrid-btable tr.ui-state-highlight");
        if($selectedGridTr.size()){
            switch (e.keyCode){
                case 13:
                    jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-save").get(0),'save');
                    jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-cancel").get(0),'cancel');
                    break;
                case 27:
                    jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-cancel").get(0),'cancel');
                    break;
            }
        }
    });
    jQuery(function($) {
        var options = {
            colNames:[' ', 'ID','名称','选修人数', '平均分', '更新时间','操作'],
            rowNum:10,
            title:'学科',
            dataUrl: '/subject/page.do',
            addUrl:'/subject/add.do',
            editUrl:'/subject/edit.do',
            removeUrl:'/subject/delete.do',
            rowList:[10,20,30],
            colModel:[
                {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                    formatter:'actions',
                    formatoptions:{
                        keys:false,
                        //delbutton: false,//disable delete button
                        editbutton: false, //禁用第行的编辑图标
                        delbutton:false,
                        savebutton:false,
                        saveOptions:{recreateForm: false, beforeShowForm:beforeSaveCallback},
//                                delOptions:{recreateForm: false, beforeShowForm:beforeDeleteCallback},
                        //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                    }
                },
                {name:'id',index:'id', width:60, sorttype:"string", editable: false,align: 'center'},
                {name:'name',index:'name', width:150,editable: false,editoptions:{size:"20",maxlength:"30"}},
                {name:'num',index:'num',width:90, editable:false,},
                {name:'avgScore',index:'avgScore', width:70, editable: false,},
                {name:'gmt_modified',index:'gmt_modified', width:150, sortable:true,editable:false
                    ,formatter:function (cellvalue, options, rowObjec) {
                        return operate.tiemToDate(rowObjec.gmtModified);
                 }},
                {name:'',formatter: function (cellvalue, options, rowObjec) {
                        let array = []
                        array.push('<a class="btn btn-primary btn-xs" onclick="openUrl(\'/subject/edit.do?id='+rowObjec.id+'\')">修改</a>&nbsp;&nbsp;&nbsp;')
                        array.push('<a class="btn btn-info btn-xs" onclick="operate.delete(\'/subject/delete.do?id='+rowObjec.id+'\')">删除</a>')
                        return array.join('')
                    }}
            ],
        }
        var optTable = $.table.oTableInit(options)
        optTable.Init();

        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

        //switch element when editing inline
        function aceSwitch( cellvalue, options, cell ) {
            console.log("aceSwitch 此方法为编辑字段format")
            setTimeout(function(){
                $(cell) .find('input[type=checkbox]')
                    .addClass('ace ace-switch ace-switch-5')
                    .after('<span class="lbl"></span>');
            }, 0);
        }
        //enable datepicker
        function pickDate( cellvalue, options, cell ) {
            setTimeout(function(){
                $(cell) .find('input[type=text]')
                    .datepicker({format:'yyyy-mm-dd' , autoclose:true});
            }, 0);
        }


        //navButtons



        function style_edit_form(form) {
            console.log("style_edit_form");
            //enable datepicker on "sdate" field and switches for "stock" field
            form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})

            form.find('input[name=stock]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
            //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
            //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');


            //update buttons classes
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
            buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

            buttons = form.next().find('.navButton a');
            buttons.find('.ui-icon').hide();
            buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
            buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
        }

        function style_delete_form(form) {
            console.log("style_delete_form");
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
            buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
        }

        function style_search_form(form) {
            console.log("style_search_form");
            var dialog = form.closest('.ui-jqdialog');
            var buttons = dialog.find('.EditTable')
            buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
            buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
            buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
        }

        function beforeSaveCallback(e){
            console.log("beforeSaveCallback");
        }


        function updatePagerIcons(table) {
            var replacement =
                {
                    'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                    'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                    'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                    'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
                };
            $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                var icon = $(this);
                var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
            })
        }

        function enableTooltips(table) {
//                    $('.navtable .ui-pg-button').tooltip({container:'body'});
//                    $(table).find('.ui-pg-div').tooltip({container:'body'});
        }

        //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        $(document).one('ajaxloadstart.page', function(e) {
            $.jgrid.gridDestroy(gridTableSelector);
            $('.ui-jqdialog').remove();
        });
    });

</script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
</script>
<!-- basic scripts -->

<!--[if !IE]> -->

<!-- inline scripts related to this page -->
<script type="text/javascript">
</script>
<%@include file="/WEB-INF/views/lesson6/public/footer.jsp" %>
