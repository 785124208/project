package edu.prj.ui.StudentMainFrm;

import javax.swing.*;

import edu.prj.bean.Question;
import edu.prj.bean.Student;
import edu.prj.dao.StudentDao;
import edu.prj.dao.impl.StudentDaoImpl;
import edu.prj.ui.Exam.ExamListFrm;
import edu.prj.ui.ExamItem.ExamItemListFrm;
import edu.prj.ui.LoginFrm.LoginFrm;

import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.awt.Toolkit.*;
import java.awt.event.KeyEvent.*;
public class StudentMainFrm extends JFrame{
	private JPanel container;
	private JLabel lblmangerbox;
	private JMenuBar menuBar;
	private JMenu sysManage,ScoreManage,helpManager;
	private JMenuItem updatepwd,startExam,quit,exit,score,help,about;
	LoginFrm loginFrm=new LoginFrm();
	public String loginName;
	public StudentMainFrm() {
		initUI();
		bindEvent();
	}
	
	public void initUI() {
		int width=800;
		int height=600;
		this.setSize(width, height);
		this.setTitle("学生");
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
		sysManage=new JMenu("系统管理");
		sysManage.setMnemonic('S');
		menuBar.add(sysManage);
		ScoreManage=new JMenu("成绩管理");
		ScoreManage.setMnemonic('B');
		menuBar.add(ScoreManage);
		helpManager=new JMenu("帮助");
		helpManager.setMnemonic('H');
		menuBar.add(helpManager);
		
		//将JMenuItem放进JMenu
		startExam=new JMenuItem("开始考试");
		sysManage.add(startExam);
		updatepwd=new JMenuItem("修改密码");
		sysManage.add(updatepwd);
		quit=new JMenuItem("注销");
		sysManage.add(quit);
		exit=new JMenuItem("退出");
		sysManage.add(exit);
		
		//将JMenuItem放进JMenu
		score=new JMenuItem("结果查看");
		ScoreManage.add(score);
		
		//将JMenuItem放进JMenu
		help=new JMenuItem("帮助");
		helpManager.add(help);
		about=new JMenuItem("关于");
		helpManager.add(about);
	}
	
	public void bindEvent() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//给JMenu添加事件
//		sysManage.setMnemonic(java.awt.event.KeyEvent.VK_S);
//		borrowManage.setMnemonic(java.awt.event.KeyEvent.VK_B);
//		helpManager.setMnemonic(java.awt.event.KeyEvent.VK_H);
		//给sys Manager添加点击事件
		updatepwd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,Event.CTRL_MASK));
		startExam.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK));
		
		//给borrow Manager添加点击事件
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,Event.CTRL_MASK));
		
		updatepwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StudentUpdatePwdFrm stuPwd=new StudentUpdatePwdFrm();
				stuPwd.setVisible(true);
			}
			
		});
		
		startExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startExam(e);
			}
			
		});
		
		score.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startScore(e);
			}
			
		});
		
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
	
	public void startExam(ActionEvent e) {
		// TODO Auto-generated method stub

		ExamListFrm tmplFrm=new ExamListFrm();
		tmplFrm.loadData();
		tmplFrm.pk=pk;
		tmplFrm.setVisible(true);
		System.out.println(pk);
	}
	
	public void startScore(ActionEvent e) {
		// TODO Auto-generated method stub

		ExamItemListFrm tmplFrm=new ExamItemListFrm();
		tmplFrm.pk=pk;
		tmplFrm.setVisible(true);
		System.out.println(pk);
	}
	
	public Long pk=	null;
	StudentDao studentService=new StudentDaoImpl();
	public void loadData() {
		if(loginName==null) {
			JOptionPane.showConfirmDialog(null, "主键值为空，加载失败！");
			return;
		}
		
		Student bean=studentService.loadByName(loginName);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		pk=bean.getStudentId();
		this.setTitle(this.getTitle());
//		this.setTitle(this.getTitle()+pk);
	}
}
