package com.aim.duty.duty_build.module.trade.service;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.cache.SessionCache;
import com.aim.duty.duty_build.cache.config.MagicConfigCache;
import com.aim.duty.duty_build.cache.config.OreConfigCache;
import com.aim.duty.duty_build_entity.bo.Brick;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.common.BuildErrorCode;
import com.aim.duty.duty_build_entity.navigation.BuildProtocalId;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_BuyProp;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_SaleProp;
import com.aim.duty.duty_market_entity.navigation.MarketProtocalId;
import com.aim.duty.duty_market_entity.protobuf.protocal.market.MarketProtocal.CS_BuyCommodity;
import com.aim.duty.duty_market_entity.protobuf.protocal.market.MarketProtocal.CS_SaleCommodity;
import com.aim.game_base.entity.net.base.Protocal.CS;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.net.WanClient;
import com.google.protobuf.ByteString;

public class TradeServiceImpl implements TradeService {
	private WanClient marketServer;

	public void setMarketServer(WanClient marketServer) {
		this.marketServer = marketServer;
	}

	@Override
	public void saleProp(Role role, int propId, int num, int singlePrice) {
		// TODO Auto-generated method stub
		if (this.hasTradeTransaction(role,BuildProtocalId.TRADE_SALE_PROP,BuildErrorCode.SALE_FAILED)) {
			return;
		}
		synchronized (role.getTrade()) {
			if (this.hasTradeTransaction(role,BuildProtocalId.TRADE_SALE_PROP,BuildErrorCode.SALE_FAILED)) {
				return;
			}
			role.getTrade().setPropId(propId);
			role.getTrade().setTradeEnd(false);
		}

		Brick prop = (Brick) role.getPropMap().get(propId);
		if(prop == null){
			IoSession clientSession = SessionCache.getSessionByRoleId(role.getId());

			clientSession.write(SC.newBuilder().setProtocal(BuildProtocalId.TRADE_SALE_PROP)
					.setData(SC_SaleProp.newBuilder().setSuccess(BuildErrorCode.SALE_FAILED).build().toByteString())
					.build());
			role.getTrade().setTradeEnd(true);
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (com.aim.duty.duty_build_entity.po.Magic magic : prop.getMagicMap().values()) {
			sb.append(MagicConfigCache.getMagicConfigById(magic.getMagicId()).getName());
		}
		sb.append(OreConfigCache.getAllMap().get(prop.getMineId()).getName());

		String name = sb.toString();

		CS cs = CS
				.newBuilder()
				.setProtocal(MarketProtocalId.SALE_COMMODITY)
				.setData(
						CS_SaleCommodity.newBuilder().setRoleId(role.getId()).setName(name).setNum(num)
								.setPropType( prop.getPropType()).setSinglePrice(singlePrice).setAbstractProp(prop.serialize())
								.build().toByteString()).build();
		marketServer.send(cs);

	}

	@Override
	public void buyProp(Role role, int commodityId, int num) {
		// TODO Auto-generated method stub
		if (this.hasTradeTransaction(role, BuildProtocalId.TRADE_BUY_PROP, BuildErrorCode.BUY_FAILED)) {
			return;
		}
		synchronized (role.getTrade()) {
			if (this.hasTradeTransaction(role, BuildProtocalId.TRADE_BUY_PROP, BuildErrorCode.BUY_FAILED)) {
				return;
			}
			role.getTrade().setPropId(commodityId);
			role.getTrade().setTradeEnd(false);
		}
		
		CS cs = CS
				.newBuilder()
				.setProtocal(MarketProtocalId.BUY_COMMODITY)
				.setData(
						CS_BuyCommodity.newBuilder().setCommodityId(commodityId).setNum(num).setRoleId(role.getId())
								.build().toByteString()).build();
		marketServer.send(cs);
	}

	private boolean hasTradeTransaction(Role role, int protocal, int errorCode) {
		if (!role.getTrade().isTradeEnd()) {
			IoSession session = SessionCache.getSessionByRoleId(role.getId());
			session.write(SC.newBuilder().setProtocal(protocal)
					.setData(SC_SaleProp.newBuilder().setSuccess(errorCode).build().toByteString()).build());
			return true;
		}
		return false;
	}

	@Override
	public void salePropCallback(Role role, int success, int commodityId) {
		try {
			if (success != 1) {
				System.out.println(success);
				return;
			}
			
			IoSession clientSession = SessionCache.getSessionByRoleId(role.getId());

			role.getPropMap().remove(role.getTrade().getPropId());

			clientSession.write(SC.newBuilder().setProtocal(BuildProtocalId.TRADE_SALE_PROP)
					.setData(SC_SaleProp.newBuilder().setPropId(role.getTrade().getPropId()).setSuccess(BuildErrorCode.SUCCESS).build().toByteString())
					.build());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{			
			role.getTrade().setTradeEnd(true);
		}
	}

	@Override
	public void buyPropCallback(Role role, int success, ByteString abstractProp, int propType, int num, int singlePrice) {
		// TODO Auto-generated method stub
		try {
			if (success != 1) {
				System.out.println(success);
				return;
			}

			IoSession clientSession = SessionCache.getSessionByRoleId(role.getId());

			Brick brick = new Brick();
			brick.deserialize(abstractProp);
			brick.setId(role.getPropUniqueId());
			role.getPropMap().put(brick.getId(), brick);

			clientSession.write(SC
					.newBuilder()
					.setProtocal(BuildProtocalId.TRADE_BUY_PROP)
					.setData(
							SC_BuyProp.newBuilder().setPropType(propType).setSuccess(success)
									.setAbstractProp(abstractProp).setPropId(brick.getId()).build().toByteString()).build());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			role.getTrade().setTradeEnd(true);
		}
	}
}
