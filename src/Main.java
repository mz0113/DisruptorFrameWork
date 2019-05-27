import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import test.Consumer;
import test.Data;
import test.PCDataFactory;
import test.Producer;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Executor executor = Executors.newSingleThreadExecutor();
        PCDataFactory factory = new PCDataFactory();
        Disruptor<Data> dataDisruptor = new Disruptor<Data>(factory,32768,executor, ProducerType.MULTI,new YieldingWaitStrategy());
        dataDisruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
        dataDisruptor.start();

        RingBuffer ringBuffer = dataDisruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        //一个int 4个字节
        ByteBuffer byteBuffer  = ByteBuffer.allocate(4);
        for (int i = 0; i < 10000; i++) {
            //如果是putInt(i),会将字节写入byteBuffer的当前位置上，并将索引下标自增。可能会超限抛出BufferOverflowException
            byteBuffer.putInt(0,i);
            producer.pushData(byteBuffer);
        }
    }
}
