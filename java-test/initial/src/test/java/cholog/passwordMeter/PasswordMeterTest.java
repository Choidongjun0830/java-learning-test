package cholog.passwordMeter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordMeterTest {

    PasswordMeter passwordMeter = new PasswordMeter();

    @DisplayName("null 입력이면 exception 발생")
    @Test
    void name() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(null));
    }

    @DisplayName("빈 값 입력이면 exception 발생")
    @Test
    void emptyInput() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(""));
    }

    @DisplayName("모든 조건을  충족하면 강함")
    @Test
    void meetAllRules() {
        PasswordStrength expected = PasswordStrength.STRONG;

        assertPasswordStrength("abcABC1234", expected);
        assertPasswordStrength("123abcABC", expected);
    }

    @DisplayName("길이가 8미만, 다른 조건 충족")
    @Test
    void digitAndUppercase() {
        assertPasswordStrength("abcABC1", PasswordStrength.NORMAL);
        assertPasswordStrength("abcC12", PasswordStrength.NORMAL);
    }

    @DisplayName("대문자 없음. 다른 조건 충족")
    @Test
    void digitAndLength() {
        assertPasswordStrength("abc12345", PasswordStrength.NORMAL);
        assertPasswordStrength("abcfdsfsd1234534", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자 없음. 다른 조건 충족")
    @Test
    void uppercaseAndLength() {
        assertPasswordStrength("abcABCdsa", PasswordStrength.NORMAL);
    }

    @DisplayName("길이만 충족")
    @Test
    void length() {
        assertPasswordStrength("dbsakbdksabkdjsa", PasswordStrength.WEAK);
    }

    @DisplayName("대문자만 충족")
    @Test
    void uppercase() {
        assertPasswordStrength("ASDSA", PasswordStrength.WEAK);
    }

    @DisplayName("숫자만 충족")
    @Test
    void digit() {
        assertPasswordStrength("123", PasswordStrength.WEAK);
    }

    @DisplayName("아무것도 충족하지 않음")
    @Test
    void no() {
        assertPasswordStrength("asd", PasswordStrength.WEAK);
    }

    private void assertPasswordStrength(String password, PasswordStrength expected) {
        PasswordStrength result1 = passwordMeter.meter(password);
        Assertions.assertThat(result1).isEqualTo(expected);
    }
}
