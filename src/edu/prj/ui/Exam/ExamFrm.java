package edu.prj.ui.Exam;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.Exam;
import edu.prj.bean.ExamItem;
import edu.prj.bean.Paper;
import edu.prj.bean.PaperItem;
import edu.prj.bean.Question;
import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.dao.ExamItemDao;
import edu.prj.dao.impl.ExamItemDaoImpl;
import edu.prj.service.ExamItemService;
import edu.prj.service.ExamService;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ExamItemServiceImpl;
import edu.prj.service.impl.ExamServiceImpl;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.StudentMainFrm.StudentMainFrm;

public class ExamFrm extends JFrame{
	private JPanel container;
	int midTime=5400;
	private JTextArea txtAbout;
	private JButton start,submit;
	private JLabel q1,q2,q3,q4,q5,clock,time;
	private JLabel qa1,qa2,qa3,qa4,qa5;
	private JComboBox<IdText>cboq1Ans,cboq2Ans,cboq3Ans,cboq4Ans,cboq5Ans;
	public StudentMainFrm studentMainFrm=null;
	
	public ExamFrm() {
		initUI();
		bindEvent();
		submit.setVisible(false);
//		customLoad();
	}
	
	public void initUI() {
		int width=500;
		int height=450;
		this.setSize(width, height);
		this.setTitle("EXAM");
		this.setResizable(false);
		
		int x=(Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
		int y=(Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
		this.setLocation(x,y);
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		custominitUI();
	}
	
	public void bindEvent() {
		timer=new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer_click(e);
			}			
		});
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Start_click(e);
			}

		});
		
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Submit_click(e);
			}
		});
//		timer.start();
	}
	
	public void custominitUI() {
		//考试提醒
		txtAbout=new JTextArea();
		txtAbout.setText("一、考试时间为90分钟，请点击开始开始作答！"+"\n"+"二、交卷点击提交，若为提交到时间自动交卷！");
		txtAbout.setBounds(10, 10, 350, 50);
		container.add(txtAbout);
		//倒计时
		clock=new JLabel();
		clock.setText("距离考试结束还有：");
		clock.setBounds(370, 5, 100, 15);
		container.add(clock);
		time=new JLabel();
		time.setText("");
		time.setBounds(370, 20, 100, 30);
		container.add(time);
		//题目
		q1=new JLabel();
		q1.setText("试题一：");
		q1.setBounds(10, 70, 100, 30);
		container.add(q1);
		
		qa1=new JLabel();
		qa1.setText("");
		qa1.setBounds(10, 100,200, 30);
		container.add(qa1);
		
		cboq1Ans=new JComboBox<IdText>();
		cboq1Ans.setBounds(350, 70, 100, 30);
		container.add(cboq1Ans);
		
		java.util.List<IdText> idTextListA=new java.util.ArrayList<IdText>();
		idTextListA.add(new IdText(0L,"A"));
		idTextListA.add(new IdText(1L,"B"));
		idTextListA.add(new IdText(2L,"C"));
		idTextListA.add(new IdText(3L,"D"));
		idTextListA.add(new IdText(4L,"E"));
		idTextListA.add(new IdText(5L,"F"));
		IdTextModel modelA=new IdTextModel(idTextListA);
		cboq1Ans.setModel(modelA);
		
		
		q2=new JLabel();
		q2.setText("试题二：");
		q2.setBounds(10, 130, 100, 30);
		container.add(q2);
		
		qa2=new JLabel();
		qa2.setText("");
		qa2.setBounds(10, 160,200, 30);
		container.add(qa2);
		
		cboq2Ans=new JComboBox<IdText>();
		cboq2Ans.setBounds(350, 130, 100, 30);
		container.add(cboq2Ans);
		
		java.util.List<IdText> idTextListB=new java.util.ArrayList<IdText>();
		idTextListB.add(new IdText(0L,"A"));
		idTextListB.add(new IdText(1L,"B"));
		idTextListB.add(new IdText(2L,"C"));
		idTextListB.add(new IdText(3L,"D"));
		idTextListB.add(new IdText(4L,"E"));
		idTextListB.add(new IdText(5L,"F"));
		IdTextModel modelB=new IdTextModel(idTextListB);
		cboq2Ans.setModel(modelB);
		
		q3=new JLabel();
		q3.setText("试题三：");
		q3.setBounds(10, 190, 100, 30);
		container.add(q3);
		
		qa3=new JLabel();
		qa3.setText("");
		qa3.setBounds(10, 220, 300, 30);
		container.add(qa3);
		
		cboq3Ans=new JComboBox<IdText>();
		cboq3Ans.setBounds(350, 190, 100, 30);
		container.add(cboq3Ans);
		
		java.util.List<IdText> idTextListC=new java.util.ArrayList<IdText>();
		idTextListC.add(new IdText(0L,"A"));
		idTextListC.add(new IdText(1L,"B"));
		idTextListC.add(new IdText(2L,"C"));
		idTextListC.add(new IdText(3L,"D"));
		idTextListC.add(new IdText(4L,"E"));
		idTextListC.add(new IdText(5L,"F"));
		IdTextModel modelC=new IdTextModel(idTextListC);
		cboq3Ans.setModel(modelC);
		
		
		q4=new JLabel();
		q4.setText("试题四：");
		q4.setBounds(10, 250, 100, 30);
		container.add(q4);
		
		qa4=new JLabel();
		qa4.setText("");
		qa4.setBounds(10, 280, 300, 30);
		container.add(qa4);
		
		cboq4Ans=new JComboBox<IdText>();
		cboq4Ans.setBounds(350, 250, 100, 30);
		container.add(cboq4Ans);
		
		java.util.List<IdText> idTextListD=new java.util.ArrayList<IdText>();
		idTextListD.add(new IdText(0L,"A"));
		idTextListD.add(new IdText(1L,"B"));
		idTextListD.add(new IdText(2L,"C"));
		idTextListD.add(new IdText(3L,"D"));
		idTextListD.add(new IdText(4L,"E"));
		idTextListD.add(new IdText(5L,"F"));
		IdTextModel modelD=new IdTextModel(idTextListD);
		cboq4Ans.setModel(modelD);
		
		
		q5=new JLabel();
		q5.setText("试题五：");
		q5.setBounds(10, 310, 100, 30);
		container.add(q5);
		
		qa5=new JLabel();
		qa5.setText("");
		qa5.setBounds(10, 340, 300, 30);
		container.add(qa5);
		
		cboq5Ans=new JComboBox<IdText>();
		cboq5Ans.setBounds(350, 310, 100, 30);
		container.add(cboq5Ans);
		
		java.util.List<IdText> idTextListE=new java.util.ArrayList<IdText>();
		idTextListE.add(new IdText(0L,"A"));
		idTextListE.add(new IdText(1L,"B"));
		idTextListE.add(new IdText(2L,"C"));
		idTextListE.add(new IdText(3L,"D"));
		idTextListE.add(new IdText(4L,"E"));
		idTextListE.add(new IdText(5L,"F"));
		IdTextModel modelE=new IdTextModel(idTextListE);
		cboq5Ans.setModel(modelE);
		//选择答案
		
		//按钮
		start=new JButton();
		start.setText("开始");
		start.setBounds(130, 360, 100, 30);
		container.add(start);
		
		submit=new JButton();
		submit.setText("提交");
		submit.setBounds(250,360, 100, 30);
		container.add(submit);
	}
	
	private void Submit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		submit_click(e);
	}
	

	private void Start_click(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
//		List<Paperitem> list = paperItemService.XXX(PaperID)
		// list.get(i)  - > list[i]  lbl1.setText(list.get(0).getQuestion())
		Long pk=2L;
		// 这边你有两个question 赋值的问题你在看看，这两个question，有两个你赋值给了一个拼写错误的qusetion
		// 你在试一下哈
		List<PaperItem> list =paperItemService.listByItem(pk);
//		System.out.println("start长度"+list.size());
//		System.out.println(list.get(0).getQuestion());
//		System.out.println(list.get(2).getQuestion());
//		System.out.println(list.get(1).getQuestion());
//		System.out.println(list.get(3).getQuestion());
//		System.out.println(list.get(4).getQuestion());
		PaperItem pitem=paperItemService.load(pk);
		if(pitem==null) {
			JOptionPane.showConfirmDialog(null, "试卷项主键值为空，加载失败！");
			return;
		}
		Paper item=paperService.load(pk);
		if(item==null) {
			JOptionPane.showConfirmDialog(null, "试卷主键值为空，加载失败！");
			return;
		}

//		Question qItem=questionService.load(1L);
//		Question qItem2=questionService.load(2L);
//		Question qItem3=questionService.load(3L);
//		Question qItem4=questionService.load(4L);
//		Question qItem5=questionService.load(5L);
//		System.out.println(list.get(0).getQuestion());
		q1.setText(q1.getText()+list.get(0).getQuestion());
		q2.setText(q2.getText()+list.get(1).getQuestion());
		q3.setText(q3.getText()+list.get(2).getQuestion());
		q4.setText(q4.getText()+list.get(3).getQuestion());
		q5.setText(q5.getText()+list.get(4).getQuestion());
		qa1.setText("A.2 B.3 C.4 D.5 E.6 F.7");
		qa2.setText("A.2 B.3 C.4 D.5 E.6 F.7");
		qa3.setText("A.1 B.3 C.4 D.5 E.6 F.7");
		qa4.setText("A.1 B.3 C.4 D.5 E.6 F.7");
		qa5.setText("A.1 B.3 C.4 D.5 E.6 F.7");
		start.setVisible(false);
		submit.setVisible(true);

//		q1.setText(q1.getText()+qItem.getQuestion());
//		q2.setText(q2.getText()+qItem2.getQuestion());
//		q3.setText(q3.getText()+qItem3.getQuestion());
//		q4.setText(q4.getText()+qItem4.getQuestion());
//		q5.setText(q5.getText()+qItem5.getQuestion());
//		
//		ExamItemDao examItemDao=new ExamItemDaoImpl();

//		String stuanswer=cboq1Ans.getSelectedItem().toString()+cboq2Ans.getSelectedItem().toString()+cboq3Ans.getSelectedItem().toString()+cboq4Ans.getSelectedItem().toString()+cboq5Ans.getSelectedItem().toString();
//		System.out.println(stuanswer);
//		String answer=qItem.getAnswer()+qItem2.getAnswer()+qItem3.getAnswer()+qItem4.getAnswer()+qItem5.getAnswer();
//		System.out.println(answer);
//		Long score = null;
//		if(cboq1Ans.getSelectedItem().toString().equals(qItem.getAnswer().toString())) {
//			score=20L;
//		}if(cboq2Ans.getSelectedItem().toString().equals(qItem2.getAnswer().toString())) {
//			score=score+20;
//		}if(cboq3Ans.getSelectedItem().toString().equals(qItem3.getAnswer().toString())) {
//			score=score+20;
//		}if(cboq4Ans.getSelectedItem().toString().equals(qItem4.getAnswer().toString())) {
//			score=score+20;
//		}if(cboq5Ans.getSelectedItem().toString().equals(qItem5.getAnswer().toString())) {
//			score=score+20;
//		}
//		System.out.println(score);
	}
	
	ExamItemService examItemService=new ExamItemServiceImpl();
	PaperItemService paperItemService=new PaperItemServiceImpl();
	StudentService studentService=new StudentServiceImpl();
	PaperService paperService=new PaperServiceImpl();
	ExamService examService=new ExamServiceImpl();
	QuestionService questionService=new QuestionServiceImpl();
	

//	private void btnstart_click(ActionEvent e) {
		// TODO Auto-generated method stub
//		Long pk=2L;
//		PaperItem pitem=paperItemService.loadByItem(pk);
//		if(pitem==null) {
//			JOptionPane.showConfirmDialog(null, "试卷项主键值为空，加载失败！");
//			return;
//		}
//		Paper item=paperService.load(pk);
//		if(item==null) {
//			JOptionPane.showConfirmDialog(null, "试卷主键值为空，加载失败！");
//			return;
//		}
//		Question qItem=questionService.load(pk);
//		
//		q1.setText(q1.getText()+qItem.getQuestion());
//		q2.setText(q1.getText()+qItem.getQuestion());
//		q3.setText(q1.getText()+qItem.getQuestion());
//		q4.setText(q1.getText()+qItem.getQuestion());
//		q5.setText(q1.getText()+qItem.getQuestion());
//	}

	@SuppressWarnings("unused")
	private void submit_click(ActionEvent e) {
		
		IdText a1=(IdText)cboq1Ans.getSelectedItem();
		String selectItem1 = null;
		Long select=a1.getId();
		if(select==0L) {
			selectItem1="A";
		}else if(select==1L) {
			selectItem1="B";
		}else if(select==2L) {
			selectItem1="C";
		}else if(select==3L) {
			selectItem1="D";
		}else if(select==4L) {
			selectItem1="E";
		}else if(select==5L) {
			selectItem1="F";
		}else if(select==null) {
			JOptionPane.showConfirmDialog(null,"请完整答题");
			return;
		}
		
		IdText a2=(IdText)cboq2Ans.getSelectedItem();
		String selectItem2 = null;
		Long select2=a2.getId();
		if(select2==0L) {
			selectItem2="A";
		}else if(select2==1L) {
			selectItem2="B";
		}else if(select2==2L) {
			selectItem2="C";
		}else if(select2==3L) {
			selectItem2="D";
		}else if(select2==4L) {
			selectItem2="E";
		}else if(select2==5L) {
			selectItem2="F";
		}else if(select2==null) {
			JOptionPane.showConfirmDialog(null,"请完整答题");
			return;
		}
		
		IdText a3=(IdText)cboq3Ans.getSelectedItem();
		String selectItem3 = null;
		Long select3=a3.getId();
		if(select3==0L) {
			selectItem3="A";
		}else if(select3==1L) {
			selectItem3="B";
		}else if(select3==2L) {
			selectItem3="C";
		}else if(select3==3L) {
			selectItem3="D";
		}else if(select3==4L) {
			selectItem3="E";
		}else if(select3==5L) {
			selectItem3="F";
		}else if(select3==null) {
			JOptionPane.showConfirmDialog(null,"请完整答题");
			return;
		}
		
		IdText a4=(IdText)cboq4Ans.getSelectedItem();
		String selectItem4 = null;
		Long select4=a4.getId();
		if(select4==0L) {
			selectItem4="A";
		}else if(select4==1L) {
			selectItem4="B";
		}else if(select4==2L) {
			selectItem4="C";
		}else if(select4==3L) {
			selectItem4="D";
		}else if(select4==4L) {
			selectItem4="E";
		}else if(select4==5L) {
			selectItem4="F";
		}else if(select4==null) {
			JOptionPane.showConfirmDialog(null,"请完整答题");
			return;
		}
		
		IdText a5=(IdText)cboq5Ans.getSelectedItem();
		
		String selectItem5 = null;
		Long select5=a5.getId();
		if(select5==0L) {
			selectItem5="A";
		}else if(select5==1L) {
			selectItem5="B";
		}else if(select5==2L) {
			selectItem5="C";
		}else if(select5==3L) {
			selectItem5="D";
		}else if(select5==4L) {
			selectItem5="E";
		}else if(select5==5L) {
			selectItem5="F";
		}else if(select5==null) {
			JOptionPane.showConfirmDialog(null,"请完整答题");
			return;
		}
		Long pk=2L;
		String stuAns=selectItem1+selectItem2+selectItem3+selectItem4+selectItem5;
		List<PaperItem> list =paperItemService.listByItem(pk);
		String stdAns=list.get(0).getAnswer()+list.get(1).getAnswer()+list.get(2).getAnswer()+list.get(3).getAnswer()+list.get(4).getAnswer();
		Long count=1L;
			if(selectItem1.equals(list.get(0).getAnswer())) {
				count++;
			}
			if(selectItem2.equals(list.get(1).getAnswer())) {
				count++;
			}
			if(selectItem3.equals(list.get(2).getAnswer())) {
				count++;
			}
			if(selectItem4.equals(list.get(3).getAnswer())) {
				count++;
			}
			if(selectItem5.equals(list.get(4).getAnswer())) {
				count++;
			}
			System.out.println(count);
		Float score=(float) (count*20);
		ExamItem item=examItemService.load(1L);
		List<PaperItem> list2 =paperItemService.listByItem(pk);
		ExamItem bean=new ExamItem();
		bean.setExamId(item.getExamId());
		bean.setQuestionId(item.getQuestionId());
		bean.setStuAnswer(stuAns);
		bean.setStdAnswer(stdAns);
		bean.setStdScore((float) 20);
		bean.setMarkResult(1L);
		bean.setGainScore(score);
		
		Exam bean2=examService.load(2L);
		bean2.setIsMark((long) 1);
		
		Long result=0L;
		String errMsg=null;
		try {
		result=examItemService.insert(bean);
		result=examService.updateMark(bean2);
		}catch(Exception ex) {
			errMsg=ex.getMessage();
		}
		
		if(result>0) {
			JOptionPane.showMessageDialog(null, "考试完成！");
			if(studentMainFrm!=null) {
				studentMainFrm.setVisible(true);
			}
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "考试失败！");
		}

		
		if(studentMainFrm!=null) {
			studentMainFrm.setVisible(true);
		}
		this.dispose();
	}
	
	Timer timer=null;
	private void timer_click(ActionEvent e) {
		// TODO Auto-generated method stub
		midTime--;
        int hour = midTime / 60 / 60 ;
        int minute = midTime % 3600 / 60;
        int second = midTime % 3600 %60; 
        time.setText("" + hour + "：" + minute + "：" + second);
	}
	 

	Long pk=null;
	public void loadData() {
		pk=studentMainFrm.pk;
		if(pk==null) {
			JOptionPane.showConfirmDialog(null,"主键值为空，加载失败！");
			return;
		}
		
		Student bean=studentService.load(pk);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		
		this.setTitle(this.getTitle()+pk);
	}
}
