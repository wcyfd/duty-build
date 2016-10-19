package com.aim.duty.duty_build.module.build.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.aim.duty.duty_base.entity.base.AbstractProp;
import com.aim.duty.duty_base.service.prop.PropService;
import com.aim.duty.duty_build.cache.RoleCache;
import com.aim.duty.duty_build.cache.SessionCache;
import com.aim.duty.duty_build.cache.config.OreConfigCache;
import com.aim.duty.duty_build_entity.bo.Brick;
import com.aim.duty.duty_build_entity.bo.Role;
import com.aim.duty.duty_build_entity.bo.Wall;
import com.aim.duty.duty_build_entity.common.BuildErrorCode;
import com.aim.duty.duty_build_entity.fo.OreConfig;
import com.aim.duty.duty_build_entity.navigation.BuildProtocalId;
import com.aim.duty.duty_build_entity.po.Magic;
import com.aim.duty.duty_build_entity.po.Room;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.CS_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_AddMagic;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ChooseMaterial;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_CreateRole;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetWallValue;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ReplaceBrick;
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
	public SC createRole(String account, String name, IoSession session) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_CreateRole.Builder sc_createArchitectBuilder = SC_CreateRole.newBuilder();

		Role role = this.initRole(account, name);
		this.initWall(role);
		this.initRoom(role);
		RoleCache.putRole(role);
		session.setAttribute("roleId", role.getId());
		SessionCache.addSession(role.getId(), session);

		sc_createArchitectBuilder.setSuccess(BuildErrorCode.SUCCESS);
		return sc.setProtocal(BuildProtocalId.CREATE_ROLE).setData(sc_createArchitectBuilder.build().toByteString()).build();
	}

	private Role initRole(String account, String name) {
		Role role = new Role();
		role.setId(RoleCache.getRoleId());
		role.setAccount(account);
		role.setName(name);
		return role;
	}

	private Wall initWall(Role role) {
		Wall wall = new Wall();
		wall.setCapacity(30);
		role.setWall(wall);
		return wall;
	}

	private Room initRoom(Role role) {
		Room room = new Room();
		role.setRoom(room);
		return room;
	}

	@Override
	public SC showWall(Role role) {
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

			for (Magic m : entrySet.getValue().getMagicMap().values()) {
				magics.put(
						m.getMagicId(),
						SC_ShowWall.Magic.newBuilder().setDuration(m.getDuration()).setValue(m.getValue())
								.setMagicId(m.getMagicId()).build());
			}
			scBrickBuilder.putAllMagics(magics).setMineId(entrySet.getValue().getMineId())
					.setId(entrySet.getValue().getId());

			bricks.put(entrySet.getKey(), scBrickBuilder.build());
		}

		return sc.setProtocal(BuildProtocalId.SHOW_WALL).setData(scShowBuilder.build().toByteString()).build();

	}

	@Override
	public SC showBrickByIndex(Role role, int propId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ShowBrick.Builder scShowBrick = SC_ShowBrick.newBuilder();
		Map<Integer, AbstractProp> propMap = role.getPropMap();
		Brick brick = (Brick) propMap.get(propId);
		scShowBrick.setMineId(brick.getMineId());
		Map<Integer, SC_ShowBrick.Magic> scMagics = new HashMap<>();
		for (Magic m : brick.getMagicMap().values()) {
			scMagics.put(
					m.getMagicId(),
					SC_ShowBrick.Magic.newBuilder().setDuration(m.getDuration()).setMagicId(m.getMagicId())
							.setValue(m.getValue()).build());
		}
		scShowBrick.putAllMagics(scMagics);

		return sc.setProtocal(BuildProtocalId.SHOW_BRICK).setData(scShowBrick.build().toByteString()).build();
	}

	@Override
	public SC replaceBrick(Role role, int indexAtWall, int propId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ReplaceBrick.Builder scReplaceBrickBuilder = SC_ReplaceBrick.newBuilder();
		Map<Integer, AbstractProp> propMap = role.getPropMap();

		if (indexAtWall == -1) {
			return sc.setProtocal(BuildProtocalId.REPLACE_BRICK)
					.setData(scReplaceBrickBuilder.setSuccess(BuildErrorCode.BUILD_REPALCE_NOTHING).build().toByteString())
					.build();
		}

		Wall wall = role.getWall();
		Map<Integer, Brick> wallBrickMap = wall.getBrickMap();
		Brick wallBrick = wallBrickMap.get(indexAtWall);
		Brick bagBrick = (Brick) propMap.get(propId);

		if (wallBrick == null && bagBrick == null) {
			return sc.setProtocal(BuildProtocalId.REPLACE_BRICK)
					.setData(scReplaceBrickBuilder.setSuccess(BuildErrorCode.BUILD_REPALCE_NOTHING).build().toByteString())
					.build();
		}

		if (wallBrick != null && bagBrick != null) {
			wallBrick.setPosition(0);
			wallBrickMap.remove(indexAtWall);
			bagBrick.setPosition(1);
			wallBrickMap.put(indexAtWall, bagBrick);
		} else if (wallBrick != null) {
			wallBrickMap.remove(indexAtWall);
			wallBrick.setPosition(0);
		} else if (bagBrick != null) {
			wallBrickMap.put(indexAtWall, bagBrick);
			bagBrick.setPosition(1);
		}
		
		this.refreshWall(role);

		return sc.setProtocal(BuildProtocalId.REPLACE_BRICK)
				.setData(scReplaceBrickBuilder.setSuccess(BuildErrorCode.SUCCESS).build().toByteString()).build();
	}

	@Override
	public SC addMagic(Role role, int propId, int magicId) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_AddMagic.Builder scAddMagicBuilder = SC_AddMagic.newBuilder();
		Brick prop = (Brick) role.getPropMap().get(propId);

		Magic magic = new Magic();
		magic.setMagicId(magicId);
		magic.setValue(20);
		magic.setDuration(30);

		prop.getMagicMap().put(magic.getMagicId(), magic);
		return sc.setProtocal(BuildProtocalId.ADD_MAGIC)
				.setData(scAddMagicBuilder.setSuccess(BuildErrorCode.SUCCESS).build().toByteString()).build();

	}

	@Override
	public SC chooseMaterial(Role role, int brickSourceId, int brickSourceNum) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ChooseMaterial.Builder sc_chooseMaterial = SC_ChooseMaterial.newBuilder();

		Room room = role.getRoom();
		room.setBrickNum(brickSourceNum);
		room.setBrickSourceId(brickSourceId);
		return sc.setProtocal(BuildProtocalId.CHOOSE_MATERIAL)
				.setData(sc_chooseMaterial.setSuccess(BuildErrorCode.SUCCESS).build().toByteString()).build();
	}

	@Override
	public SC getResult(Role role, List<CS_GetResult.Brick> bricksList) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_GetResult.Builder scGetResultBuilder = SC_GetResult.newBuilder();

		for (CS_GetResult.Brick csBrick : bricksList) {
			Brick b = new Brick();
			b.setId(role.getPropUniqueId());
			b.setMineId(role.getRoom().getBrickSourceId());

			SC_GetResult.Brick.Builder scBrickBuilder = SC_GetResult.Brick.newBuilder();
			scBrickBuilder.setId(b.getId()).setMineId(b.getMineId());

			Map<Integer, SC_GetResult.Brick.Magic> scMagics = new HashMap<>();
			for (CS_GetResult.Brick.Magic magic : csBrick.getMagicsMap().values()) {
				Magic m = new Magic();
				m.setDuration(magic.getDuration());
				m.setMagicId(magic.getMagicId());
				m.setValue(magic.getValue());

				b.getMagicMap().put(m.getMagicId(), m);
				scMagics.put(m.getMagicId(), SC_GetResult.Brick.Magic.newBuilder().setDuration(m.getDuration())
						.setMagicId(m.getMagicId()).setValue(m.getValue()).build());
			}

			scBrickBuilder.putAllMagics(scMagics);

			scGetResultBuilder.addBricks(scBrickBuilder);
			role.getPropMap().put(b.getId(), b);
		}

		return sc.setProtocal(BuildProtocalId.GET_RESULT)
				.setData(scGetResultBuilder.setSuccess(BuildErrorCode.SUCCESS).build().toByteString()).build();

	}

	@Override
	public SC showBag(Role role) {
		// TODO Auto-generated method stub
		SC.Builder sc = SC.newBuilder();
		SC_ShowBag.Builder scShowBagBuilder = SC_ShowBag.newBuilder();

		Map<Integer, SC_ShowBag.Brick> bricks = new HashMap<>();

		Map<Integer, AbstractProp> propMap = role.getPropMap();
		for (AbstractProp p : propMap.values()) {
			Brick b = (Brick) p;

			Map<Integer, SC_ShowBag.Magic> magics = new HashMap<>();
			for (Magic m : b.getMagicMap().values()) {
				magics.put(
						m.getMagicId(),
						SC_ShowBag.Magic.newBuilder().setDuration(m.getDuration()).setValue(m.getValue())
								.setMagicId(m.getMagicId()).build());
			}

			bricks.put(b.getId(),
					SC_ShowBag.Brick.newBuilder().setMineId(b.getMineId()).setId(b.getId()).putAllMagics(magics)
							.build());
		}
		return sc.setProtocal(BuildProtocalId.SHOW_BAG)
				.setData(scShowBagBuilder.putAllBricks(bricks).build().toByteString()).build();
	}

	@Override
	public SC getWallValue(Role role) {
		// TODO Auto-generated method stub
		Wall wall = role.getWall();
		Map<Integer, SC_GetWallValue.Magic> magics = new HashMap<>();

		for (Magic magic : wall.getMagicsMap().values()) {
			magics.put(magic.getMagicId(), SC_GetWallValue.Magic.newBuilder().setDuration(magic.getDuration())
					.setValue(magic.getValue()).setMagicId(magic.getMagicId()).build());
		}

		return SC
				.newBuilder()
				.setProtocal(BuildProtocalId.GET_WALL_VALUE)
				.setData(
						SC_GetWallValue.newBuilder().setBlood(wall.getBlood()).putAllMagics(magics).build()
								.toByteString()).build();
	}

	private void refreshWall(Role role) {
		Wall wall = role.getWall();
		wall.setBlood(0);
		Map<Integer, Magic> wallMagics = wall.getMagicsMap();
		for (Magic m : wallMagics.values()) {
			m.setDuration(0);
			m.setValue(0);
		}

		Map<Integer, Brick> bricks = wall.getBrickMap();
		for (Brick brick : bricks.values()) {
			wall.setBlood(wall.getBlood()+OreConfigCache.getAllMap().get(brick.getMineId()).getBlood());
			Map<Integer, Magic> ms = brick.getMagicMap();
			for (Magic m : ms.values()) {
				Magic magic = wallMagics.get(m.getMagicId());
				if (magic == null) {
					magic = new Magic();
					magic.setMagicId(m.getMagicId());
					wall.getMagicsMap().put(m.getMagicId(), magic);
				}

				magic.setDuration(magic.getDuration() + m.getDuration());
				magic.setValue(magic.getValue() + m.getValue());
			}
		}

		List<Integer> magicIds = new ArrayList<>();
		for (Magic magic : wallMagics.values()) {
			if (magic.getDuration() == 0 && magic.getValue() == 0)
				magicIds.add(magic.getMagicId());
		}
		
		for(Integer magicId:magicIds){
			wallMagics.remove(magicId);
		}

	}

}
