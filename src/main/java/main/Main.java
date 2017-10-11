package main;

import java.util.Calendar;

import main.report.ReportService;

public class Main {

	public static void main(String[] args) {
		ReportService reportService = new ReportService();
		
		reportService.createIncomingReport(1,7,Calendar.OCTOBER);
		reportService.createOutgoingReport(1,7,Calendar.OCTOBER);
		reportService.createRanking();
		reportService.showData();

	}

}
