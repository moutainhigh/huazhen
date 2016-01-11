package com.hzfh.fmp.controller.payment;

import com.hzfh.fmp.controller.common.CommonAction;
import com.hzfh.fmp.model.common.PageAlias;
import com.hzfh.fmp.model.common.enumeration.TagPermissionType;
import com.hzfh.fmp.model.common.helper.UserHelper;
import com.hzfh.fmp.model.permission.view.TagPermissionView;

public class PaymentRefundAddAction extends CommonAction {
    public boolean showEditButton;
    public boolean showPaymentCkButton;
    public boolean showPaymentRefundButton;

    public boolean isShowEditButton() {
        return showEditButton;
    }
    public boolean isShowPaymentCkButton() {
        return showPaymentCkButton;
    }public boolean isShowPaymentRefundButton() {
        return showPaymentRefundButton;
    }
    public boolean showSubmitButton;
    
    public boolean isShowSubmitButton() {
        return showSubmitButton;
    }

    private String pageVar;

    public String getPageVar() {
        return pageVar;
    }

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        this.setPageAlias(PageAlias.paymentRefundAdd);

        String ret = super.execute();
        if (!ret.equals(SUCCESS))
            return ret;

        if (this.getPagePermissionView() != null && this.getPagePermissionView().isCreate())
            this.showSubmitButton = true;

        if (this.getPagePermissionView() != null && this.getPagePermissionView().isEdit()) {
            this.showEditButton = true;
            this.showSubmitButton = true;
        }
        if (this.getPagePermissionView().getTagPermission("showPaymentCkButton") == TagPermissionType.query) {
            this.showPaymentCkButton = true;
        } else {
            this.showPaymentCkButton = false;
        }
        if (this.getPagePermissionView().getTagPermission("showPaymentRefundButton") == TagPermissionType.query) {
            this.showPaymentRefundButton = true;
        } else {
            this.showPaymentRefundButton = false;
        }


        initPageVar();

        return SUCCESS;
    }

    private void initPageVar() {
        StringBuilder sb = new StringBuilder();

        sb.append("<script type=\"text/javascript\">");
        sb.append("var PageVar={");
        sb.append(String.format("%1$s:'%2$s',", "ID", this.id));
        sb.append(String.format("%1$s:'%2$s',", "UserId", UserHelper.getEditUserNo()));
        sb.append("};");

        sb.append("var ElementVar={");
        for(TagPermissionView tagPermissionView: this.getPagePermissionView().getTagPermissionViewList()){
            sb.append(String.format("%1$s:'%2$s',", tagPermissionView.getTagName(), String.valueOf(tagPermissionView.getTagPermissionType())));
        }
        sb.append("};");

        sb.append("</script>");

        this.pageVar = sb.toString();
    }

}