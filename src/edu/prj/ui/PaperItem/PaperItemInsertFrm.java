package edu.prj.ui.PaperItem;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Paper;
import edu.prj.bean.PaperItem;
import edu.prj.bean.Question;
import edu.prj.dao.PaperDao;
import edu.prj.dao.PaperItemDao;
import edu.prj.dao.QuestionDao;
import edu.prj.dao.impl.PaperDaoImpl;
import edu.prj.dao.impl.PaperItemDaoImpl;
import edu.prj.dao.impl.QuestionDaoImpl;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

public class PaperItemInsertFrm  extends JFrame implements java.io.Serializable{
	public PaperItemListFrm paperItemListFrm=null;
	private JPanel container;
	private JLabel lblpaperId;
	private JButton submit,cancel;
	public JComboBox<IdText>cbopaperId;
	private JLabel lblquestion1,lblquestion2,lblquestion3,lblquestion4,lblquestion5;
	public JComboBox<IdText> q1;
	public JComboBox<IdText> q2;
	public JComboBox<IdText> q3;
	public JComboBox<IdText> q4;
	public JComboBox<IdText> q5;
	
	public PaperItemInsertFrm() {
		initUI();
		bindEvent();
	}

	private void bindEvent() {
		// TODO Auto-generated method stub
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				submit_click(e);
			}
		});
	}

	PaperItemService paperItemService=new PaperItemServiceImpl();
	PaperService paperService=new PaperServiceImpl();
	QuestionService questionService=new QuestionServiceImpl();
	
	public void submit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		IdText paperId=(IdText)cbopaperId.getSelectedItem();
		IdText question1=(IdText)q1.getSelectedItem();
		IdText question2=(IdText)q2.getSelectedItem();
		IdText question3=(IdText)q3.getSelectedItem();
		IdText question4=(IdText)q4.getSelectedItem();
		IdText question5=(IdText)q5.getSelectedItem();
		if(paperId==null) {
			JOptionPane.showMessageDialog(null, "请选择试卷！！！");
			return;
		}
		if(question1==null||question2==null||question3==null||question4==null||question5==null) {
			JOptionPane.showMessageDialog(null, "不可有空题目！");
			return;
		}
		if(q1.getSelectedIndex()==q2.getSelectedIndex()||q1.getSelectedIndex()==q3.getSelectedIndex()||q1.getSelectedIndex()==q4.getSelectedIndex()||q1.getSelectedIndex()==q5.getSelectedIndex()) {
			JOptionPane.showMessageDialog(null, "题目不可重复！");
			return;
		}
		if(q2.getSelectedIndex()==q3.getSelectedIndex()||q2.getSelectedIndex()==q4.getSelectedIndex()||q2.getSelectedIndex()==q5.getSelectedIndex()) {
			JOptionPane.showMessageDialog(null, "题目不可重复！");
			return;
		}
		if(q3.getSelectedIndex()==q4.getSelectedIndex()||q3.getSelectedIndex()==q5.getSelectedIndex()) {
			JOptionPane.showMessageDialog(null, "题目不可重复！");
			return;
		}
		if(q4.getSelectedIndex()==q5.getSelectedIndex()) {
			JOptionPane.showMessageDialog(null, "题目不可重复！");
			return;
		}
		
		Paper paper=paperService.load(Long.parseLong(paperId.getText()));
		PaperItem item=paperItemService.loadByName(paperId.getText());
		Question question=questionService.loadByName(question1.getText());
		Question answer=questionService.loadByAnswer(question1.getText());
		PaperItem bean=new PaperItem();
		bean.setPaperId(Long.parseLong(paperId.getText()));
		bean.setQuestionId(question.getQuestionId());
		bean.setAnswer(question.getAnswer());
		bean.setScore((float) 20);
		
		Question q2=questionService.loadByName(question2.getText());
		PaperItem bean2=new PaperItem();
		bean2.setPaperId(Long.parseLong(paperId.getText()));
		bean2.setQuestionId(q2.getQuestionId());
		bean2.setAnswer(q2.getAnswer());
		bean2.setScore((float) 20);
		
		Question q3=questionService.loadByName(question3.getText());
		PaperItem bean3=new PaperItem();
		bean3.setPaperId(Long.parseLong(paperId.getText()));
		bean3.setQuestionId(q3.getQuestionId());
		bean3.setAnswer(q3.getAnswer());
		bean3.setScore((float) 20);
		
		Question q4=questionService.loadByName(question4.getText());
		PaperItem bean4=new PaperItem();
		bean4.setPaperId(Long.parseLong(paperId.getText()));
		bean4.setQuestionId(q4.getQuestionId());
		bean4.setAnswer(q4.getAnswer());
		bean4.setScore((float) 20);
		
		Question q5=questionService.loadByName(question5.getText());
		PaperItem bean5=new PaperItem();
		bean5.setPaperId(Long.parseLong(paperId.getText()));
		bean5.setQuestionId(q5.getQuestionId());
		bean5.setAnswer(q5.getAnswer());
		bean5.setScore((float) 20);
		
		
		
		Long result=0L;
		String errMsg=null;
		try {
		result=paperItemService.insert(bean);
		result=paperItemService.insert(bean2);
		result=paperItemService.insert(bean3);
		result=paperItemService.insert(bean4);
		result=paperItemService.insert(bean5);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "添加成功！");
			if(paperItemListFrm!=null) {
				paperItemListFrm.btnReset_click(null);
				paperItemListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "添加失败！");
		}

		
		if(paperItemListFrm!=null) {
			paperItemListFrm.btnReset_click(null); 
			paperItemListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void initUI() {
		// TODO Auto-generated method stub
		int width=300;
		int height=500;
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

	private void custominitUI() {
		// TODO Auto-generated method stub
		lblpaperId=new JLabel();
		lblpaperId.setText("试卷ID");
		lblpaperId.setBounds(30, 30, 100, 30);
		container.add(lblpaperId);
		
		cbopaperId=new JComboBox<IdText>();
		cbopaperId.setBounds(120, 30, 100, 30);
		container.add(cbopaperId);
		PaperDao paperDao=new PaperDaoImpl();
		PaperItemDao paperItemDao=new PaperItemDaoImpl();
		
		java.util.List<Paper> listA=paperDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
		for(Paper item : listA) {
			idTextListA.add(new IdText(item.getPaperId(),item.getPaperId().toString()));
		}
		IdTextModel modelA=new IdTextModel(idTextListA);
		cbopaperId.setModel(modelA);
		
		
		lblquestion1=new JLabel();
		lblquestion1.setText("题目一");
		lblquestion1.setBounds(30, 90, 100, 30);
		container.add(lblquestion1);
		
		
		q1=new JComboBox<IdText>();
		q1.setBounds(120, 90, 100, 30);
		container.add(q1);
		QuestionDao questionDao=new QuestionDaoImpl();
		java.util.List<Question> listB=questionDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListB=new java.util.ArrayList<IdText>();
		for(Question item : listB) {
			idTextListB.add(new IdText(item.getQuestionId(),item.getQuestion()));
		}
		IdTextModel modelB=new IdTextModel(idTextListB);
		q1.setModel(modelB);
		
		
		lblquestion2=new JLabel();
		lblquestion2.setText("题目二");
		lblquestion2.setBounds(30, 150, 100, 30);
		container.add(lblquestion2);
		
		
		q2=new JComboBox<IdText>();
		q2.setBounds(120, 150, 100, 30);
		container.add(q2);
		java.util.List<Question> listC=questionDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListC=new java.util.ArrayList<IdText>();
		for(Question item : listC) {
			idTextListC.add(new IdText(item.getQuestionId(),item.getQuestion()));
		}
		IdTextModel modelC=new IdTextModel(idTextListC);
		q2.setModel(modelC);
		
		lblquestion3=new JLabel();
		lblquestion3.setText("题目三");
		lblquestion3.setBounds(30, 210, 100, 30);
		container.add(lblquestion3);
		
		
		q3=new JComboBox<IdText>();
		q3.setBounds(120, 210, 100, 30);
		container.add(q3);
		java.util.List<Question> listD=questionDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListD=new java.util.ArrayList<IdText>();
		for(Question item : listD) {
			idTextListD.add(new IdText(item.getQuestionId(),item.getQuestion()));
		}
		IdTextModel modelD=new IdTextModel(idTextListD);
		q3.setModel(modelD);
		
		lblquestion4=new JLabel();
		lblquestion4.setText("题目四");
		lblquestion4.setBounds(30, 270, 100, 30);
		container.add(lblquestion4);
		
		
		q4=new JComboBox<IdText>();
		q4.setBounds(120, 270, 100, 30);
		container.add(q4);
		java.util.List<Question> listE=questionDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListE=new java.util.ArrayList<IdText>();
		for(Question item : listE) {
			idTextListD.add(new IdText(item.getQuestionId(),item.getQuestion()));
		}
		IdTextModel modelE=new IdTextModel(idTextListD);
		q4.setModel(modelE);
		
		lblquestion5=new JLabel();
		lblquestion5.setText("题目五");
		lblquestion5.setBounds(30, 330, 100, 30);
		container.add(lblquestion5);
		
		
		q5=new JComboBox<IdText>();
		q5.setBounds(120, 330, 100, 30);
		container.add(q5);
		java.util.List<Question> listF=questionDao.list();
		//将List<Student>转为list<IdText>
		java.util.List<IdText> idTextListF=new java.util.ArrayList<IdText>();
		for(Question item : listF) {
			idTextListF.add(new IdText(item.getQuestionId(),item.getQuestion()));
		}
		IdTextModel modelF=new IdTextModel(idTextListF);
		q5.setModel(modelF);
		
		submit=new JButton();
		submit.setBounds(30, 390, 80, 30);
		submit.setText("生成");
		container.add(submit);
		
		cancel=new JButton();
		cancel.setBounds(120, 390, 80, 30);
		cancel.setText("取消");
		container.add(cancel);
	}
}
