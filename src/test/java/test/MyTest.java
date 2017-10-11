package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import main.data.Currency;
import main.data.Data;
import main.data.DataService;
import main.data.OperationType;

public class MyTest {

	@Test
	public void test() {

		
		//Check Ranking of Income and Outcome
		List<Data> listOfData = new ArrayList<>(Arrays.asList(
			new Data("foo1", OperationType.S, new BigDecimal(10), Currency.SAR, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 7).getTime(),  new BigDecimal(10),  new BigDecimal(10)),
			new Data("foo5", OperationType.S, new BigDecimal(15), Currency.USD, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 7).getTime(),  new BigDecimal(15),  new BigDecimal(15)),
			new Data("foo2", OperationType.S, new BigDecimal(20), Currency.SAR, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 5).getTime(),  new BigDecimal(20),  new BigDecimal(20)),
			new Data("foo3", OperationType.B, new BigDecimal(10), Currency.SAR, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 4).getTime(),  new BigDecimal(10),  new BigDecimal(10)),
			new Data("foo4", OperationType.B, new BigDecimal(20), Currency.SAR, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 3).getTime(),  new BigDecimal(20),  new BigDecimal(20)),
			new Data("foo6", OperationType.B, new BigDecimal(30), Currency.USD, new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(), new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime(),  new BigDecimal(30),  new BigDecimal(30))
				));
		
		DataService dataService = new DataService(listOfData);
		
		System.out.println("TEST DATA USED");
		for(Data data : dataService.getListOfData()) {
			System.out.println(data.getInfo());
		}
		
		//Income ranking
		assertEquals(dataService.getIncomeRanking().get(0).getEntity(), "foo2");
		assertEquals(dataService.getIncomeRanking().get(1).getEntity(), "foo5");
		//Outcome Ranking
		assertEquals(dataService.getOutcomeRanking().get(0).getEntity(), "foo6");
		assertEquals(dataService.getOutcomeRanking().get(1).getEntity(), "foo4");
		
		//Test Daily Income and proper date
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 7).getTime(), OperationType.S), new BigDecimal(0));
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 5).getTime(), OperationType.S), new BigDecimal(8000));
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 8).getTime(), OperationType.S), new BigDecimal(1000));
		
		//Test Daily Outcome
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 3).getTime(), OperationType.B), new BigDecimal(8000));
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 4).getTime(), OperationType.B), new BigDecimal(1000));
		assertEquals(dataService.getSettledValueInUsdByDate(new GregorianCalendar(2017, Calendar.OCTOBER, 5).getTime(), OperationType.B), new BigDecimal(0));
		
		//Check proper settlement Date
		//From 1 to 2 10.2017
		for(Data data : dataService.getListOfData()) {
			if(data.getEntity().equals("foo6")) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data.getSettlementDate());
				assertEquals(2017, calendar.get(Calendar.YEAR));
				assertEquals(Calendar.OCTOBER, calendar.get(Calendar.MONTH));
				assertEquals(2, calendar.get(Calendar.DAY_OF_MONTH));
			}
		}
		
		System.out.println("TEST COMPLITED");
	}
}
