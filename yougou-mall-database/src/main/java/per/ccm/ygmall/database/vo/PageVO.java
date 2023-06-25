package per.ccm.ygmall.database.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageVO<T> extends BaseVO {

    private Long total;

    private Integer size;

    private List<T> list;

    public PageVO(Long total, List<T> list) {
        this.total = total;
        this.size = list.size();
        this.list = list;
    }
}