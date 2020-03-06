package druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.springframework.util.CollectionUtils;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/6 10:54
 * @Created by zhanqian
 * @Description TODO
 */
public class MySQLParser {

    public static void main(String[] args) {
        get();
    }

    public static SQLExpr get() {
//        String sql = "select * from a where (rc>2 and (age!=10 and vc>2  or sex='male' and rc>10 ) and birth>1992 and cc>2)";
//        String sql = "select * from a where ((age>18 and rc>2) and !((age=10 and vc>2) and (age=17 and bc<1) or sex='male' and rc>10 ) and birth>1992 and cc>2)";
//        String sql = "select * from a where (rc>2 and ((age=10 and vc>2) and (age=17 and bc<1) or sex='male' and rc>10 ) and birth>1992 and cc>2)";
        String sql = "select * from a where (rc>2 and !((age!=10 and vc>2) and (age=17 and bc<1) or sex='male' and rc>10 ) and birth>1992 and cc>2)";
        System.out.println("original:" + sql);
        SQLStatementParser parser = new MySqlStatementParser(sql);
        SQLSelectStatement sqlStatement = (SQLSelectStatement) parser.parseStatement();

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        sqlStatement.accept(visitor);

        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) sqlStatement.getSelect().getQuery();
        SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) query.getWhere();
        System.out.println(sqlBinaryOpExpr.toString().replaceAll("\n", " "));
        SQLExpr right = sqlBinaryOpExpr.getRight();
        SQLExpr left = sqlBinaryOpExpr.getLeft();
        List<SQLBinaryOpExpr> children = sqlBinaryOpExpr.getChildren();
//        print(children);
//        System.out.println("children:" + children);

        return query.getWhere();
    }


    public static void print(List<SQLBinaryOpExpr> children) {
        for (SQLBinaryOpExpr e: children) {
            if (!CollectionUtils.isEmpty(e.getChildren())) {
                List<SQLBinaryOpExpr> children1 = e.getChildren();
                print(children1);
                System.out.println("1");
            }
        }
    }

}
