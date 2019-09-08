package edu.prj.ui.TeacherMainFrm;

import javax.swing.*;

import edu.prj.ui.Exam.ExamFrm;
import edu.prj.ui.Exam.ExamListFrm;
import edu.prj.ui.ExamItem.ExamItemListFrm;
import edu.prj.ui.LoginFrm.LoginFrm;
import edu.prj.ui.Paper.PaperListFrm;
import edu.prj.ui.Question.QuestionListFrm;
import edu.prj.ui.Subject.SubjectListFrm;

import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.awt.Toolkit.*;
import java.awt.event.KeyEvent.*;
public class TeacherMainFrm extends JFrame{
	private JPanel container;
	private JLabel lblmangerbox;
	private JMenuBar menuBar;
	private JMenu sysManage,TeacherManage,helpManager;
	private JMenuItem modifyPwd,quit,exit,qManage,sortManage,paperManager,examManager,updateManager,help,about;
	LoginFrm loginFrm=new LoginFrm();
	public String loginName;
	public TeacherMainFrm() {
		initUI();
		bindEvent();
	}
	
	public void initUI() {
		int width=800;
		int height=600;
		this.setSize(width, height);
		this.setTitle("欢迎教师！");
		this.setResizable(false);
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		lblmangerbox=new JLabel();
		lblmangerbox.setBounds(10, 470, 300, 100);
		lblmangerbox.setText("欢迎使用考试系统！");
		container.add(lblmangerbox);
		//创建菜单栏对象
		menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		this.setResizable(false);
		
		//将JMenu放进JMenuBar
		sysManage=new JMenu("系统管理");
		sysManage.setMnemonic('S');
		menuBar.add(sysManage);
		TeacherManage=new JMenu("教师管理");
		TeacherManage.setMnemonic('B');
		menuBar.add(TeacherManage);
		helpManager=new JMenu("帮助");
		helpManager.setMnemonic('H');
		menuBar.add(helpManager);
		
		//将JMenuItem放进JMenu（sys Manager）
		modifyPwd=new JMenuItem("修改密码");
		sysManage.add(modifyPwd);
		quit=new JMenuItem("注销");
		sysManage.add(quit);
		exit=new JMenuItem("退出");
		sysManage.add(exit);
		
		//将JMenuItem放进JMenu（borrow Manager）
		qManage=new JMenuItem("问题管理");
		TeacherManage.add(qManage);
		sortManage=new JMenuItem("分类管理");
		TeacherManage.add(sortManage);
		paperManager=new JMenuItem("考卷管理");
		TeacherManage.add(paperManager);
		examManager=new JMenuItem("考试管理");
		TeacherManage.add(examManager);
		updateManager=new JMenuItem("批改管理");
		TeacherManage.add(updateManager);
		
		//将JMenuItem放进JMenu（help Manager）
		help=new JMenuItem("帮助");
		helpManager.add(help);
		about=new JMenuItem("关于");
		helpManager.add(about);
	}
	
	public void bindEvent() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//给JMenu添加事件
//		questionManage.setMnemonic(java.awt.event.KeyEvent.VK_S);
//		borrowManage.setMnemonic(java.awt.event.KeyEvent.VK_B);
//		helpManager.setMnemonic(java.awt.event.KeyEvent.VK_H);
		//添加点击事件
//		stuManger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		modifyPwd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK));
		qManage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		examManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,Event.CTRL_MASK));
		updateManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,Event.CTRL_MASK));
		sortManage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		paperManager.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,Event.CTRL_MASK));
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,Event.CTRL_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,Event.CTRL_MASK));
//		stuManger.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
////				StudentListFrm tmplFrm=new StudentListFrm();
////				tmplFrm.setVisible(true);
//			}
//			
//		});
		//修改密码
		modifyPwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TeacherUpdatePwdFrm tmplFrm=new TeacherUpdatePwdFrm();
				tmplFrm.setVisible(true);
			}
			
		});
		//分类管理
		sortManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SubjectListFrm tmplFrm=new SubjectListFrm();
				tmplFrm.setVisible(true);
			}
			
		});
		//注销
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quit(e);
	}
		});
		
		
		//退出系统
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		//问题管理
		qManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				QuestionListFrm frm=new QuestionListFrm();
				frm.setVisible(true);
			}
			
		});
		
		
		//试卷管理
		paperManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PaperListFrm frm=new PaperListFrm();
				frm.setVisible(true);
			}
			
		});
		
		examManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ExamListFrm frm=new ExamListFrm();
				frm.setVisible(true);
			}
			
		});
		
		updateManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ExamItemListFrm frm=new ExamItemListFrm();
				frm.setVisible(true);
			}
			
		});
		
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
}
