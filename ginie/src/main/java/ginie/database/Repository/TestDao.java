package ginie.database.Repository;

import com.google.inject.Inject;
import ginie.database.entities.Test;
import ginie.database.entities.Test_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by dhruvr on 4/8/16.
 */
public class TestDao {


    private EntityManager entityManager;

    @Inject
    public TestDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List isPresent(String userName, String password){
      /* return entityManager.createQuery("Select e from Test e where e.userName=:userName and e.passWord = :password")
               .setParameter("userName", userName)
               .setParameter("password", password)
               .getResultList();*/

        return getUser(userName, password);
    }

    public List getUser(String userName, String password){
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Test> cq = criteriaBuilder.createQuery(Test.class);

        Root<Test> root = cq.from(Test.class);

        Predicate cond1 = criteriaBuilder.equal(root.get(Test_.userName), userName);
        Predicate cond2 = criteriaBuilder.equal(root.get(Test_.passWord), password);

        Predicate and = criteriaBuilder.and(cond1, cond2);
        cq.where(and);

        return   entityManager.createQuery(cq).getResultList();
    }

}
