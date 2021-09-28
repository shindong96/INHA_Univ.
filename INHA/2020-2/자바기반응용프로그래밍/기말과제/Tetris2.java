package 테트리스;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

 

class BoxNum implements Serializable{
	public int a,b;
	public Color color;
	BoxNum(){
		a=0;
		b=0;
		color=Color.DARK_GRAY;
	}
	BoxNum(int a,int b, Color color){
		this.a=a;
		this.b=b;
		this.color=color;
	}
}


public class Tetris2 extends JFrame implements KeyListener, Serializable{
	int num;
	int level;
	int x,y;
	int turn_count=0;
	BoxNum box[]=new BoxNum[4];
	JButton b[][];
	int result=0;
	
	Tetris2(){
		setTitle("테트리스");
		setSize(300,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(20,10));
		makeMenu();
		
		b = new JButton[20][10];
		for(int i=0;i<20;i++) {
			for(int j=0;j<10;j++) {
				b[i][j]=new JButton();
				b[i][j].setBackground(Color.DARK_GRAY);
				add(b[i][j]);
			}
		}
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);
	}
	
	public void makeMenu() {
		level=Integer.parseInt(JOptionPane.showInputDialog("난이도를 설정하세요(1-10)"));
		JMenuBar mb= new JMenuBar();
		JMenu m1 = new JMenu("파일");
		JMenuItem mi1 = new JMenuItem("저장");
		JMenuItem mi2 = new JMenuItem("불러오기");
		mi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Charset cs = Charset.defaultCharset();
				String str=JOptionPane.showInputDialog("저장할 위치를 입력하세요.");
				String str2=JOptionPane.showInputDialog("저장할 파일명을 입력하세요.");
				Path p = new File(str).toPath();
				File file=new File(p+"\\"+str2);
				Path p1= file.toPath();
				
					try {
						if(Files.notExists(p)) Files.createDirectory(p);
						if(Files.notExists(p1))	Files.createFile(p1);
						FileOutputStream fout = new FileOutputStream(file);
						ObjectOutputStream oout = new ObjectOutputStream(fout);
						oout.writeObject(this);
						oout.close();
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
        mi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=JOptionPane.showInputDialog("불러올 파일 위치를 입력하세요.");
				String str2=JOptionPane.showInputDialog("불러올 파일명을 입력하세요.");
				File file = new File(str+"\\"+str2);
				try {
					Tetris2 t2;
					FileInputStream fin = new FileInputStream(file);
					ObjectInputStream oin = new ObjectInputStream(fin);
					t2=(Tetris2)oin.readObject();
					oin.close();
					b=t2.b;
					box=t2.box;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		m1.add(mi1);m1.add(mi2);
		mb.add(m1);
		setJMenuBar(mb);
	}
	
	private boolean canMake() {
		for(int i=0;i<10;i++) if(!b[0][i].getBackground().equals(Color.DARK_GRAY)) return false;
		for(int i=0;i<4;i++) {
			if(!b[box[i].a][box[i].b].getBackground().equals(Color.DARK_GRAY))return false;
		}return true;
	}
	private void makeBlock() {
		erase();
		Random rd= new Random();
		num=rd.nextInt(7)+1;
		turn_count=0;
		x=0;y=4;
		for(int i=0;i<4;i++)
			box[i]=new BoxNum();
		switch(num) {
		case 1:
			box[0]=new BoxNum(0,5,Color.blue);
			box[1]=new BoxNum(1,4,Color.blue);
			box[2]=new BoxNum(1,5,Color.blue);
			box[3]=new BoxNum(1,6,Color.blue);
			break;
		case 2:
			box[0]=new BoxNum(0,4,Color.red);
			box[1]=new BoxNum(0,5,Color.red);
			box[2]=new BoxNum(1,5,Color.red);
			box[3]=new BoxNum(1,6,Color.red);
			break;
		case 3:
			box[0]=new BoxNum(0,5,Color.orange);
			box[1]=new BoxNum(0,6,Color.orange);
			box[2]=new BoxNum(1,4,Color.orange);
			box[3]=new BoxNum(1,5,Color.orange);
			break;
		case 4:
			box[0]=new BoxNum(0,4,Color.yellow);
			box[1]=new BoxNum(0,5,Color.yellow);
			box[2]=new BoxNum(1,4,Color.yellow);
			box[3]=new BoxNum(1,5,Color.yellow);
			break;
		case 5:
			y=3;
			box[0]=new BoxNum(0,3,Color.green);
			box[1]=new BoxNum(0,4,Color.green);
			box[2]=new BoxNum(0,5,Color.green);
			box[3]=new BoxNum(0,6,Color.green);
			break;
		case 6:
			box[0]=new BoxNum(0,4,Color.pink);
			box[1]=new BoxNum(1,4,Color.pink);
			box[2]=new BoxNum(1,5,Color.pink);
			box[3]=new BoxNum(1,6,Color.pink);
			break;
		case 7:
			box[0]=new BoxNum(0,6,Color.white);
			box[1]=new BoxNum(1,4,Color.white);
			box[2]=new BoxNum(1,5,Color.white);
			box[3]=new BoxNum(1,6,Color.white);
			break;
		}
		if(canMake()) draw();
		else {
			JOptionPane.showMessageDialog(null, result+"점입니다! 수고하셨습니다");
			System.exit(0);
		}
	}	
	
	public void eraseDraw() {
		for(int i=0;i<4;i++) {
			b[box[i].a][box[i].b].setBackground(Color.DARK_GRAY);
		}
	}
	public void draw() {
		for(int i=0;i<4;i++) {
			if(!b[box[i].a][box[i].b].getBackground().equals(Color.DARK_GRAY)) {
				return;
			}
		}
		for(int i=0;i<4;i++) {
			b[box[i].a][box[i].b].setBackground(box[i].color);
		}
	}
	
	public boolean canDown() {
		for(int i=0;i<4;i++) {
			if(box[i].a==19) return false;
		}
		for(int i=0;i<4;i++) {
			if(!b[box[i].a+1][box[i].b].getBackground().equals(Color.DARK_GRAY)) {
				for(int j=0;j<4;j++) {
					if(b[box[i].a+1][box[i].b].equals(b[box[j].a][box[i].b])) break;
					if(j==3) return false;
					}
			}
		}return  true;
	}
	
	
	public void moveDown() {
		if(canDown()) {
			x++;
			eraseDraw();
			for(int i=0;i<4;i++) {
				box[i].a+=1;
			}
			draw();
		}
		else makeBlock();
	}
	public boolean canRight() {
		for(int i=0;i<4;i++) {
			if(box[i].b+1>9) return false;
		}
		return true;
	}
	
	public void moveRight() {
		if(canRight()) {
		   y++;
		   eraseDraw();
	       for(int i=0;i<4;i++) box[i].b+=1;
		   draw();
		}
	}
	public boolean canLeft() {
		for(int i=0;i<4;i++) {
			if(box[i].b-1<0) return false;
		}
		return true;
	}
	
	public void moveLeft() {
		if(canLeft()) {
		   y--;
		   eraseDraw();
	       for(int i=0;i<4;i++) box[i].b-=1;
		   draw();
		}
	}
	public void erase() {
		int count=0;
		for(int i=0;i<20;i++) {
			count=0;
			for(int j=0;j<10;j++) {
				if(!b[i][j].getBackground().equals(Color.DARK_GRAY)) {
					count++;
				}	
			}
			if(count==10) {
				result+=10;
				for(int j=0;j<10;j++)	b[i][j].setBackground(Color.DARK_GRAY);
				for(int j=i;j>0;j--) {
					if(i==0) break;
					else for(int k=0;k<10;k++) b[j][k].setBackground(b[j-1][k].getBackground());
					}
				for(int j=0;j<10;j++) b[0][j].setBackground(Color.DARK_GRAY);
			}			
		}
	}
	
	
	public void turn() {
		turn_count++;
		BoxNum a[]=new BoxNum[4];
		switch(num) {
		case 1:
			eraseDraw();
			for(int i=0;i<4;i++) a[i]=new BoxNum(box[i].b-y,2-(box[i].a-x),box[i].color);
			for(int i=0;i<4;i++) {
				box[i].a=a[i].a+x;
				box[i].b=a[i].b+y;
			}
			draw();
			break;
		case 2:
			eraseDraw();
			if(turn_count%2==1)
				for(int i=0;i<4;i++) a[i]=new BoxNum(box[i].b-y,2-(box[i].a-x),box[i].color);
			else {
				if(y==-1)y++;
				else if(y==8)y--;
				for(int i=0;i<4;i++) a[i]=new BoxNum(2-(box[i].b-y),box[i].a-x,box[i].color);
				}
			for(int i=0;i<4;i++) {
				box[i].a=a[i].a+x;
				box[i].b=a[i].b+y;
			}
			draw();
			break;
		case 3:
			eraseDraw();
			if(turn_count%2==1) 
				for(int i=0;i<4;i++) a[i]=new BoxNum(box[i].b-y,2-(box[i].a-x),box[i].color);
			else {
				if(y==-1)y++;
				else if(y==8)y--;
				for(int i=0;i<4;i++) a[i]=new BoxNum(2-(box[i].b-y),box[i].a-x,box[i].color);
			}
			for(int i=0;i<4;i++) {
				box[i].a=a[i].a+x;
				box[i].b=a[i].b+y;
			}
			draw();
			break;
		case 4: 
			break;
		case 5:
			eraseDraw();
			if(turn_count%2==1)	for(int i=0;i<4;i++) {box[i].a=x+i;box[i].b=1+y;}
			else {
				if(y==-1)y++;
				else if(y==8)y-=2;
				for(int i=0;i<4;i++) {box[i].a=x+1;box[i].b=i+y;}
			}break;
		case 6:
			eraseDraw();
			for(int i=0;i<4;i++) a[i]=new BoxNum(box[i].b-y,2-(box[i].a-x),box[i].color);
			if(turn_count%4==2) {
				if(y==-1) y++;}
			else if(turn_count%4==0) {
				if(y==8) y--;}
			for(int i=0;i<4;i++) {
				box[i].a=a[i].a+x;
				box[i].b=a[i].b+y;
			}
			draw();
			break;
		case 7:
			eraseDraw();
			for(int i=0;i<4;i++) a[i]=new BoxNum(box[i].b-y,2-(box[i].a-x),box[i].color);
			if(turn_count%4==0) {
				if(y==8) y--;}
			else if(turn_count%4==2) {
				if(y==-1) y++;}
			for(int i=0;i<4;i++) {
				box[i].a=a[i].a+x;
				box[i].b=a[i].b+y;
			}
			draw();
			break;
		}
		
	}
	
	 
   	public static void main(String[] args) {
		Tetris2 t=new Tetris2();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					t.makeBlock();
					while(true) {
						Thread.sleep(100*(11-t.level));
					    t.moveDown();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).run();
		
	}

   	
   	
   	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
			int keycode=e.getKeyCode();
			switch(keycode) {
			case 37:
				moveLeft();
				break;
			case 38:
				turn();
				break;
			case 39:
				moveRight();
				break;
			case 40:
				moveDown();
				break;
			case 32:
				while(canDown()) moveDown();    //최대로 내리기
				break;
			case 27:
				System.exit(0);
				break;
			}
		}
	;

	@Override
	public void keyReleased(KeyEvent e) {}
}

