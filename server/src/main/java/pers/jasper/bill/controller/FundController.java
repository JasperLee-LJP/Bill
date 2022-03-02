package pers.jasper.bill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.jasper.bill.dto.FundDto;
import pers.jasper.bill.exception.CustomException;
import pers.jasper.bill.exception.ErrorCode;
import pers.jasper.bill.mapper.FundMapper;
import pers.jasper.bill.po.Fund;
import pers.jasper.bill.po.FundSource;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
@Api(tags = "账单流水")
public class FundController {
    @Autowired
    private FundMapper fundMapper;
    @Autowired
    private DateFormat dateFormat;

    @PostMapping(value = "fund")
    @ApiOperation(value = "添加资金流水")
    ResponseEntity<Fund> addFund(@RequestBody FundDto fundDto) throws ParseException {
        Fund fund = transfer(fundDto);
        fundMapper.addFund(fund);
        List<Fund> funds = fundMapper.getFundById(fund.getId());
        if(funds.size() > 0) {
            return new ResponseEntity<>(funds.get(0), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(fund, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "fund/{id}")
    @ApiOperation(value = "修改资金流水")
    ResponseEntity<Fund> updateFund(@PathVariable Integer id, @RequestBody FundDto fundDto)
            throws ParseException {
        Fund fund = transfer(fundDto);
        fund.setId(id);
        Integer count = fundMapper.updateFund(fund);
        if(count > 0) {
            List<Fund> funds = fundMapper.getFundById(id);
            if(funds.size() == 0) {
                throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FUND);
            }
            return new ResponseEntity<>(funds.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(fund, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "fund/{id}")
    @ApiOperation(value = "删除资金流水")
    ResponseEntity<Fund> deleteFund(@PathVariable Integer id) {
        List<Fund> funds = fundMapper.getFundById(id);
        if(funds.size() == 0) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FUND);
        }

        int count = fundMapper.deleteFund(id);
        if(count > 0) {
            return new ResponseEntity<>(funds.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(funds.get(0), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "fund")
    @ApiOperation(value = "获取资金流水列表")
    ResponseEntity<List<Fund>> getFunds() {
        List<Fund> funds = fundMapper.getFunds();
        return new ResponseEntity<>(funds, HttpStatus.OK);
    }

    private Fund transfer(FundDto fundDto) throws ParseException {
        Fund fund = new Fund();
        BeanUtils.copyProperties(fundDto, fund);
        fund.setCreateTime(new Date());

        FundSource fundSource = new FundSource();
        fundSource.setId(fundDto.getSourceId());
        fund.setFundSource(fundSource);

        String transactionTime = fundDto.getTransactionTime();
        if(transactionTime != null && transactionTime != "") {
            fund.setTransactionTime(dateFormat.parse(transactionTime));
        }
        return fund;
    }
}
