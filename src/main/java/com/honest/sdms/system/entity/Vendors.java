package com.honest.sdms.system.entity;

public class Vendors extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
    private String vendorName;
    private String vendorCode;
    private String vendorClass;
    private Double vendorScore;
    private String vendorContacts;
    private String vendorPhone;
    private String vendorAddress;
    private String vendorEmail;
    private String network;
    private String facsimile;
    private String expansion;
    private String isValid;
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode == null ? null : vendorCode.trim();
    }

    public String getVendorClass() {
        return vendorClass;
    }

    public void setVendorClass(String vendorClass) {
        this.vendorClass = vendorClass == null ? null : vendorClass.trim();
    }

    public Double getVendorScore() {
        return vendorScore;
    }

    public void setVendorScore(Double vendorScore) {
        this.vendorScore = vendorScore;
    }

    public String getVendorContacts() {
        return vendorContacts;
    }

    public void setVendorContacts(String vendorContacts) {
        this.vendorContacts = vendorContacts == null ? null : vendorContacts.trim();
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone == null ? null : vendorPhone.trim();
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress == null ? null : vendorAddress.trim();
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail == null ? null : vendorEmail.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public String getFacsimile() {
        return facsimile;
    }

    public void setFacsimile(String facsimile) {
        this.facsimile = facsimile == null ? null : facsimile.trim();
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion == null ? null : expansion.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}