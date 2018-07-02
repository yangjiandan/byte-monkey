package uk.co.probablyfine.bytemonkey;

import java.util.Random;

public class CreateAndThrowException {
    private static final Random random = new Random();

    public static void throwDirectly(String name) throws Throwable {
        String dotSeparatedClassName = name.replace("/", ".");
        Class<?> p = Class.forName(dotSeparatedClassName, false, ClassLoader.getSystemClassLoader());
        if (Throwable.class.isAssignableFrom(p)) {
    	  	throw (Throwable) p.newInstance();
      	} else {
      		throw new ByteMonkeyException(name);
      	}
    }
    
    public static Throwable throwOrDefault(String name) {
        String dotSeparatedClassName = name.replace("/", ".");

        try {
            Class<?> p = Class.forName(dotSeparatedClassName, false, ClassLoader.getSystemClassLoader());

            if (Throwable.class.isAssignableFrom(p)) {
                return (Throwable) p.newInstance();
            } else {
                return new ByteMonkeyException(name);
            }
        } catch (IllegalAccessException e) {
            return new ByteMonkeyException(name);
        } catch (Exception e) {
            return new RuntimeException(name);
        }
    }

    public static Throwable throwRandomException(String name) {
        String[] exception = name.split(",");
        int exceptionIndex = random.nextInt(exception.length);
        System.out.println("exceptions" + name);
        System.out.println("exception_index=" + exceptionIndex);
        String selectedException = exception[exceptionIndex];
        
        String dotSeparatedClassName = selectedException.replace("/", ".");

        try {
            Class<?> p = Class.forName(dotSeparatedClassName, false, ClassLoader.getSystemClassLoader());

            if (Throwable.class.isAssignableFrom(p)) {
                return (Throwable) p.newInstance();
            } else {
                return new ByteMonkeyException(selectedException);
            }
        } catch (IllegalAccessException e) {
            return new ByteMonkeyException(selectedException);
        } catch (Exception e) {
            return new RuntimeException(selectedException);
        }
    }
}