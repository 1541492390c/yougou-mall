package per.ccm.ygmall.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFeedback is a Querydsl query type for Feedback
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedback extends EntityPathBase<Feedback> {

    private static final long serialVersionUID = 1229753927L;

    public static final QFeedback feedback = new QFeedback("feedback");

    public final StringPath contactWay = createString("contactWay");

    public final StringPath content = createString("content");

    public final NumberPath<Long> feedbackId = createNumber("feedbackId", Long.class);

    public final NumberPath<Long> feedbackTypeId = createNumber("feedbackTypeId", Long.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFeedback(String variable) {
        super(Feedback.class, forVariable(variable));
    }

    public QFeedback(Path<? extends Feedback> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeedback(PathMetadata metadata) {
        super(Feedback.class, metadata);
    }

}

