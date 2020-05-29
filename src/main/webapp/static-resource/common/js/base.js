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
$(function () {
    var gridTableSelector = "#grid-table"
        ,tablePagerSelector = "#grid-pager"
        ,lastSel;
    var parent_column = $(gridTableSelector).closest('[class*="col-"]');
    //resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(gridTableSelector).jqGrid( 'setGridWidth', parent_column.width() );
    });

    //resize on sidebar collapse/expand
    $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
        if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function() {
                $(gridTableSelector).jqGrid( 'setGridWidth', parent_column.width() );
            }, 20);
        }
    });
    $.extend({
        table:{
            _option: {},
            oTableInit:function(parms){//初始化表单
                $.table._option=parms;
                console.log($.table._option)
                var oTableInit = new Object();
                //初始化Table
                oTableInit.Init = function () {
                    $(gridTableSelector).jqGrid({
                        //direction: "rtl",
                        mtype: "post",
                        datatype: "json",
                        page: 1,
                        height:'600px',
                        url:parms.dataUrl,
                        colNames:parms.colNames,
                        colModel:parms.colModel,
                        viewrecords : true,
                        rowNum:10,
                        search:true,
                        sortname:"gmtCreate",
                        rowList:[10,20,30],
                        pager : tablePagerSelector,
                        altRows: true,
                        //toppager: true,
                        multiselect: true,
                        multiboxonly: true,
                        loadComplete : function(data) {
                            console.log("数据加载完成");
                            var table = this;
                            data.content.forEach((e,i)=>{
                                $(gridTableSelector).jqGrid('addRowData',i+1,e);
                            })
//                        $(table).find(".ui-inline-save").attr("onclick", "saveRow()");
                            setTimeout(function(){
                                updatePagerIcons(table);
                                // enableTooltips(table);
                            }, 0);
                        },
                        beforeSubmit : function(){
                            console.log("beforeSubmit");
                        },
                        onSelectRow : function(id){
                            console.log("点击行:" + id);
                            if(id && id!==lastSel){
                                $(this).restoreRow(lastSel);
                                $(this).editRow(id, true);
                                var $currentTr = $(this).find("tr#" + id);
                                $currentTr.find(".ui-inline-del").hide();
                                $currentTr.find(".ui-inline-save").show();
                                $currentTr.find(".ui-inline-cancel").show();
                                lastSel=id;
                            }
                        },
                        editurl: parms.editUrl,//nothing is saved
                        caption: parms.title
                    }).bind("jqGridInlineAfterSaveRow", function(e, rowid, orgClickEvent){
                        console.log("可用于更新页面信息");
                        console.log(rowid);
                    });
                };
                return oTableInit;
            },
            queryParams:function(params){//参数查询
                var search = {
                    'page':params.pageSize,
                    'size':params.rowNum
                };
                console.log(search);
                return search;

            },
            refresh: function() {
                $(gridTableSelector).trigger("reloadGrid");
            },
            gridParam: function(val) {
                val = val == undefined || val == null ? '' : val;
                $(gridTableSelector).jqGrid("setGridParam",{
                    postData:val, //发送数据
                    url:$.table._option.dataUrl,
                    page:1
                }).trigger("reloadGrid");
            },
            //回显数据字典
            // selectDictLabel: function(datas, value) {
            //     var actions = [];
            //     $.each(datas, function(index, dict) {
            //         if (dict.dictValue == ('' + value)) {
            //             var listClass = $.common.equals("default", dict.listClass) ? "" : "badge badge-" + dict.listClass;
            //             actions.push($.common.sprintf("<span class='%s'>%s</span>", listClass, dict.dictLabel));
            //             return false;
            //         }
            //     });
            //     return actions.join('');
            // },
            //table参数end
        }
    })
})
var operate = {
    openUrl(url){
        layer.open({
            shadeClose: true,
            shade: 0.8,
            type: 2,
            area:['1200px', '600px'],
            content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            end :function () {
                console.log($.table)
                $.table.refresh()
            }
        });
    },
    closeIframe() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index);
    },
    search(){
        let val = $('#searchName').val()
        if(val != undefined){
            $.table.gridParam({'name':val})
        }else {
            $.table.refresh()
        }

    },
    delete(url){
        layer.confirm('是否删除?', {
            btn: ['确认', '取消']
        }, function(index, layero){
            operate.load()
            operate.get(url,()=>{
                operate.saveSuccess("操作");
                $.table.refresh()
            });
        });
    },
    load(){
        layer.load(1)
    },
    loadClose(){
      layer.closeAll()
    },
    get(url,successCallBack){
        $.get(url,res=>{
            if(res.code == 200){
                operate.loadClose()
                successCallBack(res)
            }else{
                operate.loadClose()
                layer.msg("系统错误！请稍后重试")
            }
        })
    },
    add(dataFormJson,url,preffix) {
        console.log(dataFormJson)
        $.ajax({
            cache : true,
            type : "POST",
            url : url,
            data : dataFormJson,
            // async : false,
            error : function(request) {
                operate.loadClose()
                layer.msg(preffix+"错误")
            },
            success : function(data) {
                operate.loadClose()
                operate.saveSuccess(preffix+"成功");
                $.table.refresh()
            }
        });
    },
    saveSuccess(msg){
        this.closeIframe();
        layer.msg(msg+"成功")
    },
    pickDate( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=text]')
                .datepicker({format:'yyyy-mm-dd HH' , autoclose:true});
        }, 0);
    },
    tiemToDate: function(str,format) {
        if(str == '' || str== null) return str;
        format = format == '' || format == null ? 'yyyy-MM-dd HH:mm' : format;
        var date = new Date(str),
            t = date.getFullYear(),
            m = date.getMonth()+1,
            d = date.getDate(),
            h = date.getHours(),
            mi = date.getMinutes(),
            s = date.getSeconds();
        if(format == '' || format == undefined){
            ti = t +'-'+ this.prefixInteger(m,2) +'-'+ this.prefixInteger(d,2);//最后拼接时间
        }else{
            ti = t +'-'+ this.prefixInteger(m,2) +'-'+ this.prefixInteger(d,2)
                +' '+ this.prefixInteger(h,2) +':'+ this.prefixInteger(mi,2) +':'+this.prefixInteger(s,2);//最后拼接时间
        }
        return ti;
    },
    prefixInteger(num, length){
        return (Array(length).join('0') + num).slice(-length);
    },
}


