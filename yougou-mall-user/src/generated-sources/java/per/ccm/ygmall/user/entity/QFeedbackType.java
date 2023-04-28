package per.ccm.ygmall.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFeedbackType is a Querydsl query type for FeedbackType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedbackType extends EntityPathBase<FeedbackType> {

    private static final long serialVersionUID = -1738140127L;

    public static final QFeedbackType feedbackType = new QFeedbackType("feedbackType");

    public final NumberPath<Long> feedbackTypeId = createNumber("feedbackTypeId", Long.class);

    public final StringPath feedbackTypeName = createString("feedbackTypeName");

    public QFeedbackType(String variable) {
        super(FeedbackType.class, forVariable(variable));
    }

    public QFeedbackType(Path<? extends FeedbackType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeedbackType(PathMetadata metadata) {
        super(FeedbackType.class, metadata);
    }

}

