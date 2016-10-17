package com.aim.duty.duty_build.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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
}
