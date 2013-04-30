package com.artalgame.speedandlength.activities;

import java.util.ArrayList;

import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.CommonComponents.ChatFrequencyUpdateEnum;
import com.artalgame.speedandlength.CommonComponents.DataFrequencyUpdateEnum;
import com.artalgame.speedandlength.CommonComponents.SALSettings;
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

	private SettingsExpandableListAdapter frequencyChatUpdateAdapter;
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
					frequencyChatUpdateExpandableListView.collapseGroup(groupPosition);
					adapter.notifyDataSetChanged();
					return false;
				}
			});
		}
	}

	private SettingsExpandableListAdapter getFrequencyChatUpdateExListAdapter() {
    	ArrayList<SettingsExpandableListGroup> groupList = new ArrayList<SettingsExpandableListGroup>();
    	ArrayList<SettingsExpandableListChild> childList = new ArrayList<SettingsExpandableListChild>();
    	
        SettingsExpandableListGroup gru1 = new SettingsExpandableListGroup();
        gru1.setName(SpeedAndLengthApplication.settings.getDataUpdateFrequency());
        for(int i=0; i<DataFrequencyUpdateEnum.values().length; i++)
        {
        	SettingsExpandableListChild ch = new SettingsExpandableListChild();
            ch.setName(DataFrequencyUpdateEnum.values()[i].getStringValue());
            ch.setTag(null);
            childList.add(ch);
        }
        gru1.setItems(childList);
        childList = new ArrayList<SettingsExpandableListChild>();
        
        groupList.add(gru1);
        
        return new SettingsExpandableListAdapter(this, groupList);
	}

	private void setFrequencyDataUpdataExListView() {
		// TODO Auto-generated method stub
		
	}

	private void setDistanceMeasureExListView() {
		// TODO Auto-generated method stub
		
	}

	private void setSpeedMeasureExListView() {
		// TODO Auto-generated method stub
		
	}

	private void setSaveButton() {
		
		saveButton = (Button)findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
	}

	//Back to main activity
	@Override
	public void onBackPressed(){
		Intent MainActivityIntent = new Intent(this, MainActivity.class);
		startActivity(MainActivityIntent);
		finish();
	}
	
	private void saveButtonClicked(){
		
	}
}
