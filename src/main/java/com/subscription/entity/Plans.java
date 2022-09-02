package com.subscription.entity;

public class Plans {

	private String planeName;
	private int fee;
	private int validity;

	public Plans(String planeName, int fee, int validity) {
		super();
		this.planeName = planeName;
		this.fee = fee;
		this.validity = validity;
	}
	
	public Plans() {
		super();
		
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

}
