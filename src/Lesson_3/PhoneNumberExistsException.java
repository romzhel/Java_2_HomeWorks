package Lesson_3;

public class PhoneNumberExistsException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Номер телефона %s уже имеется";
    private String newName;
    private String existingName;
    private String phoneNumber;

    public PhoneNumberExistsException(String newName, String existingName, String phoneNumber) {
        super(String.format(MESSAGE_TEMPLATE, phoneNumber));
        this.newName = newName;
        this.existingName = existingName;
        this.phoneNumber = phoneNumber;
    }

    public String getNewName() {
        return newName;
    }

    public String getExistingName() {
        return existingName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
