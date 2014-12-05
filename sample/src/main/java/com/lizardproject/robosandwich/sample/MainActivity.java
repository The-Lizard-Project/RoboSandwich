package com.lizardproject.robosandwich.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;

import com.lizardproject.robosandwich.RoboSandwich;
import com.lizardproject.robosandwich.RoboSandwichStyle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
	}

	@OnClick(R.id.button)
	protected void onButtonClick() {
		RoboSandwich.makeText(this, R.string.sandwich1).show();
	}

	@OnClick(R.id.button2)
	protected void onButton2Click() {
		RoboSandwich.makeText(this, R.string.sandwich2).setyOffset(getActionBarHeight()).show();
	}

	@OnClick(R.id.button3)
	protected void onButton3Click() {
		RoboSandwich.makeText(this, R.string.sandwich3).setDuration(Toast.LENGTH_LONG).show();
	}

	@OnClick(R.id.button4)
	protected void onButton4Click() {
		RoboSandwich.makeText(this, R.string.sandwich4).setGravity(Gravity.BOTTOM).show();
	}

	@OnClick(R.id.button5)
	protected void onButton5Click() {
		RoboSandwich.makeText(this, R.string.sandwich5).setStyle(RoboSandwichStyle.NEGATIVE).show();
	}

	@OnClick(R.id.button6)
	protected void onButton6Click() {
		RoboSandwich.makeText(this, R.string.sandwich6).setStyle(RoboSandwichStyle.POSITIVE).show();
	}

	@OnClick(R.id.button7)
	protected void onButton7Click() {
		RoboSandwich.makeText(this, R.string.sandwich7).setStyle(RoboSandwichStyle.POSITIVE).setQueued(true).show();
	}

	@OnClick(R.id.button8)
	protected void onButton8Click() {
		RoboSandwich.makeText(this, R.string.sandwich8).setStyle(RoboSandwichStyle.NEGATIVE).setQueued(true).show();
	}

	private int getActionBarHeight() {
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}
}