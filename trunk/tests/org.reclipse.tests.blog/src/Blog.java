import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;



public class Blog 
{
	private static Blog blog = null;
	
	public static Blog getBlog()
	{
		if(Blog.blog == null)
			Blog.blog = new Blog();
		return Blog.blog;
	}	
	
	
	
	private Storage store = null;
	
	private List<Notification> notifications = new Vector<Notification>();
		
	private Blog()
	{	
		this.store = new Storage();
		this.store.setBackend(new XMLBackend());
	}
		
	public void createNewEntry(String subject, String text)
	{
		Entry entry = new Entry(subject, text);
		this.store.add(entry);
		
		this.updateNotification(/*entry*/);
	}
	
	public void createNewComment(int entryId, String author, String text) throws Exception
	{
		Entry entry = null;
		
		Iterator<Entry> entryIt = this.store.getEntries(2).iterator();
		while(entryIt.hasNext())
		{
			Entry e = (Entry) entryIt.next();
			if(e.getId() == entryId)
			{
				entry = e;
				break;
			}
		}
		
		if(entry == null)
			throw new Exception("Unknown Entry");
		
		Comment comment = new Comment(author, text);
		entry.addComment(comment);
		
		this.updateNotification(/*comment*/);
		
	}
	
	public void addNotification(Notification notification)
	{
		this.notifications.add(notification);
	}
	
	protected void updateNotification(/*Content c*/)
	{
		Iterator<Notification> notificationIt = this.notifications.iterator();
		while(notificationIt.hasNext())
		{
			Notification notification = (Notification) notificationIt.next();
			notification.update(/*c*/);
		}
		
	}
	
	public String render()
	{
		String content = "";
		Iterator<Entry> entryIt = this.store.getEntries(4).iterator();
		while( entryIt.hasNext())
		{
			Entry entry = (Entry) entryIt.next();
			content += entry.render() + "\n";
		}		
		return content;
	}
	
	public void setBackend(Backend be)
	{
		this.store.setBackend(be);
	}
}
