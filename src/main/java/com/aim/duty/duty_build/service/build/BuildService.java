package com.aim.duty.duty_build.service.build;

public interface BuildService {

	public void initWall();

	public void showWall();

	public void showOnePositionByIndex(int index);

	public void replaceBrick(int indexAtWall, int indexAtCache);

	public void replaceCement(int indexAtWall, int indexAtCache);

	public void addMagic(int indexAtWall, int magicId, byte propType);

	public void chooseMaterial(int brickSourceId, int brickSourceNum, int cementSourceId, int cementSourceNum);

	public void getResult(int brickSourceNum, int cementSourceNum);
}
