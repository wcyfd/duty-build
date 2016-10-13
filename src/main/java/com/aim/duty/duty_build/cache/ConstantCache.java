package com.aim.duty.duty_build.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aim.duty.duty_base.entity.base.AbstractProp;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Furnace;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_build.module.build.service.BuildService;

public class ConstantCache {
	public static int totalCount = 0;
	public static int mineId = 0;
	public static int currentCount = 0;
	public static int hitCount = 0;

	public static List<Brick> completeBricks = new ArrayList<>();
	public static Cement cement = null;

	public static List<Brick> rawBricks = new ArrayList<>();
	public static int cementCount = 0;

	public static Map<Integer, Brick> wallBricks = new HashMap<>();

	public static List<Wall> walls = new ArrayList<>();

	/***********************************************/
	public static BuildService buildService = null;
	public static Wall wall = null;
	public static List<AbstractProp> warehouse = new ArrayList<>();
	public static int brickSourceId = -1;
	public static int brickSourceNum = 0;
	public static int cementSourceId = -1;
	public static int cementSourceNum = 0;

}
