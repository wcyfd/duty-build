package com.aim.duty.duty_build.navigation;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build.module.build.action.BuildAddBrickToWallAction;
import com.aim.duty.duty_build.module.build.action.BuildAddMagicAction;
import com.aim.duty.duty_build.module.build.action.BuildChooseMaterialAction;
import com.aim.duty.duty_build.module.build.action.BuildCreateRoleAction;
import com.aim.duty.duty_build.module.build.action.BuildGetResultAction;
import com.aim.duty.duty_build.module.build.action.BuildShowBagAction;
import com.aim.duty.duty_build.module.build.action.BuildShowBrickAction;
import com.aim.duty.duty_build.module.build.action.BuildShowWallAction;
import com.aim.duty.duty_build_entity.navigation.ProtocalId;
import com.aim.game_base.navigation.ActionSupport;

public class ActionNavigation {
	private static BuildAddBrickToWallAction buildAddBrickToWallAction;
	private static BuildAddMagicAction buildAddMagicAction;
	private static BuildCreateRoleAction buildCreateRoleAction;
	private static BuildChooseMaterialAction buildChooseMaterialAction;
	private static BuildGetResultAction buildGetResultAction;
	private static BuildShowBagAction buildShowBagAction;
	private static BuildShowBrickAction buildShowBrickAction;
	private static BuildShowWallAction buildShowWallAction;

	public static Map<Integer, ActionSupport> navigation = new HashMap<>();

	public static ActionSupport getAction(int protocal) {
		return navigation.get(protocal);
	}

	public static void init() {
		navigation.put(ProtocalId.CREATE_ROLE, 			buildCreateRoleAction);
		navigation.put(ProtocalId.SHOW_WALL, 			buildShowWallAction);
		navigation.put(ProtocalId.SHOW_BRICK, 			buildShowBrickAction);
		navigation.put(ProtocalId.ADD_BRICK_TO_WALL, 	buildAddBrickToWallAction);
		navigation.put(ProtocalId.ADD_MAGIC, 			buildAddMagicAction);
		navigation.put(ProtocalId.GET_RESULT, 			buildGetResultAction);
		navigation.put(ProtocalId.CHOOSE_MATERIAL, 		buildChooseMaterialAction);
		navigation.put(ProtocalId.SHOW_BAG, 			buildShowBagAction);
	}

	public static void setBuildCreateRoleAction(BuildCreateRoleAction buildCreateRoleAction) {
		ActionNavigation.buildCreateRoleAction = buildCreateRoleAction;
	}

	public static void setBuildAddBrickToWallAction(BuildAddBrickToWallAction buildAddBrickToWallAction) {
		ActionNavigation.buildAddBrickToWallAction = buildAddBrickToWallAction;
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

}
