package com.honest.sdms.system.entity;

public class ItemSpecific extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long itemId;
    private String item;
    private Long gradeId;
    private Long standardId;
    private double netWeight;
    private String specificCode;
    private String isValid;

    
    public double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public String getSpecificCode() {
		return specificCode;
	}

	public void setSpecificCode(String specificCode) {
		this.specificCode = specificCode;
	}

}