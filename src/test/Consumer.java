package test;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<Data> {
    @Override
    public void onEvent(Data o) throws Exception {
        System.out.println("消费者"+Thread.currentThread().getName()+"处理了一个任务"+o.getStr());
    }
}
