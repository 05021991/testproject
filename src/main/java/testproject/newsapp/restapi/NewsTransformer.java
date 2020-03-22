package testproject.newsapp.restapi;

import testproject.newsapp.dbapi.NewsEntity;

/**
 * Transformer for News.
 */
public class NewsTransformer extends AbstractTransformer<NewsDTO, NewsEntity>
{
	@Override
	public NewsDTO transformEntity(NewsEntity entity)
	{
		NewsDTO dto = new NewsDTO();
		dto.setTitle(entity.getTitle());
		dto.setLink(entity.getLink());
		dto.setDescription(entity.getDescription());

		return dto;
	}
}
