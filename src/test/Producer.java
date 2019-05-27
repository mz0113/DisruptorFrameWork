package test;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producer{

    private final RingBuffer<Data> ringBuffer;

    public Producer(RingBuffer ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer){
        //get the next avalible position index
        Long sequence = ringBuffer.next();
        try{
            //get the object
            Data str = ringBuffer.get(sequence);
            //fill with data
            str.setStr(byteBuffer.getInt(0));
        }finally {
            //then publish it
            ringBuffer.publish(sequence);
        }
    }
}
