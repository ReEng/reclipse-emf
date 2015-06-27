import java.net.URL;

public class PingNotification extends Notification
{
	private URL pingServer = null; 

	public PingNotification(Blog b, URL pingServer)
	{
		this.pingServer = pingServer;
		b.addNotification(this);
	}
	
	public void update(/*Content c*/)
	{
		/*
		if(c instanceof Entry)
			this.updateEntry((Entry)c);
			*/
	}
	
	private void updateEntry(Entry e)
	{
		if(!e.isUpdated())
			this.publish(e);
	}
		
	private void publish(Entry e)
	{
		
	}
}
