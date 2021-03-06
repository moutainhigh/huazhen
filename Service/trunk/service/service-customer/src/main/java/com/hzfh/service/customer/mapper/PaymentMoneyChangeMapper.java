package com.hzfh.service.customer.mapper;

import com.hzfh.api.customer.model.PaymentMoneyChange;
import com.hzfh.api.customer.model.query.PaymentMoneyChangeCondition;
import com.hzframework.data.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/*******************************************************************************
 * 
 * Copyright 2015 HZFH. All rights reserved. 
 * Author: GuoZhenYu  
 * Create Date: 2015/6/8 
 * Description:
 * 
 * Revision History:
 *      Date         Author               Description
 * 
 ******************************************************************************/


@Service("paymentMoneyChangeMapper")
public interface PaymentMoneyChangeMapper extends BaseMapper<PaymentMoneyChange, PaymentMoneyChangeCondition> {
	PaymentMoneyChange getInfoByMoneyChangeTypeAndRefSn(@Param("moneyChangeType")byte moneyChangeType,@Param("requestNo")String requestNo);
}