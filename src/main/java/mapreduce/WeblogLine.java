package mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class WeblogLine implements Writable{
	public final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"; 
	
	private long timestamp;
	private String elb;
	private String clientIP;
	private int clientPort;
	private String backendIP;
	private double requestProcessingTime;
	private double backendProcessingTime;
	private double responseProcessingTime;
	private int elbStatusCode;
	private int backendStatusCode;
	private int receivedBytes;
	private int sendBytes;
	private String httpMethod;
	private String request;
	private String url;
	private String httpVersion;
	private String userAgent;
	private String sslCipher;
	private String sslProtocol;
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getElb() {
		return elb;
	}
	
	public void setElb(String elb) {
		this.elb = elb;
	}
	

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getBackendIP() {
		return backendIP;
	}

	public void setBackendIP(String backendIP) {
		this.backendIP = backendIP;
	}

	public double getRequestProcessingTime() {
		return requestProcessingTime;
	}

	public void setRequestProcessingTime(double requestProcessingTime) {
		this.requestProcessingTime = requestProcessingTime;
	}

	public double getBackendProcessingTime() {
		return backendProcessingTime;
	}

	public void setBackendProcessingTime(double backendProcessingTime) {
		this.backendProcessingTime = backendProcessingTime;
	}

	public double getResponseProcessingTime() {
		return responseProcessingTime;
	}

	public void setResponseProcessingTime(double responseProcessingTime) {
		this.responseProcessingTime = responseProcessingTime;
	}

	public int getElbStatusCode() {
		return elbStatusCode;
	}
	
	public void setElbStatusCode(int elbStatusCode) {
		this.elbStatusCode = elbStatusCode;
	}
	
	public int getBackendStatusCode() {
		return backendStatusCode;
	}
	
	public void setBackendStatusCode(int backendStatusCode) {
		this.backendStatusCode = backendStatusCode;
	}
	
	public int getReceivedBytes() {
		return receivedBytes;
	}
	
	public void setReceivedBytes(int receivedBytes) {
		this.receivedBytes = receivedBytes;
	}
	
	public int getSendBytes() {
		return sendBytes;
	}
	
	public void setSendBytes(int sendBytes) {
		this.sendBytes = sendBytes;
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}
	
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public String getUserAgent() {
		return userAgent;
	}
	
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getSslCipher() {
		return sslCipher;
	}
	
	public void setSslCipher(String sslCipher) {
		this.sslCipher = sslCipher;
	}
	
	public String getSslProtocol() {
		return sslProtocol;
	}
	
	public void setSslProtocol(String sslProtocol) {
		this.sslProtocol = sslProtocol;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(this.getTimestamp());
		out.writeUTF(this.getBackendIP());
		out.writeUTF(this.getClientIP());
		out.writeUTF(this.getElb());
		out.writeUTF(this.getHttpMethod());
		out.writeUTF(this.getHttpVersion());
		out.writeUTF(this.getRequest());
		out.writeUTF(this.getSslCipher());
		out.writeUTF(this.getSslProtocol());
		out.writeUTF(this.getUrl());
		out.writeUTF(this.getUserAgent());
		out.writeInt(this.getClientPort());
		out.writeInt(this.getBackendStatusCode());
		out.writeInt(this.getElbStatusCode());
		out.writeInt(this.getReceivedBytes());
		out.writeInt(this.getSendBytes());
		out.writeDouble(this.getBackendProcessingTime());
		out.writeDouble(this.getRequestProcessingTime());
		out.writeDouble(this.getResponseProcessingTime());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.setTimestamp(in.readLong());
		this.setBackendIP(in.readUTF());
		this.setClientIP(in.readUTF());
		this.setElb(in.readUTF());
		this.setHttpMethod(in.readUTF());
		this.setHttpVersion(in.readUTF());
		this.setRequest(in.readUTF());
		this.setSslCipher(in.readUTF());
		this.setSslProtocol(in.readUTF());
		this.setUrl(in.readUTF());
		this.setUserAgent(in.readUTF());
		this.setClientPort(in.readInt());
		this.setBackendStatusCode(in.readInt());
		this.setElbStatusCode(in.readInt());
		this.setReceivedBytes(in.readInt());
		this.setSendBytes(in.readInt());
		this.setBackendProcessingTime(in.readDouble());
		this.setRequestProcessingTime(in.readDouble());
		this.setResponseProcessingTime(in.readDouble());
	}
}
