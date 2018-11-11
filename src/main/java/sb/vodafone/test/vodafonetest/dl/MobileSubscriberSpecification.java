package sb.vodafone.test.vodafonetest.dl;

import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class MobileSubscriberSpecification implements Specification<MobileSubscriber> {
	
	private MobileSubscriber filter;
	
	public MobileSubscriberSpecification(MobileSubscriber filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<MobileSubscriber> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		Predicate p = cb.disjunction();

		if (filter.getId() != null) {
			p.getExpressions().add(cb.equal(root.get("Id"), filter.getId()));
		}
		
		if (filter.getMsisdn() != null && filter.getMsisdn().trim() != "") {
			p.getExpressions().add(cb.and(
					cb.equal(root.get("msisdn"), filter.getMsisdn().trim())
				));
		}
		
		if (filter.getCustomer_id_owner() != null) {
			p.getExpressions().add(cb.and(
					cb.equal(root.get("customer_id_owner"), filter.getCustomer_id_owner())
				));
		}
		
		if (filter.getCustomer_id_user() != null) {
			p.getExpressions().add(cb.and(
					cb.equal(root.get("customer_id_user"), filter.getCustomer_id_user())
				));
		}

		return p;
	}
}
