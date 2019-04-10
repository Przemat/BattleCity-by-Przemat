package com.project.BattleCity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GameOver extends JPanel {
	Font font = loadFont();


	public GameOver(Window window) {
		// TODO Auto-generated constructor stub

		setBounds(0,0,1024,768);
		setBackground(Color.BLACK);
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GameOver.class.getResource("/com/project/BattleCity/image/game_over.png")));
		label.setBounds(388, 100, 248, 160);
		add(label);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				lblNewGame.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblNewGame.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		lblNewGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewGame.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblNewGame.setForeground(Color.WHITE);
		lblNewGame.setBounds(387, 450, 250, 30);
		add(lblNewGame);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				lblExit.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblExit.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblExit.setForeground(Color.WHITE);
		lblExit.setBounds(447, 550, 130, 30);
		add(lblExit);
	}
	
	public static Font loadFont() {
        Font font = null;
        try {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                                            new File("prstart.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font;
    }
}
