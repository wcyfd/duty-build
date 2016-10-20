package com.aim.duty.duty_build.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_build_entity.bo.Role;

public class RoleCache {
	public static Map<Integer, Role> roleMap = new HashMap<>();

	public static void putRole(Role role) {
		roleMap.put(role.getId(), role);
	}

	public static Role getRoleBySession(IoSession session) {
		Integer id = (Integer) session.getAttribute("roleId");
		Role role = roleMap.get(id);
		return role;
	}
	
	public static Role getRoleById(int id){
		return roleMap.get(id);
	}

	private static Lock lock = new ReentrantLock();
	private static int id = 0;

	public static int getRoleId() {
		lock.lock();
		id++;
		lock.unlock();
		return id;
	}
}
