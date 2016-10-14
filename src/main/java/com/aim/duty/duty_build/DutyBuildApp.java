package com.aim.duty.duty_build;

import java.net.InetSocketAddress;

import com.aim.duty.duty_base.cache.ConstantCache;
import com.aim.duty.duty_base.entity.bo.Brick;
import com.aim.duty.duty_base.entity.bo.Magic;
import com.aim.duty.duty_base.entity.protobuf.protocal.market.Market.CS_SaleCommodity;
import com.aim.duty.duty_base.service.Constant;
import com.aim.duty.duty_base.service.prop.PropConstant;
import com.aim.duty.duty_base.util.Util;
import com.aim.duty.duty_build.module.build.service.BuildService;
import com.aim.duty.duty_build.net.ClientHandler;
import com.aim.game_base.entity.net.base.Protocal.CS;
import com.aim.game_base.net.SpringContext;
import com.aim.game_base.net.Utils;
import com.aim.game_base.net.WanClient;
import com.aim.game_base.net.WanClient.WanClientType;
import com.google.protobuf.ByteString;

/**
 * Hello world!
 *
 */
public class DutyBuildApp {
	private static final String START_CONFIG_FILE = "ApplicationContext.xml";

	public static void main(String[] args) {
		SpringContext.initSpringCtx(START_CONFIG_FILE);

		ConstantCache.init();
		BuildService buildService = SpringContext.getBean("buildService");
		buildService.serverInit();
		

		WanClient marketServer = SpringContext.getBean("marketServer");
		marketServer.startClient(new ClientHandler(), new InetSocketAddress("10.0.51.49", 10001), WanClientType.TCP);

//		Brick brick = new Brick();
//
//		Magic magic = new Magic();
//		magic.setDuration(29);
//		magic.setMagicId(2001);
//		magic.setValue(5302);
//		brick.addMagic(magic);
//
//		Magic magic2 = new Magic();
//		magic2.setDuration(33);
//		magic2.setMagicId(4001);
//		magic2.setValue(6543);
//		brick.addMagic(magic2);
//
//		brick.setMineId(444);
//		brick.setNum(50);
//
//		while (true) {
//			Util.threadSleep(Utils.getRandomNum(400, 1000));
//			ByteString str = CS_SaleCommodity.newBuilder().setAbstractProp(brick.serialize()).setNum(31)
//					.setPropType(PropConstant.BRICK).setSinglePrice(18).build().toByteString();
//			CS.Builder builder = CS.newBuilder().setData(str).setProtocal(101);
//			marketServer.send(builder);
//		}

	}

}
