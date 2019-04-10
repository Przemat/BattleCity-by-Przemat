package com.project.BattleCity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class HighScore extends JPanel {
protected static final PrintWriter HS_zapis = null;
Font font = loadFont();

	/**
	 * Create the panel.
	 * @throws FileNotFoundException 
	 */
	public HighScore(Window window, int[] HSt, int level) {
		
		setBounds(0,0,1024,768);
		setBackground(Color.BLACK);
		setLayout(null);
		Scanner HS_file = null;
		try {
			HS_file = new Scanner(new File("HS.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long HS = Long.valueOf(HS_file.nextLine());
		
		
		
		JLabel HSN = new JLabel("0");
		JLabel HSS = new JLabel("0");
		JLabel HSP = new JLabel("0");
		JLabel HSH = new JLabel("0");
		JLabel lblSN = new JLabel("0");
		JLabel lblSS = new JLabel("0");
		JLabel lblSP = new JLabel("0");
		JLabel lblSH = new JLabel("0");
		JLabel lblHighscore = new JLabel("");
		JLabel lblTQ = new JLabel("0");
		JLabel lblTS = new JLabel("0");
	
		
		
		lblHighscore.setText("Highscore: "+HS);
		lblHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscore.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblHighscore.setForeground(Color.WHITE);
		lblHighscore.setBounds(262, 60, 500, 30);
		add(lblHighscore);
		
		JLabel lblLevel = new JLabel("Level "+(level+1));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBounds(412, 121, 200, 30);
		add(lblLevel);
		
		JLabel lblNormal = new JLabel("");
		lblNormal.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/tank_NORMAL_up_1.png")));
		lblNormal.setBounds(150, 250, 64, 64);
		add(lblNormal);
		
		JLabel lblSpeed = new JLabel("");
		lblSpeed.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/tank_SPEED_up_1.png")));
		lblSpeed.setBounds(150, 350, 64, 64);
		add(lblSpeed);
		
		JLabel lblPower = new JLabel("");
		lblPower.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/tank_POWER_up_1.png")));
		lblPower.setBounds(150, 450, 64, 64);
		add(lblPower);
		
		JLabel lblHeavy = new JLabel("");
		lblHeavy.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/tank_HEAVY_up_1.png")));
		lblHeavy.setBounds(150, 550, 64, 64);
		add(lblHeavy);
		
		JLabel lblArrow = new JLabel("");
		lblArrow.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/arrow.png")));
		lblArrow.setBounds(250, 268, 28, 28);
		add(lblArrow);
		
		JLabel lblArrow2 = new JLabel("");
		lblArrow2.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/arrow.png")));
		lblArrow2.setBounds(250, 368, 28, 28);
		add(lblArrow2);
		
		JLabel lblArrow3 = new JLabel("");
		lblArrow3.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/arrow.png")));
		lblArrow3.setBounds(250, 468, 28, 28);
		add(lblArrow3);
		
		JLabel lblArrow4 = new JLabel("");
		lblArrow4.setIcon(new ImageIcon(HighScore.class.getResource("/com/project/BattleCity/image/arrow.png")));
		lblArrow4.setBounds(250, 568, 28, 28);
		add(lblArrow4);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setHorizontalAlignment(SwingConstants.CENTER);
		lblQty.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblQty.setForeground(Color.WHITE);
		lblQty.setBounds(250, 200, 250, 30);
		add(lblQty);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblScore.setBounds(750, 200, 150, 30);
		add(lblScore);
		
		
		HSN.setHorizontalAlignment(SwingConstants.CENTER);
		HSN.setForeground(Color.WHITE);
		HSN.setFont(new Font(font.getName(), Font.PLAIN, 26));
		HSN.setBounds(350, 257, 100, 50);
		add(HSN);
		
		
		HSS.setHorizontalAlignment(SwingConstants.CENTER);
		HSS.setForeground(Color.WHITE);
		HSS.setFont(new Font(font.getName(), Font.PLAIN, 26));
		HSS.setBounds(350, 357, 100, 50);
		add(HSS);
		
	
		HSP.setHorizontalAlignment(SwingConstants.CENTER);
		HSP.setForeground(Color.WHITE);
		HSP.setFont(new Font(font.getName(), Font.PLAIN, 26));
		HSP.setBounds(350, 457, 100, 50);
		add(HSP);
		
		
		HSH.setHorizontalAlignment(SwingConstants.CENTER);
		HSH.setForeground(Color.WHITE);
		HSH.setFont(new Font(font.getName(), Font.PLAIN, 26));
		HSH.setBounds(350, 557, 100, 50);
		add(HSH);
		
		JLabel lblXN = new JLabel("x100");
		lblXN.setHorizontalAlignment(SwingConstants.CENTER);
		lblXN.setForeground(Color.WHITE);
		lblXN.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblXN.setBounds(550, 257, 150, 50);
		add(lblXN);
		
		JLabel lblXS = new JLabel("x200");
		lblXS.setHorizontalAlignment(SwingConstants.CENTER);
		lblXS.setForeground(Color.WHITE);
		lblXS.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblXS.setBounds(550, 357, 150, 50);
		add(lblXS);
		
		JLabel lblXP = new JLabel("x300");
		lblXP.setHorizontalAlignment(SwingConstants.CENTER);
		lblXP.setForeground(Color.WHITE);
		lblXP.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblXP.setBounds(550, 457, 150, 50);
		add(lblXP);
		
		JLabel lblXH = new JLabel("x300");
		lblXH.setHorizontalAlignment(SwingConstants.CENTER);
		lblXH.setForeground(Color.WHITE);
		lblXH.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblXH.setBounds(550, 557, 150, 50);
		add(lblXH);
		
	
		lblSN.setHorizontalAlignment(SwingConstants.CENTER);
		lblSN.setForeground(Color.WHITE);
		lblSN.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblSN.setBounds(750, 257, 200, 50);
		add(lblSN);
		
	
		lblSS.setHorizontalAlignment(SwingConstants.CENTER);
		lblSS.setForeground(Color.WHITE);
		lblSS.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblSS.setBounds(750, 357, 200, 50);
		add(lblSS);
		

		lblSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblSP.setForeground(Color.WHITE);
		lblSP.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblSP.setBounds(750, 457, 200, 50);
		add(lblSP);
		
	
		lblSH.setHorizontalAlignment(SwingConstants.CENTER);
		lblSH.setForeground(Color.WHITE);
		lblSH.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblSH.setBounds(750, 557, 200, 50);
		add(lblSH);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblTotal.setBounds(130, 650, 200, 50);
		add(lblTotal);
		
		
		lblTQ.setHorizontalAlignment(SwingConstants.CENTER);
		lblTQ.setForeground(Color.WHITE);
		lblTQ.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblTQ.setBounds(300, 650, 200, 50);
		add(lblTQ);
		
		
		lblTS.setHorizontalAlignment(SwingConstants.CENTER);
		lblTS.setForeground(Color.WHITE);
		lblTS.setFont(new Font(font.getName(), Font.PLAIN, 26));
		lblTS.setBounds(750, 650, 200, 50);
		add(lblTS);
		
		
		ActionListener HS_point = new ActionListener() {
			Long HSn = HS;
			int i = 0;
			int h = 0;
			public void actionPerformed(ActionEvent e) {
				
				if(i<HSt[h]){
					if(h==0){
					HSN.setText(Long.toString(Long.parseLong(HSN.getText())+1));
					lblSN.setText(Long.toString(Long.parseLong(HSN.getText())*100));
					}
					if(h==1){
						HSS.setText(Long.toString(Long.parseLong(HSS.getText())+1));
						lblSS.setText(Long.toString(Long.parseLong(HSS.getText())*200));
						}
					if(h==2){
						HSP.setText(Long.toString(Long.parseLong(HSP.getText())+1));
						lblSP.setText(Long.toString(Long.parseLong(HSP.getText())*300));
						}
					if(h==3){
						HSH.setText(Long.toString(Long.parseLong(HSH.getText())+1));
						lblSH.setText(Long.toString(Long.parseLong(HSH.getText())*300));
						}
					i++;
				lblTQ.setText(Long.toString(Long.parseLong(HSN.getText())+Long.parseLong(HSS.getText())+Long.parseLong(HSP.getText())+Long.parseLong(HSH.getText())));
				lblTS.setText(Long.toString(Long.parseLong(HSN.getText())*100+Long.parseLong(HSS.getText())*200+Long.parseLong(HSP.getText())*300+Long.parseLong(HSH.getText())*300));
				if(Long.parseLong(lblTS.getText())>HS){
					lblHighscore.setText("Highscore: "+lblTS.getText());
				}
				}else {
					h++;
					i = 0;
				}
				if(h>3){
					FileWriter filewriter = null;
					try {
						filewriter = new FileWriter("HS.txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BufferedWriter bufferedWriter = new BufferedWriter(filewriter);						
					if(HSn<Long.parseLong(lblTS.getText())){
						try{
							bufferedWriter.write(lblTS.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally{
							try {
								bufferedWriter.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}else{
						try{
							bufferedWriter.write(Long.toString(HSn));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally{
							try {
								bufferedWriter.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					conbtn(window);
					((Timer)e.getSource()).stop();
					
					
				}
				
			}
		};
		new Timer(400, HS_point).start();
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
private void conbtn(Window window){
	JLabel lblContinue = new JLabel("Continue");
			lblContinue.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent arg0) {
			lblContinue.setForeground(Color.GRAY);
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			lblContinue.setForeground(Color.WHITE);
		}
		public void mouseClicked(MouseEvent arg0){
			GameOver gameover = null;
			gameover = new GameOver(window);
			window.getGamePanel().getContentPane().removeAll();
			window.getGamePanel().getContentPane().add(gameover);
			window.getGamePanel().setVisible(true);
			
		}
	});
			lblContinue.setHorizontalAlignment(SwingConstants.CENTER);
			lblContinue.setForeground(Color.WHITE);
			lblContinue.setFont(new Font(font.getName(), Font.PLAIN, 26));
			lblContinue.setBounds(387, 700, 250, 50);
			add(lblContinue);
}
}
