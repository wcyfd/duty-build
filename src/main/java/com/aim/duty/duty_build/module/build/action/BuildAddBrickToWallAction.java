package com.aim.duty.duty_build.module.build.action;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.cache.RoleCache;
import com.aim.duty.duty_build.module.build.service.BuildService;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_AddBrickToWall;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_ShowWall;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.navigation.ActionSupport;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class BuildAddBrickToWallAction implements ActionSupport {

	private BuildService buildService;

	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}

	@Override
	public void execute(ByteString data, IoSession session) {
		// TODO Auto-generated method stub
		try {
			CS_AddBrickToWall cs = CS_AddBrickToWall.parseFrom(data);
			Role role = RoleCache.getRoleBySession(session);
			SC sc = buildService.addBrickToWall(role, cs.getIndexAtWall(), cs.getPropId());
			if (session != null) {
				session.write(sc);
			}

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
