package cn.practice.employee.service;
import java.util.List;
import cn.practice.employee.domain.Department;
import cn.practice.employee.domain.PageBean;

public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);
	
	Department findById(Integer did);
	
	void update(Department department);
	
	void delete(Department department);

	List<Department> findAll();

}
