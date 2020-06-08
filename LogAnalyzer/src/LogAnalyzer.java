
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    private List<String> uniqueIps;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
        uniqueIps = new ArrayList<>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fileResource = new FileResource(filename);
        for (String line : fileResource.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(line);

            records.add(logEntry);
        }

    }

    public int countUniqueIPs() {
        for (LogEntry record : records) {
            if (!uniqueIps.contains(record.getIpAddress())) {
                uniqueIps.add(record.getIpAddress());
            }
        }

        return uniqueIps.size();
    }

    public void printAllHigherThanNum(int num) {

        for (LogEntry record : records) {
            if (record.getStatusCode() > num) {
                System.out.println(record);
            }
        }

    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


    public List<String> uniqueIPVisitsOnDay(String someday) {

        List<String> uniqueIPs = new ArrayList<>();
        for (LogEntry record : records) {
            Date accessTime = record.getAccessTime();
            if (accessTime.toString().contains(someday) && !uniqueIPs.contains(record.getIpAddress())) {
                uniqueIPs.add(record.getIpAddress());
            }
        }

        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        List<String> uniqueIPs = new ArrayList<>();
        for (LogEntry record : records) {
            if (record.getStatusCode() >= low && record.getStatusCode() <= high && !uniqueIPs.contains(record.getIpAddress())) {
                uniqueIPs.add(record.getIpAddress());
            }
        }


        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();

        for (LogEntry record : records) {
            if (!counts.containsKey(record.getIpAddress())) {
                counts.put(record.getIpAddress(), 1);
            } else {
                counts.put(record.getIpAddress(), counts.get(record.getIpAddress()) + 1);
            }
        }
        return counts;
    }

    public List<String> iPsMostVisits(HashMap<String, Integer> counts) {
        List<String> ips = new ArrayList<>();
        int max = mostNumberVisitsByIP(counts);
        for (String ipAddress : counts.keySet()) {
            if (counts.get(ipAddress) == max) {
                ips.add(ipAddress);
            }
        }
        return ips;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsMap = new HashMap<>();
        for (LogEntry record : records) {
            Date accessTime = record.getAccessTime();
            String day = accessTime.toString().substring(4, 10);
            ArrayList<String> ips = ipsMap.get(day);
            if (ips == null) {
                ips = new ArrayList<>();
                ips.add(record.getIpAddress());
                ipsMap.put(day, ips);
            } else {
                ips.add(record.getIpAddress());
            }

        }

        return ipsMap;
    }

    public List<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIpsMap, String day) {
        ArrayList<String> ip = new ArrayList<>();
        HashMap<String, Integer> ipsMap = new HashMap<>();

        for (String time : dayIpsMap.keySet()) {
            if (time.equals(day)) {
                ip = dayIpsMap.get(time);
            }
        }
        for (int k = 0; k < ip.size(); k++) {
            if (!ipsMap.containsKey(ip.get(k))) {
                ipsMap.put(ip.get(k), 1);
            } else ipsMap.put(ip.get(k), ipsMap.get(ip.get(k)) + 1);
        }
        return iPsMostVisits(ipsMap);

    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (String ipAddress : counts.keySet()) {
            Integer count = counts.get(ipAddress);
            if (count > max) {
                max = count;
            }
        }

        return max;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysMap) {
        String mostIpVisitsDay = null;
        int max = 0;
        int current;
        for (String day : daysMap.keySet()) {
            current = daysMap.get(day).size();
            if (max < current) {
                max = current;
                mostIpVisitsDay = day;
            }
        }
        return mostIpVisitsDay;
    }
}
