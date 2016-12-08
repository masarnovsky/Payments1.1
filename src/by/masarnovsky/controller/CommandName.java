package by.masarnovsky.controller;

public enum CommandName {
    LOGIN("login"),
    LOGOUT("logout"),
    SIGNIN("signin");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getStringValue(){
        return this.commandName;
    }

    public static CommandName getCommandNameByString(String name){
        for (CommandName cn: CommandName.values()){
            if (cn.getStringValue().equals(name)){
                return cn;
            }
        }
        return null;
    }
}
