package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2019/12/25 17:07
 * @Created by zhanqian
 * @Description TODO
 */
public class RegexTest {

    public static void main(String[] args) throws IOException {
        String path = "E:\\aa.txt";
        String prefix = "^APPID是";
        Pattern pattern = Pattern.compile("" + prefix + ".*");
        File file = new File(path);
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String str = null;
            while ((str = br.readLine()) != null) {
//                result.append( System.lineSeparator() + s);
                System.out.println(str);
                Matcher ma = pattern.matcher(str);
                while (ma.find()) {
                    list.add(ma.group().replaceAll(prefix,""));
                    System.out.println(ma.group());
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
        System.out.println("...");
    }
}
