package com.extremecalculator.apputils;

/**
 * Created by SHRENIK on 22-05-2016.
 */
public class StringUtils {

    public static int countMatches(String text, char needle)
    {
        int count = 0;
        for (int i=0; i < text.length(); i++)
        {
            if (text.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }
}
