package com.sportstracker.border;

import java.util.List;
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
	public List<Match> getAllMatches()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> getAllTeams() {
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
