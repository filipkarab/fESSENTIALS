package filipeex.fessentials.messages;

import java.util.ArrayList;

public class ReplacementSet {

    private ArrayList<Replacement> replacements;

    public ReplacementSet() {
        this.replacements = new ArrayList<Replacement>();
    }

    public ReplacementSet(ArrayList<Replacement> replacements) {

        this.replacements = replacements;

    }

    public ReplacementSet(Replacement r1) {

        this.replacements.add(r1);

    }

    public ReplacementSet(Replacement r1, Replacement r2) {

        this.replacements.add(r1);
        this.replacements.add(r2);

    }

    public ReplacementSet(Replacement r1, Replacement r2, Replacement r3) {

        this.replacements.add(r1);
        this.replacements.add(r2);
        this.replacements.add(r3);

    }

    public ReplacementSet(Replacement r1, Replacement r2, Replacement r3, Replacement r4) {

        this.replacements.add(r1);
        this.replacements.add(r2);
        this.replacements.add(r3);
        this.replacements.add(r4);

    }

    public ArrayList<Replacement> get() {
        return replacements;
    }

    public void set(ArrayList<Replacement> newReplacements) {
        replacements = newReplacements;
    }

    public void add(Replacement replacement) {
        replacements.add(replacement);
    }

    public void add(String placeholder, String value) {
        replacements.add(new Replacement(placeholder, value));
    }

    public void remove(Replacement replacement) {
        replacements.remove(replacement);
    }

    public void remove(String placeholder, String value) {
        replacements.remove(new Replacement(placeholder, value));
    }

    public boolean contains(String placeholder) {

        boolean result = false;

        for (Replacement r : replacements)
            if (r.getPlaceholder().equalsIgnoreCase(placeholder))
                result = true;

        return result;
    }

}
