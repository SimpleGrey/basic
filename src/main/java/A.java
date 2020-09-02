import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanqian
 * @Date 2020/6/19 14:07
 * @Description TODO
 */
public class A {


    private static final String REGEX2 = "</a>";
    private static final String REGEX = "<img[^>]+src\\s*=\\s*\"\"?([^>\"\"\\s]+)\"\"?[^>]*>";
    private static final String INPUT = "'<img src=\"http://www.baidu.com\" data-gif-type=\"1\" />'" +
            "/n '<img src=\"http://www.baidu.com2\" data-gif-type=\"1\" />'";
    private static final String regxpForImgTag = "<(img|IMG)(.*?)(/>|></img>|>)";
    private static final String regxpForImgTag2 = "<a href=\"http://www.baidu.com\" class=\"link-image\"><img src=\"(.*?)\" data-gif-type=\"1\" />";
    private static Pattern pattern;
    private static Matcher matcher;

    public static void main( String args[] ) {

        pattern = Pattern.compile(regxpForImgTag);
        matcher = pattern.matcher(INPUT);

        String s = INPUT.replaceAll(regxpForImgTag, "<a href=\"" + "$2"  + "\" class=\"link-image\">" + "$0" + "</a>");
        System.out.println(s);

//        System.out.println("Current REGEX is: "+REGEX);
//        System.out.println("Current INPUT is: "+INPUT);

//        System.out.println("lookingAt(): "+matcher.lookingAt());
//        System.out.println("matches(): "+matcher.matches());
        while(matcher.find()) {
            System.out.println("\n");
            System.out.println(matcher.group());
        }
    }

    /**
     * 获取img标签中的src值
     * @param content
     * @return
     */
    public static List<String> getImgSrc(String content){
        List<String> list = new ArrayList<>();
        //目前img标签标示有3种表达式
        //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
        //开始匹配content中的<img />标签
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(content);
        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2);

                //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    list.add(str_src);
                }
                //结束匹配<img />标签中的src

                //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                result_img = m_img.find();
            }
        }
        return list;
    }

}
