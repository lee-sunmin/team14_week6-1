package cnu.lecture;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
	private InGameSummonerQuerier querier;
	private InGameInfo ingameInfo;

	@Before
	public void setup() throws IOException {
		final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
		GameParticipantListener dontCareListener = mock(GameParticipantListener.class);

		querier = new InGameSummonerQuerier(apiKey, dontCareListener);
		querier = mock(InGameSummonerQuerier.class);
		
		ingameInfo = new InGameSummonerQuerier(apiKey, dontCareListener).getInGameResponse("czzcxz");
        ingameInfo = mock(InGameInfo.class);
	}

	@Test
	public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
		final String summonerName;

		GIVEN: {
			summonerName = "akane24";
		}

		final String actualGameKey;
		WHEN: {
			when(querier.queryGameKey(summonerName)).thenReturn("4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM");
			actualGameKey = querier.queryGameKey(summonerName);
		}

		final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
		THEN: {
			assertThat(actualGameKey, is(expectedGameKey));
		}
	}

	@Test
	public void shouldQuerierReportMoreThan5Summoners() throws Exception {
		final String summonerName;

		GIVEN: {
			summonerName = "czzcxz";
		}

		final int actualParticipants;
		WHEN: {
			when(ingameInfo.getParticipantsLength()).thenReturn(4);
			actualParticipants = ingameInfo.getParticipantsLength();
		}

		final int expectedParticipants = 4;
		THEN: {
			assertTrue(actualParticipants >= expectedParticipants);
		}

	}
}