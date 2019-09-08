package edu.prj.dao;

import edu.prj.bean.*;

public interface BookDao {
	Long insert(Book bean);
	Long delete(Long id);
	Long update(Book bean);
	java.util.List<Book> list();
	Book load(Long id);
	Long count();
	Book loadByName(String name);
	Long countByName(String name);
	java.util.List<Book> listByName(String stuName,String bookName);
}
