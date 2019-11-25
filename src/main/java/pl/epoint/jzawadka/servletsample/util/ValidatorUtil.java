package pl.epoint.jzawadka.servletsample.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorUtil {

    private static final String DECIMAL_NUMBER_PATTERN = "\\d+(.\\d{0,2})?";
    private static final String NUMBER_PATTERN = "\\d+";

    public boolean stringIsNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public boolean stringIsNumber(String str) {
        return str != null && str.matches(NUMBER_PATTERN);
    }

    public boolean stringIsPrice(String str) {
        return str != null && str.matches(DECIMAL_NUMBER_PATTERN);
    }

}
