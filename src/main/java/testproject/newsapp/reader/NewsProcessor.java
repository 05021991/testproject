package testproject.newsapp.reader;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import testproject.newsapp.dbapi.DBApi;
import testproject.newsapp.dbapi.NewsEntity;

/**
 * Process XML and save the records into DB.
 */
@Component
public class NewsProcessor
{
	private static final Logger logger = Logger.getLogger(NewsProcessor.class.getName());

	@Autowired
	private DBApi dbApi;

	@Scheduled(cron = "0/10 * * * * ?")
	public void scheduleTaskWithCronExpression() throws JAXBException, IOException, SQLException, ClassNotFoundException
	{
		JAXBContext context = JAXBContext.newInstance(News.class, News.Channel.class, News.Channel.Item.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		News news = (News) unmarshaller.unmarshal(new URL("https://www.nydailynews.com/cmlink/NYDN.News.World.rss"));

		saveInMemory(news.channel.item);

		logger.log(Level.INFO, news.channel.item.size() + " records processed.");
	}

	/**
	 * Save records in memory.
	 *
	 * @param itemList
	 */
	private void saveInMemory(List<News.Channel.Item> itemList)
	{
		List<NewsEntity> listToSave = new ArrayList<>();
		for (News.Channel.Item item : itemList)
		{
			NewsEntity newsEntity = new NewsEntity(item.title, item.link, item.description, dateParser(item.pubDate));
			listToSave.add(newsEntity);
		}

		try
		{
			dbApi.insertNewsList(listToSave);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param strDate
	 * @return
	 */
	private Date dateParser(String strDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");

		try
		{
			return new Date(sdf.parse(strDate).getTime());
		}
		catch (ParseException e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);
		}

		return new Date(System.currentTimeMillis());
	}
}
