package dev.lpa;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class Main {
  
  public static void main(String[] args) {

//    Locale.setDefault(Locale.US);
    System.out.println("Default Locale: " + Locale.getDefault());
    
    Locale en = new Locale("en");
    Locale enAU = new Locale("en", "AU");
    Locale enCA = new Locale("en", "CA");
    
    Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build(); // nested class
    Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();
    
    var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    
    for (var locale : List.of(Locale.getDefault(), Locale.US, en, enAU, enCA,
      Locale.UK, enIN, enNZ)) {
      LocalDateTime now = LocalDateTime.now();
      System.out.printf("%-40s %s%n",
        locale.getDisplayName(), now.format(dtf.withLocale(locale)));
    }
    
    DateTimeFormatter wdayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
    LocalDate May5 = LocalDate.of(2020, 5, 5);
    
    System.out.println("-----------------------------------");
    for (var locale : List.of(
      Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE, Locale.GERMANY,
      Locale.TAIWAN, Locale.JAPAN, Locale.ITALY)) {
      System.out.println(locale.getDisplayName() + " : " + locale.getDisplayName(locale) + "=\n\t"
                           + May5.format(wdayMonth.withLocale(locale)));
      System.out.printf(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", May5);
      System.out.print(String.format(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", May5));
      
      NumberFormat decimalInfo = NumberFormat.getNumberInstance(locale);
      decimalInfo.setMaximumFractionDigits(6);  // by default 3
      System.out.println(decimalInfo.format(123456789.123456));
    }
  }
}
