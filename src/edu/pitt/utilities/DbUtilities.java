package edu.pitt.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public interface DbUtilities {

	public void createDbConnection();
	
	public ResultSet getResultSet(String sql) throws SQLException;
	
	public boolean executeQuery(String sql);
	
	public void closeDbConnection();
	
    public DefaultTableModel getDataTable(String sql) throws SQLException;
    
    public DefaultTableModel getDataTable(String sqlQuery, String[] customColumnNames) throws SQLException;
}
