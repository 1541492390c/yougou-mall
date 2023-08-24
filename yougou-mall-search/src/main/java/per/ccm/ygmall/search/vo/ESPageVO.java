package per.ccm.ygmall.search.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

@Setter
@Getter
@ToString
public class ESPageVO<T> extends BaseVO {

    private Long total;

    private Integer size;

    private List<T> list;

    public ESPageVO(Long total, List<T> list) {
        this.total = total;
        this.size = list.size();
        this.list = list;
    }
}
