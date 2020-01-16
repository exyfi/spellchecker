package spellchecker;

public class LanguageSupport {
    private  static final String RUSSIAN="абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private  static final String ENGLISH="abcdefghijklmnopqrstuvwxyz";

    public static String getLang(String language) {
        if (language.equals("RU")) {
            return RUSSIAN;
        }
        if (language.equals("EN")) {
            return ENGLISH;
        }
        else{
            return ENGLISH; //default
        }
    }
}
