package edu.prj.ui.Teacher;


import javax.swing.*;

import com.liuvei.common.SysFun;

import edu.prj.bean.Teacher;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TeacherUpdateFrm extends JFrame{
	
	
	private JPanel container;
	
	private JLabel lblManagerName;
	private JTextField txtManagerName;
	
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	
	private JLabel lblPassword2;
	private JPasswordField txtPassword2;
	
	private JLabel lblnickName;
	private JTextField txtnickName;
	
	private JLabel lblYesOrNo;
	private ButtonGroup btnYesOrNo;	//是否单选按钮组
	private JRadioButton rdoYes;	//是单选按钮
	private JRadioButton rdoNo;	//否单选按钮
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	
	public  TeacherListFrm teacherListFrm=null;
	
	public TeacherUpdateFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=450;
		int height=350;
		this.setSize(width, height);
		this.setTitle("添加书籍");
		this.setResizable(false);
		
		int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
		int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
		this.setLocation(x,y);
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		custominitUI();
	}
	
	public void custominitUI() {
		//lgingname
		lblManagerName=new JLabel();
		lblManagerName.setText("用户名");
		lblManagerName.setBounds(140, 10, 100, 30);
		container.add(lblManagerName);
		
		txtManagerName=new JTextField();
		txtManagerName.setBounds(220, 10, 100, 30);
		container.add(txtManagerName);
		//密码
		lblPassword=new JLabel();
		lblPassword.setText("密   码");
		lblPassword.setBounds(140, 60, 100, 30);
		container.add(lblPassword);
		
		txtPassword=new JPasswordField();
		txtPassword.setBounds(220, 60, 100, 30);
		container.add(txtPassword);
		
		
		//再次输入密码
		lblPassword2=new JLabel();
		lblPassword2.setText("确认密码");
		lblPassword2.setBounds(140, 110, 100, 30);
		container.add(lblPassword2);
		
		txtPassword2=new JPasswordField();
		txtPassword2.setBounds(220, 110, 100, 30);
		container.add(txtPassword2);
		//昵称
		lblnickName=new JLabel();
		lblnickName.setText("昵称");
		lblnickName.setBounds(140, 160, 100, 30);
		container.add(lblnickName);
		
		txtnickName=new JTextField();
		txtnickName.setBounds(220, 160, 100, 30);
		container.add(txtnickName);
		
		btnSubmit=new JButton();
		btnSubmit.setBounds(140, 230, 80, 30);
		btnSubmit.setText("提交");
		container.add(btnSubmit);
		
		btnReset=new JButton();
		btnReset.setBounds(240, 230, 80, 30);
		btnReset.setText("重置");
		container.add(btnReset);
		
		//提示信息
		lblMsg=new JLabel();
		lblMsg.setText("提示");
		lblMsg.setBounds(140, 270, 200, 30);
		container.add(lblMsg);
		
		//是否
		lblYesOrNo=new JLabel();
		lblYesOrNo.setText("是否禁用");
		lblYesOrNo.setBounds(140, 200, 100, 30);
		container.add(lblYesOrNo);
		//是单选按钮
		rdoYes=new JRadioButton("是");
		rdoYes.setBounds(200, 200, 50, 30);
		container.add(rdoYes);
		//否单选按钮
		rdoNo=new JRadioButton("否");
		rdoNo.setBounds(260, 200, 50, 30);
		container.add(rdoNo);
		//将是和否放到同一个group里只能选择一个
		btnYesOrNo=new ButtonGroup();
		btnYesOrNo.add(rdoYes);
		btnYesOrNo.add(rdoNo);
		rdoNo.setSelected(true);//默认选中否
	}
	
	public void bindEvent() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				window_Closing(e);
			}
		});
		
		custombindEvent();
	}
	
	public void custombindEvent() {
		
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}
		});
		
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnReset_click(e);
			}
		});
	}

	public void customLoad() {
		this.setTitle("添加管理员");
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(teacherListFrm!=null) {
			teacherListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	TeacherService teacherService=new TeacherServiceImpl();
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");
		// TODO Auto-generated method stub
		String loginname=txtManagerName.getText();
		String loginpwd=txtPassword.getText();
		String loginpwd2=txtPassword2.getText();
		String name=txtnickName.getText();
		
		
		//为空判断
		if(SysFun.isNullOrEmpty(loginname)) {
			lblMsg.setText("提示：用户名不得为空！");
			return;
		}
		if(loginpwd==null || loginpwd.isEmpty()) {
			lblMsg.setText("提示：密码不得为空！");
			return;
		}
		if(name==null || name.isEmpty()) {
			lblMsg.setText("提示：昵称不得为空！");
			return;
		}
		if(!loginpwd.equals(loginpwd2)) {
			lblMsg.setText("提示：两次输入密码不一致！");
			return;
		}
		Long or=0L;
		if(rdoNo.isSelected()) {
			or=0L;
		}else if(rdoYes.isSelected()) {
			or=1L;
		}
		

//		Teacher item=teacherService.loadByName(loginname);
//		
//		if(item!=null) {
//			lblMsg.setText("提示：该管理员账号已存在！");
//			return;
//		}
		Teacher bean=new Teacher();
		bean.setTeacherId(pk);
		bean.setLoginName(loginname);
		bean.setLoginPwd(SysFun.md5(loginpwd));
		bean.setNickName(name);
		bean.setIsDisabled(or);
		
		Long result=0L;
		String errMsg=null;
		try {
		result=teacherService.update(bean);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "修改成功！");
			if(teacherListFrm!=null) {
				teacherListFrm.btnReset_click(null);
				teacherListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "修改失败！");
		}

		
		if(teacherListFrm!=null) {
			teacherListFrm.btnReset_click(null); 
			teacherListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		Teacher bean=teacherService.load(pk);
		txtManagerName.setText(bean.getLoginName());
		txtPassword.setText(bean.getLoginPwd());
		txtPassword2.setText(bean.getLoginPwd());
		txtnickName.setText(bean.getNickName());
		lblMsg.setText("提示：重置成功");
	}
	
	Long pk=null;
	
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null, "主键值为空，加载失败！");
			return;
		}
		
		Teacher bean=teacherService.load(pk);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		
		this.setTitle(this.getTitle()+pk);
		txtManagerName.setText(bean.getLoginName());
		txtPassword.setText(bean.getLoginPwd());
		txtPassword2.setText(bean.getLoginPwd());
		txtnickName.setText(bean.getNickName());
	}
}
