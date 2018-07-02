package uk.co.probablyfine.bytemonkey;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilterByClassAndMethodName {

    private final List<Pattern> patterns = new ArrayList();

    public FilterByClassAndMethodName(String regex) {
        String[] filters = regex.split(";");
        for (String filter : filters) {
            patterns.add(Pattern.compile(filter));
        }
    }

    public boolean matches(String className, String methodName) {
        String fullName = className + "/" + methodName;
        for (Pattern p : patterns) {
            if (p.matcher(fullName).find()) {
                return true;
            }
        }
        return false;
    }
}
