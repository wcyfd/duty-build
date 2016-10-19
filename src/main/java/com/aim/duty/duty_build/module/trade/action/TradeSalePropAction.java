package com.aim.duty.duty_build.module.trade.action;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.cache.RoleCache;
import com.aim.duty.duty_build.module.trade.service.TradeService;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.CS_SaleProp;
import com.aim.game_base.navigation.ActionSupport;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class TradeSalePropAction implements ActionSupport {

	private TradeService tradeService;

	public void setTradeService(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	@Override
	public void execute(ByteString data, IoSession session) {
		// TODO Auto-generated method stub
		try {
			CS_SaleProp csSaleProp = CS_SaleProp.parseFrom(data);
			Role role = RoleCache.getRoleBySession(session);
			tradeService.saleProp(role, csSaleProp.getPropId(), csSaleProp.getNum(), csSaleProp.getSinglePrice());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
