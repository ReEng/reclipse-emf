import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


public class Storage 
{
	private Backend be = null;
	
	private List<Entry> entries = new Vector<Entry>();
	
	public void add(Entry e)
	{
		this.entries.add(e);
		this.be.save(this);
	}
	
	public List<Comment> getComments()
	{
		List<Comment> comments = new LinkedList<Comment>();
		
		for(Iterator<Entry> entryIt= this.getEntries(2).iterator(); entryIt.hasNext();)
		{
			Entry e = (Entry) entryIt.next();
			comments.addAll(e.getComments());
		}
		return comments;
	}	
	
	public List<Entry> getEntries(int a)
	{
		return this.entries;
	}
		
	public void setBackend(Backend be)
	{
		this.be = be;
	}

}
