package per.ccm.ygmall.common.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import per.ccm.ygmall.common.handler.BaseHandler;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
public class BaseService extends BaseHandler {

    protected JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
}
