package per.ccm.ygmall.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVO<T> {

    private Long total;

    private Integer size;

    private List<T> list;

    public PageVO() {
    }

    public PageVO(Long total, List<T> list) {
        this.total = total;
        this.size = list.size();
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
