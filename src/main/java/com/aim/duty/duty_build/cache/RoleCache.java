package com.aim.duty.duty_build.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.aim.duty.duty_base.entity.bo.Architect;

public class RoleCache {
	public static Map<Integer, Architect> roleMap = new HashMap<>();

	public static void putRole(Architect architect) {
		roleMap.put(architect.getId(), architect);
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
