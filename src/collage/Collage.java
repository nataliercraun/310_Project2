package collage;

import java.io.Serializable;

public class Collage implements Serializable {
	private static final long serialVersionUID = 1L;
	public String topic;
	public String url;

	public Collage (String topic_, String url_){
		this.topic= topic_;
		this.url= url_;
	}
}

