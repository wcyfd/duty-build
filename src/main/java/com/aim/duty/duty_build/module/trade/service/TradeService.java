package com.aim.duty.duty_build.module.trade.service;

import com.aim.duty.duty_build_entity.bo.Role;
import com.google.protobuf.ByteString;

public interface TradeService {
	public void saleProp(Role role, int propId, int num, int singlePrice);

	public void buyProp(Role role, int commodityId, int num);

	public void salePropCallback(Role role, int success, int commodityId);

	public void buyPropCallback(Role role, int success, ByteString abstractProp, int propType, int num, int singlePrice);
}
