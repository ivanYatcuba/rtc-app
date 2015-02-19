package net.github.rtc.app.utils;

import net.github.rtc.app.utils.datatable.search.filter.DateCriteriaCreator;
import org.hibernate.criterion.SimpleExpression;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class DateCriteriaCreatorTest {

    private static final String DATE = "date";
    private static final Date TEST_DATE = new Date();

    private static final char GT = '>';
    private static final char EQ = '>';
    private static final char LT = '>';

    @Test
    public void testEqCriteriaGT() {
       testCriteria(GT);
    }

    @Test
    public void testEqCriteriaEQ() {
        testCriteria(EQ);
    }

    @Test
    public void testEqCriteriaLT() {
        testCriteria(LT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqCriteriaWrongArg() {
        final DateCriteriaCreator creator = new DateCriteriaCreator(DATE, TEST_DATE);
        final SimpleExpression expression = creator.getDateCriteria('y');
    }

    private void testCriteria(char prop) {
        final DateCriteriaCreator creator = new DateCriteriaCreator(DATE, TEST_DATE);
        final SimpleExpression expression = creator.getDateCriteria(GT);
        final String op = expression.toString().replaceAll(DATE, "").replaceAll(TEST_DATE.toString(), "");
        assertEquals(DATE, expression.getPropertyName());
        assertEquals(TEST_DATE, expression.getValue());
        assertEquals(prop, op.charAt(0));
    }

}
