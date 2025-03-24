package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Size {

	public void addSize(int idOfSize, String nameOfTheSize) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into velicina_proizvoda_sifarnik values (?, ?)");
			ps.setInt(1, idOfSize);
			ps.setString(2, nameOfTheSize);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Velicina je uspjesno sacuvana");
			} else {
				JOptionPane.showMessageDialog(null, "Velicina nije sacuvana");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Velicina nije sacuvana");
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
	
	public void deleteSize(int idOfSize) {
		Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("delete from velicina_proizvoda_sifarnik where VelicinaID = ?");
	        ps.setInt(1, idOfSize);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Velicina je uspjesno obrisana");
			} else {
				JOptionPane.showMessageDialog(null, "Velicina nije obrisana");
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
	
	public void updateSize(int idOfSize, String newNameOfTheSize) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update velicina_proizvoda_sifarnik set NazivVelicine = ? where VelicinaID = ?");
	        ps.setString(1, newNameOfTheSize);
	        ps.setInt(2, idOfSize);
	        int tmp = ps.executeUpdate();
	        if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Velicina je uspjesno azurirana");
			} else {
				JOptionPane.showMessageDialog(null, "Velicina nije azurirana");
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
