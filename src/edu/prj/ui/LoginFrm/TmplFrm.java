package edu.prj.ui.LoginFrm;

import java.awt.*;
import java.awt.List;

import javax.swing.*;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.Book;
import edu.prj.service.BookService;
import edu.prj.service.impl.BookServiceImpl;

public class TmplFrm extends JFrame{
	private JPanel container;
	private JScrollPane pnlTablePane;
	private JTable tblObj;
	private JLabel lblTitle,lblSearch;
	private JTextField txtSearch;
	private JButton btnSearch,btnReset,btnInsert;
	
	public TmplFrm() {
		initUI();
		bindEvent();
	}
	public void initTableUI() {
		tblObj=new JTable();
		pnlTablePane=new JScrollPane(tblObj);
		
		pnlTablePane.setBounds(10, 100, 780, 300);
		
		container.add(pnlTablePane);
		
		showListData();
	}

	public void initUI() {
		int width=800;
		int height=600;
		this.setSize(width, height);
		this.setTitle("图书管理系统！");
		this.setResizable(false);
		
		
		container=new JPanel();
		container.setLayout(null);  //绝对布局
		this.setResizable(false);
		
		lblTitle=new JLabel();
		lblTitle.setText("图书信息列表");
		lblTitle.setBounds(285, 5, 300, 80);
		lblTitle.setFont(new Font("宋体",Font.BOLD,25));
		container.add(lblTitle);
		
		lblSearch=new JLabel();
		lblSearch.setText("名称");
		lblSearch.setBounds(20,460,40,30);
		container.add(lblSearch);
		
		txtSearch=new JTextField();
		txtSearch.setBounds(70,460,180,30);
		container.add(txtSearch);
		
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void showListData() {
		// TODO Auto-generated method stub
		BookService bookService=new BookServiceImpl();
		java.util.List<Book> list=null;
		list=bookService.list();
		
		String searchName=txtSearch.getName();
		if(searchName!=null&&!searchName.isEmpty()) {
			list=bookService.list();
		}else {
			list=bookService.list();
		}
	}
}

