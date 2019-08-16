package cn.practice.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.practice.employee.dao.EmployeeDao;
import cn.practice.employee.domain.Employee;
import cn.practice.employee.domain.PageBean;
import cn.practice.employee.service.EmployeeService;
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	//注入Dao的类
	@Autowired
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public Employee login(Employee employee) {
		System.out.println("Service中的login执行了");
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		pageBean.setCurrPage(currPage);
		
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		int totalPage = num.intValue();
		pageBean.setTotalPage(totalPage);
		//封装每页显示的数据
		int begin = (currPage-1)*pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
