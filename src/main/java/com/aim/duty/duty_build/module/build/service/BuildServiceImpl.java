package com.aim.duty.duty_build.module.build.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aim.duty.duty_base.common.ErrorCode;
import com.aim.duty.duty_base.entity.base.AbstractProp;
import com.aim.duty.duty_base.service.prop.PropService;
import com.aim.duty.duty_build.cache.RoleCache;
import com.aim.duty.duty_build_entity.bo.Brick;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.bo.Wall;
import com.aim.duty.duty_build_entity.navigation.ProtocalId;
import com.aim.duty.duty_build_entity.po.Magic;
import com.aim.duty.duty_build_entity.po.Room;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddBrickToWall;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddMagic;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ChooseMaterial;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_CreateRole;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBag;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowBrick;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ShowWall;
import com.aim.game_base.entity.net.base.Protocal.SC;
import com.aim.game_base.net.WanClient;

public class BuildServiceImpl implements BuildService {

	private PropService propService;

	public void setPropService(PropService propService) {
		this.propService = propService;
	}

	private WanClient marketServer;

	public void setMarketServer(WanClient marketServer) {
		this.marketServer = marketServer;
	}

	@Override
	public void serverInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public SC.Builder createRole(String account, String name) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_CreateRole.Builder sc_createArchitectBuilder = SC_CreateRole.newBuilder();

		Role architect = this.initArchitect();
		this.initWall(architect);
		this.initRoom(architect);
		RoleCache.putRole(architect);

		sc_createArchitectBuilder.setSuccess(ErrorCode.SUCCESS);
		return sc.setProtocal(ProtocalId.CREATE_ROLE).setData(sc_createArchitectBuilder.build().toByteString());
	}

	private Role initArchitect() {
		Role architect = new Role();
		architect.setId(RoleCache.getRoleId());
		return architect;
	}

	private Wall initWall(Role architect) {
		Wall wall = new Wall();
		wall.setCapacity(30);
		return wall;
	}

	private Room initRoom(Role architect) {
		Room room = new Room();
		return room;
	}

	@Override
	public SC.Builder showWall(Role role) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ShowWall.Builder scShowBuilder = SC_ShowWall.newBuilder();

		Wall wall = role.getWall();
		scShowBuilder.setBlood(wall.getBlood()).setCapacity(wall.getCapacity());

		Map<Integer, SC_ShowWall.Brick> bricks = new HashMap<>();
		scShowBuilder.putAllBricks(bricks);

		for (Map.Entry<Integer, Brick> entrySet : wall.getBrickMap().entrySet()) {

			SC_ShowWall.Brick.Builder scBrickBuilder = SC_ShowWall.Brick.newBuilder();
			Map<Integer, SC_ShowWall.Magic> magics = new HashMap<>();
			scBrickBuilder.putAllMagics(magics).setMineId(entrySet.getValue().getMineId())
					.setId(entrySet.getValue().getId());

			for (Magic m : entrySet.getValue().getMagicMap().values()) {
				magics.put(
						m.getMagicId(),
						SC_ShowWall.Magic.newBuilder().setDuration(m.getDuration()).setValue(m.getValue())
								.setMagicId(m.getMagicId()).build());
			}

			bricks.put(entrySet.getKey(), scBrickBuilder.build());
		}

		return sc.setProtocal(ProtocalId.SHOW_WALL).setData(scShowBuilder.build().toByteString());

	}

	@Override
	public SC.Builder showBrickByIndex(Role role, int propId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ShowBrick.Builder scShowBrick = SC_ShowBrick.newBuilder();
		Map<Integer, AbstractProp> propMap = role.getPropMap();
		Brick brick = (Brick) propMap.get(propId);
		scShowBrick.setMineId(brick.getMineId());
		Map<Integer, SC_ShowBrick.Magic> scMagics = new HashMap<>();
		scShowBrick.putAllMagics(scMagics);
		for (Magic m : brick.getMagicMap().values()) {
			scMagics.put(
					m.getMagicId(),
					SC_ShowBrick.Magic.newBuilder().setDuration(m.getDuration()).setMagicId(m.getMagicId())
							.setValue(m.getValue()).build());
		}

		return sc.setProtocal(ProtocalId.SHOW_BRICK).setData(scShowBrick.build().toByteString());
	}

	@Override
	public SC.Builder addBrickToWall(Role role, int indexAtWall, int propId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_AddBrickToWall.Builder scAddBrickToWallBuilder = SC_AddBrickToWall.newBuilder();
		Map<Integer, AbstractProp> propMap = role.getPropMap();
		Brick prop = (Brick) propMap.get(propId);

		Wall wall = role.getWall();
		Map<Integer, Brick> wallBrickMap = wall.getBrickMap();
		Brick b = wallBrickMap.get(indexAtWall);

		if (b != null) {
			wallBrickMap.remove(indexAtWall);
			b.setPosition(0);
		}

		wallBrickMap.put(indexAtWall, prop);
		prop.setPosition(1);
		return sc.setProtocal(ProtocalId.ADD_BRICK_TO_WALL).setData(
				scAddBrickToWallBuilder.setSuccess(ErrorCode.SUCCESS).build().toByteString());
	}

	@Override
	public SC.Builder addMagic(Role role, int propId, int magicId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_AddMagic.Builder scAddMagicBuilder = SC_AddMagic.newBuilder();
		Brick prop = (Brick) role.getPropMap().get(propId);

		Magic magic = new Magic();
		magic.setMagicId(magicId);
		magic.setValue(20);
		magic.setDuration(30);

		prop.getMagicMap().put(magic.getMagicId(), magic);
		return sc.setProtocal(ProtocalId.ADD_MAGIC).setData(
				scAddMagicBuilder.setSuccess(ErrorCode.SUCCESS).build().toByteString());

	}

	@Override
	public SC.Builder chooseMaterial(Role architect, int brickSourceId, int brickSourceNum) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ChooseMaterial.Builder sc_chooseMaterial = SC_ChooseMaterial.newBuilder();

		Room room = architect.getRoom();
		room.setBrickNum(brickSourceNum);
		room.setBrickSourceId(brickSourceId);
		return sc.setProtocal(ProtocalId.CHOOSE_MATERIAL).setData(
				sc_chooseMaterial.setSuccess(ErrorCode.SUCCESS).build().toByteString());
	}

	@Override
	public SC.Builder getResult(Role role, List<CS_GetResult.Brick> bricksList) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_GetResult.Builder scGetResultBuilder = SC_GetResult.newBuilder();

		for (CS_GetResult.Brick csBrick : bricksList) {
			Brick b = new Brick();
			b.setId(role.getPropUniqueId());
			b.setMineId(role.getRoom().getBrickSourceId());

			SC_GetResult.Brick.Builder scBrickBuilder = SC_GetResult.Brick.newBuilder();
			scBrickBuilder.setId(b.getId());
			scBrickBuilder.setMineId(b.getMineId());

			Map<Integer, SC_GetResult.Brick.Magic> scMagics = new HashMap<>();
			scBrickBuilder.putAllMagics(scMagics);

			for (CS_GetResult.Brick.Magic magic : csBrick.getMagicsMap().values()) {
				Magic m = new Magic();
				m.setDuration(magic.getDuration());
				m.setMagicId(magic.getMagicId());
				m.setValue(magic.getValue());

				b.getMagicMap().put(m.getMagicId(), m);
				scMagics.put(m.getMagicId(), SC_GetResult.Brick.Magic.newBuilder().setDuration(m.getDuration())
						.setMagicId(m.getMagicId()).setValue(m.getValue()).build());
			}

		}

		return sc.setProtocal(ProtocalId.GET_RESULT).setData(
				scGetResultBuilder.setSuccess(ErrorCode.SUCCESS).build().toByteString());

	}

	@Override
	public SC.Builder showBag(Role role) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ShowBag.Builder scShowBagBuilder = SC_ShowBag.newBuilder();

		Map<Integer, SC_ShowBag.Brick> bricks = new HashMap<>();
		scShowBagBuilder.putAllBricks(bricks);

		Map<Integer, AbstractProp> propMap = role.getPropMap();
		for (AbstractProp p : propMap.values()) {
			Brick b = (Brick) p;

			SC_ShowBag.Brick.Builder scBrick = SC_ShowBag.Brick.newBuilder();
			scBrick.setMineId(b.getMineId()).setId(b.getId());
			Map<Integer, SC_ShowBag.Magic> magics = new HashMap<>();
			scBrick.putAllMagics(magics);

			for (Magic m : b.getMagicMap().values()) {
				magics.put(
						m.getMagicId(),
						SC_ShowBag.Magic.newBuilder().setDuration(m.getDuration()).setValue(m.getValue())
								.setMagicId(m.getMagicId()).build());
			}

			bricks.put(b.getId(), scBrick.build());
		}
		return sc.setProtocal(ProtocalId.SHOW_BAG).setData(scShowBagBuilder.build().toByteString());
	}

}
