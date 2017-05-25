package gpsve.gpsve.Controllers;

import java.util.LinkedList;

/**
 * Created by Petter Månsson on 2017-04-20.
 * Buffer to control the flow of byte arrays
 */

public class Buffer {

    private LinkedList <byte[]> list = new LinkedList<byte[]>();

    /**
     *  Call to this method adds a byte[] into a linkedlist
     * @param bytes
     */
    public synchronized void put(byte[] bytes){
        list.addLast(bytes);
        notifyAll();
    }

    /**
     * Call to this method returns and removes a byte[] from a linkedlist.
     * @return byte[]
     * @throws InterruptedException
     */
    public synchronized byte[] get() throws InterruptedException{
        while(list.isEmpty()){
            wait();
        }
        return list.removeFirst();
    }

    /**
     * Returns the size of the buffer.
     * @return int
     */
    public int size(){
        return list.size();
    }
}
