package edu.prj.ui.ManagerMainFrm;

import javax.swing.*;

import edu.prj.ui.ClassRoom.ClassRoomListFrm;
import edu.prj.ui.LoginFrm.LoginFrm;
import edu.prj.ui.Student.StudentListFrm;
import edu.prj.ui.Teacher.TeacherListFrm;

import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.awt.Toolkit.*;
import java.awt.event.KeyEvent.*;
public class ManagerMainFrm extends JFrame{
	private JPanel container;
	private JLabel lblmangerbox;
	private JMenuBar menuBar;
	private JMenu TeacherManage,windowManage,helpManage;
	private JMenuItem teacher,student,classroom,quit,exit,help,about;
	LoginFrm loginFrm=new LoginFrm();
	public String loginName;
	public ManagerMainFrm() {
		initUI();
		bindEvent();
	}
	
	public void initUI() {
		int width=800;
		int height=600;
		this.setSize(width, height);
		this.setTitle("欢迎您管理员！");
		this.setResizable(false);
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		lblmangerbox=new JLabel();
		lblmangerbox.setBounds(10, 470, 300, 100);
		lblmangerbox.setText("欢迎使用考试系统");
		container.add(lblmangerbox);
		//创建菜单栏对象
		menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		this.setResizable(false);
		
		//将JMenu放进JMenuBar
		TeacherManage=new JMenu("管理模块");
		TeacherManage.setMnemonic('S');
		menuBar.add(TeacherManage);
		windowManage=new JMenu("系统");
		windowManage.setMnemonic('B');
		menuBar.add(windowManage);
		helpManage=new JMenu("帮助");
		helpManage.setMnemonic('H');
		menuBar.add(helpManage);
		
		//将JMenuItem放进JMenu（sys Manager）
		teacher=new JMenuItem("教师管理");
		TeacherManage.add(teacher);
		student=new JMenuItem("学生管理");
		TeacherManage.add(student);
		classroom=new JMenuItem("教室管理");
		TeacherManage.add(classroom);
		
		//将JMenuItem放进JMenu（borrow Manager）
		quit=new JMenuItem("注销");
		windowManage.add(quit);
		exit=new JMenuItem("退出");
		windowManage.add(exit);
		
		//将JMenuItem放进JMenu（help Manager）
		help=new JMenuItem("帮助");
		helpManage.add(help);
		about=new JMenuItem("关于");
		helpManage.add(about);
	}
	
	public void bindEvent() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//给JMenu添加事件
//		sysManage.setMnemonic(java.awt.event.KeyEvent.VK_S);
//		borrowManage.setMnemonic(java.awt.event.KeyEvent.VK_B);
//		helpManager.setMnemonic(java.awt.event.KeyEvent.VK_H);
		//给sys Manager添加点击事件
		teacher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,Event.CTRL_MASK));
		student.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		classroom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK));
		
		//给borrow Manager添加点击事件
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,Event.CTRL_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,Event.CTRL_MASK));
		
		teacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Teacher_click();
			}			
		});
		
		student.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Student_click();
			}			
		});
		
		classroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClassRoomListFrm tmplFrm=new ClassRoomListFrm();
				tmplFrm.setVisible(true);
			}			
		});
//		bookManager.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
////				BookListFrm tmplFrm=new BookListFrm();
////				tmplFrm.setVisible(true);
//			}
//			
//		});
		
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quit(e);
	}
		});
		
		
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
//		borrowManager.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
////				BorrowListFrm frm=new BorrowListFrm();
////				frm.setVisible(true);
//			}
//			
//		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				window_Closing(e);
			}
		});
	}
	
	public void window_Closing(WindowEvent e) {
		int option=JOptionPane.showConfirmDialog(this, "确定退出系统？","提示",JOptionPane.YES_NO_OPTION);
		
		if(option ==JOptionPane.YES_NO_OPTION);
			if(e.getWindow()==this) {
				if(loginFrm!=null) {
					this.dispose();
					loginFrm.setVisible(true);
				}
	}
}
	public void customLoad() {

		if (loginName != null) {
			this.setTitle(this.getTitle() + "-【" + loginName + "】");
		}
	}
	
	private void quit(ActionEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		loginFrm.setVisible(true);
	}
	

	private void Teacher_click() {
		TeacherListFrm tmplFrm=new TeacherListFrm();
		tmplFrm.setVisible(true);
	}
	

	private void Student_click() {
		StudentListFrm tmplFrm=new StudentListFrm();
		tmplFrm.setVisible(true);
	}
}
