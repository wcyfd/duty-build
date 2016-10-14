package com.aim.duty.duty_build.module.build.service;

import com.aim.duty.duty_base.common.ErrorCode;
import com.aim.duty.duty_base.entity.base.AbstractMagicProp;
import com.aim.duty.duty_base.entity.bo.Architect;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Cement;
import com.aim.duty.duty_base.entity.bo.Magic;
import com.aim.duty.duty_base.entity.bo.Wall;
import com.aim.duty.duty_base.service.prop.PropConstant;
import com.aim.duty.duty_base.service.prop.PropService;
import com.aim.duty.duty_build.cache.ConstantCache;
import com.aim.duty.duty_build.cache.RoleCache;
import com.aim.duty.duty_build.entity.protobuf.protocal.Build.SC_CreateArchitect;
import com.aim.duty.duty_build.navigation.ProtocalId;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.net.WanClient;

public class BuildServiceImpl implements BuildService {

	private PropService propService;

	public void setPropService(PropService propService) {
		this.propService = propService;
	}

	private WanClient marketServer;

	public void setMarketServer(WanClient marketServer) {
		this.marketServer = marketServer;
	}
	

	@Override
	public void serverInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SC.Builder createRole(String account,String name) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_CreateArchitect.Builder sc_createArchitectBuilder = SC_CreateArchitect.newBuilder();
		
		Architect architect = this.initArchitect();
		this.initWall(architect);
		RoleCache.putRole(architect);
		
		sc_createArchitectBuilder.setSuccess(ErrorCode.SUCCESS);
		return sc.setProtocal(ProtocalId.CREATE_ROLE).setData(sc_createArchitectBuilder.build().toByteString());
	}

	private Architect initArchitect() {
		Architect architect = new Architect();
		architect.setId(RoleCache.getRoleId());
		return architect;
	}

	private Wall initWall(Architect architect) {
		Wall wall = new Wall();
		wall.setCapacity(30);
		return wall;
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

		if (extractBrick == null)
			return;

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

		if (extractCement == null)
			return;

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
		if (propType == PropConstant.BRICK) {
			magicProp = ConstantCache.wall.getBrickMap().get(indexAtWall);
		} else if (propType == PropConstant.CEMENT) {
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
