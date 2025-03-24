package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Color {

	public void addColor(int idOfColor, String nameOfTheColor) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into boja_proizvoda_sifarnik values (?, ?)");
			ps.setInt(1, idOfColor);
			ps.setString(2, nameOfTheColor);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Boja je uspjesno sacuvana");
			} else {
				JOptionPane.showMessageDialog(null, "Boja nije sacuvana");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Boja nije sacuvana");
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
	
	public void deleteColor(int idOfColor) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from boja_proizvoda_sifarnik where BojaID = ?");
	        ps.setInt(1, idOfColor);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Boja je uspjesno obrisana");
			} else {
				JOptionPane.showMessageDialog(null, "Boja nije obrisana");
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
	
	public void updateColor(int idOfColor, String newNameOfColor) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update boja_proizvoda_sifarnik set NazivBoje = ? where BojaID = ?");
	        ps.setString(1, newNameOfColor);
	        ps.setInt(2, idOfColor);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Boja je uspjesno azurirana");
			} else {
				JOptionPane.showMessageDialog(null, "Boja nije azurirana");
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
