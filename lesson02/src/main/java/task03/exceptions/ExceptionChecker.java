package task03.exceptions;

import task03.Person;

/*
 Потокобезопасный синглтон
 */
public class ExceptionChecker {

    private static volatile ExceptionChecker instance;

    private ExceptionChecker() {}

    public static ExceptionChecker getInstance() {
        ExceptionChecker localInstance = instance;
        if (localInstance == null) {
            synchronized (ExceptionChecker.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ExceptionChecker();
                }
            }
        }
        return localInstance;
    }

    public void sameAgeAndNameExceptionCheck(Person person1, Person person2) {
        if (person1.getAge() == person2.getAge() && person1.getName().equals(person2.getName())) {
            throw new SameAgeAndNameException();
        }
    }
}
