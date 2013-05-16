package com.artalgame.speedandlength.widgets;

import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.activities.GPSActivity;
import com.artalgame.speedandlength.activities.SettingsActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingsButton implements OnClickListener {

	@Override
	public void onClick(View v) {
		Activity currentActivity = (Activity)v.getContext();
		if(currentActivity != null)
		{
			TextView caption = (TextView)currentActivity.findViewById(R.id.titleTextView);
			if(caption != null)
			{
				caption.setText("Settings");
			}
			Intent settingsActivityIntent = new Intent(v.getContext(), SettingsActivity.class);
			v.getContext().startActivity(settingsActivityIntent);
		}
	}

}
