package gpsve.gpsve.Controllers;

import java.util.LinkedList;

/**
 * Created by Petter Månsson on 2017-04-20.
 */

public class Buffer {

    private LinkedList <byte[]> list = new LinkedList<byte[]>();

    public synchronized void put(byte[] bytes){
        list.addLast(bytes);
        notifyAll();
    }

    public synchronized byte[] get() throws InterruptedException{
        while(list.isEmpty()){
            wait();
        }
        return list.removeFirst();
    }

    public int size(){
        return list.size();
    }
}
