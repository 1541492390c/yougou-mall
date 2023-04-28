package per.ccm.ygmall.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttrValue is a Querydsl query type for AttrValue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttrValue extends EntityPathBase<AttrValue> {

    private static final long serialVersionUID = -1509314354L;

    public static final QAttrValue attrValue = new QAttrValue("attrValue");

    public final NumberPath<Long> attrId = createNumber("attrId", Long.class);

    public final NumberPath<Long> attrValueId = createNumber("attrValueId", Long.class);

    public final StringPath name = createString("name");

    public QAttrValue(String variable) {
        super(AttrValue.class, forVariable(variable));
    }

    public QAttrValue(Path<? extends AttrValue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttrValue(PathMetadata metadata) {
        super(AttrValue.class, metadata);
    }

}

