package com.mall.vo;

import java.math.BigDecimal;

/**
 * Created by root on 9/11/17.
 */
public class ProductListVo {
    private Integer VoId;
    private Integer VoCategoryId;
    private String VoName;
    private String VoSubtitle;
    private String VoMainImage;
    private BigDecimal VoPrice;
    private Integer VoStatus;

    private String VoImageHost;

    public Integer getVoId() {
        return VoId;
    }

    public void setVoId(Integer voId) {
        VoId = voId;
    }

    public Integer getVoCategoryId() {
        return VoCategoryId;
    }

    public void setVoCategoryId(Integer voCategoryId) {
        VoCategoryId = voCategoryId;
    }

    public String getVoName() {
        return VoName;
    }

    public void setVoName(String voName) {
        VoName = voName;
    }

    public String getVoSubtitle() {
        return VoSubtitle;
    }

    public void setVoSubtitle(String voSubtitle) {
        VoSubtitle = voSubtitle;
    }

    public String getVoMainImage() {
        return VoMainImage;
    }

    public void setVoMainImage(String voMainImage) {
        VoMainImage = voMainImage;
    }

    public BigDecimal getVoPrice() {
        return VoPrice;
    }

    public void setVoPrice(BigDecimal voPrice) {
        VoPrice = voPrice;
    }

    public Integer getVoStatus() {
        return VoStatus;
    }

    public void setVoStatus(Integer voStatus) {
        VoStatus = voStatus;
    }

    public String getVoImageHost() {
        return VoImageHost;
    }

    public void setVoImageHost(String voImageHost) {
        VoImageHost = voImageHost;
    }

}
