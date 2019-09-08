package edu.prj.dao;

import edu.prj.bean.ExamItem;

public interface ExamItemDao {
	Long insert(ExamItem bean);
	Long delete(Long id);
	Long update(ExamItem bean);
	java.util.List<ExamItem> list();
	ExamItem load(Long id);
	Long count();
	ExamItem loadByName(String name);
	Long countByName(String name);
	java.util.List<ExamItem> listByName(String name);
	java.util.List<ExamItem> listById(Long id);
}
