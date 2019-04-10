package com.project.BattleCity;


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	//private Window window;
	private JLabel txtHS;
	int ypos = 400;
	int stop_y = 70;
	private JLabel txtStart;
	private JLabel txtExit;
	private JLabel txtCreate;
	private JLabel batc;
	private Scanner hS_file;
	
	
	/**
	 * Create the panel.
	 * @throws FileNotFoundException 
	 */
	public Menu(Window window) throws FileNotFoundException {
		Font font = loadFont();
		int HS = 0;	
		try{
			hS_file = new Scanner(new File("HS.txt"));
			HS = Integer.valueOf(hS_file.nextLine());
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		setBounds(window.getGamePanel().getBounds());
		setLayout(null);
		
		txtHS = new JLabel();
		txtHS.setHorizontalAlignment(SwingConstants.CENTER);
		txtHS.setForeground(Color.WHITE);
		txtHS.setBackground(Color.BLACK);
		//najwyzszy wynik z pliku
		txtHS.setText("Highscore : "+ HS);
		txtHS.setFont(new Font(font.getName(), Font.PLAIN, 20));
		txtHS.setBounds(312, 70, 400, 40);
		add(txtHS);
		
		
		batc = new JLabel();
		batc.setHorizontalAlignment(SwingConstants.CENTER);
		batc.setIcon(new ImageIcon(Menu.class.getResource("/com/project/BattleCity/image/battle_city.png")));
		batc.setBounds(302, 150, 420, 200);
		add(batc);
		
		txtStart = new JLabel();
		txtStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtStart.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				txtStart.setForeground(Color.WHITE);
			}
			public void mouseClicked(MouseEvent arg0){
				Game game = null;
				try {
					game = new Game(window);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.getGamePanel().getContentPane().removeAll();
				window.getGamePanel().getContentPane().add(game);
				window.getGamePanel().setVisible(true);
				
			}
		});
		txtStart.setHorizontalAlignment(SwingConstants.CENTER);
		txtStart.setForeground(Color.WHITE);
		txtStart.setBackground(Color.BLACK);
		txtStart.setFont(new Font(font.getName(), Font.PLAIN, 26));
		txtStart.setText("Start");
		txtStart.setBounds(437, 470, 150, 35);
		add(txtStart);
		
		
		txtExit = new JLabel();
		txtExit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				txtExit.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				txtExit.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		txtExit.setText("Exit");
		txtExit.setHorizontalAlignment(SwingConstants.CENTER);
		txtExit.setForeground(Color.WHITE);
		txtExit.setFont(new Font(font.getName(), Font.PLAIN, 26));
		txtExit.setBackground(Color.BLACK);
		txtExit.setBounds(437, 570, 150, 35);
		add(txtExit);
		
		txtCreate = new JLabel();
		txtCreate.setText("Created by Przemyslaw Materka");
		txtCreate.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreate.setForeground(Color.WHITE);
		txtCreate.setFont(new Font(font.getName(), Font.PLAIN, 16));
		txtCreate.setBackground(Color.BLACK);
		txtCreate.setBounds(262, 670, 500, 40);
		add(txtCreate);
		loadmenu();

	}
	private void loadmenu(){
	ActionListener timer = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtHS.setBounds(txtHS.getBounds().x, 70, txtHS.getBounds().width, txtHS.getBounds().height);
					batc.setBounds(batc.getBounds().x,150, batc.getBounds().width, batc.getBounds().height);
					txtStart.setBounds(txtStart.getBounds().x, 470, txtStart.getBounds().width, txtStart.getBounds().height);
					txtExit.setBounds(txtExit.getBounds().x, 570, txtExit.getBounds().width, txtExit.getBounds().height);
					txtCreate.setBounds(txtCreate.getBounds().x, 770, txtCreate.getBounds().width, txtCreate.getBounds().height);
					((Timer)evt.getSource()).stop();
				}
			});
					
			if (ypos==stop_y){
				((Timer)evt.getSource()).stop();
			}
			txtHS.setBounds(txtHS.getBounds().x, ypos, txtHS.getBounds().width, txtHS.getBounds().height);
			batc.setBounds(batc.getBounds().x, ypos+80, batc.getBounds().width, batc.getBounds().height);
			txtStart.setBounds(txtStart.getBounds().x, ypos+400, txtStart.getBounds().width, txtStart.getBounds().height);
			txtExit.setBounds(txtExit.getBounds().x, ypos+500, txtExit.getBounds().width, txtExit.getBounds().height);
			txtCreate.setBounds(txtCreate.getBounds().x, ypos+700, txtCreate.getBounds().width, txtCreate.getBounds().height);
			ypos--;
			
		}
	};
	new Timer(1, timer).start();
	}
	  public static Font loadFont() {
	        Font font = null;
	        try {
	            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
	                                            new File("prstart.ttf"));
	            GraphicsEnvironment ge
	                                = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(font);

	        } catch (FontFormatException | IOException ex) {
	            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return font;
	    }
}

