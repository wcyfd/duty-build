package com.aim.duty.duty_build.module.build.service;

import com.aim.duty.duty_build.ui.UIController;
import com.aim.game_base.entity.net.base.Protocal.SC;

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
	public void showWall() {
		// TODO Auto-generated method stub
		buildService.showWall();
	}

	@Override
	public void showOnePositionByIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceBrick(int indexAtWall, int indexAtCache) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceCement(int indexAtWall, int indexAtCache) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMagic(int indexAtWall, int magicId, byte propType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chooseMaterial(int brickSourceId, int brickSourceNum, int cementSourceId, int cementSourceNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getResult(int brickSourceNum, int cementSourceNum) {
		// TODO Auto-generated method stub

	}

}
