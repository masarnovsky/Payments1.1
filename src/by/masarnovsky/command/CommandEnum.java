package by.masarnovsky.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}


/* OLD

public enum CommandEnum {
    LOGIN("login"),
    LOGOUT("logout"),
    SIGNIN("signin");

    private final String commandName;

    CommandEnum(String commandName) {
        this.commandName = commandName;
    }

    public String getStringValue(){
        return this.commandName;
    }

    public static CommandEnum getCommandNameByString(String name){
        for (CommandEnum cn: CommandEnum.values()){
            if (cn.getStringValue().equals(name)){
                return cn;
            }
        }
        return null;
    }
}

 */