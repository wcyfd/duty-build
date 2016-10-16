package com.aim.duty.duty_build.module.build.service;

import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.game_base.entity.net.base.Protocal.SC;

public interface BuildService {
	public void serverInit();

	public SC.Builder createRole(String account, String name);

	public SC.Builder showWall(Role role);

	public SC.Builder showBrickByIndex(Role role,int index);

	public SC.Builder addBrickToWall(Role role,int indexAtWall, int propId);

	public SC.Builder addMagic(Role role,int propId,int magicId);

	public SC.Builder chooseMaterial(Role role, int brickSourceId, int brickSourceNum);

	public SC.Builder getResult(Role role, int brickCount);
	
	public SC.Builder showBag(Role role);
}
