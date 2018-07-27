package SeleniumPlus;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class Users {
	
	private String username;
	private String type;
	private String password;
	public String getUsername() {
		return username;
	}
	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
	
	

}
