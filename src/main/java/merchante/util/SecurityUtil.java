package merchante.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class SecurityUtil {
	public static SSLSocketFactory getSSLSocketFactory() {
		return (SSLSocketFactory)SSLSocketFactory.getDefault();
	}

	public static HostnameVerifier getHostnameVerifier() {
		return new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession sslSession) {
				return true;
			}
		};
	}
}