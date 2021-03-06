package com.hzfh.fmp.controller.employee;

import com.hzfh.api.employee.model.AttendanceApplication;
import com.hzfh.api.employee.model.ResignApplication;
import com.hzfh.fmp.controller.common.CommonAction;
import com.hzfh.fmp.model.common.PageAlias;
import com.hzfh.fmp.model.common.enumeration.TagPermissionType;
import com.hzfh.fmp.model.common.helper.UserHelper;
import com.hzfh.fmp.model.employee.AttendanceApplicationModel;
import com.hzfh.fmp.model.employee.ResignApplicationModel;
import com.hzfh.fmp.model.permission.view.TagPermissionView;
import com.hzfh.fmp.model.workFlow.ActRuTaskModel;
import com.hzframework.helper.StringHelper;

public class ResignApplicationAddAction extends CommonAction {
    public boolean showEditButton;

    public boolean isShowEditButton() {
        return showEditButton;
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
    
    private int activitiStatus;
    public int getActivitiStatus() {
		return activitiStatus;
	}

	public void setActivitiStatus(int activitiStatus) {
		this.activitiStatus = activitiStatus;
	}
	private String backUrl;
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	private boolean showExamineButton;
    private String activitiNo;

    public void setActivitiNo(String activitiNo) {
        this.activitiNo = activitiNo;
    }

    public boolean isShowExamineButton() {
        return showExamineButton;
    }
    private int status;
    
    public int getStatus() {
		return status;
	}
	@Override
    public String execute() throws Exception {
        this.setPageAlias(PageAlias.resignApplicationAdd);

        String ret = super.execute();
        if (!ret.equals(SUCCESS))
            return ret;

        if (this.getPagePermissionView() != null && this.getPagePermissionView().isCreate())
            this.showSubmitButton = true;

        if (this.getPagePermissionView() != null && this.getPagePermissionView().isEdit()) {
            this.showEditButton = true;
            this.showSubmitButton = true;
        }



        this.initShowEditButton();
        this.initPageVar();
        this.initShowExamineButton();

        return SUCCESS;
    }
	private void initShowEditButton() {
		int userId = UserHelper.getEditUserNo();
    	ResignApplication tApplication = new ResignApplication();
    	tApplication=ResignApplicationModel.getInfo(this.id);
//    	if(tApplication!=null){
//        	this.status=tApplication.getWorkFlowStatus();
//
//    	}
        if (this.getPagePermissionView() != null && this.getPagePermissionView().isEdit() && StringHelper.isNullOrEmpty(this.activitiNo)) {
            this.showEditButton = true;
            this.showSubmitButton = true;
        } else {
        	if(!StringHelper.isNullOrEmpty(this.activitiNo) 
        			&& ActRuTaskModel.getInfoByAciIdAndUserId(this.activitiNo, userId) != null && tApplication.getInUserNo()==userId){
        		 this.showEditButton = true;
                 this.showSubmitButton = true;
        	}else{
        		this.showEditButton = false;
                this.showSubmitButton = false;
        	}
        }
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
    private void initShowExamineButton() {
        int userId = UserHelper.getEditUserNo();
        if (!StringHelper.isNullOrEmpty(this.activitiNo) && ActRuTaskModel.getInfoByAciIdAndUserId(this.activitiNo, userId) != null) {
            this.showExamineButton = true;
        } else {
            this.showExamineButton = false;
        }
    }
}