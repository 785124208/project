package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Book;
import edu.prj.dao.BookDao;
import edu.prj.dao.impl.BookDaoImpl;
import edu.prj.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao=new BookDaoImpl();
	@Override
	public Long insert(Book bean) {
		// TODO Auto-generated method stub
		return bookDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return bookDao.delete(id);
	}

	@Override
	public Long update(Book bean) {
		// TODO Auto-generated method stub
		return bookDao.update(bean);
	}

	@Override
	public List<Book> list() {
		// TODO Auto-generated method stub
		return bookDao.list();
	}

	@Override
	public Book load(Long id) {
		// TODO Auto-generated method stub
		return bookDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return bookDao.count();
	}

	@Override
	public Book loadByName(String name) {
		// TODO Auto-generated method stub
		return bookDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return bookDao.countByName(name);
	}

	@Override
	public List<Book> listByName(String stuName, String bookName) {
		// TODO Auto-generated method stub
		return bookDao.listByName(stuName, bookName);
	}



}
