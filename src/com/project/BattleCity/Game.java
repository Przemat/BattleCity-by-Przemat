package com.project.BattleCity;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class Game extends JPanel {
	Window window;
	private int live = 3;
	private int con = 3;
	private int level = 0;
	private int[] HS = { 0, 0, 0, 0 };
	private JPanel panel;
	private JLabel label_1;
	private JLabel label_3;
	JLabel[][] lblqty = new JLabel[9][5];

	@Override
	public void setBounds(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		super.setBounds(x, y, width, height);
	}

	/**
	 * Create the panel.
	 * 
	 * @throws FileNotFoundException
	 */
	public Game(Window window2) throws FileNotFoundException {
		window = window2;
		setBounds(0, 0, 1024, 768);
		setBackground(Color.BLACK);
		setLayout(null);
		// wartswy
		JLayeredPane first_plane = new JLayeredPane();
		first_plane.setBounds(0, 0, 832, 832);
		add(first_plane);

		JLayeredPane second_plane = new JLayeredPane();
		second_plane.setBounds(0, 0, 832, 832);
		add(second_plane);

		JLayeredPane background = new JLayeredPane();
		background.setBounds(0, 0, 832, 832);
		add(background);

		Start(background, second_plane, first_plane);

	}

	private void Start(JLayeredPane background, JLayeredPane second_plane, JLayeredPane first_plane)
			throws FileNotFoundException {
		GUI(true, con, live, 35);
		Player player = new Player();
		Draw draw = new Draw();
		Rules rules = new Rules(live, con, level, HS);
		Colision colision = new Colision();
		Random random = new Random();

		// mapa
		draw.loadmap(Integer.toString(rules.getlevel()));
		draw.drawmap(second_plane, background);
		rules.LoadTank();
		rules.setbase(true);
		// wrogowie
		for (int i = 0; i < 3; i++) {

			rules.enemy[i] = new Enemy(Tank.NORMAL);
			rules.enemy[i].spawn(background, i);

		}
		// gracz
		player.spawn(background);
		colision.Colision_update(draw.jtab, player.player_t, rules.enemy, rules.bullet, draw.map, rules.bonus);

		ActionListener frame = new ActionListener() {
			int bonustimeck = 0;
			int bonustime = random.nextInt(3600);
			int[] shottime = { 0, 0, 0, 0, 0 };
			Direction dir = Direction.UP;
			int spawnd = 0;
			public void actionPerformed(ActionEvent e) {

				// zmiany mapy
				draw.drawwater(background);
				colision.Colision_update(draw.jtab, player.player_t, rules.enemy, rules.bullet, draw.map, rules.bonus);
				GUI(false, rules.getCon(), rules.getlive(), rules.getTankqty());
				// bonusy
				if (rules.bonus == null) {

					if (bonustimeck > bonustime) {
						rules.spawnbonus(second_plane, random.nextInt(768), random.nextInt(352), random.nextInt(6));
						bonustime = random.nextInt(3600);
						bonustimeck = 0;
					} else
						bonustimeck++;
				}
				if (player.player_t != null)
					if (colision.Bonus_Colision(player.player_t.getBounds())) {
						switch (rules.bonus.getName()) {
						case "SPEED":
							player.speed();
							break;
						case "SHIELD":
							player.shield(second_plane);
							break;
						case "LIVE":
							rules.setlive(rules.getlive() + 1);
							break;
						case "BOMB":
							for (int i = 0; i < 5; i++) {
								if (rules.enemy[i] != null) {
									rules.setHS(rules.enemy[i].getTank().ordinal());
									rules.enemy[i].Destroy(second_plane);
									background.remove(rules.enemy[i].enemy);
									rules.enemy[i] = null;
								}
							}
							break;
						case "BASE":
							draw.basebonus(background);
							break;
						case "CLOCK":
							for (int j = 0; j < 5; j++) {
								if (rules.enemy[j] != null)
									rules.enemy[j].clock();
							}
							break;
						default:
							break;
						}
						second_plane.remove(rules.bonus);
						rules.bonus = null;
					}

				// zmiany gracza
				if (player.player_t != null) {
					if (dir != player.getdir()) {
						player.movecor();
					}
					if (colision.Colision_Tank(0, player.getdx(), player.getdy()) && player.delta == true) {
						player.move();
					}
					
					dir = player.getdir();
				} else {
					if(spawnd <= 0){
					spawnd = 10;
					player.spawn(background);
					player.shield(second_plane);
					}
					spawnd--;
				}
				draw.drawTank(player.player_t, player.getImage(), player.getx(), player.gety(), player.getw(),
						player.geth());
				colision.Colision_update(draw.jtab, player.player_t, rules.enemy, rules.bullet, draw.map, rules.bonus);

				// zmiany przciwników
				for (int i = 0; i < 5; i++) {
					if (rules.enemy[i] != null) {
						rules.enemy[i].checkmove();
						if (colision.Colision_Tank(i + 1, rules.enemy[i].getdx(), rules.enemy[i].getdy())) {
							rules.enemy[i].move();
						} else
							rules.enemy[i].changedir();
						draw.drawTank(rules.enemy[i].enemy, rules.enemy[i].getImage(), rules.enemy[i].getx(),
								rules.enemy[i].gety(), rules.enemy[i].getw(), rules.enemy[i].geth());
					}
					if (rules.enemy[i] == null) {
						int place = random.nextInt(3) + 1;
						if (colision.Spawn(place)) {
							Tank tank = rules.getTank();
							if (tank != null) {
								rules.enemy[i] = new Enemy(tank);
								rules.enemy[i].newspawn(background, second_plane, place - 1);
							}
						}
					}
				}

				// zmiany pocisków
				// pocisk gracza
				if (rules.bullet[0] != null && colision.Colision_Bullet(0).size() == 0) {
					rules.bullet[0].move();
				}
				if (player.isFire() && rules.bullet[0] == null) {
					rules.bullet[0] = new Shot(player.player_t.getBounds(), player.getdir(), background);
					colision.Colision_update(draw.jtab, player.player_t, rules.enemy, rules.bullet, draw.map,
							rules.bonus);
				}

				// pociski przeciwników
				for (int i = 1; i < 6; i++) {
					if (rules.bullet[i] != null && colision.Colision_Bullet(i).size() == 0) {
						rules.bullet[i].move();
					}
					if (rules.enemy[i - 1] != null) {
						if (rules.bullet[i] == null && shottime[i - 1] > rules.enemy[i - 1].getshottime()
								&& rules.enemy[i - 1].getshot()) {
							rules.bullet[i] = new Shot(rules.enemy[i - 1].enemy.getBounds(),
									rules.enemy[i - 1].getdir(), background);
							colision.Colision_update(draw.jtab, player.player_t, rules.enemy, rules.bullet, draw.map,
									rules.bonus);
							shottime[i - 1] = 0;
						}
					}
					shottime[i - 1]++;
				}

				// kolizja pocisku
				for (int b = 0; b < 6; b++) {

					if (rules.bullet[b] != null && colision.Colision_Bullet(b).size() != 0) {

						for (int i = 0; i < colision.Colision_Bullet(b).size(); i++) {

							if (colision.Colision_Bullet(b).get(i) == "eagle") {
								draw.basedest();
								rules.setbase(false);
							}

							if (colision.Colision_Bullet(b).get(i) == "map") {
								// niszczenie bloku
								background.remove(draw.jtab[Integer
										.parseInt((String) colision.Colision_Bullet(b).get(i + 1))][Integer
												.parseInt((String) colision.Colision_Bullet(b).get(i + 2))]);
								draw.jtab[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))][Integer
										.parseInt((String) colision.Colision_Bullet(b).get(i + 2))] = null;
								i += 2;
							}
							if (colision.Colision_Bullet(b).get(i) == "enemy" && b == 0) {
								if (rules.enemy[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))]
										.getlive() == 1) {
									rules.setHS(rules.enemy[Integer
											.parseInt((String) colision.Colision_Bullet(b).get(i + 1))].getTank()
													.ordinal());
									rules.enemy[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))]
											.Destroy(second_plane);
									background.remove(rules.enemy[Integer
											.parseInt((String) colision.Colision_Bullet(b).get(i + 1))].enemy);
									rules.enemy[Integer
											.parseInt((String) colision.Colision_Bullet(b).get(i + 1))] = null;

								}

								else
									rules.enemy[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))]
											.setlive();

								i++;

							}
							if (colision.Colision_Bullet(b).get(i) == "bullet") {
								rules.bullet[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))]
										.Destroy(second_plane);
								background.remove(rules.bullet[Integer
										.parseInt((String) colision.Colision_Bullet(b).get(i + 1))].shot);
								rules.bullet[Integer.parseInt((String) colision.Colision_Bullet(b).get(i + 1))] = null;

								i++;
							}
							if (colision.Colision_Bullet(b).get(i) == "player" && b != 0 && player.isDestroyable()) {
								player.Destroy(second_plane);
								background.remove(player.player_t);
								player.player_t = null;
								rules.setlive(rules.getlive() - 1);
								i++;
							}

						}
						rules.bullet[b].Destroy(second_plane);
						background.remove(rules.bullet[b].shot);
						rules.bullet[b] = null;

						repaint();

					}
				}

				if (rules.inGame() != "NOTHING") {

					if (rules.inGame() == "END") {
						((Timer) e.getSource()).stop();
						// tabela wyników
						HS = rules.getHS();
						HighScore highscore = null;
						highscore = new HighScore(window, HS, level);
						window.getGamePanel().getContentPane().removeAll();
						window.getGamePanel().getContentPane().add(highscore);
						window.getGamePanel().setVisible(true);

					}
					if (rules.inGame() == "LOSE") {
						level = rules.getlevel();
						live = 3;
						con--;
						HS = rules.getHS();
						remove(panel);
						background.removeAll();
						second_plane.removeAll();
						((Timer) e.getSource()).stop();

						try {
							Start(background, second_plane, first_plane);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					if (rules.inGame() == "WIN") {
						rules.setlevel();
						level = rules.getlevel();
						live = rules.getlive();
						con = rules.getCon();
						HS = rules.getHS();
						background.removeAll();
						second_plane.removeAll();
						remove(panel);
						((Timer) e.getSource()).stop();
						try {
							Start(background, second_plane, first_plane);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

			}
		};
		Font font = loadFont();
		JLabel lbllevel = new JLabel();
		lbllevel.setText("Level " + (level + 1));
		lbllevel.setBounds(341, 366, 150, 100);
		lbllevel.setHorizontalAlignment(SwingConstants.CENTER);
		lbllevel.setForeground(Color.WHITE);
		lbllevel.setFont(new Font(font.getName(), Font.PLAIN, 16));
		first_plane.add(lbllevel);
		ActionListener title = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lbllevel.setBounds(1000, 1000, 10, 10);
				lbllevel.setText("");
				first_plane.remove(lbllevel);
				repaint();
				((Timer) e.getSource()).stop();
				new Timer(16, frame).start();
			}
		};

		new Timer(1000, title).start();
	}

	private void GUI(boolean start, int con, int live, int Tankqty) {

		if (start) {

			Font font = loadFont();
			panel = new JPanel();
			panel.setBounds(832, 0, 258, 832);
			add(panel);
			panel.setLayout(null);
			for (int i = 0; i < Tankqty; i++) {
				lblqty[i / 5][i % 5] = new JLabel();
				lblqty[i / 5][i % 5].setBounds(10 + 50 * (i % 5), 10 + 50 * (i / 5), 32, 32);
				lblqty[i / 5][i % 5]
						.setIcon(new ImageIcon(Game.class.getResource("/com/project/BattleCity/image/enemy.png")));
				panel.add(lblqty[i / 5][i % 5]);
			}

			label_1 = new JLabel(Integer.toString(con));
			label_1.setFont(new Font(font.getName(), Font.PLAIN, 64));
			label_1.setBounds(150, 540, 64, 62);
			panel.add(label_1);

			JLabel label_2 = new JLabel();
			label_2.setIcon(
					new ImageIcon(Game.class.getResource("/com/project/BattleCity/image/tank_player_up_1.png")));
			label_2.setBounds(70, 360, 64, 62);
			panel.add(label_2);

			label_3 = new JLabel(Integer.toString(live));
			label_3.setFont(new Font(font.getName(), Font.PLAIN, 62));
			label_3.setBounds(150, 360, 64, 62);
			panel.add(label_3);

			JLabel lblNewLabel = new JLabel();
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(Game.class.getResource("/com/project/BattleCity/image/flag.png")));
			lblNewLabel.setBounds(70, 540, 64, 64);
			panel.add(lblNewLabel);
		} else {

			label_1.setText(Integer.toString(con));
			label_3.setText(Integer.toString(live));

			for (int i = Tankqty; i < 35; i++) {
				if (lblqty[i / 5][i % 5] != null) {
					lblqty[i / 5][i % 5].setIcon(null);
					panel.remove(lblqty[Tankqty / 5][Tankqty % 5]);
					repaint();
				}
			}
		}
	}

	public static Font loadFont() {
		Font font = null;
		try {
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("prstart.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		}
		return font;
	}
}