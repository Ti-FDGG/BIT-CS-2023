package com.jinxuliang.fxmlsetproperty;

import java.util.Locale;

public class LocaleInfo {
    public static void main(String[] args) {
        Locale[] localeList = Locale.getAvailableLocales();
        //遍历数组的每个元素，依次获取所支持的国家和语言
        for (int i = 0; i < localeList.length; i++) {
            var locale=localeList[i];
            var country=locale.getCountry();
            var displayCountry= locale.getDisplayCountry();
            var language=locale.getLanguage();
            var displayLanguage=locale.getDisplayLanguage();
            if(country.length()>0)
                System.out.printf("country: %s  | displayCountry: %s %n",
                        country,displayCountry);
            if(language.length()>0)
                System.out.printf("language: %s | displayLanguage: %s %n",
                        language,displayLanguage);
            System.out.println();
        }
    }
}
