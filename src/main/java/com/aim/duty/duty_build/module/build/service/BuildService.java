package com.aim.duty.duty_build.module.build.service;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_GetResult;
import com.aim.game_base.entity.net.base.Protocal.PT;

public interface BuildService {
	public void serverInit();

	public PT createRole(String account, String name,IoSession session);

	public PT showWall(Role role);

	public PT showBrickByIndex(Role role, int index);

	public PT replaceBrick(Role role, int indexAtWall, int propId);

	public PT addMagic(Role role, int propId, int magicId);

	public PT chooseMaterial(Role role, int brickSourceId, int brickSourceNum);

	public PT getResult(Role role, List<CS_GetResult.Brick> bricks);

	public PT showBag(Role role);
	
	public PT getWallValue(Role role);
}
