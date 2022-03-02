package pers.jasper.bill.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jasper.bill.po.Fund;

import java.util.List;

@Mapper
public interface FundMapper {
    int addFund(Fund fund);
    int updateFund(Fund fund);
    int deleteFund(Integer id);
    List<Fund> getFundById(Integer id);
    List<Fund> getFunds();
}
