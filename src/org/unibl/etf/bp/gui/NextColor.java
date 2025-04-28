package org.unibl.etf.bp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.sifarnici.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class NextColor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTable table;
	private Color color = new Color();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NextColor frame = new NextColor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NextColor() {
		setTitle("Upravljanje bojama");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 257, 728, 267);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Boja ID", "Naziv boje"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		JLabel lblNewLabel = new JLabel("Boja ID");
		lblNewLabel.setBounds(164, 48, 63, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Naziv boje");
		lblNewLabel_1.setBounds(164, 88, 63, 13);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextField();
		textId.setBounds(237, 45, 96, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(237, 85, 96, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				color.addColor(Integer.parseInt(id), name);
				textId.setText("");
				textName.setText("");
				model.setRowCount(0);
				showColors();
			}
		});
		btnNewButton.setBounds(107, 182, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Azuriraj");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				color.updateColor(Integer.parseInt(id), name);
				textId.setText("");
				textName.setText("");
				model.setRowCount(0);
				showColors();
			}
		});
		btnNewButton_2.setBounds(339, 182, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Obrisi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				color.deleteColor(Integer.parseInt(id));
				textId.setText("");
				model.setRowCount(0);
				showColors();
			}
		});
		btnNewButton_3.setBounds(581, 182, 85, 21);
		contentPane.add(btnNewButton_3);
		showColors();
	}
	
	
	public void showColors() {
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			s = c.createStatement();
			rs = s.executeQuery("select * from boja_proizvoda_sifarnik");
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				defaultTableModel.addRow(v);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (s != null)
				try {
					s.close();
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
