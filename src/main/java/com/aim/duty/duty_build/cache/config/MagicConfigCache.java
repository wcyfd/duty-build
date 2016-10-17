package com.aim.duty.duty_build.cache.config;

import java.util.HashMap;
import java.util.Map;

import com.aim.duty.duty_build_entity.fo.MagicConfig;

public class MagicConfigCache {
	private static Map<Integer, MagicConfig> magicMap = new HashMap<>();

	public static void putConfig(MagicConfig config) {
		magicMap.put(config.getMagicId(), config);
	}

	public static MagicConfig getMagicConfigById(int id) {
		return magicMap.get(id);
	}
}
