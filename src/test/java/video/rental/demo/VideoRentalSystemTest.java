package video.rental.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class VideoRentalSystemTest {

	private GoldenMaster goldenMaster = new GoldenMaster();
	
	@Test
	@Disabled
	void should_generate_golden_master() {
		// Given (Arrange)
		
		// When (Act)
		goldenMaster.generate();

		// Then (Assert)
		
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	void check_run_result_against_golden_master() {
		// Given (Arrange)
		String expected = goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getRunResult();

		// Then (Assert)
		assertEquals(expected, actual.replaceAll("\r\n", "\n"));
	}

	@Test
	@EnabledOnOs({OS.LINUX, OS.MAC})
	void check_run_result_against_golden_master_on_linux() {
		// Given (Arrange)
		String expected = goldenMaster.getGoldenMaster();
		
		// When (Act)
		String actual = goldenMaster.getRunResult();

		// Then (Assert)
		assertEquals(expected, actual);
	}
}
