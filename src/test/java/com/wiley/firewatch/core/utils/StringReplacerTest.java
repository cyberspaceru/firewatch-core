package com.wiley.firewatch.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class StringReplacerTest {

    @DataProvider
    public Object[][] createCasesData() {
        return new Object[][] {
                {MatchingType.REGEXP, "[0-9]", "*", "a1b2c3", "a*b2c3", "a*b*c*"},
                {MatchingType.REGEXP_CASE_INSENSITIVE, "[A-Z]", "*", "a1b2c3", "*1b2c3", "*1*2*3"},
                {MatchingType.EQUALS, "abc", "*", "abc", "*", "*"},
                {MatchingType.EQUALS, "abc", "*", "ABC", "ABC", "ABC"},
                {MatchingType.EQUALS_IGNORE_CASE, "abc", "*", "abc", "*", "*"},
                {MatchingType.EQUALS_IGNORE_CASE, "abc", "*", "ABC", "*", "*"},
                {MatchingType.CONTAINS, "1", "*", "a1b1c1", "a*b1c1", "a*b*c*"},
                {MatchingType.START_WITH, "a", "*", "abc", "*bc", "*bc"},
                {MatchingType.END_WITH, "c", "*", "abc", "ab*", "ab*"}
        };
    }

    @Test(dataProvider = "createCasesData")
    public void match(MatchingType type, String blank, String replacement, String target, String expectedResult, String expectedResultForAll) {
        StringReplacer replacer = StringReplacer.create(type, blank, replacement);
        String actualResult = replacer.replace(target);
        String actualResultForAll = replacer.replaceAll(target);
        log.info("StringReplacer={}, Target={}, Result={}, ResultForAll={}", replacer, target, actualResult, actualResultForAll);
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResultForAll, expectedResultForAll);
    }
}
