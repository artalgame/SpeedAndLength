package com.artalgame.speedandlength.activities;

import java.util.ArrayList;
import java.util.concurrent.*;
import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.CommonComponents.ChatFrequencyUpdateEnum;
import com.artalgame.speedandlength.CommonComponents.DataFrequencyUpdateEnum;
import com.artalgame.speedandlength.CommonComponents.DistanceMeasureEnum;
import com.artalgame.speedandlength.CommonComponents.ISettingsExListData;
import com.artalgame.speedandlength.CommonComponents.SALSettings;
import com.artalgame.speedandlength.CommonComponents.SpeedMeasureEnum;
import com.artalgame.speedandlength.application.SpeedAndLengthApplication;
import com.artalgame.speedandlength.vidgets.SettingsExpandableListAdapter;
import com.artalgame.speedandlength.vidgets.SettingsExpandableListChild;
import com.artalgame.speedandlength.vidgets.SettingsExpandableListGroup;

import android.app.Activity;
import android.content.Intent;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListAdapter;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	private Button saveButton;
	private ExpandableListView defaultSpeedMeasureExpandableListView;
	private ExpandableListView defaultDistanceMeasureExpandableListView;
	private ExpandableListView frequencyDataUpdateExpandableListView;
	private ExpandableListView frequencyChatUpdateExpandableListView;
	
	private SettingsExpandableListAdapter frequencyChatUpdateAdapter;
	private SettingsExpandableListAdapter frequencyDataUpdateAdapter;
	private SettingsExpandableListAdapter speedMeasureExpandableListAdapter;
	private SettingsExpandableListAdapter distanceMeasureExpandableListAdapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setComponents();
	}
	
	private void setComponents() {
		setSaveButton();
		setSpeedMeasureExListView();
		setDistanceMeasureExListView();
		setFrequencyDataUpdataExListView();
		setFrequencyChatUpdateExListView();
	}

	private void setFrequencyChatUpdateExListView() {
		
		frequencyChatUpdateExpandableListView = (ExpandableListView)findViewById(R.id.frequencyChatUpdateExpandableListView);
		if(frequencyChatUpdateExpandableListView != null){
			frequencyChatUpdateAdapter = getFrequencyChatUpdateExListAdapter();
			frequencyChatUpdateExpandableListView.setAdapter(frequencyChatUpdateAdapter);
			frequencyChatUpdateExpandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					SettingsExpandableListAdapter adapter = frequencyChatUpdateAdapter;
					SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
					String name = child.getName();
					SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
					group.setName(name);
					SpeedAndLengthApplication.settings.setChatUpdateFrequency(name);
					frequencyChatUpdateExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}
			});
		}
	}

	private SettingsExpandableListAdapter getFrequencyChatUpdateExListAdapter() {
		
    	SettingsExpandableListAdapter adapter = getAdapter(ChatFrequencyUpdateEnum.values());
    	//set name of group from settings
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(SpeedAndLengthApplication.settings.getChatUpdateFrequency());
    	return adapter;
	}
	
	private void setFrequencyDataUpdataExListView() {
		
		frequencyDataUpdateExpandableListView = (ExpandableListView)findViewById(R.id.frequencyDataUpdateExpandableListView);
		if(frequencyDataUpdateExpandableListView != null){
			frequencyDataUpdateAdapter = getFrequencyDataUpdateExListAdapter();
			frequencyDataUpdateExpandableListView.setAdapter(frequencyDataUpdateAdapter);
			frequencyDataUpdateExpandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					SettingsExpandableListAdapter adapter = frequencyDataUpdateAdapter;
					String name = DoCommonActionsWhenExListElementClicked(adapter, groupPosition, childPosition);
					SpeedAndLengthApplication.settings.setDataUpdateFrequency(name);
					frequencyDataUpdateExpandableListView.collapseGroup(groupPosition);
					return false;
				}
			});
		}
	}

	private SettingsExpandableListAdapter getFrequencyDataUpdateExListAdapter() {
		SettingsExpandableListAdapter adapter = getAdapter(DataFrequencyUpdateEnum.values());
    	//set name of group from settings
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(SpeedAndLengthApplication.settings.getDataUpdateFrequency());
    	return adapter;
	}
	
	private void setDistanceMeasureExListView() {
		
		defaultDistanceMeasureExpandableListView = (ExpandableListView)findViewById(R.id.defaultDistanceMeasureExpandableListView);
		if(defaultDistanceMeasureExpandableListView != null){
			distanceMeasureExpandableListAdapter = getDistanceMeasureExListAdapter();
			defaultDistanceMeasureExpandableListView.setAdapter(distanceMeasureExpandableListAdapter);
			defaultDistanceMeasureExpandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					SettingsExpandableListAdapter adapter = distanceMeasureExpandableListAdapter;
					String name = DoCommonActionsWhenExListElementClicked(adapter, groupPosition, childPosition);
					SpeedAndLengthApplication.settings.setDistanceMeasure(name);
					defaultDistanceMeasureExpandableListView.collapseGroup(groupPosition);
					return false;
				}
			});
		}
	}

	private SettingsExpandableListAdapter getDistanceMeasureExListAdapter() {
		
		SettingsExpandableListAdapter adapter = getAdapter(DistanceMeasureEnum.values());
    	//set name of group from settings
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(SpeedAndLengthApplication.settings.getDistanceMeasure());
    	return adapter;
	}

	private void setSpeedMeasureExListView() {
		
		defaultSpeedMeasureExpandableListView = (ExpandableListView)findViewById(R.id.defaultSpeedMeasureExpandableListView);
		if(defaultSpeedMeasureExpandableListView != null){
			speedMeasureExpandableListAdapter = getSpeedMeasureExListAdapter();
			defaultSpeedMeasureExpandableListView.setAdapter(speedMeasureExpandableListAdapter);
			defaultSpeedMeasureExpandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					SettingsExpandableListAdapter adapter = speedMeasureExpandableListAdapter;
					String name = DoCommonActionsWhenExListElementClicked(adapter, groupPosition, childPosition);
					SpeedAndLengthApplication.settings.setSpeedMeasure(name);
					defaultSpeedMeasureExpandableListView.collapseGroup(groupPosition);
					return false;
				}
			});
		}
	}
	private SettingsExpandableListAdapter getSpeedMeasureExListAdapter() {
		SettingsExpandableListAdapter adapter = getAdapter(SpeedMeasureEnum.values());
    	//set name of group from settings
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(SpeedAndLengthApplication.settings.getSpeedMeasure());
    	return adapter;
	}

	//Return name of clicked element
	private String DoCommonActionsWhenExListElementClicked(SettingsExpandableListAdapter adapter, int groupPosition, int childPosition)
	{
		SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
		String name = child.getName();
		SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
		group.setName(name);
		adapter.notifyDataSetChanged();
		return name;
	}
	
	private SettingsExpandableListAdapter getAdapter(ISettingsExListData[] dataList)
	{
		
		ArrayList<SettingsExpandableListGroup> groupList = new ArrayList<SettingsExpandableListGroup>();
    	ArrayList<SettingsExpandableListChild> childList = new ArrayList<SettingsExpandableListChild>();
    	//Set values from dataList to group's children
        SettingsExpandableListGroup mainGroup = new SettingsExpandableListGroup();
        //set group name like first element in dataList
        mainGroup.setName(dataList[0].getStringValue());
        for(int i = 0; i < dataList.length; i++ )
        {
        	SettingsExpandableListChild ch = new SettingsExpandableListChild();
            ch.setName(dataList[i].getStringValue());
            ch.setTag(null);
            childList.add(ch);
        }
        mainGroup.setItems(childList);
        groupList.add(mainGroup);
        return new SettingsExpandableListAdapter(this, groupList);
	}

	private void setSaveButton() {
		
		saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveButtonClicked();
			}
		});
	}

	//Back to main activity
	@Override
	public void onBackPressed(){
		SpeedAndLengthApplication.settings.cancellNewSettings();
		goBack();
	}
	
	private void saveButtonClicked(){
		SpeedAndLengthApplication.settings.saveSettings();
		goBack();
	}

	private void goBack() {
		// TODO Auto-generated method stub
		Intent MainActivityIntent = new Intent(this, MainActivity.class);
		startActivity(MainActivityIntent);
		finish();
	}
}
