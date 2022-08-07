package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LocalTmp {
    public static void main(String[] args) throws ParseException {

        String str = "{[-1, 13, -14, 5, 6]}";
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        //str = str.replaceAll(match, "");
        str = str.replaceAll(" |\\[|\\]|\\}|\\{", "");

        String date = "[01/Jul/1995:00:00:11 -0400] GET";
        date = date.replaceAll("\\[|\\]|/|:", " ");
        String[] dates = date.split(" ");

        Map<String, String> months = new HashMap<>();
        months.put("Jan",  "01");
        months.put("Feb",  "02");
        months.put("Mar",  "03");
        months.put("Apr",  "04");
        months.put("May",  "05");
        months.put("Jun",  "06");
        months.put("Jul",  "07");
        months.put("Aug",  "08");
        months.put("Sep",  "09");
        months.put("Oct",  "10");
        months.put("Nov",  "11");
        months.put("Dec",  "12");


        String format = "yyyy-MM-dd HH:mm:ss";
        String value = dates[3] + "-" + months.get(dates[2]) + "-" + dates[1] + " " + dates[4] + ":" + dates[5] + ":" + dates[6];
        //String value = "2022-08-07 10:33:23";

        //DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        //LocalDate d = LocalDate.parse(value, df);

        SimpleDateFormat form = new SimpleDateFormat(format);
        Date d = form.parse(value);
        System.out.println(value);
        System.out.println("here>> " +d.toString());

    }
}
