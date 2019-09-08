package edu.prj.dao;

import edu.prj.bean.PaperItem;

public interface PaperItemDao {
	Long insert(PaperItem bean);
	Long delete(Long id);
	Long update(PaperItem bean);
	java.util.List<PaperItem> list();
	java.util.List<PaperItem> listById(Long PaperId,Long questionId);
	java.util.List<PaperItem> listByItem(Long id);
	PaperItem load(Long id);
//	PaperItem loadByItem(Long id);
	PaperItem loadByName(String name);
	Long countByName(String name);
	java.util.List<PaperItem> listByName(String name);
}
