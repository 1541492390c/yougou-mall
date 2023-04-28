package per.ccm.ygmall.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.auth.entity.AuthAccount;

@Repository
public interface AuthAccountRepository extends JpaRepository<AuthAccount, Long>, QuerydslPredicateExecutor<AuthAccount> {
}
