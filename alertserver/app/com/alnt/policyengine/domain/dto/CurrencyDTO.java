package com.alnt.policyengine.domain.dto;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;

public class CurrencyDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

    private String symbol;
    
    private int decimals;
    
    private String peggedCurrency;
    
    private String flagIcon;

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
