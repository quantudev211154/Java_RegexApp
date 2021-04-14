package Entity;

import java.util.Objects;

public class Regex_Sample {
    protected String regex = "xx";
    protected String purpose = "xx";
    protected String type = "xx";

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Regex_Sample(String regex, String purpose, String type) {
        this.regex = regex;
        this.purpose = purpose;
        this.type = type;
    }

    public Regex_Sample(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Regex_Sample)) return false;
        Regex_Sample that = (Regex_Sample) o;
        return Objects.equals(getRegex(), that.getRegex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegex());
    }

    @Override
    public String toString() {
        return regex + ";" + purpose + ";" + type;
    }
}
