package mmdb.kvs.app.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class pair {
	private String key;
	
	private String value;

	@JsonProperty
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@JsonProperty
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
