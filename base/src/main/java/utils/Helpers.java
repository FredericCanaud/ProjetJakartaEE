package utils;

import java.util.Random;

public class Helpers {
    public static final String FILES_LOCATION = "/home/freddy/Documents/glassfish6/glassfish/domains/domain1/generated/jsp/base_main_war_exploded/";

    public static String randomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 13;
        return new Random().ints(leftLimit, rightLimit + 1) .limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
