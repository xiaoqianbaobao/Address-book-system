package pojo;

/**
 * �����û�
 *
 */
public class User {
	/**
	 * ��ͨ�û�Ȩ��
	 */
	public final static String GUEST = "guest";// �����û���Ϣҳ����ѡ��
	/**
	 * ����ԱȨ��
	 */
	public final static String ADMIN = "admin";// �����û���Ϣҳ����ѡ��

	private int id;// ���
	private String account;// �˺�
	private String password;// ����
	private String status;// ���
	private String available;// �Ƿ���Ч
	private String createTime;// ����ʱ��
	private String lastUpdateTime;// ���һ���޸�ʱ��
	private String operator;// ���������

	public User() {
	}

	public User(String account, String password, String status) {
		super();
		this.account = account;
		this.password = password;
		this.status = status;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password="
				+ password + ", status=" + status + ", available=" + available
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + ", operator=" + operator + "]";
	}

}
