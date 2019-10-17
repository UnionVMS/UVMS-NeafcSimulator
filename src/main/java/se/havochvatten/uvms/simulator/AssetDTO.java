package se.havochvatten.uvms.simulator;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AssetDTO {
    private UUID id;
    private UUID historyId;
    private Boolean ircsIndicator;
    private Boolean ersIndicator;
    private Boolean aisIndicator;
    private Boolean vmsIndicator;
    private String hullMaterial;
    private OffsetDateTime commissionDate;
    private String constructionYear;
    private String constructionPlace;
    private OffsetDateTime updateTime;
    private String source;
    private String vesselType;
    private OffsetDateTime vesselDateOfEntry;
    private String cfr;
    private String imo;
    private String ircs;
    private String mmsi;
    private String iccat;
    private String uvi;
    private String gfcm;
    private Boolean active;
    private String flagStateCode;
    private String eventCode;
    private String name;
    private String externalMarking;
    private Boolean agentIsAlsoOwner;
    private Double lengthOverAll;
    private Double lengthBetweenPerpendiculars;
    private Double safteyGrossTonnage;
    private Double otherTonnage;
    private Double grossTonnage;
    private String grossTonnageUnit;
    private String portOfRegistration;
    private Double powerOfAuxEngine;
    private Double powerOfMainEngine;
    private Boolean hasLicence;
    private String licenceType;
    private String mainFishingGearCode;
    private String subFishingGearCode;
    private String gearFishingType;
    private String ownerName;
    private Boolean hasVms;
    private String ownerAddress;
    private String assetAgentAddress;
    private String countryOfImportOrExport;
    private String typeOfExport;
    private OffsetDateTime administrativeDecisionDate;
    private String segment;
    private String segmentOfAdministrativeDecision;
    private String publicAid;
    private String registrationNumber;
    private String updatedBy;
    private String prodOrgCode;
    private String prodOrgName;
    private String comment;

    public AssetDTO() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getHistoryId() {
        return this.historyId;
    }

    public void setHistoryId(UUID historyId) {
        this.historyId = historyId;
    }

    public Boolean getIrcsIndicator() {
        return this.ircsIndicator;
    }

    public void setIrcsIndicator(Boolean ircsIndicator) {
        this.ircsIndicator = ircsIndicator;
    }

    public Boolean getErsIndicator() {
        return this.ersIndicator;
    }

    public void setErsIndicator(Boolean ersIndicator) {
        this.ersIndicator = ersIndicator;
    }

    public Boolean getAisIndicator() {
        return this.aisIndicator;
    }

    public void setAisIndicator(Boolean aisIndicator) {
        this.aisIndicator = aisIndicator;
    }

    public Boolean getVmsIndicator() {
        return this.vmsIndicator;
    }

    public void setVmsIndicator(Boolean vmsIndicator) {
        this.vmsIndicator = vmsIndicator;
    }

    public String getHullMaterial() {
        return this.hullMaterial;
    }

    public void setHullMaterial(String hullMaterial) {
        this.hullMaterial = hullMaterial;
    }

    public OffsetDateTime getCommissionDate() {
        return this.commissionDate;
    }

    public void setCommissionDate(OffsetDateTime commissionDate) {
        this.commissionDate = commissionDate;
    }

    public String getConstructionYear() {
        return this.constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getConstructionPlace() {
        return this.constructionPlace;
    }

    public void setConstructionPlace(String constructionPlace) {
        this.constructionPlace = constructionPlace;
    }

    public OffsetDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(OffsetDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVesselType() {
        return this.vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public OffsetDateTime getVesselDateOfEntry() {
        return this.vesselDateOfEntry;
    }

    public void setVesselDateOfEntry(OffsetDateTime vesselDateOfEntry) {
        this.vesselDateOfEntry = vesselDateOfEntry;
    }

    public String getCfr() {
        return this.cfr;
    }

    public void setCfr(String cfr) {
        this.cfr = cfr;
    }

    public String getImo() {
        return this.imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getIrcs() {
        return this.ircs;
    }

    public void setIrcs(String ircs) {
        this.ircs = ircs;
    }

    public String getMmsi() {
        return this.mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getIccat() {
        return this.iccat;
    }

    public void setIccat(String iccat) {
        this.iccat = iccat;
    }

    public String getUvi() {
        return this.uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }

    public String getGfcm() {
        return this.gfcm;
    }

    public void setGfcm(String gfcm) {
        this.gfcm = gfcm;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFlagStateCode() {
        return this.flagStateCode;
    }

    public void setFlagStateCode(String flagStateCode) {
        this.flagStateCode = flagStateCode;
    }

    public String getEventCode() {
        return this.eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalMarking() {
        return this.externalMarking;
    }

    public void setExternalMarking(String externalMarking) {
        this.externalMarking = externalMarking;
    }

    public Boolean getAgentIsAlsoOwner() {
        return this.agentIsAlsoOwner;
    }

    public void setAgentIsAlsoOwner(Boolean agentIsAlsoOwner) {
        this.agentIsAlsoOwner = agentIsAlsoOwner;
    }

    public Double getLengthOverAll() {
        return this.lengthOverAll;
    }

    public void setLengthOverAll(Double lengthOverAll) {
        this.lengthOverAll = lengthOverAll;
    }

    public Double getLengthBetweenPerpendiculars() {
        return this.lengthBetweenPerpendiculars;
    }

    public void setLengthBetweenPerpendiculars(Double lengthBetweenPerpendiculars) {
        this.lengthBetweenPerpendiculars = lengthBetweenPerpendiculars;
    }

    public Double getSafteyGrossTonnage() {
        return this.safteyGrossTonnage;
    }

    public void setSafteyGrossTonnage(Double safteyGrossTonnage) {
        this.safteyGrossTonnage = safteyGrossTonnage;
    }

    public Double getOtherTonnage() {
        return this.otherTonnage;
    }

    public void setOtherTonnage(Double otherTonnage) {
        this.otherTonnage = otherTonnage;
    }

    public Double getGrossTonnage() {
        return this.grossTonnage;
    }

    public void setGrossTonnage(Double grossTonnage) {
        this.grossTonnage = grossTonnage;
    }

    public String getGrossTonnageUnit() {
        return this.grossTonnageUnit;
    }

    public void setGrossTonnageUnit(String grossTonnageUnit) {
        this.grossTonnageUnit = grossTonnageUnit;
    }

    public String getPortOfRegistration() {
        return this.portOfRegistration;
    }

    public void setPortOfRegistration(String portOfRegistration) {
        this.portOfRegistration = portOfRegistration;
    }

    public Double getPowerOfAuxEngine() {
        return this.powerOfAuxEngine;
    }

    public void setPowerOfAuxEngine(Double powerOfAuxEngine) {
        this.powerOfAuxEngine = powerOfAuxEngine;
    }

    public Double getPowerOfMainEngine() {
        return this.powerOfMainEngine;
    }

    public void setPowerOfMainEngine(Double powerOfMainEngine) {
        this.powerOfMainEngine = powerOfMainEngine;
    }

    public Boolean getHasLicence() {
        return this.hasLicence;
    }

    public void setHasLicence(Boolean hasLicence) {
        this.hasLicence = hasLicence;
    }

    public String getLicenceType() {
        return this.licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getMainFishingGearCode() {
        return this.mainFishingGearCode;
    }

    public void setMainFishingGearCode(String mainFishingGearCode) {
        this.mainFishingGearCode = mainFishingGearCode;
    }

    public String getSubFishingGearCode() {
        return this.subFishingGearCode;
    }

    public void setSubFishingGearCode(String subFishingGearCode) {
        this.subFishingGearCode = subFishingGearCode;
    }

    public String getGearFishingType() {
        return this.gearFishingType;
    }

    public void setGearFishingType(String gearFishingType) {
        this.gearFishingType = gearFishingType;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Boolean getHasVms() {
        return this.hasVms;
    }

    public void setHasVms(Boolean hasVms) {
        this.hasVms = hasVms;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getAssetAgentAddress() {
        return this.assetAgentAddress;
    }

    public void setAssetAgentAddress(String assetAgentAddress) {
        this.assetAgentAddress = assetAgentAddress;
    }

    public String getCountryOfImportOrExport() {
        return this.countryOfImportOrExport;
    }

    public void setCountryOfImportOrExport(String countryOfImportOrExport) {
        this.countryOfImportOrExport = countryOfImportOrExport;
    }

    public String getTypeOfExport() {
        return this.typeOfExport;
    }

    public void setTypeOfExport(String typeOfExport) {
        this.typeOfExport = typeOfExport;
    }

    public OffsetDateTime getAdministrativeDecisionDate() {
        return this.administrativeDecisionDate;
    }

    public void setAdministrativeDecisionDate(OffsetDateTime administrativeDecisionDate) {
        this.administrativeDecisionDate = administrativeDecisionDate;
    }

    public String getSegment() {
        return this.segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSegmentOfAdministrativeDecision() {
        return this.segmentOfAdministrativeDecision;
    }

    public void setSegmentOfAdministrativeDecision(String segmentOfAdministrativeDecision) {
        this.segmentOfAdministrativeDecision = segmentOfAdministrativeDecision;
    }

    public String getPublicAid() {
        return this.publicAid;
    }

    public void setPublicAid(String publicAid) {
        this.publicAid = publicAid;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getProdOrgCode() {
        return this.prodOrgCode;
    }

    public void setProdOrgCode(String prodOrgCode) {
        this.prodOrgCode = prodOrgCode;
    }

    public String getProdOrgName() {
        return this.prodOrgName;
    }

    public void setProdOrgName(String prodOrgName) {
        this.prodOrgName = prodOrgName;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
