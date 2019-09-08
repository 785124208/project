package edu.prj.ui.Paper;


import javax.swing.*;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoom;
import edu.prj.bean.Paper;
import edu.prj.bean.Question;
import edu.prj.bean.Student;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaperUpdateFrm extends JFrame{
	
	private JPanel container;
	//问题类型
	private JLabel lblPName;
	private JTextField txtPName;
	//题目
	private JLabel lblTotalScore;
	private JTextField txtTotalScore;
	//选项
	private JLabel lblPerScore;
	private JTextField txtPerScore;
	private JLabel lblPaperNum;
	private JTextField txtPaperNum;
	private JLabel lblExamMim;
	private JTextField txtExamMim;
	private JLabel lblHasGenerate;
	private JTextField txtHasGenerate;
	
	private JLabel lblAnswer;
	private JComboBox<IdText>cboAnswer;
	
	private JLabel lblSubjectId;
	private JComboBox<IdText>cboSubjectId;
	
	private JButton btnSubmit;
	private JButton btnReset;
	
	private JLabel lblMsg;
	
	public  PaperListFrm paperListFrm=null;
	
	public PaperUpdateFrm() {
		initUI();
		bindEvent();
		customLoad();
	}
	
	public void initUI() {
		int width=500;
		int height=400;
		this.setSize(width, height);
		this.setTitle("修改考卷");
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
		lblPName=new JLabel();
		lblPName.setText("试卷名称");
		lblPName.setBounds(30, 50, 100, 30);
		container.add(lblPName);
		
		txtPName=new JTextField();
		txtPName.setBounds(100, 50, 100, 30);
		container.add(txtPName);
		//问题
		lblTotalScore=new JLabel();
		lblTotalScore.setText("总分");
		lblTotalScore.setBounds(280, 50, 100, 30);
		container.add(lblTotalScore);
		
		txtTotalScore=new JTextField();
		txtTotalScore.setBounds(350, 50, 100, 30);
		container.add(txtTotalScore);
		
		//选项
		lblPerScore=new JLabel();
		lblPerScore.setText("每题分数");
		lblPerScore.setBounds(30, 90, 100, 30);
		container.add(lblPerScore);
		
		txtPerScore=new JTextField();
		txtPerScore.setBounds(100, 90, 100, 30);
		container.add(txtPerScore);
		
		lblPaperNum=new JLabel();
		lblPaperNum.setText("题目数");
		lblPaperNum.setBounds(280, 90, 100, 30);
		container.add(lblPaperNum);
		
		txtPaperNum=new JTextField();
		txtPaperNum.setBounds(350, 90, 100, 30);
		container.add(txtPaperNum);
		
		lblExamMim=new JLabel();
		lblExamMim.setText("考试分钟");
		lblExamMim.setBounds(30, 130, 100, 30);
		container.add(lblExamMim);
		
		txtExamMim=new JTextField();
		txtExamMim.setBounds(100, 130, 100, 30);
		container.add(txtExamMim);
		
		lblHasGenerate=new JLabel();
		lblHasGenerate.setText("是否生成");
		lblHasGenerate.setBounds(280, 130, 100, 30);
		container.add(lblHasGenerate);
		
		txtHasGenerate=new JTextField();
		txtHasGenerate.setBounds(350, 130, 100, 30);
		container.add(txtHasGenerate);
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
		idTextListB.add(new IdText(0L,"2019-9-7"));
		idTextListB.add(new IdText(1L,"2019-9-8"));
		idTextListB.add(new IdText(2L,"2019-9-9"));
		idTextListB.add(new IdText(3L,"2019-9-10"));
		idTextListB.add(new IdText(4L,"2019-9-11"));
		idTextListB.add(new IdText(5L,"2019-9-12"));
		idTextListB.add(new IdText(6L,"2019-9-13"));
		idTextListB.add(new IdText(7L,"2019-9-14"));
		IdTextModel modelB=new IdTextModel(idTextListB);
		cboAnswer.setModel(modelB);
		
		
//		java.util.List<Paper> listB=paperService.list();
//		//将List<Paper>转为list<IdText>
//		java.util.List<IdText> idTextListB=new java.util.ArrayList<IdText>();
//		for(Paper item : listB) {
//			idTextListB.add(new IdText(item.getPaperId(),item.getSubjectid().toString()));
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
		idTextListA.add(new IdText(0L,"2019-9-20"));
		idTextListA.add(new IdText(1L,"2019-9-21"));
		idTextListA.add(new IdText(2L,"2019-9-22"));
		idTextListA.add(new IdText(3L,"2019-9-23"));
		idTextListA.add(new IdText(4L,"2019-9-24"));
		idTextListA.add(new IdText(5L,"2019-9-25"));
		idTextListA.add(new IdText(6L,"2019-9-26"));
		idTextListA.add(new IdText(7L,"2019-9-27"));
		IdTextModel modelA=new IdTextModel(idTextListA);
		cboSubjectId.setModel(modelA);
		
//		java.util.List<Paper> listA=paperService.list();
//		//将List<Paper>转为list<IdText>
//		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
//		for(Paper item : listA) {
//			idTextListA.add(new IdText(item.getPaperId(),item.getSubjectid().toString()));
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
		this.setTitle("添加题目");
	}
	
	private void window_Closing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(paperListFrm!=null) {
			paperListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	PaperService paperService=new PaperServiceImpl();
	@SuppressWarnings("unused")
	private void btnSubmit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		String papername=txtPName.getText();
		String totalScore=txtTotalScore.getText();
		String perScore=txtPerScore.getText();
		String questionNum=txtPaperNum.getText();
		String examMinute=txtExamMim.getText();
		String hasGenerate=txtHasGenerate.getText();
		IdText startOn=(IdText)cboAnswer.getSelectedItem();
		IdText endOn=(IdText)cboSubjectId.getSelectedItem();
		//为空判断
		if(SysFun.isNullOrEmpty(papername)) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		if(totalScore==null || totalScore.isEmpty()) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		if(perScore==null || perScore.isEmpty()) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		if(questionNum==null || questionNum.isEmpty()) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		if(examMinute==null || examMinute.isEmpty()) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		if(hasGenerate==null || hasGenerate.isEmpty()) {
			lblMsg.setText("提示：不得为空！");
			return;
		}
		Long qNum=Long.parseLong(questionNum);
		Long eMin=Long.parseLong(examMinute);
		Long hasG=Long.parseLong(hasGenerate);
		Float totalS=Float.parseFloat(totalScore);
		Float perS=Float.parseFloat(perScore);
//		Long or=0L;
//		if(rdoNo.isSelected()) {
//			or=0L;
//		}else if(rdoYes.isSelected()) {
//			or=1L;
//		}
		Date selectItem = null;
		Long select=startOn.getId();
//		if(select==0L) {
//			selectItem=2019-9-7;
//		}else if(select==1L) {
//			selectItem="B";
//		}else if(select==2L) {
//			selectItem="C";
//		}else if(select==3L) {
//			selectItem="D";
//		}else if(select==4L) {
//			selectItem="E";
//		}else if(select==5L) {
//			selectItem="F";
//		}else if(select==null) {
//			lblMsg.setText("提示：请设置日期！");
//			return;
//		}
		
		Long selectsubject = null;
		Long end=endOn.getId();
		if(end==0L) {
			selectsubject=1L;
		}else if(end==1L) {
			selectsubject=2L;
		}else if(end==2L) {
			selectsubject=3L;
		}else if(end==null) {
			lblMsg.setText("提示：请设置正确答案！");
			return;
		}
//		Paper item=paperService.loadByName(question);
//		
//		if(item!=null) {
//			lblMsg.setText("提示：该管理员账号已存在！");
//			return;
//		}
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		Date date1, date2;
		Paper bean=new Paper();
		try {
			String[] dates = {"2019-9-7", "2019-9-8", "2019-9-9", "2019-9-10","2019-9-11","2019-9-12","2019-9-13","2019-9-14"};
			String[] dates2 = {"2019-9-20", "2019-9-21", "2019-9-22", "2019-9-23","2019-9-24", "2019-9-25", "2019-9-26","2019-9-27"};
			date1 = f.parse(dates[cboAnswer.getSelectedIndex()]);
			date2 = f.parse(dates2[cboAnswer.getSelectedIndex()]);
			bean.setPaperId(pk);
			bean.setStartOn(date1);
			bean.setEndOn(date2);
			bean.setPaperName(papername);
			bean.setTotalScore(totalS);
			bean.setPerScore(perS);
			bean.setQuestionNum(qNum);
			bean.setExamMinute(eMin);
//			System.out.println(bean.getStartOn());
//			System.out.println(bean.getEndOn());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		bean.setStartOn(selectItem);
//		bean.setEndOn(selectItem);
	
	
		bean.setHasGenerate(1L);
		Long result=0L;
		String errMsg=null;
	
			result=paperService.insert(bean);
			System.out.println(result);
		
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "添加成功！");
			if(paperListFrm!=null) {
				paperListFrm.btnReset_click(null);
				paperListFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "添加失败！");
		}

		
		if(paperListFrm!=null) {
			paperListFrm.btnReset_click(null); 
			paperListFrm.setVisible(true);
		}
		this.dispose();
	}
	
	private void btnReset_click(ActionEvent e) {
		txtPName.setText("");
		txtTotalScore.setText("");
		txtPerScore.setText("");
		txtPaperNum.setText("");
		txtExamMim.setText("");
		txtHasGenerate.setText("");
		cboAnswer.setSelectedItem("-请选择-");
		cboSubjectId.setSelectedItem("-请选择-");
//		rdoNo.setSelected(true);//重置为选中
		lblMsg.setText("提示：重置成功");
	}
	
	Long pk=null;
	
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null, "主键值为空，加载失败！");
			return;
		}
		
		Paper bean=paperService.load(pk);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		
		this.setTitle(this.getTitle()+pk);
		txtPName.setText("");
		txtTotalScore.setText("");
		txtPerScore.setText("");
		txtPaperNum.setText("");
		txtExamMim.setText("");
		txtHasGenerate.setText("");
		cboAnswer.setSelectedItem("-请选择-");
		cboSubjectId.setSelectedItem("-请选择-");
//		rdoNo.setSelected(true);//重置为选中
		lblMsg.setText("提示：重置成功");
	}
}
