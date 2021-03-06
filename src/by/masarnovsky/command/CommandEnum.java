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
    },
    SIGNIN{
        {
            this.command = new SigninCommand();
        }
    },
    GETALLACCOUNTS{
        {
            this.command = new GetAllAccountsCommand();
        }
    },
    GETALLBLOCKEDACCOUNTS{
        {
            this.command = new GetAllBlockedAccountsCommand();
        }
    },
    BLOCKACCOUNT{
        {
            this.command = new BlockAccountCommand();
        }
    },
    UNBLOCKACCOUNT{
        {
            this.command = new UnblockAccountCommand();
        }
    },
    CREATEACCOUNT{
        {
            this.command = new CreateAccountCommand();
        }
    },
    UPDATEHOME{
        {
            this.command = new UpdateHomeCommand();
        }
    },
    UPDATECASH{
        {
            this.command = new UpdateCashCommand();
        }
    },
    GETPAYMENTHISTORY {
        {
            this.command = new GetPaymentHistory();
        }
    },
    CREATEPAYMENT{
        {
            this.command = new CreatePaymentCommand();
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