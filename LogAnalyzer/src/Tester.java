
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }

    private void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println(logAnalyzer.countUniqueIPs());
        System.out.println(logAnalyzer.uniqueIPVisitsOnDay("Sep 24").size());
//
//        logAnalyzer.printAllHigherThanNum(200);
//        System.out.println(logAnalyzer.uniqueIPVisitsOnDay("Sep 30"));
        System.out.println(logAnalyzer.countUniqueIPsInRange(200, 299));
//        logAnalyzer.printAllHigherThanNum(400);

//        System.out.println(logAnalyzer.countUniqueIPsInRange(200, 299));
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.mostNumberVisitsByIP(counts));
        System.out.println(logAnalyzer.iPsMostVisits(counts));
//        HashMap<String, Integer> map = logAnalyzer.countVisitsPerIP();
//        System.out.println(logAnalyzer.iPsMostVisits(map));
//
        HashMap<String, ArrayList<String>> map1 = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.dayWithMostIPVisits(map1));
        System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(map1,"Sep 29"));
//        System.out.println(logAnalyzer.iPsForDays());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testUniqueIP();
    }
}
