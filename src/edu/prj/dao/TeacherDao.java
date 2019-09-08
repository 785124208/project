package edu.prj.dao;

import edu.prj.bean.*;

public interface TeacherDao {
	Long insert(Teacher bean);
	Long delete(Long id);
	Long update(Teacher bean);
	Long updatepwd(Teacher bean);
	java.util.List<Teacher> list();
	Teacher load(Long id);
	Long count();
	Teacher loadByName(String name);
	Long countByName(String name);
	java.util.List<Teacher> listByName(String name);
}
