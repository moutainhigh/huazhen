package com.hzfh.api.payment.model.response.controller;

import com.hzfh.api.payment.model.request.CommonController;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("response")
public class DirectTransactionResp extends CommonController{
	@XStreamAlias("code")
	private String code;//Y 【见返回码】
	@XStreamAlias("description")
	private String description;//N 描述信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
