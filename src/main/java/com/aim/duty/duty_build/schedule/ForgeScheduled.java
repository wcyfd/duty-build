package com.aim.duty.duty_build.schedule;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.aim.duty.duty_base.entity.bo.Forge;

public abstract class ForgeScheduled {
	private ScheduledExecutorService fightScheduled = new ScheduledThreadPoolExecutor(1);
	private Set<Forge> forgeSet = new HashSet<>();

	public void addForge(Forge forge) {
		forgeSet.add(forge);
	}

	public void start() {
		updateTemperature();
	}

	private void updateTemperature() {
		fightScheduled.scheduleAtFixedRate(new Runnable() {
			public void run() {
				for (Forge forge : forgeSet) {
					updateTemperature(forge);
				}
			}
		}, 0, 1, TimeUnit.SECONDS);

	}

	public abstract void updateTemperature(Forge forge);
}
