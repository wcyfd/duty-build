package com.aim.duty.duty_build.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetResult;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_GetWallValue;
import com.aim.duty.duty_build_entity.protobuf.protocal.Build.SC_ReplaceBrick;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_BuyProp;
import com.aim.duty.duty_build_entity.protobuf.protocal.Trade.SC_SaleProp;

public class UIController extends Observable {
	private MainFrame mainFrame;

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void start() {
		this.addObserver(mainFrame);
		mainFrame.initCode();
		mainFrame.start(mainFrame);
	}

	public void addRole() {
		List list = new ArrayList();
		list.add("addRole");
		this.setChanged();

		this.notifyObservers(list);
	}

	public void chooseMaterial() {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("chooseMaterial");
		this.setChanged();
		
		this.notifyObservers(list);
	}

	public void getResult(SC_GetResult scData) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("getResult");
		list.add(scData);
		this.setChanged();
		
		this.notifyObservers(list);
		
	}

	public void addBrickIntoWall(SC_ReplaceBrick scData) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("addBrickIntoWall");
		list.add(scData);
		this.setChanged();
		
		this.notifyObservers(list);
	}

	public void getWallValue(SC_GetWallValue scData) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("getWallValue");
		list.add(scData);		
		this.setChanged();	
		
		this.notifyObservers(list);
	}

	public void saleProp(SC_SaleProp scData) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("saleProp");
		list.add(scData);
		this.setChanged();
		
		this.notifyObservers(list);
	}

	public void buyProp(SC_BuyProp scData) {
		// TODO Auto-generated method stub
		List list = new ArrayList<>();
		list.add("buyProp");
		list.add(scData);
		this.setChanged();
		
		this.notifyObservers(list);
		
	}
	
}
