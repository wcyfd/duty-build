package com.aim.duty.duty_build.module.build.service;

import com.aim.game_base.entity.net.base.Protocal.SC;

public interface BuildService {
	public void serverInit();

	public SC.Builder createRole(String account,String name);

	public void showWall();

	public void showOnePositionByIndex(int index);

	public void replaceBrick(int indexAtWall, int indexAtCache);

	public void replaceCement(int indexAtWall, int indexAtCache);

	public void addMagic(int indexAtWall, int magicId, byte propType);

	public void chooseMaterial(int brickSourceId, int brickSourceNum, int cementSourceId, int cementSourceNum);

	public void getResult(int brickSourceNum, int cementSourceNum);
}
