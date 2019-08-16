package cn.practice.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.practice.employee.dao.DepartmentDao;
import cn.practice.employee.domain.Department;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao{
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public List<Department> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		List<Department> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}
	
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}
	
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}
	
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}
	
	public List<Department> findAll() {
		List<Department> list = this.getHibernateTemplate().find("from Department");
		return list;
	}
}
