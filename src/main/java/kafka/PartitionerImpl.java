package kafka;

import kafka.producer.Partitioner;

/**
 * Created by zhuxiang on 2016/9/13.
 * Desc :
 */
public class PartitionerImpl implements Partitioner {
    @Override
    public int partition(Object key, int numPartitions) {
        int partition;
        String k = (String)key;
        partition = Math.abs(k.hashCode()) % numPartitions;
        return partition;
    }

    /*public static void main(String[] args) {
        PartitionerImpl impl = new PartitionerImpl();
        System.out.println(impl.partition("111",4));
    }*/
}
