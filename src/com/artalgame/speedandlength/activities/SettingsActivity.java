package com.artalgame.speedandlength.activities;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.concurrent.*;
import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.CommonComponents.ISettingsExListData;
import com.artalgame.speedandlength.CommonComponents.SALSettings;
import com.artalgame.speedandlength.CommonComponents.Frequency.ChartFrequencyUpdateValues;
import com.artalgame.speedandlength.CommonComponents.Frequency.DataFrequencyUpdateValues;
import com.artalgame.speedandlength.CommonComponents.Frequency.FrequencyUpdateValue;
import com.artalgame.speedandlength.CommonComponents.Measure.DistanceMeasureValues;
import com.artalgame.speedandlength.CommonComponents.Measure.SpeedMeasureValues;
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
	private ExpandableListView frequencyChartUpdateExpandableListView;
	
	private SettingsExpandableListAdapter frequencyChartUpdateAdapter;
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
		setFrequencyChartUpdateExListView();
	}

	private void setFrequencyChartUpdateExListView() {
		
		frequencyChartUpdateExpandableListView = (ExpandableListView)findViewById(R.id.frequencyChartUpdateExpandableListView);
		if(frequencyChartUpdateExpandableListView != null){
			frequencyChartUpdateAdapter = getFrequencyChartUpdateExListAdapter();
			frequencyChartUpdateExpandableListView.setAdapter(frequencyChartUpdateAdapter);
			frequencyChartUpdateExpandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					
					SettingsExpandableListAdapter adapter = frequencyChartUpdateAdapter;
					SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
					String name = child.getName();
					SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
					group.setName(getFrequencyChartUpdateExListGroupName(childPosition));
					SpeedAndLengthApplication.settings.setChartUpdateFrequency(name);
					SpeedAndLengthApplication.settings.setChartUpdateFrequencyIndex(childPosition);
					frequencyChartUpdateExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}			
			});
		}
	}
	
	private String getFrequencyChartUpdateExListGroupName(
			int childPosition) {
		
		return new ChartFrequencyUpdateValues().getStringValues().get(childPosition);
	}

	private SettingsExpandableListAdapter getFrequencyChartUpdateExListAdapter() {
		
		ArrayList<String> values = new ChartFrequencyUpdateValues().getStringValues();
    	SettingsExpandableListAdapter adapter = getAdapter(values.toArray(new String[0]));
    	int index = SpeedAndLengthApplication.settings.getChartUpdateFrequencyIndex();
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(getFrequencyChartUpdateExListGroupName(index));
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
					SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
					String name = child.getName();
					SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
					group.setName(getFrequencyDataUpdateExListGroupName(childPosition));
					SpeedAndLengthApplication.settings.setDataUpdateFrequency(name);
					SpeedAndLengthApplication.settings.setDataUpdateFrequencyIndex(childPosition);
					frequencyDataUpdateExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}
			});
		}
	}
	
	private String getFrequencyDataUpdateExListGroupName(
			int childPosition) {
		
		return new DataFrequencyUpdateValues().getStringValues().get(childPosition);
	}

	private SettingsExpandableListAdapter getFrequencyDataUpdateExListAdapter() {
		ArrayList<String> values = new DataFrequencyUpdateValues().getStringValues();
		SettingsExpandableListAdapter adapter = getAdapter(values.toArray(new String[0]));
		int index = SpeedAndLengthApplication.settings.getDataUpdateFrequencyIndex();
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(getFrequencyDataUpdateExListGroupName(index));
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
					SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
					String name = child.getName();
					SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
					group.setName(getDistanceMeasureExListGroupName(childPosition));
					SpeedAndLengthApplication.settings.setDistanceMeasure(name);
					SpeedAndLengthApplication.settings.setDistanceMeasureIndex(childPosition);
					defaultDistanceMeasureExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}
			});
		}
	}
	
	private String getDistanceMeasureExListGroupName(
			int childPosition) {
		return new DistanceMeasureValues().getValues().get(childPosition);
	}
	
	private SettingsExpandableListAdapter getDistanceMeasureExListAdapter() {
		ArrayList<String> values = new DistanceMeasureValues().getValues();
		SettingsExpandableListAdapter adapter = getAdapter(values.toArray(new String[0]));
		int index = SpeedAndLengthApplication.settings.getDistanceMeasureIndex();
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(getDistanceMeasureExListGroupName(index));
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
					SettingsExpandableListChild child = (SettingsExpandableListChild)adapter.getChild(groupPosition, childPosition);
					String name = child.getName();
					SettingsExpandableListGroup group = (SettingsExpandableListGroup)adapter.getGroup(groupPosition);
					group.setName(getSpeedMeasureExListGroupName(childPosition));
					SpeedAndLengthApplication.settings.setSpeedMeasure(name);
					SpeedAndLengthApplication.settings.setSpeedMeasureIndex(childPosition);
					defaultSpeedMeasureExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}
			});
		}
	}
	
	private String getSpeedMeasureExListGroupName(int childPosition) {
		return new SpeedMeasureValues().getValues().get(childPosition);
	}

	private SettingsExpandableListAdapter getSpeedMeasureExListAdapter() {
		ArrayList<String> values = new SpeedMeasureValues().getValues();
		SettingsExpandableListAdapter adapter = getAdapter(values.toArray(new String[0]));
    	//set name of group from settings
		int index = SpeedAndLengthApplication.settings.getSpeedMeasureIndex();
    	((SettingsExpandableListGroup)adapter.getGroup(0)).setName(getSpeedMeasureExListGroupName(index));
    	return adapter;
	}

	private SettingsExpandableListAdapter getAdapter(String[] dataList)
	{
		
		ArrayList<SettingsExpandableListGroup> groupList = new ArrayList<SettingsExpandableListGroup>();
    	ArrayList<SettingsExpandableListChild> childList = new ArrayList<SettingsExpandableListChild>();
    	//Set values from dataList to group's children
        SettingsExpandableListGroup mainGroup = new SettingsExpandableListGroup();
        for(int i = 0; i < dataList.length; i++ )
        {
        	SettingsExpandableListChild ch = new SettingsExpandableListChild();
            ch.setName(dataList[i]);
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
