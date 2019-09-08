package edu.prj.service;

import edu.prj.bean.Manager;

public interface ManagerService {
	Long insert(Manager bean);
	Long delete(Long id);
	Long update(Manager bean);
	java.util.List<Manager> list();
	Manager load(Long id);
	Long count();
	Manager loadByName(String name);
	Long countByName(String name);
	java.util.List<Manager> listByName(String name);
}
