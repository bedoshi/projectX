package skajihara.projectX.MainContents.Home.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import skajihara.projectX.MainContents.Home.entity.Tweet;

import java.util.List;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TweetRepositoryTest {

    @Autowired
    TweetRepository tweetRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {

        tweetRepository.deleteAll();

        entityManager.createNativeQuery("INSERT INTO ACCOUNTS (id, name, bio) VALUES ('user1', 'User One', 'Bio One')")
                .executeUpdate();
        entityManager.createNativeQuery("INSERT INTO ACCOUNTS (id, name, bio) VALUES ('user2', 'User Two', 'Bio Two')")
                .executeUpdate();
        entityManager.createNativeQuery("INSERT INTO ACCOUNTS (id, name, bio) VALUES ('user3', 'User Three', 'Bio Three')")
                .executeUpdate();

        Tweet tweet1 = new Tweet();
        tweet1.setAccountId("user1");
        tweet1.setText("First test tweet");
        tweet1.setDatetime(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 3)); // 3日前
        tweetRepository.save(tweet1);

        Tweet tweet2 = new Tweet();
        tweet2.setAccountId("user2");
        tweet2.setText("Second test tweet");
        tweet2.setDatetime(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 2)); // 2日前
        tweetRepository.save(tweet2);

        Tweet tweet3 = new Tweet();
        tweet3.setAccountId("user3");
        tweet3.setText("Third test tweet");
        tweet3.setDatetime(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 1)); // 1日前
        tweetRepository.save(tweet3);
    }

    @Test
    void selectAllUnitTest() {

        List<Tweet> tweets = tweetRepository.selectAll();
        assertThat(tweets).hasSize(3);
    }

    @Test
    void selectRecentNUnitTest() {

        List<Tweet> recentTweets = tweetRepository.selectRecentN(2);
        assertThat(recentTweets).hasSize(2);
        assertThat(recentTweets.get(0).getText()).isEqualTo("Third test tweet");
        assertThat(recentTweets.get(1).getText()).isEqualTo("Second test tweet");
    }
}

