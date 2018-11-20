package sb.vodafone.test.vodafonetest.interfaces;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;

@Repository
@Transactional
public interface IMobileSubscriberRepo extends JpaRepository<MobileSubscriber,Long>, JpaSpecificationExecutor<MobileSubscriber>{

}
