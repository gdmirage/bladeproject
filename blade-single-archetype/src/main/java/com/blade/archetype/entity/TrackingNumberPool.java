package com.blade.archetype.entity;

import java.util.Date;

public class TrackingNumberPool {
    private Long id;

    private String creator;

    private Date createDate;

    private String modifier;

    private Date modifyDate;

    private Byte isDelete;

    private String usePlatform;

    private String trackingNumber;

    private String logisticsType;

    private Byte isUsed;

    private String targetCountry;

    private Date erpSendGoodDate;

    private Date onlineDate;

    private Date useDate;

    private Byte sourceType;

    private String sourcePlatform;

    private String sourceErpOrderId;

    private String usedPlatformOrderId;

    private Byte useSystem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getUsePlatform() {
        return usePlatform;
    }

    public void setUsePlatform(String usePlatform) {
        this.usePlatform = usePlatform == null ? null : usePlatform.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(String logisticsType) {
        this.logisticsType = logisticsType == null ? null : logisticsType.trim();
    }

    public Byte getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Byte isUsed) {
        this.isUsed = isUsed;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public void setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry == null ? null : targetCountry.trim();
    }

    public Date getErpSendGoodDate() {
        return erpSendGoodDate;
    }

    public void setErpSendGoodDate(Date erpSendGoodDate) {
        this.erpSendGoodDate = erpSendGoodDate;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform == null ? null : sourcePlatform.trim();
    }

    public String getSourceErpOrderId() {
        return sourceErpOrderId;
    }

    public void setSourceErpOrderId(String sourceErpOrderId) {
        this.sourceErpOrderId = sourceErpOrderId == null ? null : sourceErpOrderId.trim();
    }

    public String getUsedPlatformOrderId() {
        return usedPlatformOrderId;
    }

    public void setUsedPlatformOrderId(String usedPlatformOrderId) {
        this.usedPlatformOrderId = usedPlatformOrderId == null ? null : usedPlatformOrderId.trim();
    }

    public Byte getUseSystem() {
        return useSystem;
    }

    public void setUseSystem(Byte useSystem) {
        this.useSystem = useSystem;
    }
}