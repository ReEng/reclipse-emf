import java.util.Date;


abstract public class Content 
{
	protected Date creationDate = null;
	protected Date updateDate = null;
	
	public Content()
	{
		this.creationDate = new Date();
		this.updateDate = this.creationDate;
	}
	
	public Date getCreationDate()
	{
		return this.creationDate;
	}
	
	public Date getUpdateDate()
	{
		return this.updateDate;		
	}
	
	public boolean isUpdated()
	{
		return !this.creationDate.equals(this.updateDate);
	}
	/*
	public String toString()
	{
		return this.render();
	}
	*/
	abstract public String getAuthor();
	abstract public String getContent();
	
	abstract public String render();
}
