package com.aim.duty.duty_build.navigation;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build_entity.navigation.ProtocalId;
import com.aim.game_base.navigation.ActionSupport;

public class ActionNavigation {
	public static Map<Integer, ActionSupport> navigation = new HashMap<>();

	public static ActionSupport getAction(int protocal) {
		return navigation.get(protocal);
	}

	public static void init() {
	}
}
