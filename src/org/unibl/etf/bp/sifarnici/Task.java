package org.unibl.etf.bp.sifarnici;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Task {
		
		public void addTask(int idOfTask, String nameOfTheTask) {
			Connection c = null;
			PreparedStatement ps = null;
			try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
				ps = c.prepareStatement("insert into zadatak_sifarnik values (?, ?)");
				ps.setInt(1, idOfTask);
				ps.setString(2, nameOfTheTask);
				int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Zadatak je uspjesno sacuvan");
				} else {
					JOptionPane.showMessageDialog(null, "Zadatak nije sacuvan");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Zadatak nije sacuvan");
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
		
		public void deleteTask(int idOfTask) {
			Connection c = null;
		    PreparedStatement ps = null;
		    try {
		        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		        ps = c.prepareStatement("delete from zadatak_sifarnik where ZadatakID = ?");
		        ps.setInt(1, idOfTask);
		        int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Zadatak je uspjesno obrisan");
				} else {
					JOptionPane.showMessageDialog(null, "Zadatak nije obrisan");
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
		
		public void updateTask(int idOfTask, String newNameOfTheTask) {
		    Connection c = null;
		    PreparedStatement ps = null;
		    try {
		        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		        ps = c.prepareStatement("update zadatak_sifarnik set Opis = ? where ZadatakID = ?");
		        ps.setString(1, newNameOfTheTask);
		        ps.setInt(2, idOfTask);
		        int tmp = ps.executeUpdate();
				if(tmp==1) {
					JOptionPane.showMessageDialog(null, "Zadatak je uspjesno azuriran");
				} else {
					JOptionPane.showMessageDialog(null, "Zadatak nije azuriran");
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
