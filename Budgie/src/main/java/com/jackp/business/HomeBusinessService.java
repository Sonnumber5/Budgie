package com.jackp.business;

import org.springframework.stereotype.Service;

@Service
public class HomeBusinessService implements HomeBusinessInterface{

	@Override
	public String writtenDateFormatter(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String writtenMonth = "";
		
		switch (month) {
		
			case "01":
				writtenMonth = "January";
				break;
			case "02":
				writtenMonth = "February";
				break;
			case "03":
				writtenMonth = "March";
				break;
			case "04":
				writtenMonth = "April";
				break;
			case "05":
				writtenMonth = "May";
				break;
			case "06":
				writtenMonth = "June";
				break;
			case "07":
				writtenMonth = "July";
				break;
			case "08":
				writtenMonth = "August";
				break;
			case "09":
				writtenMonth = "September";
				break;
			case "10":
				writtenMonth = "October";
				break;
			case "11":
				writtenMonth = "November";
				break;
			case "12":
				writtenMonth = "December";
				break;
		}
		return writtenMonth + ", " + year;
	}

}
