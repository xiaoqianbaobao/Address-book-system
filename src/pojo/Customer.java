package pojo;

import java.util.Set;

/**
 * 客户信息
 *
 */
public class Customer {
	private int id;// 编号
	private String name;// 姓名
	private String sex;// 性别
	private String birth;// 生日
	private String work_unit;// 公司名称
	private String work_addr;// 公司地址
	private String home_addr;// 家庭住址
	private String role;// 职位
	private String available;// 是否可用
	private Set<Communication> communicationList;// 通讯信息
	private String createTime;// 创建时间
	private String lastUpdateTime;// 最后一次修改时间
	private String operator;// 变更操作人

	public Customer() {
		super();
	}

	public Customer(String name, String sex, String birth, String work_unit, String work_addr, String home_addr,
			String role) {
		super();
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.work_unit = work_unit;
		this.work_addr = work_addr;
		this.home_addr = home_addr;
		this.role = role;
		this.available = "Y";
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getWork_unit() {
		return work_unit;
	}

	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}

	public String getWork_addr() {
		return work_addr;
	}

	public void setWork_addr(String work_addr) {
		this.work_addr = work_addr;
	}

	public String getHome_addr() {
		return home_addr;
	}

	public void setHome_addr(String home_addr) {
		this.home_addr = home_addr;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Communication> getCommunicationList() {
		return communicationList;
	}

	public void setCommunicationList(Set<Communication> communicationList) {
		this.communicationList = communicationList;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Customer:");
		sb.append("id=" + id + ";");
		sb.append("name=" + name + ";");
		sb.append("sex=" + sex + ";");
		sb.append("birth=" + birth + ";");
		sb.append("work_unit=" + work_unit + ";");
		sb.append("work_addr=" + work_addr + ";");
		sb.append("home_addr=" + home_addr + ";");
		sb.append("role=" + role + ";");
		sb.append("available=" + available + ";");
		return sb.toString();
	}
}
