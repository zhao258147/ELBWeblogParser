package mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;

import org.apache.hadoop.io.Writable;

public class WeblogSessionSummary implements Writable, Comparable{
	private String clientIP;
	private int numberOfSessions = 0;
	private int totalUniqueURLVisits = 0;
	private long totalSessionTime = 0;

	public int getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	public void incrementNumberOfSessions(){
		this.numberOfSessions++;
	}
	
	public int getTotalUniqueURLVisits() {
		return totalUniqueURLVisits;
	}

	public void setTotalUniqueURLVisits(int totalUniqueURLVisits) {
		this.totalUniqueURLVisits = totalUniqueURLVisits;
	}
	
	public void incrementTotalUniqueURLVisits(){
		this.totalUniqueURLVisits ++;
	}
	
	public long getTotalSessionTime() {
		return totalSessionTime;
	}

	public void setTotalSessionTime(long totalSessionTime) {
		this.totalSessionTime = totalSessionTime;
	}
	
	public void incrementTotalSessionTime(long l){
		this.totalSessionTime += l;
	}
	
	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.getNumberOfSessions());
		out.writeLong(this.getTotalSessionTime());
		out.writeInt(this.getTotalUniqueURLVisits());
		out.writeUTF(this.getClientIP());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.setNumberOfSessions(in.readInt());
		this.setTotalSessionTime(in.readLong());
		this.setTotalUniqueURLVisits(in.readInt());
		this.setClientIP(in.readUTF());
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getNumberOfSessions());
		sb.append(" ");
		sb.append(getTotalSessionTime());
		sb.append(" ");
		sb.append(getTotalUniqueURLVisits());
		return sb.toString();
	}

	@Override
	public int compareTo(Object o) {
		WeblogSessionSummary a = (WeblogSessionSummary) o;
		WeblogSessionSummary b = (WeblogSessionSummary) this;

		long num1 = a.getTotalSessionTime();
		long num2 = b.getTotalSessionTime();
		return (num1 == num2 ? 0 : (num1 > num2 ? -1 : 1));
	}
}
