package pers.jasper.bill.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jasper.bill.po.Fund;

import java.util.List;

@Mapper
public interface FundMapper {
    int addFund();
    int updateFund();
    int deleteFund();
    List<Fund> getFunds();
}
