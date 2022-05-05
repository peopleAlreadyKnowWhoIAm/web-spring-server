package ua.lpnu.students.labs.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
public class LicencePlateRubber {

  String text = null;
  String replacement = null;

  Pattern pattern = null;

  static final String STANDART_REPLACEMENT = "CAR_NUMBER"; 

  static final String REGEX_CAR_LICENCE_STRING = 
      "([A-Z]{2}\\d{4}[A-Z]{2})|([A-Z]{2}\\s\\d{4}\\s[A-Z]{2})|(\\d{3}-\\d{2}\\s[A-Z]{2})";

  /**
   * Set all data and compile regex [Pattern].
   * @param text text to work with
   * @param replacement string to replace words according to the pattern
   */
  public LicencePlateRubber(String text, String replacement) {
    this.text = text;
    this.replacement = replacement;
    pattern = Pattern.compile(REGEX_CAR_LICENCE_STRING);
  }

  public LicencePlateRubber() {
    this(null, STANDART_REPLACEMENT);
  }

  public LicencePlateRubber(String text) {
    this(text, STANDART_REPLACEMENT);
  }
  
  /**
   * Find all car licence plate numbers and replace it with [replacement] String locally.
   * @throws NullPointerException when [replacement] or [text] is null
   */
  public void process() throws NullPointerException {
    if (text == null || replacement == null) {
      throw new NullPointerException();
    }

    val matcher = pattern.matcher(this.text);
    this.text = matcher.replaceAll(replacement);
  }
}
