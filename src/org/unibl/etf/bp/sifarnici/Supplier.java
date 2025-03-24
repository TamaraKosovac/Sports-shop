package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Supplier {

		public void addSupplier(int idOfSupplier, String nameOfTheSupplier) {
			Connection c = null;
			PreparedStatement ps = null;
			try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
				ps = c.prepareStatement("insert into dobavljac_sifarnik values (?, ?)");
				ps.setInt(1, idOfSupplier);
				ps.setString(2, nameOfTheSupplier);
				int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Dobavljac je uspjesno sacuvan");
				} else {
					JOptionPane.showMessageDialog(null, "Dobavljac nije sacuvan");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Dobavljac nije sacuvan");
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
		
		public void deleteSupplier(int idOfSupplier) {
			Connection c = null;
		    PreparedStatement ps = null;
		    try {
		        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		        ps = c.prepareStatement("delete from dobavljac_sifarnik where DobavljacID = ?");
		        ps.setInt(1, idOfSupplier);
		        int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Dobavljac je uspjesno obrisan");
				} else {
					JOptionPane.showMessageDialog(null, "Dobavljac nije obrisan");
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
		
		public void updateSupplier(int idOfSupplier, String newNameOfTheSupplier) {
		    Connection c = null;
		    PreparedStatement ps = null;
		    try {
		        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		        ps = c.prepareStatement("update dobavljac_sifarnik set NazivDobavljaca = ? where DobavljacID = ?");
		        ps.setString(1, newNameOfTheSupplier);
		        ps.setInt(2, idOfSupplier);
		        int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Dobavljac je uspjesno azuriran");
				} else {
					JOptionPane.showMessageDialog(null, "Dobavljac nije azuriran");
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
