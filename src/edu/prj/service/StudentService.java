package edu.prj.service;

import edu.prj.bean.Student;

public interface StudentService {
	Long insert(Student bean);
	Long delete(Long id);
	Long update(Student bean);
	Long updatepwd(Student bean);
	java.util.List<Student> list();
	Student load(Long id);
	Long count();
	Student loadByName(String name);
	Long countByName(String name);
	java.util.List<Student> listByName(String name);
}
