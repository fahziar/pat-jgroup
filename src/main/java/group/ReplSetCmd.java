package group;

import java.io.Serializable;

/**
 * Created by luthfi on 27/10/15.
 */
public class ReplSetCmd <T> implements Serializable {
    public CommandEnum getCmd() {
        return cmd;
    }

    public void setCmd(CommandEnum cmd) {
        this.cmd = cmd;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    enum CommandEnum{
        add,
        remove,
        contains
    }

    private CommandEnum cmd;
    private T content;

    public ReplSetCmd(CommandEnum cmd, T content){
        this.cmd = cmd;
        this.content = content;
    }
}
