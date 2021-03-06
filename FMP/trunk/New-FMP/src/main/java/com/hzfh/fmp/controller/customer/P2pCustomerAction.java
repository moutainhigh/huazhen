package com.hzfh.fmp.controller.customer;

import com.hzfh.fmp.controller.common.CommonAction;
import com.hzfh.fmp.model.common.PageAlias;
import com.hzfh.fmp.model.common.enumeration.TagPermissionType;
import com.hzfh.fmp.model.common.helper.UserHelper;
import com.hzfh.fmp.model.permission.view.TagPermissionView;

public class P2pCustomerAction extends CommonAction {
    private String pageVar;
    private boolean showBuildCustomerButton = false;
    private boolean showAssignEmpButton = false;

    public boolean isShowBuildCustomerButton() {
        return showBuildCustomerButton;
    }

    public boolean isShowAssignEmpButton() {
        return showAssignEmpButton;
    }

    public String getPageVar() {
        return pageVar;
    }

    @Override
    public String execute() throws Exception {
        this.setPageAlias(PageAlias.p2pCustomerList);
        String ret = super.execute();
        if (!ret.equals(SUCCESS))
            return ret;

        this.initPageVar();
        this.initShowBuildCustomerButton();
        this.initShowAssignEmpButton();
        return SUCCESS;
    }

    private void initPageVar() {
        StringBuilder sb = new StringBuilder();
        sb.append("<script type=\"text/javascript\">");
        sb.append("var PageVar={");
        sb.append(String.format("%1$s:'%2$s',", "DeptType", UserHelper.getUserCache().getDeptType()));
        sb.append(String.format("%1$s:'%2$s',", "UserID", UserHelper.getUserCache().getUserId()));
        sb.append("};");

        sb.append("var ElementVar={");
        for (TagPermissionView tagPermissionView : this.getPagePermissionView().getTagPermissionViewList()) {
            sb.append(String.format("%1$s:'%2$s',", tagPermissionView.getTagName(), String.valueOf(tagPermissionView.getTagPermissionType())));
        }
        sb.append("};");
        sb.append("</script>");
        this.pageVar = sb.toString();
    }

    private void initShowBuildCustomerButton() {
        if (this.getPagePermissionView().getTagPermission("showBuildCustomerButton") == TagPermissionType.query) {
            this.showBuildCustomerButton = true;
        }
    }

    private void initShowAssignEmpButton() {
        if (this.getPagePermissionView().getTagPermission("showAssignEmpButton") == TagPermissionType.query) {
            this.showAssignEmpButton = true;
        }
    }
}
