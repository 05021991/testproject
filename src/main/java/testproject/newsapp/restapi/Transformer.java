package testproject.newsapp.restapi;

import java.util.List;

/**
 * Transformer interface to convert entity to DTO.
 *
 * @param <E> - resultant DTO
 * @param <T> - database Entity
 */
public interface Transformer<E, T>
{
	/**
	 * Transform one entity into DTO.
	 *
	 * @param entity
	 * @return DTO
	 */
	E transformEntity(T entity);

	/**
	 * Transform list of entities into list of DTO.
	 *
	 * @param entityList
	 * @return DTO List
	 */
	List<E> transformEntityList(List<T> entityList);
}
