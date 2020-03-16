package pojo;

/**
 * ͨѶ��Ϣ
 *
 */
public class Communication {
	private int id;//��Ϣ���
	private Customer cust;// �ͻ����
	private String office_phone;// �칫�绰
	private String mobile_phone;// �ƶ��绰
	private String email;// ��������
	private String qq;// QQ����
	private String available;// �Ƿ����
	private String createTime;// ����ʱ��
	private String lastUpdateTime;// ���һ���޸�ʱ��
	private String operator;// ���������

	public Communication() {
	}

	public Communication(Customer cust, String office_phone, String mobile_phone, String email, String qq) {
		super();
		this.cust = cust;
		this.office_phone = office_phone;
		this.mobile_phone = mobile_phone;
		this.email = email;
		this.qq = qq;
		available = "Y";
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

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getOffice_phone() {
		return office_phone;
	}

	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
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
		sb.append("id=" + id + ";");
		sb.append("cust=" + cust.getId() + ";");
		sb.append("office_phone=" + office_phone + ";");
		sb.append("mobile_phone=" + mobile_phone + ";");
		sb.append("qq=" + qq + ";");
		sb.append("available=" + available + ";");
		return sb.toString();
	}

}
