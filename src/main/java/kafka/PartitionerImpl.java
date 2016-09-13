package kafka;

import kafka.producer.Partitioner;

/**
 * Created by zhuxiang on 2016/9/13.
 * Desc :
 */
public class PartitionerImpl implements Partitioner {
    @Override
    public int partition(Object o, int i) {

        return 0;
    }
}
