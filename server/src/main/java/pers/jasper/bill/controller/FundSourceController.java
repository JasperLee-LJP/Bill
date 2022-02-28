package pers.jasper.bill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.jasper.bill.exception.CustomException;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.mapper.FundSourceMapper;
import pers.jasper.bill.po.FundSource;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
@Api(tags = "资金来源")
public class FundSourceController {
    @Autowired
   private FundSourceMapper fundSourceMapper;

    @PostMapping(value = "fundSource")
    @ApiOperation(value = "添加资金来源")
    ResponseEntity<FundSource> addFundSource(@RequestBody FundSource fundSource){
        fundSource.setCreateTime(new Date());
        fundSourceMapper.addFundSource(fundSource);
        return new ResponseEntity<>(fundSource, HttpStatus.CREATED);
    }

    @PutMapping(value = "fundSource/{id}")
    @ApiOperation(value = "修改资金来源")
    ResponseEntity<FundSource> updateFundSource(@PathVariable Integer id, @RequestBody FundSource fundSource){
        List<FundSource> sources = fundSourceMapper.getFundSourceById(id);
        if(sources.size() == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FUND);
        }

        fundSource.setId(id);
        Integer count = fundSourceMapper.updateFundSource(fundSource);
        if(count > 0) {
            FundSource source = sources.get(0);
            BeanUtils.copyProperties(fundSource, source, "id", "createTime");
            return new ResponseEntity<>(sources.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(fundSource, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "fundSource/{id}")
    @ApiOperation(value = "删除资金来源")
    ResponseEntity<FundSource> deleteFundSource(@PathVariable Integer id){
        List<FundSource> sources = fundSourceMapper.getFundSourceById(id);
        if(sources.size() == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FUND);
        }

        int count = fundSourceMapper.deleteFundSource(id);
        if(count > 0) {
            return new ResponseEntity<>(sources.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(sources.get(0), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "fundSource")
    @ApiOperation(value = "获取资金来源列表")
    ResponseEntity<List<FundSource>> getFundSources(){
        List<FundSource> sources = fundSourceMapper.getFundSources();
        return new ResponseEntity<>(sources, HttpStatus.OK);
    }
}
