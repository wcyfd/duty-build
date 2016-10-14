package com.aim.duty.duty_build.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class UIController extends Observable {
	private MainFrame mainFrame;

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public void start(){
		this.addObserver(mainFrame);
		mainFrame.start(mainFrame);
		
	}
	
	public void addRole(int roleId,String name){
		List list = new ArrayList();
		list.add("addRole");
		list.add(roleId);
		list.add(name);
		this.setChanged();
		
		this.notifyObservers(list);
	}
}
