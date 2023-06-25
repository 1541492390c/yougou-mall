package per.ccm.ygmall.user.service;

import per.ccm.ygmall.user.dto.AddrDTO;
import per.ccm.ygmall.user.vo.AddrVO;

import java.util.List;

public interface AddrService {
    /**
     * 保存收货地址信息
     *
     * @param addrDTO 收货地址传输数据
     * */
    void save(AddrDTO addrDTO) throws Exception;

    /**
     * 根据用户ID收货地址列表
     *
     * @param userId 用户ID
     * */
    List<AddrVO> getAddrList(Long userId) throws Exception;

    /**
     * 更新收货地址信息
     *
     * @param addrDTO 收货地址传输数据
     * */
    void update(AddrDTO addrDTO) throws Exception;

    /**
     * 根据主键ID删除收货地址信息
     *
     * @param addrId 主键ID
     * */
    void delete(Long addrId) throws Exception;
}
