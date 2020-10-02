package java8.timeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Example {
    public static void main(String[] args) {
        System.out.println(LocalDate.of(12, 12, 12));
        LocalDate date = LocalDate.of(2020,10,02);
        LocalDate today = LocalDate.now();
        System.out.println(today);
        boolean value = today.equals(date)?true:false;
        System.out.println(value);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);


        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("This year is not a Leap year");
        }

        String date1 = "20200202";
        LocalDate format1 = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format1);

        String date2 = "Oct 12 2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate holiday = LocalDate.parse(date2, formatter);
        System.out.println(holiday);
    }
}
