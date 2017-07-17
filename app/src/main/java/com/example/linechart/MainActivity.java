package com.example.linechart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private linechartview findViewById;
	private ArrayList<Map<String, Float>> mListPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById = (linechartview) findViewById(R.id.lcv);
		initData();

		findViewById.setmListPoint(mListPoint);
		// findViewById.setIsGone(false);
		
	}

	private void initData() {
		mListPoint = new ArrayList<Map<String, Float>>();
		float[] y = new float[] {1f, 2.5f, 5f,6f, 7.5f, 10f };
		for (int i = 0; i < y.length; i++) {
			HashMap<String, Float> hashMap = new HashMap<String, Float>();
			hashMap.put("key_y", (y[i]));// i*20 +100
			mListPoint.add(hashMap);
		}
		

		findViewById.setXnumber(new String[] { "12-1", "12-2", "12-3", "12-4","12-5" });
		findViewById.setYnumber(4);
		findViewById.setMaSxy(10.0f);

	}
}
