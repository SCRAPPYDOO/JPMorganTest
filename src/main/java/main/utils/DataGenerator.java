package main.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import main.data.Currency;
import main.data.Data;
import main.data.OperationType;

public class DataGenerator {
	public static List<Data> generateRandomData() {
		List<Data> temp = new ArrayList<>();
		Random generator = new Random();
		for(int i=0; i<10; i++) {
			Date date1 = new GregorianCalendar(2017, Calendar.OCTOBER, 1).getTime();
			Date date2 = new GregorianCalendar(2017, Calendar.OCTOBER, generator.nextInt(8) + 1).getTime();
			BigDecimal agreedFx = new BigDecimal(generator.nextDouble()*10).setScale(2, RoundingMode.HALF_UP);
			BigDecimal units = new BigDecimal(generator.nextInt(300));
			BigDecimal pricePerUnit = new BigDecimal( generator.nextDouble()*10).setScale(2, RoundingMode.HALF_UP);
			OperationType operationType = generator.nextInt(2) == 0 ? OperationType.S : OperationType.B;
			Currency currency = Currency.values()[generator.nextInt(5)];
			temp.add(new Data("foo" + i, operationType, agreedFx, currency, date1, date2, units, pricePerUnit));
		}
		return temp;
	}
}
