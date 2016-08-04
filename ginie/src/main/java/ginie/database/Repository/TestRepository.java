package ginie.database.Repository;

import com.google.inject.Inject;
import ginie.database.entities.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by dhruvr on 4/8/16.
 */
public class TestRepository {


    private EntityManager entityManager;

    @Inject
    public TestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List isPresent(String userName, String password){
       return entityManager.createQuery("Select e from Test e where e.userName=:userName and e.passWord = :password")
               .setParameter("userName", userName)
               .setParameter("password", password)
               .getResultList();
    }

}
