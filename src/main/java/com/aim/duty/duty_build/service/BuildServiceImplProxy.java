package com.aim.duty.duty_build.service;

import com.aim.duty.duty_base.entity.bo.Forge;

public class BuildServiceImplProxy implements BuildService {

	private BuildService service;

	public BuildServiceImplProxy(BuildService service) {
		this.service = service;
	}

	@Override
	public void addFuel(Forge forge, int propId) {
		// TODO Auto-generated method stub
		service.addFuel(forge, propId);
	}

	@Override
	public void updateTemperature(Forge forge) {
		// TODO Auto-generated method stub
		service.updateTemperature(forge);
	}

	@Override
	public void ready(int propId, int num) {
		// TODO Auto-generated method stub
		service.ready(propId, num);
	}

	@Override
	public void hitBrick() {
		// TODO Auto-generated method stub
		service.hitBrick();
	}

	@Override
	public void createBrick() {
		// TODO Auto-generated method stub
		service.createBrick();
	}

	@Override
	public void destroyBrick(int propId) {
		// TODO Auto-generated method stub
		service.destroyBrick(propId);
	}

	@Override
	public void addBrickMagic(int magicId) {
		// TODO Auto-generated method stub
		service.addBrickMagic(magicId);
	}

	@Override
	public void createCement(int mineId, int num) {
		// TODO Auto-generated method stub
		service.createCement(mineId, num);
	}

	@Override
	public void addCementMagic(int magicId) {
		// TODO Auto-generated method stub
		service.addCementMagic(magicId);
	}

	@Override
	public void addBrickIntoWall(int index) {
		// TODO Auto-generated method stub
		service.addBrickIntoWall(index);
	}

	@Override
	public void createWall() {
		// TODO Auto-generated method stub
		service.createWall();
	}

	@Override
	public void removeBrickFromWall(int index) {
		// TODO Auto-generated method stub
		service.removeBrickFromWall(index);
	}

}
