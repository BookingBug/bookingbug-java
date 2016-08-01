package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.Params;
import bookingbugAPI2.models.params.PersonParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class PersonAPITest extends AbstractAPITest{

    private Company company;
    private Person person;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/person.json");
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
        person = getPerson();
    }

    @Test
    public void PersonList() {
        try {
            BBCollection<Person> people;

            //All people
            people = defaultAPI.admin().person().personList(company, null);
            assertNotNull(people);

            //Paginated people
            people = defaultAPI.admin().person().personList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(people);
            assertTrue(people.size() <= 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void PersonUpdate() {
        try {
            BBCollection<Person> people = defaultAPI.admin().person().personList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(people);

            PersonParams.Update params = new PersonParams.Update()
                    .setName("Test Name")
                    .setDescription("Test Description");

            Person Person = defaultAPI.admin().person().personUpdate(company, people.getObjectAtIndex(0).id, params);
            assertNotNull(Person);
            assertEquals(Person.getName(), params.getName());
            assertEquals(Person.getDescription(), params.getDescription());

            //TODO: expand here asserts

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void PersonCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            PersonParams.Create params = new PersonParams.Create()
                    .setName("Barry")
                    .setDescription("");

            Person person = mockAPI.admin().person().personCreate(getCompany(), params);
            assertNotNull(person);
            assertEquals(person.getName(), params.getName());
            assertEquals(person.getDescription(), params.getDescription());
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void PersonNewSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().person().getNewPersonSchema(company);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void PersonEditSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().person().getEditPersonSchema(person);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
