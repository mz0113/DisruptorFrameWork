package test;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<Data> {
    @Override
    public Data newInstance() {
        return new Data(null);
    }
}
