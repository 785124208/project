package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Question;
import edu.prj.dao.QuestionDao;
import edu.prj.dao.impl.QuestionDaoImpl;
import edu.prj.service.QuestionService;

public class QuestionServiceImpl  implements QuestionService{
	private QuestionDao questionDao=new QuestionDaoImpl();
	@Override
	public Long insert(Question bean) {
		// TODO Auto-generated method stub
		return questionDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return questionDao.delete(id);
	}

	@Override
	public Long update(Question bean) {
		// TODO Auto-generated method stub
		return questionDao.update(bean);
	}

	@Override
	public List<Question> list() {
		// TODO Auto-generated method stub
		return questionDao.list();
	}

	@Override
	public Question load(Long id) {
		// TODO Auto-generated method stub
		return questionDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return questionDao.count();
	}

	@Override
	public Question loadByName(String name) {
		// TODO Auto-generated method stub
		return questionDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return questionDao.countByName(name);
	}

	@Override
	public List<Question> listByName(String name) {
		// TODO Auto-generated method stub
		return questionDao.listByName(name);
	}

	@Override
	public Question loadByAnswer(String answer) {
		// TODO Auto-generated method stub
		return questionDao.loadByAnswer(answer);
	}

}
