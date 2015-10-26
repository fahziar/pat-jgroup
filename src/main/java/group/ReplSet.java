package group;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luthfi on 27/10/15.
 */
public class ReplSet<T> extends ReceiverAdapter {
    private Set<T> set;
    private JChannel channel;
    private String channelName;

    public ReplSet(String channelName)
    {
        this.channelName = channelName;
        set = new HashSet<T>();
    }
    public void add(T element)
    {
        set.add(element);
    }
    public boolean contains(T element)
    {
        return set.contains(element);
    }
    public boolean remove(T element)
    {
        return set.remove(element);
    }

    public int size()
    {
        return set.size();
    }

    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    public Iterator<T> getIterator()
    {
        return set.iterator();
    }

    public void connectChannel() throws Exception {
        this.channel = new JChannel();
        this.channel.setReceiver(this);
        this.channel.connect(this.channelName);
    }

    public void closeChannel() {
        channel.close();
    }

    @Override
    public void setState(InputStream input) throws Exception {
        Set <T> s = (Set<T>) Util.objectFromStream(new DataInputStream(input));

        synchronized (set){
            set.clear();
            set.addAll(s);
        }
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (set) {
            Util.objectToStream(set, new DataOutputStream(output));
        }
    }

    @Override
    public void receive(Message msg) {
        ReplSetCmd<T> cmd = (ReplSetCmd<T>) msg.getObject();

        switch (cmd.getCmd()){
            case add:
                set.add(cmd.getContent());
                break;
            case remove:
                set.remove(cmd.getContent());
                break;
            case contains:
                set.contains(cmd.getContent());
                break;
        }
    }
}
