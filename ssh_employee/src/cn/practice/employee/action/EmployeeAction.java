package cn.practice.employee.action;

import java.util.List;

import cn.practice.employee.domain.Employee;
import cn.practice.employee.domain.PageBean;
import cn.practice.employee.domain.Department;
import cn.practice.employee.service.DepartmentService;
import cn.practice.employee.service.EmployeeService;
import cn.practice.employee.service.impl.EmployeeServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	private Employee employee = new Employee();
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

//ע��ҵ�����
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String login(){
		System.out.println("loginִ����");
		Employee existEmployee = employeeService.login(employee);
		if(existEmployee == null){
			System.out.println("��¼ʧ�ܣ�");
			this.addActionError("�û������������");
			return INPUT;
		}else{
			System.out.println("��½�ɹ���");
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}
	
	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	public String findAll(){
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String saveUI(){
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	public String edit(){
		employee = employeeService.findById(employee.getEid());
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	
	public String update(){
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	public String delete(){
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
