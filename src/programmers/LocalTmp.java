package programmers;

public class LocalTmp {
    public static void main(String[] args) {

        String str = "{[-1, 13, -14, 5, 6]}";
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        //str = str.replaceAll(match, "");
        str = str.replaceAll(" |\\[|\\]|\\}|\\{", "");

        System.out.println(str);

        String[] strs = str.split(",");

        for(String s : strs) {
            System.out.println(s);
        }
        System.out.println(strs.length);
    }
}
