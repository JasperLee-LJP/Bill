package pers.jasper.bill.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "资金流水")
public class FundDto {
    @ApiModelProperty(value = "资金来源ID", required = true)
    private Integer sourceId;
    @ApiModelProperty( value = "交易时间", example = "yyyy-MM-dd HH:mm:ss", required = true)
    private String transactionTime;
    @ApiModelProperty(value = "金额", required = true)
    private Integer amount;
    @ApiModelProperty(value = "本金")
    private Integer principal;
    @ApiModelProperty(value = "利息")
    private Integer interest;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }
}
