package com.ani.matrimony.repoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ani.matrimony.model.User;
import com.ani.matrimony.repo.UserRepo;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

    private final EntityManager em;

    public UserRepoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public String save(User user) {
        if (user != null) {
            em.merge(user);
            return "Success";
        }
        return "Failure";
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = query.getResultStream().findFirst().orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByfirstnameContainingAndAgeAndReligion(Integer userid,String firstname, Integer age, String gender, String religion, String maritalstatus, String occupation) {
        StringBuilder queryString = new StringBuilder("SELECT u FROM User u WHERE 1=1");
        
        if (firstname != null && !firstname.isEmpty()) {
            queryString.append(" AND u.firstname LIKE :firstname");
        }
        if (age != null) {
            queryString.append(" AND u.age = :age");
        }
        if (gender != null && !gender.isEmpty()) {
            queryString.append(" AND u.gender = :gender");
        }
        if (religion != null && !religion.isEmpty()) {
            queryString.append(" AND u.religion = :religion");
        }
        if (maritalstatus != null && !maritalstatus.isEmpty()) {
            queryString.append(" AND u.maritalstatus = :maritalstatus");
        }
        if (occupation != null && !occupation.isEmpty()) {
            queryString.append(" AND u.occupation = :occupation");
        }

        System.out.println("Query String: " + queryString.toString());

        TypedQuery<User> query = em.createQuery(queryString.toString(), User.class);
        
        if (firstname != null && !firstname.isEmpty()) {
            query.setParameter("firstname", "%" + firstname + "%");
        }
        if (age != null) {
            query.setParameter("age", age);
        }
        if (gender != null && !gender.isEmpty()) {
            query.setParameter("gender", gender);
        }
        if (religion != null && !religion.isEmpty()) {
            query.setParameter("religion", religion);
        }
        if (maritalstatus != null && !maritalstatus.isEmpty()) {
            query.setParameter("maritalstatus", maritalstatus);
        }
        if (occupation != null && !occupation.isEmpty()) {
            query.setParameter("occupation", occupation);
        }

        List<User> results = query.getResultList();
        System.out.println("Results: " + results);

        return results;
    }
    
    @Override
    public void deleteById(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }
    @Override
	public List<User> findAll() {
		String hql = "from User";
		Query query = em.createQuery(hql);
		return query.getResultList();
		
	}
    @Override
    public List<User> findByBlocked(Boolean blocked) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.blocked = :blocked");
        query.setParameter("blocked", blocked);
        return query.getResultList();
    }
    
}
