package ru.sapteh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main (String[] args) throws IOException, ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date dateToday = calendar.getTime();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите день экзамена");
        System.out.println("Пример введения даты экзамена: 10/10/1000");
        String eGe = bufferedReader.readLine();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateExam = dateFormat.parse(eGe);
        int quantityDay = quantityDay(dateExam,dateToday);
        int quantityMonth = quantityMonth(dateExam,dateToday);
        int quantityYear = quantityYear(dateExam,dateToday);
        String dayCount = "",monthCount = "", yearCount = "";
        while (quantityDay >= 30){
            quantityDay = quantityDay - 30;
        }
        while (quantityMonth >= 12){
            quantityMonth = quantityMonth - 12;
        }
        while (quantityDay <= -30){
            quantityDay = quantityDay + 30;
        }
        while (quantityMonth <= -12){
            quantityMonth = quantityMonth + 12;
        }
        if (quantityDay >= 0 && quantityMonth >= 0 && quantityYear >= 0){
            if (quantityDay == 0 && quantityMonth == 0 && quantityYear == 0){
                System.out.println("Экзамен сегодня");
            } else System.out.println("Экзамен будет через " + dayMonthYear(dayCount,monthCount,yearCount,quantityDay,quantityMonth,quantityYear));
        }
        if (quantityDay < 0 || quantityMonth < 0 ||quantityYear < 0) {
            System.out.println("Экзамен был " + dayMonthYear(dayCount,monthCount,yearCount,quantityDay,quantityMonth,quantityYear) + " назад");
        }
    }
    public static int quantityDay (Date dateEGE, Date dateToday){
        return (int)((dateEGE.getTime() - dateToday.getTime())/1000/24/60/60);
    }
    public static int quantityMonth (Date dateEGE, Date dateToday){
        return (int)((dateEGE.getTime() - dateToday.getTime())/1000/30/24/60/60);
    }
    public static int quantityYear (Date dateEGE, Date dateToday){
        return (int)((dateEGE.getTime() - dateToday.getTime())/1000/12/30/24/60/60);
    }
    public static String dayMonthYear(String dayCount,String monthCount,String yearCount,int quantityDay, int quantityMonth, int quantityYear){
        if (quantityDay != 0) {
            dayCount = Math.abs(quantityDay) + wordDay(quantityDay);
        }
        if (quantityMonth != 0) {
            monthCount = Math.abs(quantityMonth) + wordMonth(quantityMonth);
        }
        if (quantityYear != 0) {
            yearCount = Math.abs(quantityYear) + wordYear(quantityYear);
        }
        return dayCount + monthCount + yearCount;
    }
    public static String wordDay (int quantityDay){
        quantityDay = Math.abs(quantityDay);
        return switch (quantityDay%10){
            case 1 -> " День ";
            case 2, 3, 4 -> " Дня ";
            default -> " Дней ";
        };
    }
    public static String wordMonth (int quantityMonth){
        quantityMonth = Math.abs(quantityMonth);
        return switch (quantityMonth){
            case 1 -> " Месяц ";
            case 2, 3, 4 -> " Месяца ";
            default -> " Месяцев ";
        };
    }
    public static String wordYear (int quantityYear){
        quantityYear = Math.abs(quantityYear);
        return switch (quantityYear%100){
            case 1 -> " Год";
            case 2, 3, 4 -> " Года";
            default -> " Лет";
        };
    }
}