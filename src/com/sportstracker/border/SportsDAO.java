package com.sportstracker.border;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import com.sportstracker.entities.*;

public class SportsDAO implements ISportDatabase, IUserDatabase
{
	public static SessionFactory getFactory()
	{
		StandardServiceRegistry ssr = null;
		Metadata md = null;
		SessionFactory sf = null;
		
		// Not using try..catch here because handling errors is generally usage-specific
		// I feel it should be handled in a controller, rather than here in border
		ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		md = new MetadataSources(ssr).getMetadataBuilder().build();
		sf = md.getSessionFactoryBuilder().build();
		
		return sf;
	}
	
	@Override
	public ArrayList<Match> getAllMatches()
	{
		SessionFactory fact = null;
		Session ss = null;
		ArrayList<Match> matches = null;
		
		try
		{
			fact = getFactory();
			ss = fact.openSession();
			
			matches = (ArrayList<Match>)ss.createQuery("select m from Match m").list();
		}
		catch (HibernateException hx)
		{
			// TODO is this intentionally left blank?
		}
		finally
		{
			ss.close();
			fact.close();
		}
		
		return matches;
	}

	@Override
	public ArrayList<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addMatch(Match match)
	{
		SessionFactory fact = null;
		Session ss = null;
		Transaction tran = null;
		int nid;
		
		try
		{
			fact = getFactory();
			ss = fact.openSession();
			tran = ss.beginTransaction();
			
			nid = (int)ss.save(match);
			
			tran.commit();
		}
		catch (HibernateException e)
		{
			tran.rollback();
			throw e;
		}
		finally
		{
			ss.close();
			fact.close();
		}
		
		return nid;
	}

	@Override
	public User getUser(String username)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<User> query = ss.createQuery("FROM User u WHERE u.username = :uname", User.class);
		query.setParameter("uname", username);
		List<User> results = query.list();
		User user;
		if (results.isEmpty())
			user = null;
		else if (results.size() > 1)
			throw new RuntimeException("This should never, ever happen");
		else
			user = results.get(0);
		
		ss.close();
		fact.close();
		
		return user;
	}

	@Override
	public String createAccount(String username, String password)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		Transaction tran = ss.beginTransaction();
		
		String inserted = null;
		
		try
		{
			User account = new User(username, password);
			inserted = (String)ss.save(account);
			tran.commit();
		}
		catch (HibernateException hx)
		{
			tran.rollback();
			throw hx;
		}
		finally
		{
			ss.close();
			fact.close();
		}
		
		return inserted;
	}
}
