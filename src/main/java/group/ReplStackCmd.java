package group;

import java.io.Serializable;

/**
 * Created by fahziar on 25/10/2015.
 */
public class ReplStackCmd <T> implements Serializable{
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
        push,
        pop
    }

    private CommandEnum cmd;
    private T content;

    public ReplStackCmd(CommandEnum cmd, T content){
        this.cmd = cmd;
        this.content = content;
    }


}
