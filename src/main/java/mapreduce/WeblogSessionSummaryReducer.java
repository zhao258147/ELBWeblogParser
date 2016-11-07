package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WeblogSessionSummaryReducer extends Reducer<Text, WeblogLine, Text, WeblogSessionSummary> {
	@Override
    public void reduce(Text key, Iterable<WeblogLine> values, Context output)
            throws IOException, InterruptedException {
		
	}
}
