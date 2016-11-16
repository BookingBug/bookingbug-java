package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.PurchaseListParams;
import bookingbugAPI2.models.params.PurchaseParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class PurchaseAPITest extends AbstractAPITest {

    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/purchase.json");
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

    @Test
    public void purchaseList() {
        try {
            BBCollection<Purchase> purchases;

            //All purchases
            purchases = defaultAPI.admin().purchase().purchaseList(company, new PurchaseListParams().setCreated_from(new DateTime("2016-07-11T12:30:32.596Z")));
            assertNotNull(purchases);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void purchaseRead() {
        try {
            BBCollection<Purchase> purchases = defaultAPI.admin().purchase().purchaseList(company, new PurchaseListParams().setCreated_from(new DateTime("2016-07-11T12:30:32.596Z")));
            assertNotNull(purchases);

            Purchase purchase = defaultAPI.admin().purchase().purchaseRead(company, purchases.getObjectAtIndex(0).id);
            assertNotNull(purchase);
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void purchasePay() {
        try {
            MockWebServer server = mockServer(dispatcher);
            BBCollection<Purchase> purchases = defaultAPI.admin().purchase().purchaseList(company, new PurchaseListParams().setCreated_from(new DateTime("2016-07-11T12:30:32.596Z")));
            assertNotNull(purchases);

            PurchaseParams params = new PurchaseParams()
                    .setPaymentStatus("paid");

            Purchase purchase = mockAPI.admin().purchase().purchasePay(company, purchases.getObjectAtIndex(0).id, params);
            server.shutdown();
            // TODO: 18.07.2016 Complete purchasePay() test
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
