
public abstract class Observer {
	protected TibcoEMSQueueReceiver subject;
	protected MessageViewPanel msgView;
	public abstract void update();
}
