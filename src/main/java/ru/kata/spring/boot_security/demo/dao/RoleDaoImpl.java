package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String roleName) {
        TypedQuery<Role> query = entityManager.createQuery(
                "FROM Role r WHERE r.roleName = ?1", Role.class).setParameter(1, roleName);
        return query.getSingleResult();
    }

    public Set<Role> getRolesSet(String[] roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String role : roleNames) {
            roles.add(this.getRoleByName(role));
        }
        return roles;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }
}
