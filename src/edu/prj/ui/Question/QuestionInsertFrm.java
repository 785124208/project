package edu.prj.ui.Question;


import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoom;
import edu.prj.bean.Question;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.QuestionServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class QuestionInsertFrm extends JFrame{
	
	private JPanel container;
	//问题类型
	private JLabel lblQType;
	private JTextField txtQType;
	//题目
	private JLabel lblQuestion;
	private JTextField txtQuestion;
	//选项
	private JLabel lblItemA;
	private JTextField txtItemA;
	private JLabel lblItemB;
	private JTextField txtItemB;
	private JLabel lblItemC;
	private JTextField txtItemC;
	private JLabel lblItemD;
	private JTextField txtItemD;
	private JLabel lblItemE;
	private JTextField txtItemE;
	private JLabel lblItemF;
	private JTextField txtItemF;
	
	private JLabel lblAnswer;
	private JComboBox<IdText>cboAnswer;
	
	private JLabel lblSubjectId;
	private JComboBox<IdText>cboSubjectId;
	
	private JLabel lblTag;
	private JTextField txtTag;
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	
	public  QuestionListFrm questionListFrm=null;
	
	public QuestionInsertFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=500;
		int height=400;
		this.setSize(width, height);
		this.setTitle("添加题目");
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
		lblQType=new JLabel();
		lblQType.setText("题目类型");
		lblQType.setBounds(30, 50, 100, 30);
		container.add(lblQType);
		
		txtQType=new JTextField();
		txtQType.setBounds(100, 50, 100, 30);
		container.add(txtQType);
		//问题
		lblQuestion=new JLabel();
		lblQuestion.setText("问题");
		lblQuestion.setBounds(280, 50, 100, 30);
		container.add(lblQuestion);
		
		txtQuestion=new JTextField();
		txtQuestion.setBounds(350, 50, 100, 30);
		container.add(txtQuestion);
		
		//选项
		lblItemA=new JLabel();
		lblItemA.setText("选项A");
		lblItemA.setBounds(30, 90, 100, 30);
		container.add(lblItemA);
		
		txtItemA=new JTextField();
		txtItemA.setBounds(100, 90, 100, 30);
		container.add(txtItemA);
		
		lblItemB=new JLabel();
		lblItemB.setText("选项B");
		lblItemB.setBounds(280, 90, 100, 30);
		container.add(lblItemB);
		
		txtItemB=new JTextField();
		txtItemB.setBounds(350, 90, 100, 30);
		container.add(txtItemB);
		
		lblItemC=new JLabel();
		lblItemC.setText("选项C");
		lblItemC.setBounds(30, 130, 100, 30);
		container.add(lblItemC);
		
		txtItemC=new JTextField();
		txtItemC.setBounds(100, 130, 100, 30);
		container.add(txtItemC);
		
		lblItemD=new JLabel();
		lblItemD.setText("选项D");
		lblItemD.setBounds(280, 130, 100, 30);
		container.add(lblItemD);
		
		txtItemD=new JTextField();
		txtItemD.setBounds(350, 130, 100, 30);
		container.add(txtItemD);
		
		lblItemE=new JLabel();
		lblItemE.setText("选项E");
		lblItemE.setBounds(30, 170, 100, 30);
		container.add(lblItemE);
		
		txtItemE=new JTextField();
		txtItemE.setBounds(100, 170, 100, 30);
		container.add(txtItemE);
		
		lblItemF=new JLabel();
		lblItemF.setText("选项F");
		lblItemF.setBounds(280, 170, 100, 30);
		container.add(lblItemF);
		
		txtItemF=new JTextField();
		txtItemF.setBounds(350, 170, 100, 30);
		container.add(txtItemF);
		//正确答案
		lblAnswer=new JLabel();
		lblAnswer.setText("正确答案");
		lblAnswer.setBounds(30, 210, 100, 30);
		container.add(lblAnswer);
		
		cboAnswer=new JComboBox<IdText>();
		cboAnswer.setBounds(100, 210, 100, 30);
		container.add(cboAnswer);
		
		//是否选择
		java.util.List<IdText> idTextListB=new java.util.ArrayList<IdText>();
		idTextListB.add(new IdText(0L,"A"));
		idTextListB.add(new IdText(1L,"B"));
		idTextListB.add(new IdText(2L,"C"));
		idTextListB.add(new IdText(3L,"D"));
		idTextListB.add(new IdText(4L,"E"));
		idTextListB.add(new IdText(5L,"F"));
		IdTextModel modelB=new IdTextModel(idTextListB);
		cboAnswer.setModel(modelB);
		
		
//		java.util.List<Question> listB=questionService.list();
//		//将List<Question>转为list<IdText>
//		java.util.List<IdText> idTextListB=new java.util.ArrayList<IdText>();
//		for(Question item : listB) {
//			idTextListB.add(new IdText(item.getQuestionId(),item.getSubjectid().toString()));
//		}
//		IdTextModel modelB=new IdTextModel(idTextListB);
//		cboAnswer.setModel(modelB);
		//所属科目
		lblSubjectId=new JLabel();
		lblSubjectId.setText("所属科目");
		lblSubjectId.setBounds(280, 210, 100, 30);
		container.add(lblSubjectId);
		
		cboSubjectId=new JComboBox<IdText>();
		cboSubjectId.setBounds(350, 210, 100, 30);
		container.add(cboSubjectId);
		
		
		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
		idTextListA.add(new IdText(0L,"1（语文）"));
		idTextListA.add(new IdText(1L,"2（数学）"));
		idTextListA.add(new IdText(2L,"3（英语）"));
		IdTextModel modelA=new IdTextModel(idTextListA);
		cboSubjectId.setModel(modelA);
		
//		java.util.List<Question> listA=questionService.list();
//		//将List<Question>转为list<IdText>
//		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
//		for(Question item : listA) {
//			idTextListA.add(new IdText(item.getQuestionId(),item.getSubjectid().toString()));
//		}
//		IdTextModel modelA=new IdTextModel(idTextListA);
//		cboSubjectId.setModel(modelA);
		
		btnSubmit=new JButton();
		btnSubmit.setBounds(140, 290, 80, 30);
		btnSubmit.setText("提交");
		container.add(btnSubmit);
		
		btnReset=new JButton();
		btnReset.setBounds(240, 290, 80, 30);
		btnReset.setText("重置");
		container.add(btnReset);
		
		//提示信息
		lblMsg=new JLabel();
		lblMsg.setText("提示");
		lblMsg.setBounds(140, 320, 200, 30);
		container.add(lblMsg);
		
		
		//标签
		lblTag=new JLabel();
		lblTag.setText("标签");
		lblTag.setBounds(140, 250, 100, 30);
		container.add(lblTag);
		
		txtTag=new JTextField();
		txtTag.setBounds(220, 250, 100, 30);
		container.add(txtTag);
		
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
		//提交按钮
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}
		});
		//重置按钮
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnReset_click(e);
			}
		});
	}

	public void customLoad() {
		this.setTitle("");
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(questionListFrm!=null) {
			questionListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	QuestionService questionService=new QuestionServiceImpl();
	@SuppressWarnings("unused")
	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String type=txtQType.getText();
		String question=txtQuestion.getText();
		String itemA=txtItemA.getText();
		String itemB=txtItemB.getText();
		String itemC=txtItemC.getText();
		String itemD=txtItemD.getText();
		String itemE=txtItemE.getText();
		String itemF=txtItemF.getText();
		IdText answer=(IdText)cboAnswer.getSelectedItem();
		IdText subject=(IdText)cboSubjectId.getSelectedItem();
		String tag=txtTag.getText();

		//为空判断
		if(SysFun.isNullOrEmpty(type)) {
			lblMsg.setText("提示：用户名不得为空！");
			return;
		}
		if(question==null || question.isEmpty()) {
			lblMsg.setText("提示：密码不得为空！");
			return;
		}
//		if(name==null || name.isEmpty()) {
//			lblMsg.setText("提示：昵称不得为空！");
//			return;
//		}
		if(tag==null || tag.isEmpty()) {
			lblMsg.setText("提示：备注不得为空！");
			return;
		}
		Long qtype=Long.parseLong(type);
		
//		Long or=0L;
//		if(rdoNo.isSelected()) {
//			or=0L;
//		}else if(rdoYes.isSelected()) {
//			or=1L;
//		}
		String selectItem = null;
		Long select=answer.getId();
		if(select==0L) {
			selectItem="A";
		}else if(select==1L) {
			selectItem="B";
		}else if(select==2L) {
			selectItem="C";
		}else if(select==3L) {
			selectItem="D";
		}else if(select==4L) {
			selectItem="E";
		}else if(select==5L) {
			selectItem="F";
		}else if(select==null) {
			lblMsg.setText("提示：请设置正确答案！");
			return;
		}
		
		Long selectsubject = null;
		Long subjectid=subject.getId();
		if(subjectid==0L) {
			selectsubject=1L;
		}else if(subjectid==1L) {
			selectsubject=2L;
		}else if(select==2L) {
			selectsubject=3L;
		}else if(select==null) {
			lblMsg.setText("提示：请设置正确答案！");
			return;
		}
		Question item=questionService.loadByName(question);
		
		if(item!=null) {
			lblMsg.setText("提示：该管理员账号已存在！");
			return;
		}
		Question bean=new Question();
		bean.setqType(qtype);
		bean.setQuestion(question);
		bean.setItemA(itemA);
		bean.setItemB(itemB);
		bean.setItemC(itemC);
		bean.setItemD(itemD);
		bean.setItemE(itemE);
		bean.setItemF(itemF);
		bean.setAnswer(selectItem);
		bean.setSubjectid(selectsubject);
		bean.setTag(tag);
		
		Long result=0L;
		String errMsg=null;
		try {
		result=questionService.insert(bean);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "添加成功！");
			if(questionListFrm!=null) {
				questionListFrm.btnReset_click(null);
				questionListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "添加失败！");
		}

		
		if(questionListFrm!=null) {
			questionListFrm.btnReset_click(null); 
			questionListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		txtQType.setText("");
		txtQuestion.setText("");
		txtItemA.setText("");
		txtItemB.setText("");
		txtItemC.setText("");
		txtItemD.setText("");
		txtItemE.setText("");
		txtItemF.setText("");
		cboAnswer.setSelectedItem("-请选择-");
		cboSubjectId.setSelectedItem("-请选择-");
		txtTag.setText("");
//		rdoNo.setSelected(true);//重置为选中
		lblMsg.setText("提示：重置成功");
	}
	

}
