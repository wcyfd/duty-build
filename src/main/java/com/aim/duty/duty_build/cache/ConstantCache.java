package com.aim.duty.duty_build.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Forge;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_build.service.BuildService;

public class ConstantCache {
	public static int totalCount = 0;
	public static int mineId = 0;
	public static int currentCount = 0;
	public static int hitCount = 0;

	public static List<Brick> completeBricks = new ArrayList<>();
	public static Cement cement = null;

	public static List<Brick> rawBricks = new ArrayList<>();
	public static int cementCount = 0;

	public static Map<Integer,Brick> wallBricks = new HashMap<>();
	
	public static List<Wall> walls= new ArrayList<>();
	
	public static Forge forge = null;
	public static BuildService buildService = null;

}
