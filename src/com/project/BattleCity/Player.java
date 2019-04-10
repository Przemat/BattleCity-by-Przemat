package com.project.BattleCity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Player {
	JLabel player_t = new JLabel("");

	// test
	boolean delta;
	
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_UP = "move up p";
	private static final String MOVE_DOWN = "move down p";
	private static final String MOVE_LEFT = "move left p";
	private static final String MOVE_RIGHT = "move right p";
	private static final String STOP_UP = "move up r";
	private static final String STOP_DOWN = "move down r";
	private static final String STOP_LEFT = "move left r";
	private static final String STOP_RIGHT = "move right r";
	private static final String FIRE = "fire p";
	private static final String STOPFIRE = "fire r";

	private int dx;
	private int dy;
	private int x = 288;
	private int y = 768;
	private int w = 64;
	private int h = 64;
	private int speed = 1;
	private Direction dir = Direction.UP;
	int image = 0;
	private boolean destroyable = true;

	private boolean up = true;
	private boolean down = true;
	private boolean left = true;
	private boolean right = true;
	private boolean fire = false;

	private ImageIcon P_UP_1;
	private ImageIcon P_DOWN_1;
	private ImageIcon P_LEFT_1;
	private ImageIcon P_RIGHT_1;
	private ImageIcon P_UP_2;
	private ImageIcon P_DOWN_2;
	private ImageIcon P_LEFT_2;
	private ImageIcon P_RIGHT_2;
	private ImageIcon P_DESTROY1;
	private ImageIcon P_DESTROY2;
	private ImageIcon P_DESTROY3;
	private ImageIcon P_DESTROY4;
	private ImageIcon P_DESTROY5;

	private ImageIcon P_SHIELD1;
	private ImageIcon P_SHIELD2;

	public Player() {
		loadImage();
		keybind();
	}

	private void keybind() {
		// TODO Auto-generated method stub
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), MOVE_UP);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), STOP_UP);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), MOVE_DOWN);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), STOP_DOWN);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), MOVE_LEFT);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), STOP_LEFT);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), MOVE_RIGHT);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), STOP_RIGHT);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), FIRE);
		player_t.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), STOPFIRE);
		player_t.getActionMap().put(MOVE_UP, KPUP);
		player_t.getActionMap().put(MOVE_DOWN, KPDOWN);
		player_t.getActionMap().put(MOVE_LEFT, KPLEFT);
		player_t.getActionMap().put(MOVE_RIGHT, KPRIGHT);
		player_t.getActionMap().put(STOP_UP, Stop);
		player_t.getActionMap().put(STOP_DOWN, Stop);
		player_t.getActionMap().put(STOP_LEFT, Stop);
		player_t.getActionMap().put(STOP_RIGHT, Stop);
		player_t.getActionMap().put(FIRE, KPFIRE);
		player_t.getActionMap().put(STOPFIRE, KPSTOPFIRE);
	}

	private void loadImage() {
		P_UP_1 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_up_1.png"));
		P_UP_2 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_up_2.png"));
		P_DOWN_1 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_down_1.png"));
		P_DOWN_2 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_down_2.png"));
		P_LEFT_1 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_left_1.png"));
		P_LEFT_2 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_left_2.png"));
		P_RIGHT_1 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_right_1.png"));
		P_RIGHT_2 = new ImageIcon(Player.class.getResource("/com/project/BattleCity/image/tank_player_right_2.png"));
		P_DESTROY1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_1.png"));
		P_DESTROY2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_2.png"));
		P_DESTROY3 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_3.png"));
		P_DESTROY4 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_4.png"));
		P_DESTROY5 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_5.png"));
		P_SHIELD1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/shield_1.png"));
		P_SHIELD2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/shield_2.png"));
	}

	public void spawn(JLayeredPane back) {
		if (player_t == null)
			player_t = new JLabel("");
		player_t.setIcon(P_UP_1);
		player_t.setBounds(288, 768, 64, 64);
		x = 288;
		y = 768;
		back.add(player_t);
		keybind();
	}

	public void move() {
		x += dx * speed;
		y += dy * speed;

	}

	public void movecor() {
		if (x % 16 < 8)
			x = x - (x % 16);
		else
			x = x + 1 - (x % 16);
		if (y % 16 < 8)
			y = y - (y % 16);
		else
			y = y + 1 - (y % 16);
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getw() {
		return w;
	}

	public int geth() {
		return h;
	}

	public int getdx() {
		return dx;
	}

	public int getdy() {
		return dy;
	}

	public Direction getdir() {
		return dir;
	}

	public ImageIcon getImage() {

		if (delta) {
			if (image > 10) {
				up = !up;
				down = !down;
				left = !left;
				right = !right;
				image = 0;
			} else
				image++;
		}
		if (dir == Direction.UP) {
			if (up) {
				return P_UP_1;
			} else {
				return P_UP_2;
			}
		} else if (dir == Direction.DOWN) {
			if (down) {
				return P_DOWN_1;
			} else {
				return P_DOWN_2;
			}
		} else if (dir == Direction.LEFT) {
			if (left) {
				return P_LEFT_1;
			} else {
				return P_LEFT_2;
			}
		} else if (dir == Direction.RIGHT) {
			if (right) {
				return P_RIGHT_1;
			} else {
				return P_RIGHT_2;
			}
		} else
			return P_UP_1;

	}

	AbstractAction Stop = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			delta = false;

		}
	};
	AbstractAction KPUP = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {

			dy = -2;
			dx = 0;
			dir = Direction.UP;
			delta = true;
		}
	};
	AbstractAction KPDOWN = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			dy = 2;
			dx = 0;
			dir = Direction.DOWN;
			delta = true;
		}
	};
	AbstractAction KPLEFT = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			dy = 0;
			dx = -2;
			dir = Direction.LEFT;
			delta = true;
		}
	};
	AbstractAction KPRIGHT = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			dy = 0;
			dx = 2;
			dir = Direction.RIGHT;
			delta = true;
		}
	};
	AbstractAction KPFIRE = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
				fire = true;
		}
	};
	AbstractAction KPSTOPFIRE = new AbstractAction() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			fire = false;
			}
	};

	private ImageIcon destico(int index) {
		if (index == 1)
			return P_DESTROY1;
		if (index == 2)
			return P_DESTROY2;
		if (index == 3)
			return P_DESTROY3;
		if (index == 4)
			return P_DESTROY4;
		if (index == 5)
			return P_DESTROY5;
		return null;
	}

	public void Destroy(JLayeredPane sec) {
		JLabel boom = new JLabel();
		boom.setBounds(x - 32, y - 32, 128, 128);
		sec.add(boom);

		ActionListener dest = new ActionListener() {
			int image = 1;

			public void actionPerformed(ActionEvent e) {
				boom.setIcon(destico(image));
				if (image == 5) {
					boom.setBounds(0, 0, 0, 0);
					boom.setIcon(null);
					sec.remove(boom);
					((Timer) e.getSource()).stop();

				}
				image++;

			}
		};
		new Timer(50, dest).start();
	}

	private ImageIcon appearico(boolean ico) {
		if (ico)
			return P_SHIELD1;
		else
			return P_SHIELD2;
	}

	public void shield(JLayeredPane sec) {
		JLabel shield = new JLabel();
		shield.setBounds(x, y, 64, 64);
		sec.add(shield);
		destroyable = false;

		ActionListener appearAL = new ActionListener() {
			boolean image = true;
			int time = 0;

			public void actionPerformed(ActionEvent e) {
				shield.setIcon(appearico(image));
				shield.setBounds(x, y, w, h);
				if (time == 200) {
					shield.setIcon(null);
					shield.setBounds(0, 0, 0, 0);
					sec.remove(shield);
					destroyable = true;
					((Timer) e.getSource()).stop();
				}
				image = !image;
				time++;

			}
		};
		new Timer(50, appearAL).start();
	}

	public void speed() {
		speed = 2;
		ActionListener speedAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speed = 1;
				((Timer) e.getSource()).stop();
			}
		};
		new Timer(10000, speedAL).start();
	}

	public boolean isFire() {
		return fire;
	}

	public boolean isDestroyable() {
		return destroyable;
	}
}
