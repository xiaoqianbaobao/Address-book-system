package pojo;

import java.util.Set;

/**
 * �ͻ���Ϣ
 *
 */
public class Customer {
	private int id;// ���
	private String name;// ����
	private String sex;// �Ա�
	private String birth;// ����
	private String work_unit;// ��˾����
	private String work_addr;// ��˾��ַ
	private String home_addr;// ��ͥסַ
	private String role;// ְλ
	private String available;// �Ƿ����
	private Set<Communication> communicationList;// ͨѶ��Ϣ
	private String createTime;// ����ʱ��
	private String lastUpdateTime;// ���һ���޸�ʱ��
	private String operator;// ���������

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
