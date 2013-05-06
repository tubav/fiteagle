package org.fiteagle.core.userdatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
  private UserDB userDatabase;
  
  public UserManager(){
    this.userDatabase = new InMemoryUserDB();
    String dummyPublicKey = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDFrEGAjMHYsmOeRmBaILZ6IbVW6v5bxYK24o45DTXBW/fxmP8quGiIlGY8Q4g50t5OR+tUTn0G4XMue5ahyyMVwLFhIC5JT2E3g9E1t5QlCOUmFOYzElcOlRUipAFRoRRgY4Te+JdcF+ZTwrHMYGPwPlnTsj8e3i/l1tLeb0nzsADn8oLdnps2XPVFFTF3hTPv7du/w1ewOBfVeWdkm3ugetGs8upq/g4ijxxAcaE+iyuqNxUvq0FzvcMi+Tmr9wGQXRcK50suh2ENLjl+pTLnfJNsBLkV3zgJpAJPm2cP4AkLZhFZqHNdK2Do9wLS9hsNbnogJtNqO6qxziKyP+LH";
    List<String> dummyPubKeys = new ArrayList<>(); 
    dummyPubKeys.add(dummyPublicKey);
    User dummyUser = new User("fiteagle.av.test", "test", "testUser",dummyPubKeys );
    try {
      userDatabase.add(dummyUser);
    } catch ( SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public User getUserById(String uuid) throws SQLException{
    
        return userDatabase.get(uuid);
   
  }
  
}
