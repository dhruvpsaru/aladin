/*
package ginie.database.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;

*/
/**
 * Created by dhruvr on 4/8/16.
 *//*

public class AbstractHibernateRepository<T> {

    protected Class<T> entityClass;
    protected EntityManager entityManager;

    public AbstractHibernateRepository(EntityManager entityManager {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    */
/**
     * Retrieves the meta-model for a certain entity.
     *
     * @return the meta-model of a certain entity.
     *//*

    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    @Transactional
    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }


    @Transactional
    public void remove(Long entityId) {
        T entity = find(entityId);

        if (entity != null)
            remove(entity);
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Transactional
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Transactional
    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));
        List<T> listResult = getEntityManager().createQuery(cq).getResultList();
        return listResult;
    }

    @Transactional
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    @Transactional
    public int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        Long count = getEntityManager().createQuery(cq).getSingleResult();

        return count.intValue();
    }

    @Transactional
    public void deleteAll() {
        EntityManager em = getEntityManager();
        String query = new StringBuilder("DELETE FROM ")
                .append(entityClass.getSimpleName())
                .append(" e")
                .toString();
        em.createQuery(query).executeUpdate();
    }
}
*/
