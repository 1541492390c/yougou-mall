package per.ccm.ygmall.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpu is a Querydsl query type for Spu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpu extends EntityPathBase<Spu> {

    private static final long serialVersionUID = -1415066874L;

    public static final QSpu spu = new QSpu("spu");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final StringPath categories = createString("categories");

    public final StringPath cover = createString("cover");

    public final StringPath imgList = createString("imgList");

    public final StringPath name = createString("name");

    public final NumberPath<Long> spuId = createNumber("spuId", Long.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public QSpu(String variable) {
        super(Spu.class, forVariable(variable));
    }

    public QSpu(Path<? extends Spu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpu(PathMetadata metadata) {
        super(Spu.class, metadata);
    }

}

