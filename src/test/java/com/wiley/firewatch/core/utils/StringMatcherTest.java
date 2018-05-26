package com.wiley.firewatch.core.utils;


import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class StringMatcherTest {

    @DataProvider
    public Object[][] createCasesData() {
        return new Object[][] {
                {MatchingType.REGEXP, "[a-z]{1,}", "abc", true},
                {MatchingType.REGEXP, "[a-z]{1,}", "ABC", false},
                {MatchingType.REGEXP_CASE_INSENSITIVE, "[a-z]{1,}", "abc", true},
                {MatchingType.REGEXP_CASE_INSENSITIVE, "[a-z]{1,}", "ABC", true},
                {MatchingType.REGEXP_CASE_INSENSITIVE, "[0-9]{1,}", "ABC", false},
                {MatchingType.EQUALS, "abc", "abc", true},
                {MatchingType.EQUALS, "abc", "ABC", false},
                {MatchingType.EQUALS_IGNORE_CASE, "abc", "ABC", true},
                {MatchingType.EQUALS_IGNORE_CASE, "abc", "ABC", true},
                {MatchingType.EQUALS_IGNORE_CASE, "123", "ABC", false},
                {MatchingType.CONTAINS, "b", "abc", true},
                {MatchingType.CONTAINS, "1", "abc", false},
                {MatchingType.START_WITH, "ab", "abc", true},
                {MatchingType.START_WITH, "bc", "abc", false},
                {MatchingType.END_WITH, "bc", "abc", true},
                {MatchingType.END_WITH, "ab", "abc", false}
        };
    }

    @Test(dataProvider = "createCasesData")
    public void match(MatchingType type, String expected, String actual, boolean expectedResult) {
        StringMatcher matcher = StringMatcher.create(type, expected);
        boolean actualResult = matcher.match(actual);
        log.info("StringMatcher={}, TestingActualValue={}, Result={}", matcher, actual, actualResult);
        Assert.assertEquals(actualResult, expectedResult);
    }
}
