package mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class WeblogLineCompositeKeyWritable implements WritableComparable {
	String clientIP;
	long timeStamp;
	
	public WeblogLineCompositeKeyWritable(){
	}
	
	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(clientIP);
		out.writeLong(timeStamp);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.setClientIP(in.readUTF());
		this.setTimeStamp(in.readLong());
	}

	@Override
	public int compareTo(Object o) {
		WeblogLineCompositeKeyWritable aKey = (WeblogLineCompositeKeyWritable) this;
		WeblogLineCompositeKeyWritable bKey = (WeblogLineCompositeKeyWritable) o;
		
		int result = aKey.getClientIP().compareTo(bKey.getClientIP());
		if(result != 0) return result;
		
		long aTs = aKey.getTimeStamp();
		long bTs = bKey.getTimeStamp();
		return (aTs == bTs ? 0 : (aTs > bTs) ? 1 : -1);
	}
	

}
