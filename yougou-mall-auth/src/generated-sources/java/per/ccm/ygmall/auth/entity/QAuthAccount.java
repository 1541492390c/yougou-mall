package per.ccm.ygmall.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthAccount is a Querydsl query type for AuthAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthAccount extends EntityPathBase<AuthAccount> {

    private static final long serialVersionUID = -1290345370L;

    public static final QAuthAccount authAccount = new QAuthAccount("authAccount");

    public final StringPath account = createString("account");

    public final NumberPath<Long> authAccountId = createNumber("authAccountId", Long.class);

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath mp = createString("mp");

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath username = createString("username");

    public QAuthAccount(String variable) {
        super(AuthAccount.class, forVariable(variable));
    }

    public QAuthAccount(Path<? extends AuthAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthAccount(PathMetadata metadata) {
        super(AuthAccount.class, metadata);
    }

}

