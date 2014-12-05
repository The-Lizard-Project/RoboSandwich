package com.lizardproject.robosandwich;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RoboSandwich {

	private static RoboSandwich lastSandwich;
	private final Context context;
	private Toast toast;
	private View toastView;
	private TextView toastTextView;

	private String message;
	private int gravity;
	private RoboSandwichStyle style;
	private int duration;
	private int yOffset;
	private boolean queued;

	private RoboSandwich(Context context, String message) {
		this.context = context;
		this.message = message;
		initDefaultValues();
		initViews();
	}

	/**
	 * Android Toast based static factory method of RoboSanchwich
	 *
	 * @param context The context to use. Usually your Application or Activity object.
	 * @param message The message to be displayed on the Sandwich
	 */
	public static RoboSandwich makeText(Context context, String message) {
		return new RoboSandwich(context, message);
	}

	/**
	 * Android Toast based static factory method of RoboSanchwich
	 *
	 * @param context   The context to use. Usually your Application or Activity object.
	 * @param messageId The id of the message to be displayed on the Sandwich
	 */
	public static RoboSandwich makeText(Context context, int messageId) {
		return new RoboSandwich(context, context.getString(messageId));
	}

	/**
	 * Set the location at which the notification should appear on the screen.
	 *
	 * @param gravity android.view.Gravity.<br>
	 *                Recommended:
	 *                <li>Gravity.TOP</li>
	 *                <li>Gravity.BOTTOM</li>
	 *                <br>
	 *                Default: Gravity.BOTTOM - u can change it in the config file.
	 */
	public RoboSandwich setGravity(int gravity) {
		this.gravity = gravity;
		return this;
	}

	/**
	 * Set the style of the notification.
	 *
	 * @param style Style to be used in notification<br>
	 *              Default: RoboSandwichStyle.INFO - u can change it in the config file.
	 */
	public RoboSandwich setStyle(RoboSandwichStyle style) {
		this.style = style;
		return this;
	}

	/**
	 * Set how long to display the notification.
	 *
	 * @param duration How long to display the notification.<br>
	 *                 Use: Toast.LENGTH_SHORT or Toast.LENGTH_LONG<br>
	 *                 Default: Toast.LENGTH_SHORT - u can change it in the config file.
	 */
	public RoboSandwich setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * Set the offset in y axe in pixels.
	 *
	 * @param yOffset Offset in y axe in pixels.<br>
	 *                Typical usage: display the notification below the ActionBar/Toolbar<br>
	 *                Default: 0 - u can change it in the config file.
	 */
	public RoboSandwich setyOffset(int yOffset) {
		this.yOffset = yOffset;
		return this;
	}

	/**
	 * Set if the notification should be queued or not.<br>
	 * Queued notification will wait for previous Sandwich to be dismissed.
	 *
	 * @param queued Default: false - u can change it in the config file.
	 */
	public RoboSandwich setQueued(boolean queued) {
		this.queued = queued;
		return this;
	}

	private void initDefaultValues() {
		Resources resources = context.getResources();
		gravity = resources.getInteger(R.integer.gravity);
		style = RoboSandwichStyle.valueOf(resources.getString(R.string.style));
		duration = resources.getInteger(R.integer.duration);
		yOffset = resources.getInteger(R.integer.yOffset);
		queued = resources.getBoolean(R.bool.queued);
	}

	private void initViews() {
		toastView = LayoutInflater.from(context).inflate(R.layout.v_toast, null);
		toastTextView = (TextView) toastView.findViewById(R.id.toast_tv);
	}

	private void initToast() {
		toast = new Toast(context);
		toast.setGravity(Gravity.FILL_HORIZONTAL | gravity, 0, yOffset);
		toast.setDuration(duration);
		toast.setView(toastView);
	}

	private void setToastText(String text) {
		toastTextView.setText(text);
	}

	private void loadToastStyle(RoboSandwichStyle style) {
		switch (style) {
			case INFO:
				setToastBackground(R.color.toast_blue);
				break;
			case POSITIVE:
				setToastBackground(R.color.toast_green);
				break;
			case NEGATIVE:
				setToastBackground(R.color.toast_red);
				break;
		}
	}

	private void setToastBackground(int color) {
		toastView.setBackgroundColor(context.getResources().getColor(color));
	}

	/**
	 * Show the RoboSandwich.
	 */
	public void show() {
		initToast();
		setToastText(message);
		loadToastStyle(style);

		if (lastSandwich != null && (!queued || lastSandwich.getMessage().equals(message))) {
			lastSandwich.cancel();
		}
		toast.show();

		lastSandwich = this;
	}

	/**
	 * Cancel the RoboSandwich.
	 */
	public void cancel() {
		toast.cancel();
	}

	protected String getMessage() {
		return message;
	}
}