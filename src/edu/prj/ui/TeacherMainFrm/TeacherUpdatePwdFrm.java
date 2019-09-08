package edu.prj.ui.TeacherMainFrm;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Manager;
import edu.prj.bean.Teacher;
import edu.prj.bean.Teacher;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;

public class TeacherUpdatePwdFrm extends JFrame{
	private JPanel container;
	
	private JLabel lblOldPwd;
	private JPasswordField txtOldPwd;
	
	private JLabel lblNewPwd;
	private JPasswordField txtNewPwd;

	private JLabel lblNewPwd2;
	private JPasswordField txtNewPwd2;
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	public String loginName="admin";
	public  TeacherMainFrm teacherMainFrm=null;
	
	public TeacherUpdatePwdFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=400;
		int height=300;
		this.setSize(width, height);
		this.setTitle("修改密码");
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
		//旧密码
		lblOldPwd=new JLabel();
		lblOldPwd.setText("旧密码");
		lblOldPwd.setBounds(110, 10, 100, 30);
		container.add(lblOldPwd);
		
		txtOldPwd=new JPasswordField();
		txtOldPwd.setBounds(200, 10, 100, 30);
		container.add(txtOldPwd);
		//新密码
		lblNewPwd=new JLabel();
		lblNewPwd.setText("新密码");
		lblNewPwd.setBounds(110, 60, 100, 30);
		container.add(lblNewPwd);
		
		txtNewPwd=new JPasswordField();
		txtNewPwd.setBounds(200, 60, 100, 30);
		container.add(txtNewPwd);
		
		lblNewPwd2=new JLabel();
		lblNewPwd2.setText("确认密码");
		lblNewPwd2.setBounds(110,110, 100, 30);
		container.add(lblNewPwd2);
		
		txtNewPwd2=new JPasswordField();
		txtNewPwd2.setBounds(200, 110, 100, 30);
		container.add(txtNewPwd2);

		btnSubmit=new JButton();
		btnSubmit.setBounds(110, 160, 80, 30);
		btnSubmit.setText("提交");
		container.add(btnSubmit);
		
		btnReset=new JButton();
		btnReset.setBounds(200, 160, 80, 30);
		btnReset.setText("重置");
		container.add(btnReset);
		
		//提示信息
		lblMsg=new JLabel();
		lblMsg.setText("");
		lblMsg.setBounds(100, 210, 200, 30);
		container.add(lblMsg);
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
		if (loginName != null) {
			this.setTitle(this.getTitle() + "-【" + loginName + "】");
		}
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(teacherMainFrm!=null) {
			teacherMainFrm.setVisible(true);
		}
		this.dispose();
	}
	
	TeacherService teacherService=new TeacherServiceImpl();
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");
		String oldpwd=txtOldPwd.getText();
		String newpwd=txtNewPwd.getText();
		String newpwd2=txtNewPwd2.getText();

		if (loginName == null) {
			lblMsg.setText("12312312312312");
			return;
		}
		//为空判断
		if(oldpwd==null || oldpwd.isEmpty()) {
			lblMsg.setText("提示：旧密码不得为空！");
			return;
		}
		if(newpwd==null || newpwd.isEmpty()) {
			lblMsg.setText("提示：密码不得为空！");
			return;
		}
		if(!newpwd2.equals(newpwd)) {
			lblMsg.setText("提示：两次输入密码不一致！");
			return;
		}
//		Long pk=null;
//		Teacher bean=studentService.load(pk);
//		if(bean==null) {
//			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
//			return;
//		}
		Teacher bean2=teacherService.loadByName(loginName);
		if(bean2==null) {
			lblMsg.setText("提示：账号获取失败！");
			return;
		}
		if(!oldpwd.equals(bean2.getLoginPwd())) {
			lblMsg.setText("提示：旧密码错误！");
			return;
		}
		
//		ClassRoom item=classRoomService.loadByName(roomname);
//		
//		if(item!=null) {
//			lblMsg.setText("提示：班级已存在！");
//			return;
//		}
		bean2.setLoginPwd(SysFun.md5(newpwd2));
		Long result=0L;
		String errMsg=null;
		try {
		result=teacherService.updatepwd(bean2);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "修改成功！");
			if(teacherMainFrm!=null) {
				teacherMainFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "修改失败！");
		}

		
		if(teacherMainFrm!=null) {
			teacherMainFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		Teacher bean=teacherService.loadByName(loginName);
		txtOldPwd.setText("");
		txtNewPwd.setText("");
		txtNewPwd2.setText("");
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
	}
}
