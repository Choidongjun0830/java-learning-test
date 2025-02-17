package cholog.passwordMeter;

public class PasswordMeter {
    public PasswordStrength meter(String pw) {
        if(pw == null || pw.isBlank()) {
            throw new IllegalArgumentException();
        }

        int metCount = 0;
        metCount = calculateMetCount(pw, metCount);

        if(metCount == 1 || metCount == 0) {
            return PasswordStrength.WEAK;
        }

        if(metCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int calculateMetCount(String pw, int metCount) {
        if(overLength(pw)) metCount++;
        if(containsUppercase(pw)) metCount++;
        if(containsDigit(pw)) metCount++;
        return metCount;
    }

    private static boolean overLength(String pw) {
        return pw.length() >= 8;
    }

    private boolean containsDigit(String pw) {
        for (char ch : pw.toCharArray()) {
            if(Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsUppercase(String pw) {
        for (char ch : pw.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
