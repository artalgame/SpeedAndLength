package com.artalgame.speedandlength.widgets.settingsExpandableList;

import java.util.ArrayList;


public class SettingsExpandableListGroup {
	private String name;
    private ArrayList<SettingsExpandableListChild> items;

    public String getName() {
        return name;
    }
    public void setName(String name) {
    	this.name = name;
	}
	
    public ArrayList<SettingsExpandableListChild> getItems() {
        return items;
    }

	public void setItems(ArrayList<SettingsExpandableListChild> Items) {
        this.items = Items;
    }

	     

}
