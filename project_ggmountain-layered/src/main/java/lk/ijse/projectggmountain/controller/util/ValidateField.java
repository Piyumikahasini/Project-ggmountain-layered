package lk.ijse.projectggmountain.controller.util;

import javafx.scene.control.Control;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateField {
    public static boolean employeeIdCheck(String custId) {
        String pattern = "^E\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }
    public static boolean loadIdCheck(String custId) {
        String pattern = "^LOAD-\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }
    public static boolean emailCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean contactCheck(String contact){
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();                                                                                                           }
    public static boolean nicCheck(String nic) {
        Pattern patternNew = Pattern.compile("^([0-9]{2})(0[1-9]|1[0-2])([0-3][0-9])([0-9]{4})([0-9]{4})([vVxX])?$");
        Pattern patternOld = Pattern.compile("^[0-9]{9}[vVxX]$");
        Pattern patternForeign = Pattern.compile("^[0-9]{12}$");

        Validator<String> validator = new Validator<String>() {
            @Override
            public ValidationResult apply(Control control, String value) {
                boolean matchesNew = patternNew.matcher(value).matches();
                boolean matchesOld = patternOld.matcher(value).matches();
                boolean matchesForeign = patternForeign.matcher(value).matches();
                boolean matches = matchesNew || matchesOld || matchesForeign;
                return ValidationResult.fromMessageIf(control, "Invalid NIC", Severity.ERROR, !matches);
            }
        };

        ValidationResult result = validator.apply(null, nic);
        return result.getErrors().isEmpty();
    }

    public static boolean customerIdCheck(String custId) {
        String pattern = "^C\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }

    public static boolean supplierIdCheck(String custId) {
        String pattern = "^S\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }

    public static boolean eventIdCheck(String custId) {
        String pattern = "^EV\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }

    public static boolean itemIdCheck(String custId) {
        String pattern = "^I\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(custId);
        return m.matches();
    }

    public static boolean yearCheck(String input) {
        if (input.matches("\\d{4}")) {
            return true; // Input contains only numeric characters
        } else {
            return false; // Input contains non-numeric characters
        }
    }

    public static boolean dateCheck(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static List<String> getValidationMessages(String password) {
        List<String> messages = new ArrayList<>();
        if (password == null || password.length() < 8) {
            messages.add("Password must be at least 8 characters long\n");
        }
        if (!password.matches(".*[A-Z].*")) {
            messages.add("Password must contain at least one uppercase letter\n");
        }
        if (!password.matches(".*[a-z].*")) {
            messages.add("Password must contain at least one lowercase letter\n");
        }
        if (!password.matches(".*\\d.*")) {
            messages.add("Password must contain at least one digit\n");
        }
        if (!password.matches(".*[!@#$%^&*()-_=+\\\\[\\\\]{};:\\\"\\\\\\\\|,.<>/?].*")) {
            messages.add("Password must contain at least one special character\n");
        }
        return messages;
    }

    public static boolean priceCheck(String input) {
        try {
            if (input.matches("\\d+(\\.\\d{1,2})?")) {
                return true; // Input contains only numeric characters, including up to two decimal points
            } else {
                return false; // Input contains non-numeric characters or invalid decimal points
            }
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean numberCheck(String input) {
        try {
            if (input.matches("\\d+")) {
                return true; // Input contains only numeric characters
            } else {
                return false; // Input contains non-numeric characters
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
