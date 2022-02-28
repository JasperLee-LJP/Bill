package pers.jasper.bill.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jasper.bill.po.FundSource;

import java.util.List;

@Mapper
public interface FundSourceMapper {
    int addFundSource(FundSource fundSource);
    int updateFundSource(FundSource fundSource);
    int deleteFundSource(Integer id);
    List<FundSource> getFundSourceById(Integer id);
    List<FundSource> getFundSources();
}
