package com.example.demo.util;

import java.io.StringReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

@Component
public class ParseSql {
    Logger logger = LoggerFactory.getLogger(getClass());
    public boolean checknow(String sql) {
        boolean checker = false;
        String statement;
        try {
            CCJSqlParserManager parseSql = new CCJSqlParserManager();
            statement = "Select id, name, location FROM UserTable where id = :1";
            Select select = (Select) parseSql.parse(new StringReader(statement));
            PlainSelect plain = (PlainSelect) select.getSelectBody();
            List<SelectItem> selectitems = plain.getSelectItems();
            for (int index = 0; index < selectitems.size(); index++) {
                Expression expressionIns = ((SelectExpressionItem) selectitems.get(index)).getExpression();
                if (expressionIns instanceof Column) {
                    Column columnInstance = (Column) expressionIns;
                    System.out.println("Table Name :: "+columnInstance.getTable());
                    System.out.println("Column  Name :: "+columnInstance.getColumnName());
                } else if (expressionIns instanceof Function) {
                    Function functionIns = (Function) expressionIns;
                    System.out.println("Function Name :: "+functionIns.getName());
                }
            }

        } catch (JSQLParserException e) {
            logger.error(e.getMessage());
        }
        return checker;
    }


}