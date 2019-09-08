package edu.prj.dao;

import edu.prj.bean.Paper;

public interface PaperDao {
	Long insert(Paper bean);
	Long delete(Long id);
	Long update(Paper bean);
	java.util.List<Paper> list();
	Paper load(Long id);
	Long count();
	Paper loadByName(String name);
	Long countByName(String name);
	java.util.List<Paper> listByName(String name);
}
