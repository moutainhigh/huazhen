<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="m" uri="/hz-tags" %>

<m:ContentPage materPageId="layout">
    <m:Content contentPlaceHolderId="center">
        <div class="wrappSearch mt30">
            <h3></h3>
            <div class="wrappSearchContent">
                打款状态:<select id="selectStatus"></select>
                产品类型：<select id="selectProductType"></select>
                产品：<select id="selectProduct"></select>
                <input id="btnSearch" type="button" value="查询" class="btn_style"/>
                <%--<s:if test="showExcelButton">--%>
                    <%--<input type="button" id="btnExcel" value="导出Excel" class="btn_style" />--%>
                <%--</s:if>--%>
            </div>
        </div>
        <p class="mt50">
        </p>
        <div class="pic mt20">
            <table id="gridTable" class="gridTable">
            </table>
            <div id="gridPager" class="gridPager">
            </div>
        </div>
        ${pageVar}

    </m:Content>
</m:ContentPage>
