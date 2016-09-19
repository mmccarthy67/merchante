package merchante.model;

import java.net.URI;

public class UriRequestModel {
	private URI uri;
	private long size;
	private int request;
	
	/**
	 * @return the uri
	 */
	public URI getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}
	/**
	 * @return the request
	 */
	public int getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(int request) {
		this.request = request;
	}
}