package testproject.newsapp.reader;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Java class representation of XML.
 */
@XmlRootElement(name = "rss")
public class News
{
	Channel channel;

	public Channel getChannel()
	{
		return channel;
	}

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	static class Channel
	{
		@XmlElement(name = "item")
		List<Item> item;

		public List<Item> getItemList()
		{
			return item;
		}

		public void setItemList(List<Item> item)
		{
			this.item = item;
		}

		static class Item
		{
			String title;
			String link;
			String description;
			String pubDate;

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

			public String getPubDate()
			{
				return pubDate;
			}

			public void setPubDate(String pubDate)
			{
				this.pubDate = pubDate;
			}
		}
	}
}
