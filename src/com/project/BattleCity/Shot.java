package com.project.BattleCity;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Shot {
	private Direction shotdir;
	private int dx;
	private int dy;
	JLabel shot = new JLabel("");
public Shot(Rectangle loc,Direction dir, JLayeredPane back){
	shot.setIcon(getImage(dir));
	shot.setBounds(getloc(loc,dir));
	back.add(shot);
	shotdir = dir;
	
}
private ImageIcon getImage(Direction dir){
	ImageIcon Icon = null;
	if(dir == Direction.UP)Icon = new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_up.png"));
	if(dir == Direction.DOWN)Icon = new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_down.png"));
	if(dir == Direction.LEFT)Icon = new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_left.png"));
	if(dir == Direction.RIGHT)Icon = new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_right.png"));
	return Icon;	
}
private Rectangle getloc(Rectangle loc, Direction dir){
	Rectangle shotloc = null;
	if (dir==Direction.UP){
		shotloc = new Rectangle(loc.x+24, loc.y-19, shot.getIcon().getIconWidth(), shot.getIcon().getIconHeight());
		dy = -10;
		dx = 0;
	}
	if (dir==Direction.DOWN){
		shotloc = new Rectangle(loc.x+24, loc.y+67, shot.getIcon().getIconWidth(), shot.getIcon().getIconHeight());
		dy = 10;
		dx = 0;
	}
	if (dir==Direction.LEFT){
		shotloc = new Rectangle(loc.x-19, loc.y+24, shot.getIcon().getIconWidth(), shot.getIcon().getIconHeight());
		dx = -10;
		dy = 0;
	}
	if (dir==Direction.RIGHT){
		shotloc = new Rectangle(loc.x+67, loc.y+24, shot.getIcon().getIconWidth(), shot.getIcon().getIconHeight());
		dx = 10;
		dy = 0;
	}
	return shotloc;
}
public void move(){
	shot.setBounds(shot.getBounds().x+dx, shot.getBounds().y+dy, shot.getBounds().width, shot.getBounds().height);
}
public Direction getdir() {
	return shotdir;
}
private ImageIcon destico(int index){
	ImageIcon boom=null;
	if (index == 1)boom= new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_explosion_1.png"));
	if (index == 2)boom= new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_explosion_2.png"));
	if (index == 3)boom= new ImageIcon(Shot.class.getResource("/com/project/BattleCity/image/bullet_explosion_3.png"));
	return boom;
}
public void Destroy(JLayeredPane sec){
	JLabel boom = new JLabel();
	boom.setBounds(shot.getBounds().x-24, shot.getBounds().y-24, 64, 64);
	sec.add(boom);
	
ActionListener dest = new ActionListener() {
	int image = 1;
		public void actionPerformed(ActionEvent e) {
			boom.setIcon(destico(image));
			if(image==3){
				boom.setBounds(0,0,0,0);
				boom.setIcon(null);
				sec.remove(boom);
				((Timer) e.getSource()).stop();
			}
			image++;
			
		}
};

new Timer(50, dest).start();
		}
	
}

