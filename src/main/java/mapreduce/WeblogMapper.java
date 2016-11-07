package mapreduce;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class WeblogMapper extends Mapper<Object, Text, WeblogLineCompositeKeyWritable, WeblogLine> {
	Logger logger = Logger.getLogger(WeblogMapper.class);
	
	final static SimpleDateFormat dateFormat = new SimpleDateFormat(WeblogLine.DATE_FORMAT);
	
    @Override
    public void map(Object key, Text value, Context output) throws IOException,
            InterruptedException {
    	try{
	    	WeblogLine line = new WeblogLine();
	    	
	    	//use regex to retrieve individual items from log 
	        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
	        Matcher matcher = regex.matcher(value.toString());
	        
	        if(matcher.find()){
	        	String time = matcher.group();
				line.setTimestamp(dateFormat.parse(time).getTime());
	        }
	        
	        if(matcher.find()){
	        	line.setElb(matcher.group());
	        }
	        
	        if(matcher.find()){
	        	/*
	        	 * if cookie or login information can be retrieved, 
	        	 * individual client can be a lot easier to identify  
	        	 */
	        	String[] clientIP = matcher.group().split(":");
	        	line.setClientIP(clientIP[0]);
	        	
	        	line.setClientPort(Integer.parseInt(clientIP[1]));
	        }
	        
	        if(matcher.find()){
	        	line.setBackendIP(matcher.group());
	        }
	        
	        if(matcher.find()){
	        	line.setRequestProcessingTime(Double.parseDouble(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setBackendProcessingTime(Double.parseDouble(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setResponseProcessingTime(Double.parseDouble(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setElbStatusCode(Integer.parseInt(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setBackendStatusCode(Integer.parseInt(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setReceivedBytes(Integer.parseInt(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	line.setSendBytes(Integer.parseInt(matcher.group()));
	        }
	        
	        if(matcher.find()){
	        	String request = matcher.group();
	        	line.setRequest(request);
	        	
	        	String[] info = request.replaceAll("\"", "").split(" ");
	        	line.setHttpMethod(info[0]);
	        	line.setUrl(info[1]);
	        	line.setHttpVersion(info[2]);
	        }
	        
	        if(matcher.find()){
	        	line.setUserAgent(matcher.group());
	        }
	        
	        if(matcher.find()){
	        	line.setSslCipher(matcher.group());
	        } 
	
	        if(matcher.find()){
	        	line.setSslProtocol(matcher.group());
	        } 
        
	        WeblogLineCompositeKeyWritable outputKey = new WeblogLineCompositeKeyWritable();
	        outputKey.setClientIP(line.getClientIP());
	        outputKey.setTimeStamp(line.getTimestamp());
	        
        	if(line.getClientIP() != null){
        		output.write(outputKey, line);
        	}
        } catch(ParseException | NullPointerException | ArrayIndexOutOfBoundsException e ){
        	//a few lines in the log cannot be parsed, skip special cases 
        	logger.error(value.toString());
        	logger.error(e);
        }
    }
}
