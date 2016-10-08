package com.aim.duty.duty_build.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aim.duty.duty_base.cache.config.MineConfigCache;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Forge;
import com.aim.duty.duty_base.entity.bo.Magic;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_base.entity.config.MineConfig;
import com.aim.duty.duty_base.service.Service;
import com.aim.duty.duty_base.util.Util;
import com.aim.duty.duty_build.cache.ConstantCache;

public class BuildServiceImpl implements BuildService {

	private Service service;

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public void addFuel(Forge forge, int propId) {
		// TODO Auto-generated method stub
		service.addFuel(forge, propId, 1);
	}

	@Override
	public void updateTemperature(Forge forge) {
		// TODO Auto-generated method stub
		service.updateTemperature(forge);
	}

	@Override
	public void ready(int propId, int num) {
		// TODO Auto-generated method stub
		MineConfig config = MineConfigCache.map.get(propId);

		if (config == null)
			return;
		ConstantCache.totalCount = num;
		ConstantCache.mineId = propId;
		ConstantCache.currentCount = 0;
		ConstantCache.hitCount = 0;
		ConstantCache.cement = null;
		ConstantCache.cementCount = 0;
	}

	@Override
	public void hitBrick() {
		// TODO Auto-generated method stub
		if (ConstantCache.hitCount > ConstantCache.totalCount) {
			return;
		}

		ConstantCache.hitCount += 1;
		int value = Util.rand(1);
		ConstantCache.currentCount += value;
	}

	@Override
	public void createBrick() {
		// TODO Auto-generated method stub
		Brick brick = new Brick();
		brick.setMineId(ConstantCache.mineId);
		ConstantCache.currentCount--;
		ConstantCache.rawBricks.add(brick);
	}

	@Override
	public void addBrickMagic(int magicId) {
		// TODO Auto-generated method stub
		List<Brick> rawsBricks = ConstantCache.rawBricks;
		int magicValue = Util.rand(50);
		for (Brick b : rawsBricks) {
			Magic magic = new Magic();
			magic.setMagicId(magicId);
			magic.setValue(magicValue);
			b.addMagic(magic);
			ConstantCache.completeBricks.add(b);
		}
		rawsBricks.clear();
	}

	@Override
	public void createCement(int mineId, int num) {
		// TODO Auto-generated method stub
		Cement cement = new Cement();
		cement.setMineId(mineId);

		ConstantCache.cementCount = num;
		ConstantCache.cement = cement;
	}

	@Override
	public void addCementMagic(int magicId) {
		// TODO Auto-generated method stub
		int value = Util.rand(50);
		Magic magic = new Magic();
		magic.setDuration(0);
		magic.setMagicId(magicId);
		magic.setValue(value);
		ConstantCache.cement.addMagic(magic);
	}

	@Override
	public void addBrickIntoWall(int index) {
		// TODO Auto-generated method stub
		Map<Integer, Brick> wallBricks = ConstantCache.wallBricks;
		Brick b = ConstantCache.completeBricks.get(index);
		wallBricks.put(index, b);
	}

	@Override
	public void removeBrickFromWall(int index) {
		// TODO Auto-generated method stub
		Map<Integer, Brick> wallBricks = ConstantCache.wallBricks;
		wallBricks.remove(index);
	}

	@Override
	public void createWall() {
		// TODO Auto-generated method stub
		if (ConstantCache.wallBricks.size() > 20) {
			return;
		}

		Wall wall = new Wall();
		ConstantCache.completeBricks.removeAll(ConstantCache.wallBricks.values());
		int cementMindId = ConstantCache.cement.getMineId();

		for (Brick b : ConstantCache.wallBricks.values()) {
			int mineId = b.getMineId();
			MineConfig config = MineConfigCache.map.get(mineId);
			int blood = config.brickBlood;
			wall.setBlood(wall.getBlood() + blood);

			for (Set<Magic> magics : b.getMagicDetailMap().values()) {
				for(Magic magic:magics){
					wall.addMagic(magic);			
				}
			}
		}
		MineConfig cementMineConfig = MineConfigCache.map.get(cementMindId);
		wall.setBlood(wall.getBlood() + cementMineConfig.cementBlood * ConstantCache.wallBricks.size());

		ConstantCache.walls.add(wall);
		ConstantCache.cement = null;
		ConstantCache.cementCount = 0;
		ConstantCache.currentCount = 0;
		ConstantCache.hitCount = 0;
		ConstantCache.mineId = 0;
		ConstantCache.totalCount = 0;
	}

	@Override
	public void destroyBrick(int propId) {
		// TODO Auto-generated method stub
		
	}

}
