package com.hzfh.fmp.controller.employee;

import com.hzfh.fmp.controller.common.CommonAction;
import com.hzfh.fmp.model.common.PageAlias;

public class PositionAction extends CommonAction {
    private boolean showAddButton;

    public boolean isShowAddButton() {
        return showAddButton;
    }

    public void setShowAddButton(boolean showAddButton) {
        this.showAddButton = showAddButton;
    }

    @Override
    public String execute() throws Exception {
        this.setPageAlias(PageAlias.positionList);

        String ret = super.execute();
        if (!ret.equals(SUCCESS))
            return ret;
        if(this.getPagePermissionView()!=null&&this.getPagePermissionView().isCreate()){
           this.showAddButton = true;
        }
        return SUCCESS;
    }
}
