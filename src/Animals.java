public abstract class Animals {
    private String name;
    private String commands;
    private String date;

    public Animals(String name, String commands, String date) {
        this.name = name;
        this.commands = commands;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getCommands() {
        return commands;
    }

    public String getDate() {
        return date;
    }

    public void addCommands(String anotherCommands) {
        this.commands = anotherCommands;
    }

    public void printCommands() {
        System.out.println(commands);
    }
}
