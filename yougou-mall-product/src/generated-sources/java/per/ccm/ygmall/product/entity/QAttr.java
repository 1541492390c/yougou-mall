package per.ccm.ygmall.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttr is a Querydsl query type for Attr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttr extends EntityPathBase<Attr> {

    private static final long serialVersionUID = -917932445L;

    public static final QAttr attr = new QAttr("attr");

    public final NumberPath<Long> attrId = createNumber("attrId", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> spuId = createNumber("spuId", Long.class);

    public QAttr(String variable) {
        super(Attr.class, forVariable(variable));
    }

    public QAttr(Path<? extends Attr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttr(PathMetadata metadata) {
        super(Attr.class, metadata);
    }

}

