package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeblogSessionSummaryMapper extends Mapper<Object, Text, IntWritable, WeblogSessionSummary> {
	static final IntWritable one = new IntWritable(1);
	
	@Override
    public void map(Object key, Text value, Context output) throws IOException,
            InterruptedException {
		
		String[] tokens = value.toString().split(" |\\t");
		WeblogSessionSummary summary = new WeblogSessionSummary();
		summary.setClientIP(tokens[0]);
		summary.setNumberOfSessions(Integer.parseInt(tokens[1]));
		summary.setTotalSessionTime(Long.parseLong(tokens[2]));
		summary.setTotalUniqueURLVisits(Integer.parseInt(tokens[3]));
		output.write(one, summary);
	}
	
}
