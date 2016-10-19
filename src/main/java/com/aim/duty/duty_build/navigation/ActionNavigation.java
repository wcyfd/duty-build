package com.aim.duty.duty_build.navigation;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build.module.build.action.BuildGetWallValueAction;
import com.aim.duty.duty_build.module.build.action.BuildReplaceBrickAction;
import com.aim.duty.duty_build.module.build.action.BuildAddMagicAction;
import com.aim.duty.duty_build.module.build.action.BuildChooseMaterialAction;
import com.aim.duty.duty_build.module.build.action.BuildCreateRoleAction;
import com.aim.duty.duty_build.module.build.action.BuildGetResultAction;
import com.aim.duty.duty_build.module.build.action.BuildShowBagAction;
import com.aim.duty.duty_build.module.build.action.BuildShowBrickAction;
import com.aim.duty.duty_build.module.build.action.BuildShowWallAction;
import com.aim.duty.duty_build.module.trade.action.TradeBuyPropAction;
import com.aim.duty.duty_build.module.trade.action.TradeSalePropAction;
import com.aim.duty.duty_build_entity.navigation.BuildProtocalId;
import com.aim.game_base.navigation.ActionSupport;

public class ActionNavigation {
	private static BuildReplaceBrickAction buildReplaceBrickAction;
	private static BuildAddMagicAction buildAddMagicAction;
	private static BuildCreateRoleAction buildCreateRoleAction;
	private static BuildChooseMaterialAction buildChooseMaterialAction;
	private static BuildGetResultAction buildGetResultAction;
	private static BuildShowBagAction buildShowBagAction;
	private static BuildShowBrickAction buildShowBrickAction;
	private static BuildShowWallAction buildShowWallAction;
	private static BuildGetWallValueAction buildGetWallValueAction;
	
	private static TradeBuyPropAction tradeBuyPropAction;
	private static TradeSalePropAction tradeSalePropAction;

	public static Map<Integer, ActionSupport> navigation = new HashMap<>();

	public static ActionSupport getAction(int protocal) {
		return navigation.get(protocal);
	}

	public static void init() {
		navigation.put(BuildProtocalId.CREATE_ROLE, 		buildCreateRoleAction);
		navigation.put(BuildProtocalId.SHOW_WALL, 			buildShowWallAction);
		navigation.put(BuildProtocalId.SHOW_BRICK, 			buildShowBrickAction);
		navigation.put(BuildProtocalId.REPLACE_BRICK, 		buildReplaceBrickAction);
		navigation.put(BuildProtocalId.ADD_MAGIC, 			buildAddMagicAction);
		navigation.put(BuildProtocalId.GET_RESULT, 			buildGetResultAction);
		navigation.put(BuildProtocalId.CHOOSE_MATERIAL, 	buildChooseMaterialAction);
		navigation.put(BuildProtocalId.SHOW_BAG, 			buildShowBagAction);
		navigation.put(BuildProtocalId.GET_WALL_VALUE, 		buildGetWallValueAction);
		navigation.put(BuildProtocalId.TRADE_BUY_PROP, 		tradeBuyPropAction);
		navigation.put(BuildProtocalId.TRADE_SALE_PROP,		tradeSalePropAction);
	}

	public static void setBuildCreateRoleAction(BuildCreateRoleAction buildCreateRoleAction) {
		ActionNavigation.buildCreateRoleAction = buildCreateRoleAction;
	}

	public static void setBuildReplaceBrickAction(BuildReplaceBrickAction buildReplaceBrickAction) {
		ActionNavigation.buildReplaceBrickAction = buildReplaceBrickAction;
	}

	public static void setBuildAddMagicAction(BuildAddMagicAction buildAddMagicAction) {
		ActionNavigation.buildAddMagicAction = buildAddMagicAction;
	}

	public static void setBuildChooseMaterialAction(BuildChooseMaterialAction buildChooseMaterialAction) {
		ActionNavigation.buildChooseMaterialAction = buildChooseMaterialAction;
	}

	public static void setBuildGetResultAction(BuildGetResultAction buildGetResultAction) {
		ActionNavigation.buildGetResultAction = buildGetResultAction;
	}

	public static void setBuildShowBagAction(BuildShowBagAction buildShowBagAction) {
		ActionNavigation.buildShowBagAction = buildShowBagAction;
	}

	public static void setBuildShowBrickAction(BuildShowBrickAction buildShowBrickAction) {
		ActionNavigation.buildShowBrickAction = buildShowBrickAction;
	}

	public static void setBuildShowWallAction(BuildShowWallAction buildShowWallAction) {
		ActionNavigation.buildShowWallAction = buildShowWallAction;
	}
	
	public static void setBuildGetWallValueAction(BuildGetWallValueAction buildGetWallValueAction) {
		ActionNavigation.buildGetWallValueAction = buildGetWallValueAction;
	}
	
	public static void setTradeBuyPropAction(TradeBuyPropAction tradeBuyPropAction) {
		ActionNavigation.tradeBuyPropAction = tradeBuyPropAction;
	}
	
	public static void setTradeSalePropAction(TradeSalePropAction tradeSalePropAction) {
		ActionNavigation.tradeSalePropAction = tradeSalePropAction;
	}

}
