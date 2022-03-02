package pers.jasper.bill.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "资金来源")
public class FundSourceDto {
    @ApiModelProperty(value = "来源名称", required = true)
    private String source;
    @ApiModelProperty(value = "显示颜色")
    private String color;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
