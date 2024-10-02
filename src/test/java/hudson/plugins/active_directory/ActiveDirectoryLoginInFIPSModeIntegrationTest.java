package hudson.plugins.active_directory;

import java.util.ArrayList;
import java.util.List;

import org.htmlunit.FailingHttpStatusCodeException;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class ActiveDirectoryLoginInFIPSModeIntegrationTest {
	@Rule
	public JenkinsRule j = new JenkinsRule();

	@Test(expected = FailingHttpStatusCodeException.class)
	public void testLoginFailureWithShortPasswordInFIPSmode() throws Exception {
		ActiveDirectoryDomain activeDirectoryDomain = new ActiveDirectoryDomain("samdom.example.com", "localhost:3268"
				, "site", "Administrator", "ia4uV1EeKait");
		List<ActiveDirectoryDomain> domains = new ArrayList<>(1);
		domains.add(activeDirectoryDomain);
		ActiveDirectorySecurityRealm activeDirectorySecurityRealm = new ActiveDirectorySecurityRealm(null, domains, null, null, null
				, null, GroupLookupStrategy.RECURSIVE, false, true, null, true, null, true);
		j.getInstance().setSecurityRealm(activeDirectorySecurityRealm);
		// Try to login as Fred with a short password, it will throw an exception
		j.createWebClient().login("Fred", "ia4uV1EeKait");
	}

}