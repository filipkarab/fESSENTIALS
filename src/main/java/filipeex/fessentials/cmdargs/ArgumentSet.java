package filipeex.fessentials.cmdargs;

import java.util.ArrayList;

public class ArgumentSet {

    private ArrayList<String> arguments;

    public ArgumentSet() {
        this.arguments = new ArrayList<String>();
    }

    public ArgumentSet(ArrayList<String> args) {

        this.arguments = args;

    }

    public ArgumentSet(String a1) {

        this.arguments.add(a1);

    }

    public ArgumentSet(String a1, String a2) {

        this.arguments.add(a1);
        this.arguments.add(a2);

    }

    public ArgumentSet(String a1, String a2, String a3) {

        this.arguments.add(a1);
        this.arguments.add(a2);
        this.arguments.add(a3);

    }

    public ArgumentSet(String a1, String a2, String a3, String a4) {

        this.arguments.add(a1);
        this.arguments.add(a2);
        this.arguments.add(a3);
        this.arguments.add(a4);

    }

    public ArrayList<String> get() {
        return arguments;
    }

    public void set(ArrayList<String> newArguments) {
        arguments = newArguments;
    }

    public void add(String argument) {
        arguments.add(argument);
    }

    public void remove(String argument) {
        arguments.remove(argument);
    }

}
