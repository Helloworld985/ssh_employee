package cn.practice.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.practice.employee.dao.DepartmentDao;
import cn.practice.employee.domain.Department;
import cn.practice.employee.domain.PageBean;
import cn.practice.employee.service.DepartmentService;
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		pageBean.setCurrPage(currPage);
		
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		
		int begin = (currPage-1)*pageSize;
		List<Department> list = departmentDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	public void save(Department department) {
		departmentDao.save(department);
	}
	
	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}
	
	public void update(Department department) {
		departmentDao.update(department);
	}
	
	public void delete(Department department) {
		departmentDao.delete(department);
	}
	
	public List<Department> findAll() { 
		return departmentDao.findAll();
	}
}
