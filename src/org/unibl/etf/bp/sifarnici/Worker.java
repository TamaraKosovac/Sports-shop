package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Worker {
	
	public void addWorker(int idOfWorker, String jmbgOfTheWorker) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into radnik_sifarnik values (?, ?)");
			ps.setInt(1, idOfWorker);
			ps.setString(2, jmbgOfTheWorker);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Radnik je uspjesno sacuvan");
			} else {
				JOptionPane.showMessageDialog(null, "Radnik nije sacuvan");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Radnik nije sacuvan");
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void deleteWorker(int idOfWorker) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from radnik_sifarnik where RadnikID = ?");
	        ps.setInt(1, idOfWorker);
	        int tmp = ps.executeUpdate();
	        if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Radnik je uspjesno obrisan");
			} else {
				JOptionPane.showMessageDialog(null, "Radnik nije obrisan");
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null)
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        if (c != null)
	            try {
	                c.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	    }
	}
	
	public void updateWorker(int idOfWorker, String newNameOfTheWorker) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update radnik_sifarnik set RadnikJMBG = ? where RadnikID = ?");
	        ps.setString(1, newNameOfTheWorker);
	        ps.setInt(2, idOfWorker);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Radnik je uspjesno azuriran");
			} else {
				JOptionPane.showMessageDialog(null, "Radnik nije azuriran");
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null)
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        if (c != null)
	            try {
	                c.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	    }
	}
}
