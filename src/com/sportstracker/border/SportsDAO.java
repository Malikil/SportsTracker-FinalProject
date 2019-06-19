package com.sportstracker.border;

import java.util.ArrayList;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import com.sportstracker.entities.Match;
import com.sportstracker.entities.Player;
import com.sportstracker.entities.Team;

public class SportsDAO implements ISportDatabase
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

}
