package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Paper;
import edu.prj.dao.PaperDao;
import edu.prj.dao.impl.PaperDaoImpl;
import edu.prj.service.PaperService;

public class PaperServiceImpl implements PaperService{

	private PaperDao paperDao=new PaperDaoImpl();
	@Override
	public Long insert(Paper bean) {
		// TODO Auto-generated method stub
		return paperDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return paperDao.delete(id);
	}

	@Override
	public Long update(Paper bean) {
		// TODO Auto-generated method stub
		return paperDao.update(bean);
	}

	@Override
	public List<Paper> list() {
		// TODO Auto-generated method stub
		return paperDao.list();
	}

	@Override
	public Paper load(Long id) {
		// TODO Auto-generated method stub
		return paperDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return paperDao.count();
	}

	@Override
	public Paper loadByName(String name) {
		// TODO Auto-generated method stub
		return paperDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return paperDao.countByName(name);
	}

	@Override
	public List<Paper> listByName(String name) {
		// TODO Auto-generated method stub
		return paperDao.listByName(name);
	}

}
