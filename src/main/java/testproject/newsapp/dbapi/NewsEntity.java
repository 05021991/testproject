package testproject.newsapp.dbapi;

import java.sql.Date;

/**
 * Database Entity for News.
 */
public class NewsEntity
{
	private String title;
	private String link;
	private String description;
	private Date pubDate;

	public NewsEntity()
	{
	}

	public NewsEntity(String title, String link, String description, Date pubDate)
	{
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(Date pubDate)
	{
		this.pubDate = pubDate;
	}
}
