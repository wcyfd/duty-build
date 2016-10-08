package com.aim.duty.duty_build.service;

import com.aim.duty.duty_base.entity.bo.Forge;

public interface BuildService {
	public void addFuel(Forge forge, int propId);

	public void updateTemperature(Forge forge);

	public void ready(int propId, int num);

	public void hitBrick();

	public void createBrick();

	public void addBrickMagic(int magicId);

	public void createCement(int mineId, int num);

	public void destroyBrick(int propId);

	public void addCementMagic(int magicId);

	public void addBrickIntoWall(int index);
	
	public void removeBrickFromWall(int index);

	public void createWall();
}
