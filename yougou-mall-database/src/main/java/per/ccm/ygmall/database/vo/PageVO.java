package per.ccm.ygmall.database.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVO<T> {

    private Long total;

    private Integer size;

    private List<T> list;

    public PageVO(Long total, List<T> list) {
        this.total = total;
        this.size = list.size();
        this.list = list;
    }
}