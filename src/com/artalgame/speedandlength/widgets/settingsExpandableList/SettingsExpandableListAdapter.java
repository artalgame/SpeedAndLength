package com.artalgame.speedandlength.widgets.settingsExpandableList;

import java.util.ArrayList;

import com.artalgame.speedandlength.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

public class SettingsExpandableListAdapter extends BaseExpandableListAdapter{
	private Context context;
	private ArrayList<SettingsExpandableListGroup> groups;
	
	public SettingsExpandableListAdapter(Context context, ArrayList<SettingsExpandableListGroup> groups){
		
		this.context = context;
        this.groups = groups;
    }
	
	public void addItem(SettingsExpandableListChild item, SettingsExpandableListGroup group){
		if(!groups.contains(group)){
			groups.add(group);
		}
			int index = groups.indexOf(group);
			ArrayList<SettingsExpandableListChild> groupItems = groups.get(index).getItems();
			groupItems.add(item);
			groups.get(index).setItems(groupItems);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		SettingsExpandableListGroup group = groups.get(groupPosition);
		return group.getItems().get(childPosition);
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		SettingsExpandableListChild child = (SettingsExpandableListChild)getChild(groupPosition, childPosition);
		if(child != null)
		{
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.settings_expandablelist_child_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
		tv.setText(child.getName().toString());
		tv.setTag(child.getTag());
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getItems().size();
	}
	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}
	@Override
	public int getGroupCount() {
		return groups.size();
	}
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		SettingsExpandableListGroup group = (SettingsExpandableListGroup) getGroup(groupPosition);
		if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.settings_expandablelist_group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
		tv.setText(group.getName());
		return convertView;
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}