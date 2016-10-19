package com.aim.duty.duty_build;

import java.net.InetSocketAddress;

import com.aim.duty.duty_build.cache.config.MagicConfigCache;
import com.aim.duty.duty_build.cache.config.OreConfigCache;
import com.aim.duty.duty_build.cache.config.PlayCountConfigCache;
import com.aim.duty.duty_build.module.build.service.BuildService;
import com.aim.duty.duty_build.navigation.ActionNavigation;
import com.aim.duty.duty_build.navigation.ProtocalNavigation;
import com.aim.duty.duty_build.net.BuildClientHandler;
import com.aim.duty.duty_build.net.MarketClientHandler;
import com.aim.duty.duty_build.net.ServerHandler;
import com.aim.duty.duty_build.ui.UIController;
import com.aim.duty.duty_build_entity.fo.MagicConfig;
import com.aim.duty.duty_build_entity.fo.OreConfig;
import com.aim.duty.duty_build_entity.fo.PlayCountConfig;
import com.aim.game_base.net.SpringContext;
import com.aim.game_base.net.WanClient;
import com.aim.game_base.net.WanClient.WanClientType;
import com.aim.game_base.net.WanServer;
import com.aim.game_base.net.WanServer.WanServerType;

/**
 * Hello world!
 *
 */
public class DutyBuildApp {
	private static final String START_CONFIG_FILE = "ApplicationContext.xml";

	public static void main(String[] args) {

		SpringContext.initSpringCtx(START_CONFIG_FILE);

		CacheInit();
		ActionNavigation.init();
		ProtocalNavigation.init();

		BuildService buildService = SpringContext.getBean("buildService");
		buildService.serverInit();

		WanClient marketServer = SpringContext.getBean("marketServer");
		marketServer.startClient(new MarketClientHandler(), new InetSocketAddress("127.0.0.1", 10001),
				WanClientType.TCP);

		WanServer.startServer(new ServerHandler(), new InetSocketAddress(10002), WanServerType.TCP);

		WanClient buildClient = SpringContext.getBean("buildServer");
		buildClient.startClient(new BuildClientHandler(), new InetSocketAddress("127.0.0.1", 10002), WanClientType.TCP);

		UIController controller = SpringContext.getBean("uiController");
		controller.start();
	}

	private static void CacheInit() {
		String[] names = { "铜矿", "铁矿", "银矿", "金矿" };
		for (int i = 0; i < names.length; i++) {
			OreConfig config = new OreConfig();
			config.setConfigId(i);
			config.setName(names[i]);
			config.setBlood((i+1)*20);
			OreConfigCache.putOreConfig(config);
		}

		Integer[] counts = { 20, 30, 40 };
		for (int i = 0; i < counts.length; i++) {
			PlayCountConfig config = new PlayCountConfig();
			config.setCount(counts[i]);
			PlayCountConfigCache.putConfig(config);
		}
		String[] magicNames = { "火", "电", "冰" };
		for (int i = 0; i < magicNames.length; i++) {
			MagicConfig config = new MagicConfig();
			config.setMagicId(i);
			config.setMaxDuration((i + 1) * 5);
			config.setMaxValue((i + 1) * 5);
			config.setName(magicNames[i]);

			MagicConfigCache.putConfig(config);
		}

	}

}
