var ManagePage = {
    DateInputElem: function (value, options) {
        var el = document.createElement("input");
        el.type = "text";
        el.value = value;
        el.onclick = function () { el.focus(); el.select(); WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' }); };
        $(el).addClass("FormElement");
        $(el).addClass("ui-widget-content");
        $(el).addClass("ui-corner-all");
        $(el).css("width", "204px");
        return el;
    },
    DateInputValue: function (elem, operation, value) {
        if (operation === 'get') {
            return $(elem).val();
        } else if (operation === 'set') {
            $(elem).val(value);
        }
    },
    InitGrid: function () {
        //grid start
        $("#gridTable").jqGrid({
            url: $Url.BuildPaymentUrl('/payment/paymentRefund/ajaxListPaymentRefund.action'),
            editurl: $Url.BuildPaymentUrl("/payment/paymentRefund/ajaxEditPaymentRefund.action"),
            datatype: "json",
            mtype: 'GET',
            colNames: ["操作","还款编号","打款编号","p2p产品","P2P产品","customerNo","客户","payerNo","付款人","投资金额(元)","利息(元)","还款金额(元)","手续费(元)","还款时间","还款截止时间","交易状态","是否发送短信","审核状态"],
            colModel: [

				{
					name: "act", index: "act", width: 80, align: "center", sortable: false
				},
				{
					name: "id", index: "id", width: 20, align: "center",hidden:true, sorttype: "number", formoptions: { rowpos: 1, colpos: 1 }, editable: true, editoptions: { readonly: true, size: 40 }
				},
				{
					name: "salesNo", index: "salesNo", width: 40, align: "left", formoptions: { rowpos: 2, colpos: 1 },formatter:$Link.MakeSalesDetailsUrl, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "p2pProductNo", index: "p2pProductNo", width: 40, align: "left", hidden:true,formoptions: { rowpos: 2, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
                {
                    name: "editComment", index: "editComment", width: 80, align: "left",formoptions: { rowpos: 3, colpos: 1 },formatter: $Link.MakeProductUrl, sortable: false, editable: true, editoptions: { size: 40 }
                },
				{
					name: "customerNo", index: "customerNo", width: 40, align: "left",hidden:true, formoptions: { rowpos: 3, colpos: 1 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "customerName", index: "customerName", width: 40, align: "left", formatter: $Link.MakeCustomerUrl, formoptions: { rowpos: 3, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "payerNo", index: "payerNo", width: 40, align: "left", hidden:true, formoptions: { rowpos: 4, colpos: 1 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "payerName", index: "payerName", width: 40, align: "left", formoptions: { rowpos: 4, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "salesMoney", index: "salesMoney", width: 40, align: "left", formoptions: { rowpos: 5, colpos: 1 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "interest", index: "interest", width: 40, align: "left", formoptions: { rowpos: 5, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "payMoney", index: "payMoney", width: 40, align: "left", formoptions: { rowpos: 6, colpos: 1 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "serviceCharge", index: "serviceCharge", width: 40, align: "left", formoptions: { rowpos: 6, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 }
				},
				{
					name: "actualPayTime", index: "actualPayTime", width: 40, align: "left", formatter:"date",formoptions: { rowpos: 7, colpos: 1 }, sortable: false, formatoptions: { srcformat: 'Y-m-d', newformat: 'Y-m-d' }, editable: true, edittype: 'custom', editoptions: { custom_element: ManagePage.DateInputElem, custom_value: ManagePage.DateInputValue, size: 40 }
				},
				{
					name: "payEndTime", index: "payEndTime", width: 40, align: "left",hidden:true,formatter:"date", formoptions: { rowpos: 7, colpos: 2 }, sortable: false, formatoptions: { srcformat: 'Y-m-d', newformat: 'Y-m-d' }, editable: true, edittype: 'custom', editoptions: { custom_element: ManagePage.DateInputElem, custom_value: ManagePage.DateInputValue, size: 40 }
				},
				{
					name: "status", index: "status", width: 40, align: "left", formatter:"select", formoptions: { rowpos: 8, colpos: 1 }, sortable: false, editable: true, editoptions: { size: 40,value: EnumList.GetEnumListToEdit("paymentRefundStatus",$Url.BuildPaymentUrl("/common/enumList.action")) }
				},
                {
                    name: "isSendSms", index: "isSendSms", width: 40, align: "left",formatter:"select",  formoptions: { rowpos: 15, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 },editoptions: { size: 40,value: EnumList.GetEnumListToEdit("dicSmsStatus",$Url.BuildPaymentUrl("/common/enumList.action")) }
                },
                {
                    name: "examineStatus", index: "examineStatus", width: 40, align: "left",formatter:"select",  formoptions: { rowpos: 15, colpos: 2 }, sortable: false, editable: true, editoptions: { size: 40 },editoptions: { size: 40,value: EnumList.GetEnumListToEdit("examineStatus",$Url.BuildPaymentUrl("/common/enumList.action")) }
                }

            ],
            postData: {
                byPayType:0,
                showAllList:ElementVar.showAllList
            },
            sortname: "id",
            sortorder: "desc",
            viewrecords: true,
            rowNum: 10,
            rowList: [10],
            altclass: "altRowsColour",
            shrinkToFit:true,
            autowidth: true,
            height: "auto",
            multiselect: true,
            prmNames: {
                search: "search",
                page: "pageIndex",
                rows: "pageSize"
            },
            jsonReader: {
                root: "resultList",
                page: "pageIndex",
                total: "pageCount",
                records: "recordCount",
                repeatitems: false
            },
            pager: "#gridPager",
            gridComplete: function () {
                //var space = "|";
                var ids = $("#gridTable").jqGrid('getCol', 'id', true);
                var salesNos = $("#gridTable").jqGrid('getCol', 'salesNo', true);

                for (var i = 0; i < ids.length; i++) {
                    var salesNo = salesNos[i].value;
                    var id = ids[i].id;
                    var detail = "";
                    var principal = "";
                    var interest ="";


                    detail = "<a class=\"blue\"  href=\"javascript:ManagePage.GetDetail('" + id + "')\">查看</a>";
                    principal = "<a class=\"blue\"  href=\"javascript:ManagePage.GetPrincipal('" + salesNo + "')\">本金续投</a>";
                    interest = "<a class=\"blue\"  href=\"javascript:ManagePage.GetInterest('" + salesNo + "')\">本息续投</a>";
                    $("#gridTable").jqGrid("setRowData", id, { act: detail+" | "+principal+" | "+interest});
                }
            }
        });
    },
    InitQuery: function () {
        $("#btnSearch").click(function () {
            var byP2pProduct = $("#byP2pProduct").val();
            var byStatus = $("#byStatus").val();
            var byStartRepayIssue = $("#byStartRepayIssue").val();
            var byEndRepayIssue = $("#byEndRepayIssue").val();
            var smsStatus = $("#smsStatus").val();
            $("#gridTable").jqGrid('setGridParam', {
                datatype: "json",
                postData: {
                    "byP2pProduct": byP2pProduct,
                    "byStatus": byStatus,
                    "byStartRepayIssue":byStartRepayIssue,
                    "byEndRepayIssue":byEndRepayIssue,
                    "smsStatus":smsStatus
                },
                page: 1
            }).trigger("reloadGrid");
        });
    },
    GetDetail: function (index) {
        window.open($Url.BuildPaymentUrl("/payment/paymentRefund/edit?id="+index));
    },
    GetPrincipal: function (index) {
        window.open($Url.BuildPaymentUrl("/sales/sales/editForP2pProduct?salesNo="+index));
    },
    GetInterest: function (index) {
        window.open($Url.BuildPaymentUrl("/sales/sales/editForP2pProduct?salesNo="+index));
    },
    CancelSendSms:function(){
        var ids = $("#gridTable").jqGrid('getGridParam','selarrrow');
        $.each(ids,function(i,id) {
            var isSendSms = $("#gridTable").jqGrid('getCell',id,'isSendSms');
            if(isSendSms==2){
                alert("选中数据中含有‘已发送’，请重新选择");
                ids = 0;
            }
        });
        if(ids==0){
            return;
        }
        var url = $Url.BuildPaymentUrl("/payment/paymentRefund/ajaxCancelSendSms.action");
        $.ajax({
            type: "post",
            url: url,
            data: {
                ids:ids.join(",")
            },
            beforeSend: function () {
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            },
            success: function (data, textStatus) {
                alert(data.errDesc);
                location.reload()
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    },
    SendSms:function(type){
        var ids = $("#gridTable").jqGrid('getGridParam','selarrrow');
        var url = $Url.BuildPaymentUrl("/payment/paymentRefund/ajaxSendSms.action");
        $.ajax({
            type: "post",
            url: url,
            data: {
                type:type,
                ids:ids.join(",")
            },
            beforeSend: function () {
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            },
            success: function (data, textStatus) {
                if(data.errDesc=="操作成功"){
                    alert(data.errDesc);
                    $("#gridTable").trigger("reloadGrid");
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }
}


$(function () {
    $("#cancelSms").click(function(){
        ManagePage.CancelSendSms();
    });
    $("#sendSms").click(function(){
        ManagePage.SendSms(1);
    });

    $("#continueSms").click(function(){
        ManagePage.SendSms(2);
    });
    $("#btnAdd").click(function () { ManagePage.GetAdd(); });
    $(".date").click(function () {
        WdatePicker({dateFmt: 'yyyy-MM-dd'});
    });
    $("#honour").click(function(){
        var honourDate = $("#honourDate").val();
        if(honourDate==null||honourDate==""){
            alert("请选择日期");
            return;
        }
        window.open($Url.BuildSalesUrl("/report/paymentReportDeatil/list?honourDate="+honourDate+"&type=honour"));
    });
    $("#interest").click(function(){
        var honourDate = $("#honourDate").val();
        if(honourDate==null||honourDate==""){
            alert("请选择日期");
            return;
        }
        window.open($Url.BuildSalesUrl("/report/paymentReportDeatil/list?honourDate="+honourDate+"&type=interest"));
    });

    EnumList.GetEnumListToSelect($("#smsStatus"),"dicSmsStatusAll",$Url.BuildCustomerUrl("/common/enumList.action"))
    EnumList.GetEnumListToSelect($("#byP2pProduct"),"p2pProductListAll",$Url.BuildCustomerUrl("/common/enumList.action"))
    EnumList.GetEnumListToSelect($("#byStatus"),"paymentRefundStatusAll",$Url.BuildCustomerUrl("/common/enumList.action"))

    ManagePage.InitGrid();
    ManagePage.InitQuery();

    $("#btnExcel").click(function(){
        var byP2pProduct = $("#byP2pProduct").val();
        var byStatus = $("#byStatus").val();
        var byStartRepayIssue = $("#byStartRepayIssue").val();
        var byEndRepayIssue = $("#byEndRepayIssue").val();
        var smsStatus = $("#smsStatus").val();
        var url = $Url.BuildCustomerUrl("/payment/paymentRefund/ajaxExportExcel");
        location.href = url + "?" +
            "sord=desc" + "&" +
            "sidx=id" + "&" +
            "byP2pProduct=" + byP2pProduct + "&" +
            "byStatus=" + byStatus + "&" +
            "byStartRepayIssue=" + byStartRepayIssue + "&" +
            "byEndRepayIssue=" + byEndRepayIssue + "&" +
            "smsStatus=" + smsStatus+"&"+
            "byPayType=0";
    });
});