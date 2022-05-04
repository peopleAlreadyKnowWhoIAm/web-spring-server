package ua.lpnu.students.labs.regex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LicencePlateRubber {

  String text = null;
  String replacement = null;

  static final String STANDART_REPLACEMENT = "CAR_NUMBER"; 

  public LicencePlateRubber(String text, String replacement){
    this.text = text;
    this.replacement = replacement;
  }

  public LicencePlateRubber(String text){
    this(text, STANDART_REPLACEMENT);
  }
  
  public void process(){
    //TODO write
  }
}
