<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="m" uri="/hz-tags" %>

<m:ContentPage materPageId="layout">
    <m:Content contentPlaceHolderId="center">
        <div class="wrappSearch mt30">
            <h3></h3>
            <div class="wrappSearchContent">
                员工姓名:<input type="text" id="txtName"/>
                部门：<select id="selectDepartment"></select>
                职位：<select id="selectPositionNo"></select>
                年份：<select id="year"></select>
                月份：<select id="month"></select>
                <input id="btnSearch" type="button" value="查询" class="btn_style"/>
                <input type="button" id="btnExcel" value="导出Excel" class="btn_style"/>
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
