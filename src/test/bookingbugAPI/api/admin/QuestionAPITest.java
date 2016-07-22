package bookingbugAPI.api.admin;

import bookingbugAPI.models.BBCollection;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.ModelTest;
import bookingbugAPI.models.Question;
import bookingbugAPI.models.params.QuestionListParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;


public class QuestionAPITest extends AbstractAPITest {
    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/question.json");
                return new MockResponse().setResponseCode(201).setBody(resp.toString());
            }

            return new MockResponse().setResponseCode(400).setBody("{}");
        }
    };

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    //TODO: Add detail_group_id
    @Ignore
    @Test
    public void questionList() {
        try {
            BBCollection<Question> questions = defaultAPI.admin().question().questionList(company, new QuestionListParams());
            assertNotNull(questions);
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
