package com.laioffer.travelplanner.utils;

import com.laioffer.travelplanner.entities.Category;
import com.laioffer.travelplanner.enumerate.CategoryEnum;

public class EnumUtils {
    public static boolean ifInclude(String input) {
        for (CategoryEnum c : CategoryEnum.values()) {
            if (c.name().equals(input)) {
                return true;
            }
        }

        return false;
    }
}
