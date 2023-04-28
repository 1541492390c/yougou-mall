package per.ccm.ygmall.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.AttrValue;

@Repository
public interface AttrValueRepository extends JpaRepository<AttrValue, Long>, QuerydslPredicateExecutor<AttrValue> {
}
