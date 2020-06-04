package model.dao;

import db.DB;
import model.dao.impl.VisitorDaoJDBC;

public class DaoFactory {
	
	public static VisitorDao createVisitorDao() {
		return new VisitorDaoJDBC(DB.getConnection());
	}

}
