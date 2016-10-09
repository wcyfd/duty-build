package com.aim.duty.duty_build.service;

import com.aim.duty.duty_base.entity.base.AbstractMagicProp;
import com.aim.duty.duty_base.entity.base.Constant;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Magic;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_build.cache.ConstantCache;

public class BuildServiceImpl implements BuildService {

	@Override
	public void initWall() {
		// TODO Auto-generated method stub
		Wall wall = new Wall();
		int brickCount = 30;
		wall.setCapacity(brickCount);

		ConstantCache.wall = wall;
	}

	@Override
	public void showWall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showOnePositionByIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceBrick(int indexAtWall, int indexAtCache) {
		// TODO Auto-generated method stub
		Wall wall = ConstantCache.wall;
		Brick brickInWall = wall.getBrickMap().remove(indexAtWall);
		Brick brickAtCache = (Brick) ConstantCache.warehouse.remove(indexAtCache);
		ConstantCache.warehouse.add(brickInWall);
		wall.getBrickMap().put(indexAtWall, brickAtCache);
	}

	@Override
	public void replaceCement(int indexAtWall, int indexAtCache) {
		// TODO Auto-generated method stub
		Wall wall = ConstantCache.wall;
		Cement cementInWall = wall.getCementMap().remove(indexAtWall);
		Cement cementAtCache = (Cement) ConstantCache.warehouse.remove(indexAtCache);
		ConstantCache.warehouse.add(cementInWall);
		wall.getCementMap().put(indexAtWall, cementAtCache);
	}

	@Override
	public void addMagic(int indexAtWall, int magicId, byte propType) {
		// TODO Auto-generated method stub
		AbstractMagicProp magicProp = null;
		if (propType == Constant.BRICK) {
			magicProp = ConstantCache.wall.getBrickMap().get(indexAtWall);
		} else if (propType == Constant.CEMENT) {
			magicProp = ConstantCache.wall.getCementMap().get(indexAtWall);
		}
		int size = magicProp.getMagicDetailMap().size();
		if (size > 0) {
			System.out.println("已经有魔法");
			return;
		}

		Magic magic = new Magic();
		magic.setDuration(1);
		magic.setMagicId(magicId);
		magic.setValue(1);

		magicProp.addMagic(magic);
	}

}
