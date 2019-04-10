package com.project.BattleCity;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Enemy {
	JLabel enemy = new JLabel("");
	
	private int dx =2;
	private int dy=0;
	private int x = 0;
	private int y = 0;
	private int w =64;
	private int h =64;
	public int speed = 1;
	private Direction dir = Direction.DOWN;
	private int image = 0;
	private int live = 1;
	
	
	boolean up=true;
	boolean down=true;
	boolean left=true;
	boolean right=true;
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
	private ImageIcon P_APPEAR1;
	private ImageIcon P_APPEAR2;
	private ImageIcon P_APPEAR3;
	private ImageIcon P_APPEAR4;
	
	private boolean shotable = true;
	
	private Tank ltank;
	Random newdir = new Random();
	public Enemy(Tank tank){
		loadImage(tank);
		if(tank == Tank.SPEED)speed=2;
		if(tank == Tank.HEAVY)live = 3;
		ltank = tank;
	}
	public Tank getTank(){
		return ltank;
	}
	public int getlive(){
	return live;
	}
	public void setlive(){
		live--;
		}
	
	private void loadImage(Tank tank){
		P_UP_1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_up_1.png"));
		P_UP_2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_up_2.png"));
		P_DOWN_1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_down_1.png"));
		P_DOWN_2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_down_2.png"));
		P_LEFT_1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_left_1.png"));
		P_LEFT_2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_left_2.png"));
		P_RIGHT_1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_right_1.png"));
		P_RIGHT_2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/tank_"+tank.name()+"_right_2.png"));
		
		
		P_DESTROY1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_1.png"));
		P_DESTROY2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_2.png"));
		P_DESTROY3 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_3.png"));
		P_DESTROY4 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_4.png"));
		P_DESTROY5 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/big_explosion_5.png"));
		
		
		P_APPEAR1 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/appear_1.png"));
		P_APPEAR2 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/appear_2.png"));
		P_APPEAR3 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/appear_3.png"));
		P_APPEAR4 = new ImageIcon(Enemy.class.getResource("/com/project/BattleCity/image/appear_4.png"));
	}
	public void spawn(JLayeredPane back, int index) {
	
		enemy.setIcon(P_UP_1);
		enemy.setBounds(index*384,0,64, 64);
		x = index*384;		
		back.add(enemy);
	}
	private ImageIcon appearico(int index){
		if (index == 1)return P_APPEAR1;
		if (index == 2)return P_APPEAR2;
		if (index == 3)return P_APPEAR3;
		if (index == 4)return P_APPEAR4;
		return null;
	}
	public void newspawn(JLayeredPane back,JLayeredPane sec,int index){
		JLabel appear = new JLabel();
		enemy.setBounds(index*384,0,64, 64);
		x = index*384;
		appear.setBounds(384*index,0, 64,64);
		sec.add(appear);
		
		
ActionListener appearAL = new ActionListener() {
			int image = 1;
			public void actionPerformed(ActionEvent e) {
				appear.setIcon(appearico(image));
				if(image==4){
					appear.setIcon(null);
					appear.setBounds(0,0,0,0);
					sec.remove(appear);
					((Timer) e.getSource()).stop();
					spawn(back,index);
				}
				image++;
				
			}
};
new Timer(50, appearAL).start();
			}
	
	public void move(){
			x+=dx;
			y+=dy;
			
	}
	public boolean getshot(){
		return shotable;
	}
	public void checkmove(){
		if(dir == Direction.UP){
			dy=-2*speed;
			dx=0;
		}
		if(dir == Direction.DOWN){
			dy=2*speed;
			dx=0;
		}
		if(dir == Direction.LEFT){
			dy=0;
			dx=-2*speed;
		}
		if(dir == Direction.RIGHT){
			dy=0;
			dx=2*speed;
		}
	}
	public int getx(){
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
	public int getdx(){
		return dx;
	}
	public int getdy(){
		return dy;
	}
	public Direction getdir(){
		return dir;
	}
	public void changedir(){
		switch(newdir.nextInt(4)){
		case 0:{
			dir = Direction.UP;
			break;
		}
		case 1:{
			dir = Direction.DOWN;
			break;
		}
		case 2:{
			dir = Direction.LEFT;
			break;
		}
		case 3:{
			dir = Direction.RIGHT;
			break;
		}
		default:{
			dir = Direction.UP;
			break;
		}
		}
	}
	public ImageIcon getImage() {
		
		
			if( image > 10){
			up = !up;
			down = !down;
			left = !left;
			right = !right;
			image = 0;
		}
			else image++;
		
		if (dir == Direction.UP){
			if(up){
				return P_UP_1;
			}
			else {
				return P_UP_2;
			}
		}
		else if (dir == Direction.DOWN){
			if(down){
				return P_DOWN_1;
			}else{
				return P_DOWN_2;
			}
		}
		else if (dir == Direction.LEFT){
			if(left){
				return P_LEFT_1;
			}else{
				return P_LEFT_2;
			}
		}
		else if (dir == Direction.RIGHT){
			if(right){
				return P_RIGHT_1;
			}else {
				return P_RIGHT_2;
			}
		}else return P_UP_1;
		
	}
	private ImageIcon destico(int index){
		if (index == 1)return P_DESTROY1;
		if (index == 2)return P_DESTROY2;
		if (index == 3)return P_DESTROY3;
		if (index == 4)return P_DESTROY4;
		if (index == 5)return P_DESTROY5;
		return null;
	}
	public void Destroy(JLayeredPane sec){
		JLabel boom = new JLabel();
		boom.setBounds(x-32, y-32, 128,	128);
		sec.add(boom);
		
ActionListener dest = new ActionListener() {
			int image = 1;
			public void actionPerformed(ActionEvent e) {
				boom.setIcon(destico(image));
				if(image==5){
					
					boom.setIcon(null);
					boom.setBounds(0,0,0,0);
					sec.remove(boom);
					((Timer) e.getSource()).stop();
					
				}
				image++;
				
			}
};
new Timer(50, dest).start();
			}
	public int getshottime(){
		 if (ltank == Tank.POWER)return 60;
		 else return 120;
	}
	public void clock() {
		speed =0;
		shotable = false;
		// TODO Auto-generated method stub
		ActionListener clockAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ltank == Tank.SPEED)speed=2;
				else speed = 1;
				shotable = true;
			}
};
new Timer(5000, clockAL).start();
	}
		
}