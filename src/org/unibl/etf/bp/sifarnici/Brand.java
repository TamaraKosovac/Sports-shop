package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Brand {
	public void addBrand(int idOfBrand, String nameOfTheBrand) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into brend_proizvoda_sifarnik values (?, ?)");
			ps.setInt(1, idOfBrand);
			ps.setString(2, nameOfTheBrand);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Brend je uspjesno sacuvan");
			} else {
				JOptionPane.showMessageDialog(null, "Brend nije sacuvan");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Brend nije sacuvan");
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
	
	public void deleteBrand(int idOfBrand) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from brend_proizvoda_sifarnik where BrendID = ?");
	        ps.setInt(1, idOfBrand);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Brend je uspjesno obrisan");
			} else {
				JOptionPane.showMessageDialog(null, "Brend nije obrisan");
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
	
	public void updateBrand(int idOfBrand, String newNameOfTheBrand) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update brend_proizvoda_sifarnik set NazivBrenda = ? where BrendID = ?");
	        ps.setString(1, newNameOfTheBrand);
	        ps.setInt(2, idOfBrand);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Brend je uspjesno azuriran");
			} else {
				JOptionPane.showMessageDialog(null, "Brend nije azuriran");
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
