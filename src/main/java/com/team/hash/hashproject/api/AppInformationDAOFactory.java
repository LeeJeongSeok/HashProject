package com.team.hash.hashproject.api;

public class AppInformationDAOFactory {
	private static AppInformationDAOFactory mAppDAOFactory = null;
	private AppInformationDAO mAppDao = null;
	
	private AppInformationDAOFactory() {
		
	}
	
	public static AppInformationDAOFactory getInstance() {
		if (mAppDAOFactory == null) {
			synchronized (AppInformationDAOFactory.class) {
				if (mAppDAOFactory == null) {
					mAppDAOFactory = new AppInformationDAOFactory();
				}
			}
		}
		return mAppDAOFactory;
	}
	
	public AppInformationDAO getDAO() {
		if (mAppDao != null) {
			return mAppDao;
		}

		try {
			mAppDao = (AppInformationDAO)Class.forName("com.team.hash.hashproject.api.AppInformationSQLDAO").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		return mAppDao;
	}
}
