package org.unibl.etf.bp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class NextExit extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NextExit dialog = new NextExit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	 public NextExit() {
		 setBounds(100, 100, 300, 150);  
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("Zatvori aplikaciju?");
	        lblNewLabel.setBounds(99, 40, 106, 13);
	        contentPanel.add(lblNewLabel);
	        
	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER)); 
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);
	        
	        JButton okButton = new JButton("Da");
	        okButton.setActionCommand("Da");
	        okButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });
	        buttonPane.add(okButton);
	        getRootPane().setDefaultButton(okButton);
	        
	        JButton cancelButton = new JButton("Ne");
	        cancelButton.setActionCommand("Ne");
	        cancelButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        buttonPane.add(cancelButton);
	}
}
