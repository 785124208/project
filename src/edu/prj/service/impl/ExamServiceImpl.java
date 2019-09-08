package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Exam;
import edu.prj.dao.ExamDao;
import edu.prj.dao.impl.ExamDaoImpl;
import edu.prj.service.ExamService;

public class ExamServiceImpl implements ExamService{
	private ExamDao examDao=new ExamDaoImpl();
	@Override
	public Long insert(Exam bean) {
		// TODO Auto-generated method stub
		return examDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return examDao.delete(id);
	}

	@Override
	public Long update(Exam bean) {
		// TODO Auto-generated method stub
		return examDao.update(bean);
	}

	@Override
	public List<Exam> list() {
		// TODO Auto-generated method stub
		return examDao.list();
	}

	@Override
	public Exam load(Long id) {
		// TODO Auto-generated method stub
		return examDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return examDao.count();
	}

	@Override
	public Exam loadByName(String name) {
		// TODO Auto-generated method stub
		return examDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return examDao.countByName(name);
	}

	@Override
	public List<Exam> listByName(String name) {
		// TODO Auto-generated method stub
		return examDao.listByName(name);
	}

	@Override
	public List<Exam> listById(Long id) {
		// TODO Auto-generated method stub
		return examDao.listById(id);
	}

	@Override
	public Long updateMark(Exam bean) {
		// TODO Auto-generated method stub
		return examDao.updateMark(bean);
	}

}
