package per.ccm.ygmall.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.platform.entity.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>, QuerydslPredicateExecutor<Banner> {
}
