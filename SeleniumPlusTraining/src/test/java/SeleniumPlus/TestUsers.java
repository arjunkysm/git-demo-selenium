package SeleniumPlus;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class TestUsers {
	
private List<Users> user;

public List<Users> getUser() {
	return user;
}

@XmlElement
public void setUser(List<Users> user) {
	this.user = user;
}

}
