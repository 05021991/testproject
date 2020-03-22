package testproject.newsapp.restapi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import testproject.newsapp.dbapi.DBApi;
import testproject.newsapp.dbapi.NewsEntity;

/**
 * News API.
 */
@RestController
public class NewsApi
{
	private static final Logger logger = Logger.getLogger(NewsApi.class.getName());

	@Autowired
	DBApi dbApi;

	/**
	 * API for News.
	 *
	 * @param size If no size is provided, then by default it is 10.
	 * @return Returns list of News DTO of provided size.
	 */
	@GetMapping("/news")
	public List<NewsDTO> getNews(@RequestParam(value = "size", defaultValue = "10") int size)
	{
		List<NewsDTO> result = new ArrayList<>();
		try
		{
			List<NewsEntity> newsEntityList = dbApi.getNews(size);
			result = new NewsTransformer().transformEntityList(newsEntityList);

			return result;
		}
		catch (SQLException e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);
		}

		return result;
	}
}
