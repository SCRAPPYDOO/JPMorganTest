package main.report;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.data.Data;
import main.data.DataService;
import main.data.OperationType;
import main.utils.DataGenerator;

public class ReportService {

	DataService dataService = new DataService(DataGenerator.generateRandomData());
	
	public void createIncomingReport(int firstDay, int lastDay, int month) {
		System.out.println("DAILY INCOME REPORTS");
		firstDay -= 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		for(int i=firstDay; i<lastDay; i++) { 
			calendar.set(Calendar.DAY_OF_MONTH, i+1);
			this.createReportByDay(calendar.getTime(), OperationType.S);
		}
	}
	
	public void createOutgoingReport(int firstDay, int lastDay, int month) {
		firstDay -= 1;
		System.out.println("");
		System.out.println("");
		System.out.println("DAILY OUTCOME REPORTS");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		for(int i=firstDay; i<lastDay; i++) { 
			calendar.set(Calendar.DAY_OF_MONTH, i+1);
			this.createReportByDay(calendar.getTime(), OperationType.B);
		}
	}
	
	public void createReportByDay(Date date, OperationType operationType) {
		BigDecimal incomes = dataService.getSettledValueInUsdByDate(date, operationType);
		System.out.println(date + ": INCOMES = " + incomes + " USD");	
	}
	
	public void createRanking() {
		System.out.println("");
		System.out.println("");
		System.out.println("INCOMING AMOUNT RANKING");
		List<Data> incomeRankingList = dataService.getIncomeRanking();
		for(int i=0; i<incomeRankingList.size(); i++) {
			int position = i+1;
			System.out.println(position + ": " + incomeRankingList.get(i).getEntity() + " : " + incomeRankingList.get(i).getUsdAmountOfTrade() + " USD");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("OUTGOING AMOUNT RANKING");
		List<Data> outcomeRankingList = dataService.getOutcomeRanking();
		for(int i=0; i<outcomeRankingList.size(); i++) {
			int position = i+1;
			System.out.println(position + ": " + outcomeRankingList.get(i).getEntity() + " : " + outcomeRankingList.get(i).getUsdAmountOfTrade() + " USD");
		}
	}
	
	public void showData() {
		System.out.println("");
		System.out.println("");
		System.out.println("DATA USED");
		for(Data data : dataService.getListOfData()) {
			System.out.println(data.getInfo());
		}
	}
}