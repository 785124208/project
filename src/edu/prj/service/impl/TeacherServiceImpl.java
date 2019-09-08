package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Teacher;
import edu.prj.dao.TeacherDao;
import edu.prj.dao.impl.TeacherDaoImpl;
import edu.prj.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	private TeacherDao borrowDao=new TeacherDaoImpl();
	@Override
	public Long insert(Teacher bean) {
		// TODO Auto-generated method stub
		return borrowDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return borrowDao.delete(id);
	}

	@Override
	public Long update(Teacher bean) {
		// TODO Auto-generated method stub
		return borrowDao.update(bean);
	}

	@Override
	public List<Teacher> list() {
		// TODO Auto-generated method stub
		return borrowDao.list();
	}

	@Override
	public Teacher load(Long id) {
		// TODO Auto-generated method stub
		return borrowDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return borrowDao.count();
	}

	@Override
	public Teacher loadByName(String name) {
		// TODO Auto-generated method stub
		return borrowDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return borrowDao.countByName(name);
	}

	@Override
	public List<Teacher> listByName(String name) {
		// TODO Auto-generated method stub
		return borrowDao.listByName(name);
	}

	@Override
	public Long updatepwd(Teacher bean) {
		// TODO Auto-generated method stub
		return borrowDao.updatepwd(bean);
	}

}
