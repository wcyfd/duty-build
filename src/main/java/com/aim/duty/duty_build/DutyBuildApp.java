package com.aim.duty.duty_build;

import com.aim.duty.duty_build.cache.ConstantCache;
import com.aim.duty.duty_build.service.BuildService;
import com.aim.duty.duty_build.service.BuildServiceImpl;
import com.aim.duty.duty_build.service.BuildServiceImplProxy;

/**
 * Hello world!
 *
 */
public class DutyBuildApp {
	public static void main(String[] args) {
		BuildService buildServiceImpl = new BuildServiceImpl();
		BuildService buildService = new BuildServiceImplProxy(buildServiceImpl);
		ConstantCache.buildService = buildService;
		
		buildService.initWall();

	}
}
