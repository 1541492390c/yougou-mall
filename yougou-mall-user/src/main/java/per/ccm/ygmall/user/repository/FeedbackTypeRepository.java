package per.ccm.ygmall.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.FeedbackType;

@Repository
public interface FeedbackTypeRepository extends JpaRepository<FeedbackType, Long>, QuerydslPredicateExecutor<FeedbackType> {
}
