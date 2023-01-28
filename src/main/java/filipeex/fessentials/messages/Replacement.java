package filipeex.fessentials.messages;

public class Replacement {

    private String placeholder;
    private String value;

    public Replacement(String placeholder, String value) {

        this.placeholder = placeholder;
        this.value = value;

    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getValue() {
        return value;
    }

    public void setPlaceholder(String newPlaceholder) {
        this.placeholder = newPlaceholder;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

}
