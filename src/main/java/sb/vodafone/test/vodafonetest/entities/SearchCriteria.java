package sb.vodafone.test.vodafonetest.entities;

public class SearchCriteria {
	private Long Id;
	
	private String msisdn;
	
	private Integer customer_id_owner;
	
	private Integer customer_id_user;

	public SearchCriteria() {
	}
	
	public SearchCriteria(Long id, String msisdn, Integer customer_id_owner, Integer customer_id_user) {
		super();
		Id = id;
		this.msisdn = msisdn;
		this.customer_id_owner = customer_id_owner;
		this.customer_id_user = customer_id_user;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Integer getCustomer_id_owner() {
		return customer_id_owner;
	}
	public void setCustomer_id_owner(int customer_id_owner) {
		this.customer_id_owner = customer_id_owner;
	}
	public Integer getCustomer_id_user() {
		return customer_id_user;
	}
	public void setCustomer_id_user(int customer_id_user) {
		this.customer_id_user = customer_id_user;
	}
}
