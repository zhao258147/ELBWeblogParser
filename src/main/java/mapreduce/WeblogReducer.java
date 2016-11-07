package mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeblogReducer extends Reducer<WeblogLineCompositeKeyWritable, WeblogLine, Text, WeblogSessionSummary> {
	public static final int SESSION_LENGTH = 900;
	
	@Override
    public void reduce(WeblogLineCompositeKeyWritable key, Iterable<WeblogLine> values, Context output)
            throws IOException, InterruptedException {
		
		List<String> urls = new ArrayList<String>();
		
		WeblogSessionSummary summary = new WeblogSessionSummary();
		long prevTimestamp = 0; 
		
		Iterator<WeblogLine> iter = values.iterator();
        while(iter.hasNext()){
        	WeblogLine value = iter.next();
		
        	if(prevTimestamp == 0){
        		summary.incrementNumberOfSessions();
        	} else {
        		//time difference between the current request from the prev request in milli 
        		long diff = (prevTimestamp - value.getTimestamp())/1000;

        		//still requesting content for the previous session, so not a new session
        		boolean contentRetrival = value.getUrl().contains("wp-content") || value.getHttpMethod().equals("POST");
        		
        		//if the difference if longer than 15 mintues and not requesting content, start new session
	        	if(diff > SESSION_LENGTH && !contentRetrival){
	        		urls.clear();
	        		
	        		summary.incrementNumberOfSessions();
	        	} else {
	        		summary.incrementTotalSessionTime(diff);		        		
	        	}
        	}

        	prevTimestamp = value.getTimestamp();
        	
        	//check if url is unique in the current session
        	if(value.getHttpMethod().equals("GET") && !value.getUrl().contains("wp-content") && !urls.contains(value.getUrl())){
        		urls.add(value.getUrl());
        		summary.incrementTotalUniqueURLVisits();
        	}
        }
        output.write(new Text(key.getClientIP()), summary);
    }
	
}
