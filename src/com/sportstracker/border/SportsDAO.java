package com.sportstracker.border;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.NotYetImplementedException;

import com.sportstracker.entities.*;

/**
 * The main interaction point with the database. Implements interfaces for both
 * regular match information and username/logging in.
 */
public class SportsDAO implements ISportDatabase, IUserDatabase
{
	/**
	 * Gets a session factory for connecting to the database
	 * @return A SessionFactory
	 */
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
	
	/**
	 * Gets all matches from the database
	 * @return Returns an ArrayList with all matches in it
	 */
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
	
	public List<Match> getMatchesBeforeDate(Date date)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Match> query = (Query<Match>)ss.createQuery("select m from Match m where m.time < :date", Match.class);
		query.setParameter("date", date);
		List<Match> results = query.list();
		
		ss.close();
		fact.close();
		
		return results;
	}
	public List<Match> getMatchesAfterDate(Date date)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Match> query = (Query<Match>)ss.createQuery("select m from Match m where m.time > :date", Match.class);
		query.setParameter("date", date);
		List<Match> results = query.list();
		
		ss.close();
		fact.close();
		
		return results;
	}

	/**
	 * Gets all players from the database
	 * @return Returns an ArrayList with all players in it
	 */
	@Override
	public ArrayList<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets all teams from the database
	 * @return Returns an ArrayList with all teams in it
	 */
	@Override
	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Team getTeamByName(String teamName)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Team> query = (Query<Team>)ss.createQuery("select t from team t where t.teamName = :name", Team.class);
		query.setParameter("name", teamName);
		List<Team> results = query.list();
		
		ss.close();
		fact.close();
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}
	
	public Integer createTeam(Team team)
	{
		// TODO add a team to the database
		throw new NotYetImplementedException("Not yet implemented");
	}

	/**
	 * Adds the given match to the database. If adding the match fails for
	 * whatever reason the database is reset to its state before the attempt.
	 * @param match The match to add
	 * @return Returns the id of the match that was added
	 */
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

	/**
	 * Gets a single user from the database. Throws an exception if more than
	 * one user is found. Considering that username should be the primary key,
	 * duplicates should be impossible.
	 * @param username The username to get
	 * @return The user with the given username, or null if the user doesn't exist
	 */
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

	/**
	 * Creates an account with the given username and password. If the user
	 * already exists the operation will fail. 
	 * @param username The desired username
	 * @param password The desired password
	 * @return The username that got added. Should be the same as the 'username' parameter
	 */
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
