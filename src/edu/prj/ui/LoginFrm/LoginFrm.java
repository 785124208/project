package edu.prj.ui.LoginFrm;

import edu.prj.bean.Manager;
import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.service.ManagerService;
import edu.prj.service.StudentService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.ManagerServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.ManagerMainFrm.ManagerMainFrm;
import edu.prj.ui.StudentMainFrm.StudentMainFrm;
import edu.prj.ui.StudentMainFrm.StudentUpdatePwdFrm;
import edu.prj.ui.TeacherMainFrm.TeacherMainFrm;

import java.awt.Color;

import javax.swing.*;

import com.liuvei.common.SysFun;

import java.awt.*;
import java.awt.event.*;

public class LoginFrm extends JFrame{
	private JPanel container;
	private JLabel lblLoginName;
	private JTextField txtLoginName;
	private JLabel lblLoginPass;
	private JPasswordField txtLoginPass;
	private JButton btnsubmit;
	private JButton btnreset;
	private JLabel lblmessagebox;
	private ButtonGroup btngrpProfession;	//权限单选按钮组
	private JRadioButton rdoManager;	//管理员单选按钮
	private JRadioButton rdoTeacher;	//教师选按钮
	private JRadioButton rdoStudent;	//学生单选按钮
	
	public LoginFrm() {
		initUI();
		bindEvent();
	}
	
	public void initUI() {
		int width=500;
		int height=400;
		this.setSize(width, height);
		this.setTitle("Welcome！");
		this.setResizable(false);
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		lblLoginName=new JLabel();
		lblLoginName.setText("账号：");
		lblLoginName.setBounds(130, 50, 80, 50);
		container.add(lblLoginName);
		
		txtLoginName=new JTextField();
		txtLoginName.setText("");
		txtLoginName.setBounds(210, 60, 150, 30);
		container.add(txtLoginName);
		
		lblLoginPass=new JLabel();
		lblLoginPass.setText("密码：");
		lblLoginPass.setBounds(130, 110, 80, 50);
		container.add(lblLoginPass);
		
		txtLoginPass=new JPasswordField();
		txtLoginPass.setText("");
		txtLoginPass.setBounds(210, 120, 150, 30);
		container.add(txtLoginPass);
		
		btnsubmit=new JButton();
		btnsubmit.setText("登录");
		btnsubmit.setBounds(130, 260, 80, 50);
		container.add(btnsubmit);
		
		btnreset=new JButton();
		btnreset.setText("重置");
		btnreset.setBounds(280, 260, 80, 50);
		container.add(btnreset);
		
		lblmessagebox=new JLabel();
		lblmessagebox.setText("");
		lblmessagebox.setForeground(Color.RED);
		lblmessagebox.setBounds(180, 210, 150, 50);
		container.add(lblmessagebox);
		
		//管理员单选按钮
		rdoManager=new JRadioButton("管理员");
		rdoManager.setBounds(100, 170, 80, 30);
		container.add(rdoManager);
		//教师单选按钮
		rdoTeacher=new JRadioButton("教师");
		rdoTeacher.setBounds(220, 170, 80, 30);
		container.add(rdoTeacher);
		//学生单选按钮
		rdoStudent=new JRadioButton("学生");
		rdoStudent.setBounds(310, 170, 80, 30);
		container.add(rdoStudent);
		//将三者放到同一个group里只能选择一个
		btngrpProfession=new ButtonGroup();
		btngrpProfession.add(rdoManager);
		btngrpProfession.add(rdoTeacher);
		btngrpProfession.add(rdoStudent);
		rdoManager.setSelected(true);//默认选中管理员
	}
	
	public void bindEvent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnsubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnsubmit_click(e);
			}
			
		});
		
		btnreset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnreset_click(e);
			}
			
		});
	}
	/**
	 * 自定义加载
	 */
	public void customLoad() {
		
	}
	
	public void btnsubmit_click(ActionEvent e) {
		boolean isOK=false;
		this.lblmessagebox.setText("正在登陆");
		String loginname=txtLoginName.getText().trim();
		String loginpass=txtLoginPass.getText().trim();
		
		if(loginname==null||loginname.isEmpty()) {
			lblmessagebox.setText("账号不得为空");
			return;
		}
		
		if(loginpass.isEmpty()||loginpass==null){
			lblmessagebox.setText("密码不得为空");
			return;
		}
		Long profession=0L;
		if(rdoManager.isSelected()) {
			profession=0L;
		}else if(rdoTeacher.isSelected()) {
			profession=1L;
		}else if(rdoStudent.isSelected()) {
			profession=2L;
		}
		
		
		if(profession==0L) {
		ManagerService managerService=new ManagerServiceImpl();
		Manager bean=managerService.loadByName(loginname);
		if(bean==null) {
			lblmessagebox.setText("登陆失败，错误代码101");
			return;
		}
		if(!SysFun.md5(loginpass).equals(bean.getLoginPwd())) {
			lblmessagebox.setText("登陆失败，错误代码102");
			return;
		}		
		
		isOK=true;
		if(isOK) {
			lblmessagebox.setText("登陆成功！");
			this.setVisible(false);
			ManagerMainFrm mainFrm=new ManagerMainFrm();
			txtLoginName.setText("");
			txtLoginPass.setText("");
			mainFrm.loginName=loginname;
			mainFrm.customLoad();
			mainFrm.setVisible(true);
		}else {
			lblmessagebox.setText("登陆失败！");
		}
		}else if(profession==1L) {
			TeacherService teacherService=new TeacherServiceImpl();
			Teacher bean=teacherService.loadByName(loginname);
			if(bean==null) {
				lblmessagebox.setText("登陆失败，错误代码101");
				return;
			}
			if(!SysFun.md5(loginpass).equals(bean.getLoginPwd())) {
				lblmessagebox.setText("登陆失败，错误代码102");
				return;
			}		
			
			isOK=true;
			if(isOK) {
				lblmessagebox.setText("登陆成功！");
				this.setVisible(false);
				TeacherMainFrm mainFrm=new TeacherMainFrm();
				txtLoginName.setText("");
				txtLoginPass.setText("");
				mainFrm.loginName=loginname;
				mainFrm.customLoad();
				mainFrm.setVisible(true);
			}else {
				lblmessagebox.setText("登陆失败！");
			}
		}else if(profession==2L) {
			StudentService studentService=new StudentServiceImpl();
			Student bean=studentService.loadByName(loginname);
			if(bean==null) {
				lblmessagebox.setText("登陆失败，错误代码101");
				return;
			}
			if(!SysFun.md5(loginpass).equals(bean.getLoginPwd())) {
				lblmessagebox.setText("登陆失败，错误代码102");
				return;
			}
			
			isOK=true;
			if(isOK) {
				lblmessagebox.setText("登陆成功！");
				this.setVisible(false);
				StudentMainFrm mainFrm=new StudentMainFrm();
//				StudentUpdatePwdFrm suFrm=new StudentUpdatePwdFrm();
				txtLoginName.setText("");
				txtLoginPass.setText("");
				mainFrm.loginName=loginname;
//				suFrm.loginName=loginname;
//				suFrm.customLoad();
//				suFrm.setVisible(true);
				mainFrm.customLoad();
				mainFrm.loginName=loginname;
				mainFrm.customLoad();
				mainFrm.setVisible(true);
			}else {
				lblmessagebox.setText("登陆失败！");
			}
		}
	}
	
	public void btnreset_click(ActionEvent e) {
		this.txtLoginName.setText("");
		this.txtLoginPass.setText("");
		this.lblmessagebox.setText("您已成功重置!");
		this.rdoManager.setSelected(true);
	}
}
