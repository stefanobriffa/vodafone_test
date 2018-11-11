package sb.vodafone.test.vodafonetest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name="MobileSubscriber")
public class MobileSubscriber {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message="No mobile number specified")
	@Column(name="msisdn", unique=true)
	private String msisdn;
	
	@NotNull(message="No owner id specified")
	@Column(name="customer_id_owner")
	private Integer customer_id_owner;
	
	@NotNull(message="No user id specified")
	@Column(name="customer_id_user")
	private Integer customer_id_user;
	
	@NotNull
	@Column(name="service_type")
	private ServiceType service_type;
	
	@NotNull
	@Column(name="service_start_date")
	private Long service_start_date;
	
	public MobileSubscriber() {
	}
	
	public MobileSubscriber(Long id, String msisdn, Integer customer_id_owner, Integer customer_id_user,
			ServiceType service_type, Long service_start_date) {
		super();
		Id = id;
		this.msisdn = msisdn;
		this.customer_id_owner = customer_id_owner;
		this.customer_id_user = customer_id_user;
		this.service_type = service_type;
		this.service_start_date = service_start_date;
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
	public ServiceType getService_type() {
		return service_type;
	}
	public void setService_type(ServiceType service_type) {
		this.service_type = service_type;
	}
	public Long getService_start_date() {
		return service_start_date;
	}
}
