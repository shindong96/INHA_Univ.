import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class NotePad extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePad frame = new NotePad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotePad() {
		setTitle("메모장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("파일");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("새로 만들기");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("새로 만들기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("열기");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("열기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("저장");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("저장 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("다른 이름으로 저장");
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("다른 이름으로 저장 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("페이지 설정");
		mnNewMenu.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("페이지 설정 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("인쇄");
		mnNewMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("인쇄 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("끝내기");
		mnNewMenu.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("끝내기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("편집");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("실행 취소");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("실행 취소 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu_1.add(separator_2);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("잘라내기");
		mnNewMenu_1.add(mntmNewMenuItem_8);
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("잘라내기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("복사");
		mnNewMenu_1.add(mntmNewMenuItem_9);
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("복사 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("붙여넣기");
		mnNewMenu_1.add(mntmNewMenuItem_10);
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("붙여넣기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("삭제");
		mnNewMenu_1.add(mntmNewMenuItem_11);
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("삭제 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu_1.add(separator_3);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("찾기");
		mnNewMenu_1.add(mntmNewMenuItem_12);
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("찾기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("다음 찾기");
		mnNewMenu_1.add(mntmNewMenuItem_13);
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("다음 찾기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("바꾸기");
		mnNewMenu_1.add(mntmNewMenuItem_14);
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("바꾸기 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("이동");
		mnNewMenu_1.add(mntmNewMenuItem_15);
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("이동 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_1.add(separator_4);
		
		JMenuItem mntmNewMenuItem_16 = new JMenuItem("모두 선택");
		mnNewMenu_1.add(mntmNewMenuItem_16);
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("모두 선택 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_17 = new JMenuItem("시간/날짜");
		mnNewMenu_1.add(mntmNewMenuItem_17);
		mntmNewMenuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("시간/날짜 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("서식");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_18 = new JMenuItem("자동 줄 바꿈");
		mnNewMenu_2.add(mntmNewMenuItem_18);
		mntmNewMenuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("자동 줄 바꿈 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenuItem mntmNewMenuItem_19 = new JMenuItem("글꼴");
		mnNewMenu_2.add(mntmNewMenuItem_19);
		mntmNewMenuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("글꼴 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("보기");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_20 = new JMenuItem("상태 표시 줄");
		mnNewMenu_3.add(mntmNewMenuItem_20);
		mntmNewMenuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("상태 표시 줄 메뉴를 선택 하였습니다.");
			}
		});
		
		JMenu mnNewMenu_4 = new JMenu("도움말");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_21 = new JMenuItem("도움말 보기");
		mnNewMenu_4.add(mntmNewMenuItem_21);
		mntmNewMenuItem_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("도움말 보기 메뉴를 선택 하였습니다.");
			}
		});
		
		JSeparator separator_5 = new JSeparator();
		mnNewMenu_4.add(separator_5);
		
		JMenuItem mntmNewMenuItem_22 = new JMenuItem("메모장 정보");
		mnNewMenu_4.add(mntmNewMenuItem_22);
		mntmNewMenuItem_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("메모장 정보 메뉴를 선택 하였습니다.");
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane, BorderLayout.CENTER);
	}

}
