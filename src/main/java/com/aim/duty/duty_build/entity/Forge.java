package com.aim.duty.duty_build.entity;

import com.aim.duty.duty_base.entity.AbstractProp;

public class Forge extends AbstractProp {

	private int temperature;
	private int burnStartTime;
	private int burnDeltaTime;
	private int burnFuelId;
	private int readyFuelId;
	private int readyFuelNum;
	private Equip equip = null;

	public Forge() {
		// TODO Auto-generated constructor stub
		burnFuelId = -1;
		readyFuelId = -1;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	public int getReadyFuelId() {
		return readyFuelId;
	}

	public void setReadyFuelId(int readyFuelId) {
		this.readyFuelId = readyFuelId;
	}

	public int getReadyFuelNum() {
		return readyFuelNum;
	}

	public void setReadyFuelNum(int readyFuelNum) {
		this.readyFuelNum = readyFuelNum;
	}

	public int getBurnStartTime() {
		return burnStartTime;
	}

	public void setBurnStartTime(int burnStartTime) {
		this.burnStartTime = burnStartTime;
	}

	public int getBurnDeltaTime() {
		return burnDeltaTime;
	}

	public void setBurnDeltaTime(int burnDeltaTime) {
		this.burnDeltaTime = burnDeltaTime;
	}

	public int getBurnFuelId() {
		return burnFuelId;
	}

	public void setBurnFuelId(int burnFuelId) {
		this.burnFuelId = burnFuelId;
	}

	public long getBurnEndTime() {
		return getBurnStartTime() + getBurnDeltaTime();
	}

	public Equip getEquip() {
		return equip;
	}

	public void setEquip(Equip equip) {
		this.equip = equip;
	}

}
