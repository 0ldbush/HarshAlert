package com.alnt.policyengine.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;

@Entity
@Table(name = "CURRENCY")
@SQLDelete(sql = "UPDATE CURRENCY SET INT_STATUS = 3 WHERE ID = ?")
public class Currency extends BaseMasterEntity{

	private static final long serialVersionUID = 1L;

	@Column(name="symbol")
    private String symbol;
    
    @Column(name="decimals")
    private int decimals;
    
    @Column(name="peggedCurrency")
    private String peggedCurrency;
    
    @Column(name="flagIcon")
    private String flagIcon;

    @Column(name="country")
    private String country;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	public String getPeggedCurrency() {
		return peggedCurrency;
	}

	public void setPeggedCurrency(String peggedCurrency) {
		this.peggedCurrency = peggedCurrency;
	}

	public String getFlagIcon() {
		return flagIcon;
	}

	public void setFlagIcon(String flagIcon) {
		this.flagIcon = flagIcon;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
