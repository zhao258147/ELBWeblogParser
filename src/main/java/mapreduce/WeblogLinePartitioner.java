package mapreduce;

import org.apache.hadoop.mapreduce.Partitioner;

public class WeblogLinePartitioner extends Partitioner<WeblogLineCompositeKeyWritable, WeblogLine> {

	@Override
	public int getPartition(WeblogLineCompositeKeyWritable key, WeblogLine value, int numPartitions) {
		return Math.abs(key.getClientIP().hashCode() % numPartitions);
	}

}
