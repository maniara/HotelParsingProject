package Parser;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {

	public static String getDate(int iDay){
		Calendar cal=Calendar.getInstance ( );

		cal.add ( Calendar.DAY_OF_MONTH, iDay );

		String nYear = String.valueOf(cal.get ( Calendar.YEAR ));
		String nMonth = String.valueOf(cal.get ( Calendar.MONTH ) + 1);
		String nDay =  String.valueOf(cal.get(Calendar.DAY_OF_MONTH) );
		String date = nYear+"-"+nMonth+"-"+nDay;
		
		return date;
	}
}
