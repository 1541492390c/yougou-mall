package per.ccm.ygmall.platform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBanner is a Querydsl query type for Banner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBanner extends EntityPathBase<Banner> {

    private static final long serialVersionUID = -1831746538L;

    public static final QBanner banner = new QBanner("banner");

    public final NumberPath<Long> bannerId = createNumber("bannerId", Long.class);

    public final StringPath desc = createString("desc");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath img = createString("img");

    public final StringPath link = createString("link");

    public final StringPath page = createString("page");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QBanner(String variable) {
        super(Banner.class, forVariable(variable));
    }

    public QBanner(Path<? extends Banner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBanner(PathMetadata metadata) {
        super(Banner.class, metadata);
    }

}

