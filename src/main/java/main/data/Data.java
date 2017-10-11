package main.data;

import java.math.BigDecimal;
import java.util.Date;

public class Data {
	String entity;
	OperationType operationType;
	BigDecimal agreedFx;
	Currency currency;
	Date instructionDate;
	Date settlementDate;
	BigDecimal units;
	BigDecimal pricePerUnit;

	public Data(String entity, OperationType operationType, BigDecimal agreedFx, Currency currency, Date instructionDate,
			Date settlementDate, BigDecimal units, BigDecimal pricePerUnit) {
		super();
		this.entity = entity;
		this.operationType = operationType;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public BigDecimal getUsdAmountOfTrade() {
		return this.pricePerUnit.multiply(this.units).multiply(this.agreedFx);
	}
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getInfo() {
		return entity + " " + operationType + " " + agreedFx + " " + currency + " " + instructionDate + " " + settlementDate + " " + units + " " + pricePerUnit;
	}
}
