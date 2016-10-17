package com.aim.duty.duty_build.cache.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.aim.duty.duty_build_entity.fo.OreConfig;

public class OreConfigCache {
	private static Map<Integer, OreConfig> map = new HashMap<>();
	private static Vector<Integer> idVector = new Vector<>();
	private static Vector<String> nameVector = new Vector<>();

	public static void putOreConfig(OreConfig config) {
		map.put(config.getConfigId(), config);
		idVector.add(config.getConfigId());
		nameVector.add(config.getName());
	}

	public static Map<Integer, OreConfig> getAllMap() {
		return map;
	}

	public static int getConfigIdByIndex(int index) {
		return idVector.get(index);
	}

	public static Vector<String> getNames() {
		return nameVector;
	}

}
