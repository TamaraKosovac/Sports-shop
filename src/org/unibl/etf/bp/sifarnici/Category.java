package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Category {
	
	public void addCategory(int idOfCategory, String nameOfTheCategory) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into kategorija_proizvoda_sifarnik values (?, ?)");
			ps.setInt(1, idOfCategory);
			ps.setString(2, nameOfTheCategory);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Kategorija je uspjesno sacuvana");
			} else {
				JOptionPane.showMessageDialog(null, "Kategorija nije sacuvana");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kategorija nije sacuvana");
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
	
	public void deleteCategory(int idOfCategory) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from kategorija_proizvoda_sifarnik where KategorijaID = ?");
	        ps.setInt(1, idOfCategory);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Kategorija je uspjesno obrisana");
			} else {
				JOptionPane.showMessageDialog(null, "Kategorija nije obrisana");
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
	
	public void updateCategory(int idOfCategory, String newNameOfTheCategory) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update kategorija_proizvoda_sifarnik set NazivKategorije = ? where KategorijaID = ?");
	        ps.setString(1, newNameOfTheCategory);
	        ps.setInt(2, idOfCategory);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Kategorija je uspjesno azurirana");
			} else {
				JOptionPane.showMessageDialog(null, "Kategorija nije azurirana");
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
