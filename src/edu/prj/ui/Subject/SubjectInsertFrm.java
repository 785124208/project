package edu.prj.ui.Subject;


import javax.swing.*;

import com.liuvei.common.SysFun;

import edu.prj.bean.Subject;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.SubjectServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SubjectInsertFrm extends JFrame{
	
	private JPanel container;
	
	private JLabel lblSubjectName;
	private JTextField txtSubjectName;
	
	private JLabel lblSTATUS;
	private JTextField txtSTATUS;
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	
	public  SubjectListFrm subjectListFrm=null;
	
	public SubjectInsertFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=300;
		int height=250;
		this.setSize(width, height);
		this.setTitle("添加科目");
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
		lblSubjectName=new JLabel();
		lblSubjectName.setText("科目名");
		lblSubjectName.setBounds(40, 10, 100, 30);
		container.add(lblSubjectName);
		
		txtSubjectName=new JTextField();
		txtSubjectName.setBounds(120, 10, 100, 30);
		container.add(txtSubjectName);
		
		btnSubmit=new JButton();
		btnSubmit.setBounds(40, 110, 80, 30);
		btnSubmit.setText("提交");
		container.add(btnSubmit);
		
		btnReset=new JButton();
		btnReset.setBounds(140, 110, 80, 30);
		btnReset.setText("重置");
		container.add(btnReset);
		
		//提示信息
		lblMsg=new JLabel();
		lblMsg.setText("提示");
		lblMsg.setBounds(40, 160, 200, 30);
		container.add(lblMsg);
		
		//状态
		lblSTATUS=new JLabel();
		lblSTATUS.setText("状态");
		lblSTATUS.setBounds(40, 60, 100, 30);
		container.add(lblSTATUS);
		
		txtSTATUS=new JTextField();
		txtSTATUS.setBounds(120, 60, 100, 30);
		container.add(txtSTATUS);
		
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
		this.setTitle("添加科目");
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(subjectListFrm!=null) {
			subjectListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	SubjectService subjectService=new SubjectServiceImpl();
	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String subjectName=txtSubjectName.getText();
		String STATUS=txtSTATUS.getText();

		//为空判断
		if(SysFun.isNullOrEmpty(subjectName)) {
			lblMsg.setText("提示：用户名不得为空！");
			return;
		}
		if(STATUS==null || STATUS.isEmpty()) {
			lblMsg.setText("提示：教室号不得为空！");
			return;
		}
		
//		Long or=0L;
//		if(rdoNo.isSelected()) {
//			or=0L;
//		}else if(rdoYes.isSelected()) {
//			or=1L;
//		}

		Subject item=subjectService.loadByName(subjectName);
		
		if(item!=null) {
			lblMsg.setText("提示：该管理员账号已存在！");
			return;
		}
		Subject bean=new Subject();
		bean.setSubjectName(subjectName);
		bean.setSTATUS(STATUS);
		
		Long result=0L;
		String errMsg=null;
		try {
		result=subjectService.insert(bean);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "添加成功！");
			if(subjectListFrm!=null) {
				subjectListFrm.btnReset_click(null);
				subjectListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "添加失败！");
		}

		
		if(subjectListFrm!=null) {
			subjectListFrm.btnReset_click(null); 
			subjectListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		txtSubjectName.setText("");
		txtSTATUS.setText("");
		lblMsg.setText("提示：重置成功");
	}
	

}
