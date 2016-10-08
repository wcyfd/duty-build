package com.aim.duty.duty_build;

import com.aim.duty.duty_base.entity.bo.Forge;
import com.aim.duty.duty_base.service.Service;
import com.aim.duty.duty_base.service.ServiceImpl;
import com.aim.duty.duty_build.cache.ConstantCache;
import com.aim.duty.duty_build.schedule.ForgeScheduled;
import com.aim.duty.duty_build.service.BuildService;
import com.aim.duty.duty_build.service.BuildServiceImpl;
import com.aim.duty.duty_build.service.BuildServiceImplProxy;

/**
 * Hello world!
 *
 */
public class DutyBuildApp {
	public static void main(String[] args) {
		Service service = new ServiceImpl();
		BuildServiceImpl buildServiceImpl = new BuildServiceImpl();
		buildServiceImpl.setService(service);
		BuildService buildService = new BuildServiceImplProxy(buildServiceImpl);
		ConstantCache.buildService = buildService;
		ConstantCache.forge = new Forge();
		ForgeScheduled timer = new ForgeScheduled() {

			@Override
			public void updateTemperature(Forge forge) {
				// TODO Auto-generated method stub
				ConstantCache.buildService.updateTemperature(forge);
			}
		};
		timer.addForge(ConstantCache.forge);
		timer.start();

	}
}
