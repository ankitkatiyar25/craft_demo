package twitter_api;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestPasswordEncoading {

	@Test
	public void testPasswordEncoading() {

		LdapShaPasswordEncoder ldapShaPasswordEncoder = new LdapShaPasswordEncoder();
		Assert.assertEquals(ldapShaPasswordEncoder.encodePassword("123456", null), "{SHA}fEqNCco3Yq9h5ZUglD3CZJT4lBs=");
	}

}
