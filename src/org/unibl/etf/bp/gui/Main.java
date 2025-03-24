package org.unibl.etf.bp.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("SportWear informacioni sistem");
		frame.setBounds(150, 20, 20, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Aplikacija");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Izlaz");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextExit.main(null);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		JMenu fileMenu1 = new JMenu("Sifarnici");
		menuBar.add(fileMenu1);
		JMenuItem description = new JMenuItem("Opis");
		description.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextDescription.main(null);
			}
		});
		fileMenu1.add(description);
		JMenuItem color = new JMenuItem("Boja");
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextColor.main(null);
			}
		});
		fileMenu1.add(color);
		JMenuItem brand = new JMenuItem("Brend");
		brand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextBrand.main(null);
			}
		});
		fileMenu1.add(brand);
		JMenuItem size = new JMenuItem("Velicina");
		size.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextSize.main(null);
			}
		});
		fileMenu1.add(size);
		JMenuItem category = new JMenuItem("Kategorija");
		category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextCategory.main(null);
			}
		});
		fileMenu1.add(category);
		JMenuItem worker = new JMenuItem("Radnik");
		worker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextWorker.main(null);
			}
		});
		fileMenu1.add(worker);
		JMenuItem task = new JMenuItem("Zadaci");
		task.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextTask.main(null);
			}
		});
		fileMenu1.add(task);
		JMenuItem supplier = new JMenuItem("Dobavljac");
		supplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextSupplier.main(null);
			}
		});
		fileMenu1.add(supplier);
		
		JMenu mnNewMenu_1 = new JMenu("Radnje");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Pogledaj");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowWorkPlace.main(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Dodaj");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddWorkPlace.main(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Azuriraj");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateWorkPlace.main(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Obrisi");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteWorkPlace.main(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		JMenu fileMenu2 = new JMenu("Upravljanje resursima");
		menuBar.add(fileMenu2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Upravljanje proizvodima");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductManagement.main(null);
			}
		});
		fileMenu2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Upravljanje radnicima");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerManagement.main(null);
			}
		});
		fileMenu2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Upravljanje dobavljacima");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuppliersManagement.main(null);
			}
		});
		fileMenu2.add(mntmNewMenuItem_7);
	}
}
