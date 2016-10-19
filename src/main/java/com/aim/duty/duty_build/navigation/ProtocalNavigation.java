package com.aim.duty.duty_build.navigation;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build_entity.navigation.BuildProtocalId;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddMagic;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ChooseMaterial;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_CreateRole;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetWallValue;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ReplaceBrick;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBag;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBrick;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowWall;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_BuyProp;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_SaleProp;

public class ProtocalNavigation {
	public static Map<Integer, Class<?>> map = new HashMap<>();

	public static void init() {
		map.put(BuildProtocalId.CREATE_ROLE, SC_CreateRole.class);
		map.put(BuildProtocalId.SHOW_WALL, SC_ShowWall.class);
		map.put(BuildProtocalId.SHOW_BRICK, SC_ShowBrick.class);
		map.put(BuildProtocalId.REPLACE_BRICK, SC_ReplaceBrick.class);
		map.put(BuildProtocalId.ADD_MAGIC, SC_AddMagic.class);
		map.put(BuildProtocalId.GET_RESULT, SC_GetResult.class);
		map.put(BuildProtocalId.CHOOSE_MATERIAL, SC_ChooseMaterial.class);
		map.put(BuildProtocalId.SHOW_BAG, SC_ShowBag.class);
		map.put(BuildProtocalId.GET_WALL_VALUE, SC_GetWallValue.class);
		
		map.put(BuildProtocalId.TRADE_BUY_PROP, SC_BuyProp.class);
		map.put(BuildProtocalId.TRADE_SALE_PROP, SC_SaleProp.class);
	}
	
	public static Class<?> getClassByProtocalId(int protocalId){
		return map.get(protocalId);
	}
	
}
