package ui;

import data.dal.IUserDAO;

public class UI {
	
	//region Fields
	
	IUserDAO db;
	
	//endregion
	
	//region Constructor
	
	public UI(IUserDAO db)
	{
		this.db = db;
	}
	
	//endregion
	
	
}
