package com.cooksys.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cooksys.entity.UserAccount;

@Repository
public class UserAccountRepository {
	
	private EntityManager entityManager;
	
	public UserAccountRepository()
	{
		
	}
	
	public UserAccountRepository(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	public List<UserAccount> getAllUsers()
	{
		return entityManager.createQuery("FROM UserAccount",UserAccount.class).getResultList();
	}
	
	@Transactional
	public UserAccount create(UserAccount user)
	{
		entityManager.persist(user);
		return user;
	}
	
	@Transactional
	public UserAccount update(UserAccount user)
	{
		UserAccount result = entityManager.merge(user);
		return user;
	}

}
