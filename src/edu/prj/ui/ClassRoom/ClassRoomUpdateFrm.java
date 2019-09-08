package edu.prj.ui.ClassRoom;


import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoom;
import edu.prj.service.ClassRoomService;
import edu.prj.service.impl.ClassRoomServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ClassRoomUpdateFrm extends JFrame{
	
	private JPanel container;
	
	private JLabel lblRoomName;
	private JTextField txtRoomName;
	
	private JLabel lblGradeId;
	private JComboBox<IdText> cboGradeId;
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	public  ClassRoomListFrm classRoomListFrm=null;
	
	public ClassRoomUpdateFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=300;
		int height=250;
		this.setSize(width, height);
		this.setTitle("教室更新");
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
		//班级名称
		lblRoomName=new JLabel();
		lblRoomName.setText("班级名称");
		lblRoomName.setBounds(60, 10, 100, 30);
		container.add(lblRoomName);
		
		txtRoomName=new JTextField();
		txtRoomName.setBounds(150, 10, 100, 30);
		container.add(txtRoomName);
		//年级ID
		lblGradeId=new JLabel();
		lblGradeId.setText("年级ID");
		lblGradeId.setBounds(60, 60, 100, 30);
		container.add(lblGradeId);
		
		cboGradeId=new JComboBox<IdText>();
		cboGradeId.setBounds(150, 60, 100, 30);
		container.add(cboGradeId);

		btnSubmit=new JButton();
		btnSubmit.setBounds(60, 110, 80, 30);
		btnSubmit.setText("提交");
		container.add(btnSubmit);
		
		btnReset=new JButton();
		btnReset.setBounds(150, 110, 80, 30);
		btnReset.setText("重置");
		container.add(btnReset);
		
		//提示信息
		lblMsg=new JLabel();
		lblMsg.setText("");
		lblMsg.setBounds(100, 160, 200, 30);
		container.add(lblMsg);
		
		java.util.List<ClassRoom> listA=classRoomService.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
		for(ClassRoom item : listA) {
			idTextListA.add(new IdText(item.getRoomId(),item.getGradeId().toString()));
		}
		IdTextModel modelA=new IdTextModel(idTextListA);
		cboGradeId.setModel(modelA);
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
		this.setTitle("教室更新");
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(classRoomListFrm!=null) {
			classRoomListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	ClassRoomService classRoomService=new ClassRoomServiceImpl();
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");
		String roomname=txtRoomName.getText();
		IdText itemGradeId=(IdText)cboGradeId.getSelectedItem();

		//为空判断
		if(cboGradeId.getSelectedIndex()==-1) {
			lblMsg.setText("提示：请选择年级Id！");
			return;
		}
		Long gradeid=itemGradeId.getId();
//
//		ClassRoom item=classRoomService.loadByName(roomname);
//		
//		if(item!=null) {
//			lblMsg.setText("提示：班级已存在！");
//			return;
//		}
		ClassRoom bean=new ClassRoom();
		bean.setRoomId(pk);
		bean.setRoomName(roomname);
		bean.setGradeId(gradeid);

		Long result=0L;
		String errMsg=null;
		try {
		result=classRoomService.update(bean);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "修改成功！");
			if(classRoomListFrm!=null) {
				classRoomListFrm.btnReset_click(null);
				classRoomListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "修改失败！");
		}

		
		if(classRoomListFrm!=null) {
			classRoomListFrm.btnReset_click(null); 
			classRoomListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		ClassRoom bean=classRoomService.load(pk);
		txtRoomName.setText("");
		cboGradeId.setSelectedIndex(0);
		lblMsg.setText("提示：重置成功");
	}
	
	Long pk=null;
	
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null, "主键值为空，加载失败！");
			return;
		}
		
		ClassRoom bean=classRoomService.load(pk);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		
		this.setTitle(this.getTitle()+pk);
		txtRoomName.setText(bean.getRoomName());
		cboGradeId.setSelectedIndex(0);
	}
}
