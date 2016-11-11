package com.aim.duty.duty_build.module.build.action;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.module.build.service.BuildService;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_CreateRole;
import com.aim.game_base.entity.net.base.Protocal.PT;
import com.aim.game_base.navigation.ActionSupport;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class BuildCreateRoleAction implements ActionSupport {

	private BuildService buildService;

	public void setBuildService(BuildService buildService) {
		this.buildService = buildService;
	}

	@Override
	public void execute(ByteString data, IoSession session) {
		// TODO Auto-generated method stub
		try {
			CS_CreateRole cs = CS_CreateRole.parseFrom(data);
			String account = cs.getAccount();
			String name = cs.getName();
			PT builder = buildService.createRole(account, name,session);
			if (session != null) {
				session.write(builder);
			}

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
