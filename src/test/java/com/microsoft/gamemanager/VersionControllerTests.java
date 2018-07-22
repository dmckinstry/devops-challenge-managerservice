package com.microsoft.gamemanager;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VersionControllerTests {

	@Test
	public void versionReturnsString() {
                VersionController vc = new VersionController();
                String version = vc.Version();
                assertFalse(version.isEmpty());
        }
}
