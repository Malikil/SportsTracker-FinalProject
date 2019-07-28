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
	
	/**
	 * Retrieves matches before current date
	 */
	public List<Match> getMatchesBeforeDate(Date date)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Match> query = (Query<Match>)ss.createQuery("select m from Match m where m.time < :date order by m.time desc", Match.class);
		query.setParameter("date", date);
		List<Match> results = query.list();
		
		return results;
	}
	
	/**
	 * Retrieves matches after current date
	 */
	public List<Match> getMatchesAfterDate(Date date)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Match> query = (Query<Match>)ss.createQuery("select m from Match m where m.time > :date order by m.time asc", Match.class);
		query.setParameter("date", date);
		List<Match> results = query.list();
		
		return results;
	}

	/**
	 * Gets all players from the database
	 * @return Returns an ArrayList with all players in it
	 */
	@Override
	public ArrayList<Player> getAllPlayers() {
		SessionFactory fact = null;
		Session ss = null;
		ArrayList<Player> players = null;
		
		try
		{
			fact = getFactory();
			ss = fact.openSession();
			
			players = (ArrayList<Player>)ss.createQuery("select p from Player p").list();
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
		
		return players;
	}
	
	/**
	 * 
	 * @param name Name of the player you are trying to find
	 * @return returns existing player or null if no player by that name exists
	 * 
	 * TODO Test method
	 */
	public Player getPlayerByName(String name)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Player> query = (Query<Player>)ss.createQuery("select p from Player p where p.firstName||' '||p.lastName = :name", Player.class);
		query.setParameter("name", name);
		List<Player> results = query.list();
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}
	
	/**
	 * 
	 * @param player Creates player by player name
	 * @return returns player id
	 */
	public Integer createPlayer(Player player)
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
			
			nid = (int)ss.save(player);
			
			
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
	
	public Boolean updatePlayer(Player player)
	{
		SessionFactory fact = null;
		Session ss = null;
		Transaction tran = null;
		
		try
		{
			fact = getFactory();
			ss = fact.openSession();
			tran = ss.beginTransaction();
			
			ss.update(player);
			
			tran.commit();
		}
		catch (HibernateException ex)
		{
			tran.rollback();
			return false;
		}
		return true;
	}

	/**
	 * Gets all teams from the database
	 * @return Returns an ArrayList with all teams in it
	 */
	@Override
	public ArrayList<Team> getAllTeams()
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		ArrayList<Team> results = (ArrayList<Team>)ss.createQuery("select t from Team t", Team.class).list();
		// TODO ss.close(); Don't close them here because of lazy fetching. Will this cause a major memory leak?
		// TODO fact.close(); I noticed there was a daemon thread called "abandoned connection cleanup", is it safe to rely on that?
		return results;
		
	}
	
	/**
	 * 
	 * @param teamName Name of the team you are trying to add
	 * @return returns name of existing team or null if no team exists
	 */
	public Team getTeamByName(String teamName)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Team> query = (Query<Team>)ss.createQuery("select t from Team t where t.teamName = :name", Team.class);
		query.setParameter("name", teamName);
		List<Team> results = query.list();
		
		// TODO ss.close();
		// TODO fact.close();
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}
	
	/**
	 * 
	 * @param team Creates team by team name
	 * @return Returns team id
	 */
	public Integer createTeam(Team team)
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
			
			nid = (int)ss.save(team);
			
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
	 * Adds the given match to the database. If adding the match fails for
	 * whatever reason the database is reset to its state before the attempt.
	 * @param match The match to add
	 * @return Returns the id of the match that was added
	 */
	@Override
	public int createMatch(Match match)
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
	
	public Boolean changePassword(String username, String password)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		Transaction tran = null;
		
		try
		{
			User user = getUser(username);
			tran = ss.beginTransaction();
			
			user.setPassword(password);
			ss.update(user);
			tran.commit();
			return true;
		}
		catch(HibernateException e)
		{
			if(tran != null)
			{
				tran.rollback();
			}
			
			return false;
		}
	}
	
	public boolean addFavourite(String username, String team)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		Transaction tran = null;
		
		try
		{
			User user = getUser(username);
			
			tran = ss.beginTransaction();
			
			user.addFavourite(team);
				
			ss.update(user);
			tran.commit();
			return true;
			
		} catch(HibernateException e)
		{
			if(tran != null)
			{
				tran.rollback();
			}
			
			return false;
		}
	}
	public boolean removeFavourite(String username, String team)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		Transaction tran = null;
		
		try
		{
			User user = getUser(username);
			tran = ss.beginTransaction();
			
			user.removeFavourite(team);
				
			ss.update(user);
			tran.commit();
			return true;
		}
		catch(HibernateException e)
		{
			if(tran != null)
			{
				tran.rollback();
			}
			
			return false;
		}
	}

	public Match getMatchByID(int matchid)
	{
		SessionFactory fact = getFactory();
		Session ss = fact.openSession();
		
		Query<Match> query = (Query<Match>)ss.createQuery("select m from Match m where m.id = :id", Match.class);
		query.setParameter("id", matchid);
		List<Match> results = query.list();
		
		// TODO ss.close();
		// TODO fact.close();
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}
}
