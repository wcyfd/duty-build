package com.aim.duty.duty_build.cache.config;

import java.util.List;
import java.util.Vector;

import com.aim.duty.duty_build_entity.fo.PlayCountConfig;

public class PlayCountConfigCache {

	private static Vector<Integer> list = new Vector<>();

	public static void putConfig(PlayCountConfig config) {
		list.add(config.getCount());
	}

	public static List<Integer> getCounts() {
		return list;
	}
}
