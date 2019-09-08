package edu.prj.service;

import edu.prj.bean.ClassRoom;

;

public interface ClassRoomService {
	Long insert(ClassRoom bean);
	Long delete(Long id);
	Long update(ClassRoom bean);
	java.util.List<ClassRoom> list();
	ClassRoom load(Long id);
	Long count();
	ClassRoom loadByName(String name);
	Long countByName(String name);
	java.util.List<ClassRoom> listByName(String name);
}
