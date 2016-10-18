package com.aim.duty.duty_build.navigation;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build_entity.navigation.ProtocalId;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddBrickToWall;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddMagic;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ChooseMaterial;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_CreateRole;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBag;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBrick;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowWall;

public class ProtocalNavigation {
	public static Map<Integer, Class<?>> map = new HashMap<>();

	public static void init() {
		map.put(ProtocalId.CREATE_ROLE, SC_CreateRole.class);
		map.put(ProtocalId.SHOW_WALL, SC_ShowWall.class);
		map.put(ProtocalId.SHOW_BRICK, SC_ShowBrick.class);
		map.put(ProtocalId.ADD_BRICK_TO_WALL, SC_AddBrickToWall.class);
		map.put(ProtocalId.ADD_MAGIC, SC_AddMagic.class);
		map.put(ProtocalId.GET_RESULT, SC_GetResult.class);
		map.put(ProtocalId.CHOOSE_MATERIAL, SC_ChooseMaterial.class);
		map.put(ProtocalId.SHOW_BAG, SC_ShowBag.class);
	}
	
	public static Class<?> getClassByProtocalId(int protocalId){
		return map.get(protocalId);
	}
	
}
