package com.aim.duty.duty_build.module.build.service;


public class BuildServiceImplProxy implements BuildService{

	private BuildService buildService;
	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}
	

	@Override
	public void initWall() {
		// TODO Auto-generated method stub
		buildService.initWall();
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
