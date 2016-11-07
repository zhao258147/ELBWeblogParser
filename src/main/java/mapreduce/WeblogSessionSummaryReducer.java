package mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeblogSessionSummaryReducer extends Reducer<IntWritable, WeblogSessionSummary, Text, Text> {
	static final int NUM_OF_FREQUENT_USERS  = 5;
	
	@Override
    public void reduce(IntWritable key, Iterable<WeblogSessionSummary> values, Context output)
            throws IOException, InterruptedException {
		
		long sessionTimeTotal = 0;
		int uniqueURLTotal = 0;
		int totalSessions = 0;
		
		PriorityQueue<WeblogSessionSummary> maxHeap = new PriorityQueue<WeblogSessionSummary>(NUM_OF_FREQUENT_USERS + 1);
		
		Iterator<WeblogSessionSummary> iter = values.iterator();
        while(iter.hasNext()){
        	WeblogSessionSummary value = iter.next();
        	
        	totalSessions += value.getNumberOfSessions();
        	sessionTimeTotal += value.getTotalSessionTime();
        	uniqueURLTotal += value.getTotalUniqueURLVisits();
        	
        	WeblogSessionSummary summary = new WeblogSessionSummary();
        	summary.setClientIP(value.getClientIP());
        	summary.setNumberOfSessions(value.getNumberOfSessions());
        	
        	maxHeap.add(summary);
        	if(maxHeap.size() > NUM_OF_FREQUENT_USERS){
        		maxHeap.poll();
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()){
        	WeblogSessionSummary summary = maxHeap.poll();
        	sb.append(summary.getClientIP());
        	sb.append(" ");
        }
        
        output.write(new Text("Top Visitors"), new Text(sb.toString()));
        output.write(new Text("Average Session Time"), new Text(Long.toString(sessionTimeTotal/totalSessions)));
        output.write(new Text("Average Unique URL Visits"), new Text(Double.toString((double)uniqueURLTotal/(double)totalSessions)));
	}
}
