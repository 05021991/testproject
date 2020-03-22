package testproject.newsapp.restapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract transformer to have common methods.
 *
 * @param <E>
 * @param <T>
 */
public abstract class AbstractTransformer<E, T> implements Transformer<E, T>
{
	@Override
	public List<E> transformEntityList(List<T> entityList)
	{
		List<E> result = new ArrayList<>();
		for (T entity : entityList)
		{
			result.add(transformEntity(entity));
		}
		return result;
	}
}
