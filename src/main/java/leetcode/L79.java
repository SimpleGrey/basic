package leetcode;

/**
 * @author zhanqian
 * @Date 2020/9/14 19:41
 * @Description TODO
 */
public class L79 {

//    public boolean exist(char[][] board, String word) {
//        long length = word.toCharArray().length;
//        int x = board.length;
//        if (x == 0) {
//            return false;
//        }
//        int y = board[0].length;
//        if (y == 0) {
//            return false;
//        }
//        int[][] record = new int[x][y];
//        pm(board, word, x, y, record);
//
//    }
//
//    private void pm(char[][] board, String word, int x, int y, int[][] record) {
//
//    }

    public static void main(String[] args) {
//        String s = "http://gusteau-test.oss-cn-hangzhou.aliyuncs.com/aa/2020/9/28/e73fgajmosu.jpg";
//        System.out.println(s.split("aliyuncs.com/")[1]);

        String s1 = "1";
        System.out.println(s1.lastIndexOf('_'));
        System.out.println(s1.substring(0, s1.lastIndexOf('_') + 1));
        System.out.println(s1.substring(s1.lastIndexOf('_') + 1));
    }
}
