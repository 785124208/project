package edu.prj.service;

import edu.prj.bean.Exam;

public interface ExamService {
	Long insert(Exam bean);
	Long delete(Long id);
	Long update(Exam bean);
	Long updateMark(Exam bean);
	java.util.List<Exam> list();
	Exam load(Long id);
	Long count();
	Exam loadByName(String name);
	Long countByName(String name);
	java.util.List<Exam> listByName(String name);
	java.util.List<Exam> listById(Long id);
}
