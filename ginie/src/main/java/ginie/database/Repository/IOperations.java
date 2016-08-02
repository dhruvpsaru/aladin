package ginie.database.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dhruvr on 1/8/16.
 */
public interface IOperations<T extends Serializable> {

    T findOne(final Long id);

    List<T> findAll();

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    Long findRecordsCount(final T entity);

}
