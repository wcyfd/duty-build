package com.aim.duty.duty_build.net;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build.ui.UIController;
import com.aim.duty.duty_build_entity.common.BuildErrorCode;
import com.aim.duty.duty_build_entity.navigation.BuildProtocalId;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ChooseMaterial;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_CreateRole;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetWallValue;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ReplaceBrick;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.net.IoHandlerAdapter;
import com.aim.game_base.net.SpringContext;
import com.google.protobuf.ByteString;

public class BuildClientHandler extends IoHandlerAdapter {
	// 当一个客户端连结进入时
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(session.getRemoteAddress() + "打开连接");
	}

	// 当一个 新的session创建时
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println(session.getRemoteAddress() + "创建新的 会话");
	}

	// 当一个客户端关闭时
	@Override
	public void sessionClosed(IoSession session) {
		// if (null != session) {
		// Integer roleInteger = (Integer) session.getAttribute("roleId");
		// Role role = new Role();
		// if (roleInteger != 0) {
		// role = RoleCache.getRoleById(roleInteger);
		// }
		// if (role != null) {
		// System.out.println("玩家正常断开数据处理，用户ID：" + role.getRoleId());
		// session.close(true);
		// SessionCloseHandler.manipulate(role);
		// }
		// }
	}

	// 异常捕获
	@Override
	public void exceptionCaught(IoSession session, Throwable e) throws Exception {

		// if (e.getMessage().equals("远程主机强迫关闭了一个现有的连接。")) {
		// System.out.println(e.getMessage());
		// this.sessionClosed(session);
		// } else {
		// System.err.println("程序业务逻辑出现异常,已处理该账户信息,并强制下线！" + (Integer)
		// session.getAttribute("roleId"));
		// e.printStackTrace();
		// this.sessionClosed(session);
		// }
	}

	// 当客户端发送的消息到达时
	@Override
	public void messageReceived(IoSession session, Object messageObj) throws Exception {
		SC sc = (SC) messageObj;
		int protocal = sc.getProtocal();
		ByteString data = sc.getData();
		UIController uiController = SpringContext.getBean("uiController");
		if (protocal == BuildProtocalId.CREATE_ROLE) {
			SC_CreateRole scData = SC_CreateRole.parseFrom(data);
			if (scData.getSuccess() == BuildErrorCode.SUCCESS)
				uiController.addRole();
		} else if (protocal == BuildProtocalId.CHOOSE_MATERIAL) {
			SC_ChooseMaterial scData = SC_ChooseMaterial.parseFrom(data);
			if (scData.getSuccess() == BuildErrorCode.SUCCESS)
				uiController.chooseMaterial();
		} else if (protocal == BuildProtocalId.GET_RESULT) {
			SC_GetResult scData = SC_GetResult.parseFrom(data);
			if (scData.getSuccess() == BuildErrorCode.SUCCESS) {
				uiController.getResult(scData);
			}
		} else if (protocal == BuildProtocalId.REPLACE_BRICK) {
			SC_ReplaceBrick scData = SC_ReplaceBrick.parseFrom(data);
			if (scData.getSuccess() == BuildErrorCode.SUCCESS) {
				uiController.addBrickIntoWall(scData);
			}
		} else if (protocal == BuildProtocalId.GET_WALL_VALUE) {
			SC_GetWallValue scData = SC_GetWallValue.parseFrom(data);
			uiController.getWallValue(scData);
		}

		// Message message = (Message) messageObj;
		//
		// if (null == message) {
		// System.out.println("ERR_MESSAGE_REC");
		// return;
		// }
		// ActionSupport action = Navigation.getAction(message.getType());
		// Integer roleId = (Integer) session.getAttribute("roleId");
		// if (message.getType() != (short) NavigationModule.HEART_LINK)
		// System.out.println("服务器接收用户：" + roleId + "的消息，请求指令号为:" +
		// message.getType());
		//
		// // 如果协议必须有玩家
		// if (roleId == null &&
		// !NavigationModule.ALLOW_NO_ROLE_PROTOCAL.contains(message.getType()))
		// {
		// System.out.println(message.getType()+" 必须要有玩家");
		//
		// Message msg= new Message();
		// msg.setType(LoginConstant.RELOGION);
		// session.write(msg);
		//
		// return;
		// }
		//
		// try {
		// action.execute(message, session);
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("伪造的协议ID：" + message.getType());
		// // 判断是否属于无效的协议号 TODO 处理方式可能需要变更
		// // if(e.getClass().equals(NumberFakeException.class))
		// // {
		// // session.close(true);
		// // }
		// }
	}

	// 当发送消息成功时调用这个方法，注意这里的措辞，发送成功之后，也就是说发送消息是不能用这个方法的。

	@Override
	public void messageSent(IoSession session, Object message) {
		// Integer roleId = (Integer) session.getAttribute("roleId");
		// Message msg = (Message) message;
		// //
		// // Role role = RoleCache.getRoleBySession(session);
		// // if(role != null)
		// // {
		// // //用户操作添加到记录
		// // role.addPlayerOperation(String.valueOf(msg.getType()));
		// // }
		// // 打印发出的信息
		// // if(msg.getType() != (short)20303)
		// // {
		// PrintToClientMsg.printIOBuffer(roleId, msg);
		// // }
		// //
	}
}
