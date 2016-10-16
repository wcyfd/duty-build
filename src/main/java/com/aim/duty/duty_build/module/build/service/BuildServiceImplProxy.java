package com.aim.duty.duty_build.module.build.service;

import com.aim.duty.duty_build.ui.UIController;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.entity.net.base.Protocal.SC.Builder;

public class BuildServiceImplProxy implements BuildService {

	private BuildService buildService;

	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}

	private UIController uiController;

	public void setUiController(UIController uiController) {
		this.uiController = uiController;
	}

	@Override
	public void serverInit() {
		// TODO Auto-generated method stub
		buildService.serverInit();
		uiController.start();
	}

	@Override
	public SC.Builder createRole(String account, String name) {
		// TODO Auto-generated method stub
		SC.Builder builder = buildService.createRole(account, name);
		uiController.addRole(1, name);
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
	public SC.Builder getResult(Role architect, int brickCount) {
		// TODO Auto-generated method stub
		return buildService.getResult(architect, brickCount);
	}
	
	@Override
	public Builder showBag(Role role) {
		// TODO Auto-generated method stub
		return buildService.showBag(role);
	}

}
