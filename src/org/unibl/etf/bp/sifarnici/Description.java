package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Description {
	
	public void addDescription(int idOfDescription, String nameOfTheDescription) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into opis_proizvoda_sifarnik values (?, ?)");
			ps.setInt(1, idOfDescription);
			ps.setString(2, nameOfTheDescription);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Opis je uspjesno sacuvan");
			} else {
				JOptionPane.showMessageDialog(null, "Opis nije sacuvan");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Opis nije sacuvan");
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
	
	public void deleteDescription(int idOfDescription) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from opis_proizvoda_sifarnik where OpisID = ?");
	        ps.setInt(1, idOfDescription);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Opis je uspjesno obrisan");
			} else {
				JOptionPane.showMessageDialog(null, "Opis nije obrisan");
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
	
	public void updateDescription(int idOfDescription, String newDescription) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update opis_proizvoda_sifarnik set Opis = ? where OpisID = ?");
	        ps.setString(1, newDescription);
	        ps.setInt(2, idOfDescription);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Opis je uspjesno azuriran");
			} else {
				JOptionPane.showMessageDialog(null, "Opis nije azuriran");
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
