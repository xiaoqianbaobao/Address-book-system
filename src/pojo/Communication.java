package pojo;

/**
 * 通讯信息
 *
 */
public class Communication {
	private int id;//信息编号
	private Customer cust;// 客户编号
	private String office_phone;// 办公电话
	private String mobile_phone;// 移动电话
	private String email;// 电子邮箱
	private String qq;// QQ号码
	private String available;// 是否可用
	private String createTime;// 创建时间
	private String lastUpdateTime;// 最后一次修改时间
	private String operator;// 变更操作人

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
