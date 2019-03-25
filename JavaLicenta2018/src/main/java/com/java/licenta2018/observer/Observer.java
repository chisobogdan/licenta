package com.java.licenta2018.observer;

import com.java.licenta2018.ems.TibcoEMSQueueReceiver;
import com.java.licenta2018.view.MessageViewPanel;

public abstract class Observer {
	protected TibcoEMSQueueReceiver subject;
	protected MessageViewPanel msgView;
	public abstract void update();
}
