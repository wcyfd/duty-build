package com.aim.duty.duty_build.service.build;

import com.aim.duty.duty_base.entity.base.AbstractMagicProp;
import com.aim.duty.duty_base.entity.base.Constant;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Magic;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_base.service.PropService;
import com.aim.duty.duty_build.cache.ConstantCache;

public class BuildServiceImpl implements BuildService {

	private PropService propService;

	public void SetPropService(PropService propService) {
		this.propService = propService;
	}

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

		Brick brickAtCache = (Brick) ConstantCache.warehouse.get(indexAtCache);
		Brick extractBrick = (Brick) propService.extract(brickAtCache, 1);

		if (extractBrick == null) return;

		Brick brickInWall = wall.getBrickMap().remove(indexAtWall);
		
		if (brickAtCache.getNum() == 0) 
			ConstantCache.warehouse.remove(indexAtCache);
		
		ConstantCache.warehouse.add(brickInWall);
		wall.getBrickMap().put(indexAtWall, extractBrick);
	}
	
	@Override
	public void replaceCement(int indexAtWall, int indexAtCache) {
		// TODO Auto-generated method stub
		Wall wall = ConstantCache.wall;

		Cement cementAtCache = (Cement) ConstantCache.warehouse.get(indexAtCache);
		Cement extractCement = (Cement) propService.extract(cementAtCache, 1);

		if (extractCement == null) return;

		Cement brickInWall = wall.getCementMap().remove(indexAtWall);
		
		if (cementAtCache.getNum() == 0) 
			ConstantCache.warehouse.remove(indexAtCache);
		
		ConstantCache.warehouse.add(brickInWall);
		wall.getCementMap().put(indexAtWall, extractCement);
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

	@Override
	public void chooseMaterial(int brickSourceId, int brickSourceNum, int cementSourceId, int cementSourceNum) {
		// TODO Auto-generated method stub
		ConstantCache.brickSourceId = brickSourceId;
		ConstantCache.brickSourceNum = brickSourceNum;
		ConstantCache.cementSourceId = cementSourceId;
		ConstantCache.cementSourceNum = cementSourceNum;
	}

	@Override
	public void getResult(int brickSourceNum, int cementSourceNum) {
		// TODO Auto-generated method stub
		Brick brick = new Brick();
		brick.setMineId(ConstantCache.brickSourceId);
		brick.setNum(brickSourceNum);

		Cement cement = new Cement();
		cement.setMineId(ConstantCache.cementSourceId);
		cement.setNum(cementSourceNum);

		ConstantCache.warehouse.add(cement);
		ConstantCache.warehouse.add(brick);

	}

}
