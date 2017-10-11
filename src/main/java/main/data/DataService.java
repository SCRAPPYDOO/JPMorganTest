package main.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataService {
	private List<Data> listOfData = new ArrayList<>();
	
	public DataService(List<Data> listOfData) {
		this.setListOfData(listOfData);
	}

	public DataService() {
		// TODO Auto-generated constructor stub
	}

	public List<Data> getListOfData() {
		return listOfData;
	}

	public void setListOfData(List<Data> listOfData) {
		this.listOfData = listOfData;
		this.listOfData.stream().forEach(data -> {
			this.calculateNewSettlementDate(data);
		});
	}
	
	private void calculateNewSettlementDate(Data data) {
		Calendar settlementDate = Calendar.getInstance();
		settlementDate.setTime(data.getSettlementDate());
		int dayOfWeek = settlementDate.get(Calendar.DAY_OF_WEEK);
		if(this.isNormalWorkingDayForCurrency(data)) {
			//monday - friday
			if(dayOfWeek == Calendar.SATURDAY) {
				settlementDate.set(Calendar.DAY_OF_MONTH, settlementDate.get(Calendar.DAY_OF_MONTH) + 2);
			} else if (dayOfWeek == Calendar.SUNDAY) {
				settlementDate.set(Calendar.DAY_OF_MONTH, settlementDate.get(Calendar.DAY_OF_MONTH) + 1);
			}
		} else {
			if(dayOfWeek == Calendar.FRIDAY) {
				settlementDate.set(Calendar.DAY_OF_MONTH, settlementDate.get(Calendar.DAY_OF_MONTH) + 2);
			} else if (dayOfWeek == Calendar.SATURDAY) {
				settlementDate.set(Calendar.DAY_OF_MONTH, settlementDate.get(Calendar.DAY_OF_MONTH) + 1);
			}
		}
		
		data.setSettlementDate(settlementDate.getTime());
	}

	private boolean isNormalWorkingDayForCurrency(Data data) {
		Currency dataaCurrency = data.getCurrency();
		if(dataaCurrency == Currency.SAR || dataaCurrency == Currency.AED) {
			return false;
		}
		return true;
	}

	public List<Data> getIncomeRanking() {
		return this.sortListByOperationType(OperationType.S);
	}

	public List<Data> getOutcomeRanking() {
		return this.sortListByOperationType(OperationType.B);
	}
	
	private List<Data> sortListByOperationType(OperationType operationType) {		
		List<Data> temp = new ArrayList<>();
		this.listOfData.stream().sorted((data1, data2) -> data2.getUsdAmountOfTrade().compareTo(data1.getUsdAmountOfTrade())).forEach((data) -> {
			if(data.getOperationType().equals(operationType)) { 
				temp.add(data); 
			}
		});
		return temp;
	}

	public BigDecimal getSettledValueInUsdByDate(Date date, OperationType operationType) {
		BigDecimal value = BigDecimal.ZERO;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for(Data data : listOfData)	{
			Calendar dataCalendar = Calendar.getInstance();
			dataCalendar.setTime(data.getSettlementDate());
			if(data.getOperationType().equals(operationType) &&
				dataCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
				dataCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
				dataCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
			) {
				value = value.add(data.getUsdAmountOfTrade());
			}
		}
		return value;
	}
}
