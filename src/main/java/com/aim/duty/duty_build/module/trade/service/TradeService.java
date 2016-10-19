package com.aim.duty.duty_build.module.trade.service;

import com.aim.duty.duty_build_entity.bo.Role;

public interface TradeService {
	public void saleProp(Role role, int propId, int num, int singlePrice);

	public void buyProp(Role role, int commodityId, int num);
}
