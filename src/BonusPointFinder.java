/**
 @Author Domantas Kernagis
 @Date 2020-09-14

  * Devbridge Sourcery for Developers special task for the entry exam bonus points
  * Task -- method that prints all dates between two given years that remain the same if numbers of the date are reversed.
 **/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BonusPointFinder {
    public static void main(String[] args) {
        printBonusDatesBetween(2010, 2015);
        printBonusDatesBetween(1999, 2004);
        printBonusDatesBetween(2010, 2010);
        try {
            printBonusDatesBetween(-1999,-2001);
        }
        catch (RuntimeException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }

        try {
            printBonusDatesBetween(2005, 1999);
        }
        catch (RuntimeException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }
    }

    /*This method prints all of the bonus dates between the fromYear (inclusive) and toYear(exclusive)*/
    public static void printBonusDatesBetween(int fromYear, int toYear){
        System.out.println(String.format("fromYear - %d, toYear - %d", fromYear, toYear));
        DateValidation(fromYear, toYear);

        LocalDate startDate = LocalDate.of(fromYear, 1, 1);
        LocalDate endDate;

        //checks if the fromYear and toYear are equal, if they are, the endDate is determined accordingly
        if(fromYear == toYear){
            endDate = LocalDate.of(toYear, 12, 31);
        } else {
            endDate = LocalDate.of(toYear-1, 12, 31);
        }

        List<LocalDate> palindromeDates = getPalindromeDates(startDate, endDate);

        if(palindromeDates.size() == 0){
            System.out.println("There are no bonus dates");
        } else {
            for (LocalDate date : palindromeDates)
                System.out.println(date);
        }

        System.out.println();
    }

    /*This method checks every date (from the startDate till the endDate), whether it is a palindromic date, if it is,
     * it's added to the LocalDate list*/
    public static List<LocalDate> getPalindromeDates(LocalDate startDate, LocalDate endDate){
        List<LocalDate> palindromeDates = new ArrayList<>();

        while(!startDate.isAfter(endDate)){
            String tempDate = startDate.toString().replace("-", "");
            if(new StringBuilder(tempDate).reverse().toString().equals(tempDate)){
                palindromeDates.add(startDate);
            }

            startDate = startDate.plusDays(1);
        }

        return palindromeDates;
    }

    /*This method checks, if there are no input errors, if this method finds an error, it will send
    an exception according to the error it finds*/
    public static void DateValidation(int fromYear, int toYear){
        if(fromYear < 0 || toYear < 0){
            throw new RuntimeException("The years cannot be negative");
        }

        else if(fromYear > toYear){
            throw new RuntimeException("fromYear cannot be bigger than toYear");
        }
    }
}
