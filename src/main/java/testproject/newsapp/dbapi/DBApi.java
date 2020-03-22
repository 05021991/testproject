package testproject.newsapp.dbapi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Database API.
 */
@Component
public class DBApi
{
	private static final Logger logger = Logger.getLogger(DBApi.class.getName());

	@Autowired
	private Environment environment;

	private Connection connection;

	@PostConstruct
	public void init()
	{
		String dbDriver = environment.getProperty("spring.datasource.driverClassName");
		String dbUrl = environment.getProperty("spring.datasource.url");
		String dbUsername = environment.getProperty("spring.datasource.username");
		String dbPassword = environment.getProperty("spring.datasource.password");

		try
		{
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			logger.log(Level.SEVERE, "JDBC connection cannot be established.", e);
		}
	}

	/**
	 * Insert list of News entity.
	 *
	 * @param newsList
	 * @throws SQLException
	 */
	public void insertNewsList(List<NewsEntity> newsList) throws SQLException
	{
		for (NewsEntity newsEntity : newsList)
		{
			insertNews(newsEntity);
		}
	}

	/**
	 * Save News Entity.
	 *
	 * @param newsEntity
	 * @throws SQLException
	 */
	public void insertNews(NewsEntity newsEntity) throws SQLException
	{
		String sqlString = "INSERT INTO NEWS (title, link, description, pub_date) VALUES (?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlString);
		statement.setString(1, newsEntity.getTitle());
		statement.setString(2, newsEntity.getLink());
		statement.setString(3, newsEntity.getDescription());
		statement.setDate(4, newsEntity.getPubDate());

		statement.executeUpdate();
	}

	/**
	 * Gets the list of News entity.
	 *
	 * @param limit The number of records to retrieve.
	 * @return
	 * @throws SQLException
	 */
	public List<NewsEntity> getNews(int limit) throws SQLException
	{
		String sqlString = "SELECT title, link, description, pub_date FROM NEWS ORDER BY pub_date desc LIMIT " + limit;

		List<NewsEntity> result = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);

		while (resultSet.next())
		{
			String title = resultSet.getString("title");
			String link = resultSet.getString("link");
			String description = resultSet.getString("description");
			Date pubDate = resultSet.getDate("pub_date");

			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setTitle(title);
			newsEntity.setLink(link);
			newsEntity.setDescription(description);
			newsEntity.setPubDate(pubDate);

			result.add(newsEntity);
		}

		return result;
	}
}
