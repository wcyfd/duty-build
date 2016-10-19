package com.aim.duty.duty_build.module.trade.service;

import com.aim.duty.duty_build.cache.config.MagicConfigCache;
import com.aim.duty.duty_build.cache.config.OreConfigCache;
import com.aim.duty.duty_build_entity.bo.Brick;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_market_entity.navigation.MarketProtocalId;
import com.aim.duty.duty_market_entity.protobuf.protocal.market.MarketProtocal.CS_BuyCommodity;
import com.aim.duty.duty_market_entity.protobuf.protocal.market.MarketProtocal.CS_SaleCommodity;
import com.aim.game_base.entity.net.base.Protocal.CS;
import com.aim.game_base.net.WanClient;

public class TradeServiceImpl implements TradeService {
	private WanClient marketServer;

	public void setMarketServer(WanClient marketServer) {
		this.marketServer = marketServer;
	}

	@Override
	public void saleProp(Role role, int propId, int num, int singlePrice) {
		// TODO Auto-generated method stub
		Brick prop = (Brick) role.getPropMap().get(propId);

		int propType = prop.getPropType();
		
		
		StringBuilder sb= new StringBuilder();
		for(com.aim.duty.duty_build_entity.po.Magic magic:prop.getMagicMap().values()){
			sb.append(MagicConfigCache.getMagicConfigById(magic.getMagicId()).getName());								
		}
		sb.append(OreConfigCache.getAllMap().get(prop.getMineId()).getName());
		
		String name = sb.toString();

		CS cs = CS
				.newBuilder()
				.setProtocal(MarketProtocalId.SALE_COMMODITY)
				.setData(
						CS_SaleCommodity.newBuilder().setRoleId(role.getId()).setName(name).setNum(num)
								.setPropType(propType).setSinglePrice(singlePrice).setAbstractProp(prop.serialize())
								.build().toByteString()).build();
		marketServer.send(cs);
	}

	@Override
	public void buyProp(Role role, int commodityId, int num) {
		// TODO Auto-generated method stub
		CS cs = CS
				.newBuilder()
				.setProtocal(MarketProtocalId.BUY_COMMODITY)
				.setData(
						CS_BuyCommodity.newBuilder().setCommodityId(commodityId).setNum(num).setRoleId(role.getId())
								.build().toByteString()).build();
		marketServer.send(cs);
	}

}
