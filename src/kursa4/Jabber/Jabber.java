package kursa4.Jabber;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class Jabber  {

    private final String serverLogin = "pukamirka2" ;
    private final String serverPass = "489052369a" ;
    private final String jabberHost = "jabber.ru";

    public void sendNotification(String userJabberLogin , String articleName) throws Exception{

        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(serverLogin, serverPass)
                .setXmppDomain(jabberHost)
                .setResource(jabberHost)
                .setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                })
                .setPort(5222)
                .build();
        AbstractXMPPConnection connection = new XMPPTCPConnection( config);
        connection.connect().login();
        Message message = new Message(userJabberLogin+"@jabber.ru",
                "Hi "+userJabberLogin+" your article:"+articleName+" \nWas verified and added to the website , thank you for your activity.");
        connection.sendStanza(message);
    }


}
