package com.aim.duty.duty_build.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class SessionCache {
	private static Map<Integer, IoSession> sessionMap = new HashMap<>();

	public static void addSession(int roleId, IoSession session) {
		sessionMap.put(roleId, session);
	}
	
	public static IoSession getSessionByRoleId(int roleId	){
		return sessionMap.get(roleId);
	}
}
