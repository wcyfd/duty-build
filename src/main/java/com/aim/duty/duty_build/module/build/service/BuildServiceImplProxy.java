package com.aim.duty.duty_build.module.build.service;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.ui.UIController;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_GetResult.Brick;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.entity.net.base.Protocal.SC.Builder;

public class BuildServiceImplProxy implements BuildService {

	private BuildService buildService;

	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}

	@Override
	public void serverInit() {
		// TODO Auto-generated method stub
		buildService.serverInit();
	}

	@Override
	public SC.Builder createRole(String account, String name,IoSession session) {
		// TODO Auto-generated method stub
		SC.Builder builder = buildService.createRole(account, name,session);
		
		return builder;
	}

	@Override
	public SC.Builder showWall(Role role) {
		// TODO Auto-generated method stub
		return buildService.showWall(role);
	}

	@Override
	public SC.Builder showBrickByIndex(Role role, int index) {
		// TODO Auto-generated method stub
		return buildService.showBrickByIndex(role, index);
	}

	@Override
	public SC.Builder addBrickToWall(Role role, int indexAtWall, int propId) {
		// TODO Auto-generated method stub
		return buildService.addBrickToWall(role, indexAtWall, propId);
	}

	@Override
	public SC.Builder addMagic(Role role, int propId, int magicId) {
		// TODO Auto-generated method stub
		return buildService.addMagic(role, propId, magicId);
	}

	@Override
	public SC.Builder chooseMaterial(Role architect, int brickSourceId, int brickSourceNum) {
		// TODO Auto-generated method stub
		return buildService.chooseMaterial(architect, brickSourceId, brickSourceNum);
	}

	@Override
	public Builder getResult(Role role, List<Brick> bricks) {
		// TODO Auto-generated method stub
		return buildService.getResult(role, bricks);
	}
	
	@Override
	public Builder showBag(Role role) {
		// TODO Auto-generated method stub
		return buildService.showBag(role);
	}

}
