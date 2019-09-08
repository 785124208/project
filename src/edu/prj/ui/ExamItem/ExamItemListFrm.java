package edu.prj.ui.ExamItem;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.ExamItem;
import edu.prj.bean.PaperItem;
import edu.prj.bean.Student;
import edu.prj.service.ExamItemService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ExamItemServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.Exam.ExamFrm;
import edu.prj.ui.StudentMainFrm.StudentMainFrm;
import edu.prj.ui.TeacherMainFrm.TeacherMainFrm;

public class ExamItemListFrm extends JFrame implements java.io.Serializable{
	ExamItemService examItemService=new ExamItemServiceImpl();
	public TeacherMainFrm mainFrm = null;
	String loginName;
	private JPanel container;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JLabel lblTitle,lblSearch;
	private JTextField txtSearch,txtSearchBook;
	private JButton btnSearch,btnReset,btnInsert;
	public StudentMainFrm studentMainFrm=null;
	
	public ExamItemListFrm() {
		initUI();
		bindEvent();
	}
	public void initTableUI() {
		tblObj=new JTable();
		pnlTablePane=new JScrollPane(tblObj);
		
		pnlTablePane.setBounds(10, 100, 780, 300);
		
		container.add(pnlTablePane);
		
		showListData();
		createTblObjMenu();
	}

	public void initUI() {
		int width=800;
		int height=600;
		this.setLocationRelativeTo(getOwner());
		this.setSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(x,y);
		this.setTitle("成绩查看");
		this.setResizable(false);
		
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.add(container);
		
		lblTitle=new JLabel();
		lblTitle.setText("试卷管理");
		lblTitle.setBounds(285, 5, 300, 80);
		lblTitle.setFont(new Font("宋体",Font.BOLD,25));
		container.add(lblTitle);
		
		lblSearch=new JLabel();
		lblSearch.setText("输入名字查找");
		lblSearch.setBounds(20,460,40,30);
		container.add(lblSearch);
		
		txtSearch=new JTextField();
		txtSearch.setBounds(70,460,180,30);
		container.add(txtSearch);
		
//		txtSearchBook=new JTextField();
//		txtSearchBook.setBounds(70,500,180,30);
//		container.add(txtSearchBook);
//		
		btnSearch=new JButton();
		btnSearch.setText("查询");
		btnSearch.setBounds(260, 460, 70, 30);
		container.add(btnSearch);
		
		btnReset=new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(340,460,70,30);
		container.add(btnReset);
//		
//		btnInsert=new JButton();
//		btnInsert.setText("添加");
//		btnInsert.setBounds(420, 460, 70, 30);
//		container.add(btnInsert);
		
		initTableUI();
	}

	
	public void bindEvent() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				window_Closing(e);
			}
		});

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSearch_click(e);
			}
			
		});
		
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}
			
		});
		
//		btnInsert.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				btnInsert_click(e);
//			}
//			
//		});
		
		tblObj.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					int rowIndex=tblObj.rowAtPoint(e.getPoint());
					if(rowIndex==-1) {
						return;
					}
					
					tblObj.setRowSelectionInterval(rowIndex, rowIndex);
					
					tblObjMenu.show(tblObj,e.getX(),e.getY());
				}
			}
		});
	}
	
	
	private void showListData() {
		// TODO Auto-generated method stub
//		ManagerService managerService=new ManagerServiceImpl();
		java.util.List<ExamItem> list=null;
		String searchName=txtSearch.getText();
//		String searchBook=txtSearchBook.getText();
		if(searchName!=null&&searchName.isEmpty()) {
			list=examItemService.list();
		}
		else {
			list=examItemService.list();
		}
		ATableModel<ExamItem> tableModel = null;
		tableModel = new ATableModel<ExamItem>(list, 8) {

			@Override
			public Object getPropValue(ExamItem bean, int columIndex) {
				// TODO Auto-generated method stub

				ExamItem item = (ExamItem) bean;
				if (columIndex == 0) {
					return item.getItemId();
				} else if (columIndex == 1) {
					return item.getExamId();
				} else if (columIndex == 2) {
					return item.getQuestionId();
				}  else if (columIndex == 3) {
					return item.getStuAnswer();
				} else if (columIndex == 4) {
					return item.getStdAnswer();
				} else if (columIndex == 5) {
					return item.getStdScore();
				} else if (columIndex == 6) {
					return item.getMarkResult();
				} else if (columIndex == 7) {
					return item.getGainScore();
				}
				return null;
			}

			@Override
			public String getTitle(int columIndex) {
				// TODO Auto-generated method stub
				if (columIndex == 0) {
					return "项目ID";
				} else if (columIndex == 1) {
					return "考试ID";
				} else if (columIndex == 2) {
					return "题目ID";
				} else if (columIndex == 3) {
					return "学生答案";
				} else if (columIndex == 4) {
					return "标准答案";
				} else if (columIndex == 5) {
					return "得分";
				} else if (columIndex == 6) {
					return "阅卷结果";
				} else if (columIndex == 7) {
					return "总分";
				}

				return "无";
			}
		};
		tblObj.setModel(tableModel);

	}
	
	public void btnSearch_click(ActionEvent e) {
		showListData();
	}
	
	public void btnReset_click(ActionEvent e) {
		txtSearch.setText("");
		showListData();
	}
	
//	public void btnInsert_click(ActionEvent e) {
//		ExamItemInsertFrm paperInsertFrm=new ExamItemInsertFrm();
//		paperInsertFrm.paperListFrm=this;
//		this.dispose();
//		paperInsertFrm.setVisible(true);
//	}
	
	public void customLoad() {

		if (loginName != null) {
			this.setTitle(this.getTitle() + "-【" + loginName + "】");
		}
	}
	
	public void window_Closing(WindowEvent e) {
		int option=JOptionPane.showConfirmDialog(this, "确定退出系统？","提示",JOptionPane.YES_NO_OPTION);
		
		if(option ==JOptionPane.YES_NO_OPTION);
			if(e.getWindow()==this) {
				if(mainFrm!=null) {
					this.dispose();
					mainFrm.setVisible(true);
				}
	}
}
	//定义表格对象的右键菜单
	private JPopupMenu tblObjMenu=null;
	
	public void createTblObjMenu() {
		tblObjMenu=new JPopupMenu();
		//删除
//		JMenuItem deleteMenuItem=new JMenuItem("删除");
//		tblObjMenu.add(deleteMenuItem);
//		
//		deleteMenuItem.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				deleteMenuItem_click(e);
//			}			
//		});
		
		//修改
//		JMenuItem updateMenuItem=new JMenuItem("开始考试");
//		tblObjMenu.add(updateMenuItem);
//		updateMenuItem.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				updateMenuItem_click(e);
//			}
//		});
	}
	
//	private void deleteMenuItem_click(ActionEvent e) {
//		// TODO Auto-generated method stub
//		int index=tblObj.getSelectedRow();
//		if(index!=-1) {
//			TableModel model=tblObj.getModel();
//			Object obj=model.getValueAt(index, 0);
//			Long pk=Long.valueOf(""+obj);
//			
//			String title="系统提示";
//			String message="请确认是否删除选中题目";
//			int option=JOptionPane.YES_NO_OPTION;
//			int buttonValue=JOptionPane.showConfirmDialog(null, message,title,option);
//			
//			if(buttonValue==JOptionPane.YES_OPTION) {
//				Long result=examService.delete(pk);
//				if(result>0) {
//					JOptionPane.showMessageDialog(null, "删除成功！");
//					showListData();
//				}else {
//					JOptionPane.showMessageDialog(null, "删除失败！");
//				}
//			}
//		}
//	}
	

	private void updateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=tblObj.getSelectedRow();
		if(index!=-1) {
			TableModel model=tblObj.getModel();
			Object obj=model.getValueAt(index, 0);
			ExamFrm exf=new ExamFrm();
			this.dispose();
			exf.setVisible(true);
//			Long pk=Long.valueOf(""+obj);
//			if(pk>0) {
////				ExamItemUpdateFrm studentUpdateFrm=new ExamItemUpdateFrm();
////				studentUpdateFrm.pk=pk;
////				studentUpdateFrm.loadData();
////				studentUpdateFrm.studentListFrm=this;
////				this.dispose();
////				studentUpdateFrm.setVisible(true);
//			}
			}
	}
	StudentService studentService=new StudentServiceImpl();
	public Long pk=null;
	public void loadData() {
		if(pk==null) {
			JOptionPane.showConfirmDialog(null,"主键值为空，加载失败！");
			return;
		}
		
		Student bean=studentService.load(pk);
		if(bean==null) {
			JOptionPane.showConfirmDialog(null, "对应数据不存在加载失败！");
			return;
		}
		showListData();
		this.setTitle(this.getTitle()+pk);
	}
}
