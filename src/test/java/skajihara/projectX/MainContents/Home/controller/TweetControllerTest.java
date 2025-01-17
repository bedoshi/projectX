package skajihara.projectX.MainContents.Home.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import skajihara.projectX.MainContents.Home.service.TweetService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TweetService tweetService;

    @Test
    public void getAllTweetsUnitTest() throws Exception {

        doReturn(new ArrayList<>()).when(tweetService).selectAllTweets();

        mockMvc.perform(get("/api/tweets")).andExpect(status().isOk());

        verify(tweetService, times(1)).selectAllTweets();
    }

    @Test
    public void getRecentTweetsUnitTest() throws Exception {

        doReturn(new ArrayList<>()).when(tweetService).selectRecentTweets(anyInt());

        mockMvc.perform(get("/api/tweets/recent")).andExpect(status().isOk());

        verify(tweetService, times(1)).selectRecentTweets(anyInt());
    }

    @Test
    public void createTweetUnitTest() throws Exception {

        doNothing().when(tweetService).createTweet(any());

        mockMvc.perform(post("/api/tweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(tweetService, times(1)).createTweet(any());
    }

    @Test
    public void updateTweetUnitTest() throws Exception {

        doNothing().when(tweetService).updateTweet(anyInt(),any());

        mockMvc.perform(put("/api/tweets/{id}",123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(tweetService, times(1)).updateTweet(anyInt(),any());
    }

    @Test
    public void deleteTweetUnitTest() throws Exception {

        doNothing().when(tweetService).deleteTweet(anyInt());

        mockMvc.perform(delete("/api/tweets/{id}",123)).andExpect(status().isOk());

        verify(tweetService, times(1)).deleteTweet(anyInt());
    }
}
