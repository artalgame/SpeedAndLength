package com.artalgame.speedandlength.vidgets;


import com.artalgame.speedandlength.R;
import com.artalgame.speedandlength.activities.GPSActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class GPSButton implements OnClickListener {

	@Override
	public void onClick(View v) {
		Activity currentActivity = (Activity)v.getContext();
		if(currentActivity != null)
		{
			TextView caption = (TextView)currentActivity.findViewById(R.id.titleTextView);
			if(caption != null)
			{
				caption.setText("GPS");
			}
		}
			Intent gpsActivityIntent = new Intent(v.getContext(), GPSActivity.class);
			v.getContext().startActivity(gpsActivityIntent);
	}
}
