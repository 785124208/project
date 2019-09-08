package edu.prj.dao;

import edu.prj.bean.Question;;

public interface QuestionDao {
	Long insert(Question bean);
	Long delete(Long id);
	Long update(Question bean);
	java.util.List<Question> list();
	Question load(Long id);
	Long count();
	Question loadByName(String name);
	Question loadByAnswer(String answer);
	Long countByName(String name);
	java.util.List<Question> listByName(String name);
}
