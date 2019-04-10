package com.project.BattleCity;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;

public class Window {

	private JFrame frmBattlecity;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		frmBattlecity.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattlecity = new JFrame();
		frmBattlecity.getContentPane().setBackground(Color.BLACK);
		frmBattlecity.setBackground(Color.BLACK);
		frmBattlecity.setTitle("BattleCity");
		frmBattlecity.setBounds(100, 100, 1088, 872);
		frmBattlecity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattlecity.setResizable(false);
		frmBattlecity.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/com/project/BattleCity/image/tank_player_up_1.png")));
	}

//Zwrot okna
	public JFrame getGamePanel() {
		// TODO Auto-generated method stub
		return frmBattlecity;
	}

}
