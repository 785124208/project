package edu.prj.ui.Paper;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Paper;
import edu.prj.service.PaperService;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.ui.TeacherMainFrm.TeacherMainFrm;

public class PaperListFrm extends JFrame implements java.io.Serializable{
	PaperService paperService=new PaperServiceImpl();
	public TeacherMainFrm mainFrm = null;
	String loginName;
	private JPanel container;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JLabel lblTitle,lblSearch;
	private JTextField txtSearch,txtSearchBook;
	private JButton btnSearch,btnReset,btnInsert;
	
	public PaperListFrm() {
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
		this.setTitle("试卷管理");
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
		
		btnInsert=new JButton();
		btnInsert.setText("添加");
		btnInsert.setBounds(420, 460, 70, 30);
		container.add(btnInsert);
		
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
		
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnInsert_click(e);
			}
			
		});
		
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
		java.util.List<Paper> list=null;
		
		String searchName=txtSearch.getText();
//		String searchBook=txtSearchBook.getText();
		if(searchName!=null&&!searchName.isEmpty()) {
			list=paperService.listByName(searchName);
		}else {
			list=paperService.list();
		}
		ATableModel<Paper> tableModel = null;
		tableModel = new ATableModel<Paper>(list, 9) {

			@Override
			public Object getPropValue(Paper bean, int columIndex) {
				// TODO Auto-generated method stub

				Paper item = (Paper) bean;
				if (columIndex == 0) {
					return item.getPaperId();
				} else if (columIndex == 1) {
					return item.getPaperName();
				} else if (columIndex == 2) {
					return item.getTotalScore();
				}  else if (columIndex == 3) {
					return item.getPerScore();
				} else if (columIndex == 4) {
					return item.getQuestionNum();
				} else if (columIndex == 5) {
					return item.getExamMinute();
				} else if (columIndex == 6) {
					return item.getStartOn();
				} else if (columIndex == 7) {
					return item.getEndOn();
				} else if (columIndex == 8) {
					return item.getHasGenerate()==0?"未生成":"已生成";
				}
				return null;
			}

			@Override
			public String getTitle(int columIndex) {
				// TODO Auto-generated method stub
				if (columIndex == 0) {
					return "试卷ID";
				} else if (columIndex == 1) {
					return "试卷类型";
				} else if (columIndex == 2) {
					return "总分";
				} else if (columIndex == 3) {
					return "每题分数";
				} else if (columIndex == 4) {
					return "题目数";
				} else if (columIndex == 5) {
					return "考试时间（MIN）";
				} else if (columIndex == 6) {
					return "有效开始日期";
				} else if (columIndex == 7) {
					return "有效结束日期";
				} else if (columIndex == 8) {
					return "是否已生成";
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
	
	public void btnInsert_click(ActionEvent e) {
		PaperInsertFrm paperInsertFrm=new PaperInsertFrm();
		paperInsertFrm.paperListFrm=this;
		this.dispose();
		paperInsertFrm.setVisible(true);
	}
	
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
		JMenuItem deleteMenuItem=new JMenuItem("删除");
		tblObjMenu.add(deleteMenuItem);
		
		deleteMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteMenuItem_click(e);
			}			
		});
		
		JMenuItem setMenuItem=new JMenuItem("生成试卷");
		tblObjMenu.add(setMenuItem);
		
		setMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PaperFrm paperFrm=new PaperFrm();
				paperFrm.setVisible(true);
			}			
		});
		
		//修改
		JMenuItem updateMenuItem=new JMenuItem("更新");
		tblObjMenu.add(updateMenuItem);
		updateMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateMenuItem_click(e);
			}
		});
	}
	
	private void deleteMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=tblObj.getSelectedRow();
		if(index!=-1) {
			TableModel model=tblObj.getModel();
			Object obj=model.getValueAt(index, 0);
			Long pk=Long.valueOf(""+obj);
			
			String title="系统提示";
			String message="请确认是否删除选中题目";
			int option=JOptionPane.YES_NO_OPTION;
			int buttonValue=JOptionPane.showConfirmDialog(null, message,title,option);
			if(pk>0&&pk<3) {
				JOptionPane.showMessageDialog(null, "基础信息不可删除！");
				showListData();
			}
			if(buttonValue==JOptionPane.YES_OPTION) {
				Long result=paperService.delete(pk);
				
				if(result>0) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					showListData();
				}else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			}
		}
	}
	

	private void updateMenuItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index=tblObj.getSelectedRow();
		if(index!=-1) {
			TableModel model=tblObj.getModel();
			Object obj=model.getValueAt(index, 0);
			Long pk=Long.valueOf(""+obj);
			if(pk>0) {
				PaperUpdateFrm paperUpdateFrm=new PaperUpdateFrm();
				paperUpdateFrm.pk=pk;
				paperUpdateFrm.loadData();
				paperUpdateFrm.paperListFrm=this;
				this.dispose();
				paperUpdateFrm.setVisible(true);
			}
			}
	}
}
