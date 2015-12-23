package com.zhangyin.saodi.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.zhangyin.saodi.base.Direction;
import com.zhangyin.saodi.base.Node;
import com.zhangyin.saodi.base.NodeMatrix;

public class MyFrame  extends JFrame{
	 
   
	
	public static int widthandHeight=30;
	
	 NodeMatrix nm;
	 
	 Node current;
	 
	 int i;
	 int j;
	 
	 StringBuffer sb=new StringBuffer();

	public MyFrame(NodeMatrix nm) throws HeadlessException {
		 super();
		 this.nm = nm;
		 this.setLayout(new BorderLayout());
		 this.setVisible(true);
		 setLocationRelativeTo(null);
		 this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 Toolkit kit = Toolkit.getDefaultToolkit();
	     Dimension dimension = kit.getScreenSize();
	     int windowWidth=nm.col*widthandHeight;
	     int windowHeight=nm.row*widthandHeight;
	     this.setBounds(0, 0, windowWidth,windowHeight);
		 this.add(nm,BorderLayout.CENTER);
		 //this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 int screenWidth = dimension.width; //获取屏幕的宽
		 int screenHeight = dimension.height; //获取屏幕的高
		 this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
		 MouseAdapter ma=new MouseAdapter() {
			 @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e){
				 Object source = e.getSource();
				 //if(current==null){
					 Node n= (Node)source;
					 n.ispassed=true;
					 current=n;
					 i=current.i;
					 j=current.j;
					 current.repaint();
				// }
			 }
		};
		for (int i = 0; i < nm.row; i++) {
				for (int j = 0; j < nm.col; j++) { 
					 Node n=nm.nodes[i][j];
					 if(n.isBlank){
						n.addMouseListener(ma); 
				}
			}
		}

		this.addKeyListener(new java.awt.event.KeyAdapter() { 
				public void keyPressed(java.awt.event.KeyEvent evt) { 
					int keyCode = evt.getKeyCode();
					if(keyCode==32){
						clear();
						return;
					}
					if(keyCode==38){
						if(canmove(Direction.Up)){
							sb.append(Direction.Up);
							move(Direction.Up);
						}
					}
					if(keyCode==40){
						if(canmove(Direction.Down)){
							sb.append(Direction.Down);
							move(Direction.Down);
						}
					}
					if(keyCode==37){
						if(canmove(Direction.Left)){
							sb.append(Direction.Left);
							move(Direction.Left);
						}
					}
					if(keyCode==39){
						if(canmove(Direction.Right)){
							sb.append(Direction.Right);
							move(Direction.Right);
						}
					}
					current.ispassed=true;
					current.repaint();
					showMessage();
					
					
				} 
			});
		 
	} 
	public void move(Direction d){
		if(current.canMoveDirection.containsKey(d)&&current.canMoveDirection.get(d).ispassed==false){
			current.ispassed=true;
			current.repaint();
			current=current.canMoveDirection.get(d);
			move(d);
		}
	}
	
	public Boolean canmove(Direction d){
		if(current.canMoveDirection.containsKey(d)&&current.canMoveDirection.get(d).ispassed==false){
			return true;
		}
		return false;
	}
	
	public void showMessage(){
		System.out.println("起始坐标 "+i+"    "+j+"  "+sb.toString());
	}
	
	public void clear(){
		current=null;
		i=0;
		j=0;
		sb=new StringBuffer("");
		for (int i = 0; i < nm.row; i++) {
			for (int j = 0; j < nm.col; j++) { 
				 Node n=nm.nodes[i][j];
				 if(n.isBlank){
					n.ispassed=false;
			}
		}
		nm.repaint();
	}
	}
	 
	 
	

}
