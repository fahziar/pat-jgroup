package group;


import org.apache.commons.lang3.SerializationUtils;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

/**
 * Created by fahziar on 25/10/2015.
 */
public class ReplStack<T> extends ReceiverAdapter{
    private Stack<T> stack;
    private JChannel channel;
    private String channelName;

    public ReplStack(String channelName) throws Exception{
        this.channelName = channelName;
        stack = new Stack<T>();

        this.channel = new JChannel();
        this.channel.setReceiver(this);
        this.channel.connect(this.channelName);
    }

    @Override
    public void setState(InputStream input) throws Exception {
        Stack <T> s = (Stack<T>) Util.objectFromStream(new DataInputStream(input));

        synchronized (stack){
            stack.clear();
            stack.addAll(s);
        }
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (stack) {
            Util.objectToStream(stack, new DataOutputStream(output));
        }
    }

    @Override
    public void receive(Message msg) {
        ReplStackCmd<T> cmd = (ReplStackCmd<T>) msg.getObject();

        switch (cmd.getCmd()){
            case push:
                stack.push(cmd.getContent());
                break;
            case pop:
                stack.pop();
                break;
        }
    }

    public void push(T obj) throws Exception{
        ReplStackCmd<T> cmd = new ReplStackCmd<T>(ReplStackCmd.CommandEnum.push, obj);
        Message msg = new Message(null, null, cmd);
        channel.send(msg);
    }

    public T pop(){
        ReplStackCmd<T> cmd = new ReplStackCmd<T>(ReplStackCmd.CommandEnum.pop, null);
        return stack.pop();
    }

    public T top(){
        return stack.peek();
    }
}
