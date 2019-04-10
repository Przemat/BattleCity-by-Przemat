package com.project.BattleCity;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.io.FileNotFoundException;

public class Start {

	public static void main(String[] args) {
		
	
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 java.awt.EventQueue.invokeLater(new Runnable() {

				public void run() {
					//tworzy okno
	                Window window = new Window();
	                EventQueue.invokeLater(new Runnable() {
	        			public void run() {
	        				try {
	        					
	        					window.getGamePanel().setVisible(true);
	        				} catch (Exception e) {
	        					e.printStackTrace();
	        				}
	        			}
	        		});
	                Menu menu = null;
					try {
						menu = new Menu(window);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//daje okno skrytowi Menu
	                window.getGamePanel().getContentPane().add(menu);
	                window.getGamePanel().setVisible(true);

	            }
	        });
	}

}
