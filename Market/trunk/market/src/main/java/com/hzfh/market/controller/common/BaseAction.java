package com.hzfh.market.controller.common;


import com.hzfh.market.model.common.PageAlias;
import com.hzfh.market.model.common.helper.UrlHelper;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

public abstract class BaseAction extends ActionSupport {
    private String ip;

    public String getIp() {
        return ip;
    }

    @Override
    public String execute() {
        ip = ServletActionContext.getRequest().getRemoteAddr();
        return SUCCESS;
    }

    public String buildWWWSiteUrl(PageAlias pageAlias){
        return UrlHelper.buildWWWSiteUrl(pageAlias);
    }

    public String buildJs(String js){
        return UrlHelper.buildJs(js);
    }

    public String buildCss(String css){
        return UrlHelper.buildCss(css);
    }

    public String buildImg(String img){
        return UrlHelper.buildImg(img);
    }

    public String buildDevJs(String js){
        return UrlHelper.buildDevJs(js);
    }
}
