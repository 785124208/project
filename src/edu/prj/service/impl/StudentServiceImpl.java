package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.StudentDao;
import edu.prj.dao.impl.StudentDaoImpl;
import edu.prj.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao=new StudentDaoImpl();
	@Override
	public Long insert(Student bean) {
		// TODO Auto-generated method stub
		return studentDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return studentDao.delete(id);
	}

	@Override
	public Long update(Student bean) {
		// TODO Auto-generated method stub
		return studentDao.update(bean);
	}

	@Override
	public List<Student> list() {
		// TODO Auto-generated method stub
		return studentDao.list();
	}

	@Override
	public Student load(Long id) {
		// TODO Auto-generated method stub
		return studentDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return studentDao.count();
	}

	@Override
	public Student loadByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.countByName(name);
	}

	@Override
	public List<Student> listByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.listByName(name);
	}

	@Override
	public Long updatepwd(Student bean) {
		// TODO Auto-generated method stub
		return studentDao.updatepwd(bean);
	}

}
