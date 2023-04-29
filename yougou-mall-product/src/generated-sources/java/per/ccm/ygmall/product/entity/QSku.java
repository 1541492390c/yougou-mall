package per.ccm.ygmall.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSku is a Querydsl query type for Sku
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSku extends EntityPathBase<Sku> {

    private static final long serialVersionUID = -1415067029L;

    public static final QSku sku = new QSku("sku");

    public final StringPath desc = createString("desc");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Long> skuId = createNumber("skuId", Long.class);

    public final NumberPath<Integer> skuStock = createNumber("skuStock", Integer.class);

    public final StringPath specs = createString("specs");

    public final NumberPath<Long> spuId = createNumber("spuId", Long.class);

    public QSku(String variable) {
        super(Sku.class, forVariable(variable));
    }

    public QSku(Path<? extends Sku> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSku(PathMetadata metadata) {
        super(Sku.class, metadata);
    }

}

