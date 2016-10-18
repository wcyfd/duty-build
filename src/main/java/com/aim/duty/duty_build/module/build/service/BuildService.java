package com.aim.duty.duty_build.module.build.service;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_GetResult;
import com.aim.game_base.entity.net.base.Protocal.SC;

public interface BuildService {
	public void serverInit();

	public SC createRole(String account, String name,IoSession session);

	public SC showWall(Role role);

	public SC showBrickByIndex(Role role, int index);

	public SC addBrickToWall(Role role, int indexAtWall, int propId);

	public SC addMagic(Role role, int propId, int magicId);

	public SC chooseMaterial(Role role, int brickSourceId, int brickSourceNum);

	public SC getResult(Role role, List<CS_GetResult.Brick> bricks);

	public SC showBag(Role role);
}
