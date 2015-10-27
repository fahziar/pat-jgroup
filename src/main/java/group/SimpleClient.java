package group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by luthfi on 26/10/15.
 */
public class SimpleClient {

    private static ReplStack<String> replStack;
    private static ReplSet<String> replSet;

    public static void main (String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int opt;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                Welcome to Jgroups Simple Client                   ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println(" 1. Replicated Stack");
        System.out.println(" 2. Replicated Set");
        System.out.println("-------------------------------------------------------------------");
        System.out.print(" Your Choice : ");
        opt = in.nextInt();
        switch (opt) {
            case 1 :
                startStack();
                break;
            case 2 :
                startSet();
                break;
        }
    }

    private static void startStack() throws Exception {
        replStack = new ReplStack<String>("test");
        replStack.connectChannel();
        eventLoopStack();
        replStack.closeChannel();
    }

    private static void eventLoopStack() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (!line.equals("quit"))
        {
            try {
                if (line.contains("push")) {
                    replStack.push(line.substring(line.indexOf(' ') + 1));
                }
                else if (line.equals("pop")) {
                    String poppedString = (String) replStack.pop();
                    System.out.println("Popped String : "+poppedString);
                }
                else if (line.equals("top")) {
                    System.out.println(replStack.top());
                }
            }
            catch (Exception e)
            {
                if (e.equals(new StringIndexOutOfBoundsException()))
                    System.err.println("Method unknown");
                if (e.equals(new java.util.EmptyStackException()))
                    System.err.println("Empty stack");
                else System.err.println(e.toString());
            }
            finally {
                line = in.readLine();
            }
        }
    }

    private static void startSet() throws Exception {
        replSet = new ReplSet<String>("test");
        replSet.connectChannel();
        eventLoopSet();
        replSet.closeChannel();
    }

    private static void eventLoopSet() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (!line.equals("quit"))
        {
            try {
                if (line.substring(0, line.indexOf(' ')).equals("add")) {
                    replSet.add(line.substring(line.indexOf(' ')+1));
                }
                else if (line.substring(0, line.indexOf(' ')).equals("remove")) {
                    if (replSet.remove(line.substring(line.indexOf(' ')+1)))
                        System.out.println(line.substring(line.indexOf(' ')+1)+" has been removed");
                    else
                        System.err.println(line.substring(line.indexOf(' ')+1)+" cannot removed");
                }
                else if (line.substring(0, line.indexOf(' ')).equals("contains")) {
                    if (replSet.contains(line.substring(line.indexOf(' ')+1)))
                        System.out.println(line.substring(line.indexOf(' ')+1)+" belongs in set");
                    else
                        System.err.println(line.substring(line.indexOf(' ')+1)+" not belongs in set");
                }
            }
            catch (StringIndexOutOfBoundsException e )
            {
                System.err.println("Method unknown");
            }
            finally {
                line = in.readLine();
            }
        }
    }
}
