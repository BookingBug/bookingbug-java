package bookingbugAPI.models;

import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.services.Http.PlainHttpService;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


public class Purchase extends BBRoot{


    public Purchase(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Purchase(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }


    public Purchase() {}


    /**
     * Get a Specific Purchase Total
     * @param companyId Company Id
     * @param purchaseTotalId Purchase Total Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseReadTotal(String companyId, String purchaseTotalId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseReadTotal().set("companyId", companyId).set("purchaseTotalId", purchaseTotalId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Purchase Item
     * @param companyId Company Id
     * @param purchaseTotalId Purchase Total Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseReadItem(String companyId, String purchaseTotalId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseReadItem().set("companyId", companyId).set("purchaseTotalId", purchaseTotalId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Purchase
     * @param purchaseId Purchase Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseRead(String purchaseId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseRead().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Purchase from a booking ref
     * @param bookingRefId Booking Ref Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseFindByBookingRef(String bookingRefId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseFindByBookingRef().set("bookingRefId", bookingRefId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Update a number of bookings in a Specific Purchase
     * @param purchaseId Purchase Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseUpdate(String purchaseId, Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseUpdate().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Update a booking’s status from WAITLIST to BOOKING if there is space available
     * and the booking was on the waitlist originally.
     * @param purchaseId Purchase Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookWaitlistItem(String purchaseId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookWaitlistItem().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Delete (Cancel) All Bookings associated with a given Purchase
     * @param purchaseId Purchase Id
     * @return Resourse
     * @throws IOException
     */
    public Resource deleteBookingsForPurchase(String purchaseId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.deleteBookingsForPurchase().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_DELETE(url, auth_token), auth_token);
    }


    /**
     *
     * @param purchaseId
     * @param bookingId
     * @return
     * @throws IOException
     */
    public Resource bookingRead(String purchaseId, String bookingId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingRead().set("purchaseId", purchaseId).set("bookingId", bookingId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Update Booking
     * @param purchaseId Purchase Id
     * @param bookingId Booking Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingUpdate(String purchaseId, String bookingId, Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingUpdate().set("purchaseId", purchaseId).set("bookingId", bookingId).expand());
        return new Resource(PlainHttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Delete (Cancel) Booking
     * @param purchaseId Purchase Id
     * @param bookingId Booking Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingCancel(String purchaseId, String bookingId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingCancel().set("purchaseId", purchaseId).set("bookingId", bookingId).expand());
        return new Resource(PlainHttpService.api_DELETE(url, auth_token), auth_token);
    }


    /**
     * Add a new file attachment to a booking
     * @param purchaseId Purchase Id
     * @param bookingId Booking Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingAttachementAdd(String purchaseId, String bookingId, Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingAttachementAdd().set("purchaseId", purchaseId).set("bookingId", bookingId).expand());
        return new Resource(PlainHttpService.api_POST(url, params, auth_token), auth_token);
    }


    /**
     * Add or update a file attachment to a booking
     * @param purchaseId Purchase Id
     * @param bookingId Booking Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingAttachementAddOrUpdate(String purchaseId, String bookingId, Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingAttachementAddOrUpdate().set("purchaseId", purchaseId).set("bookingId", bookingId).expand());
        return new Resource(PlainHttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Get a list of file attachments for a booking
     * @param purchaseId Purchase Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingAttachementList(String purchaseId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.bookingAttachementList().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all Purchased Deals
     * @param purchaseId Purchase Id
     * @return Resource
     * @throws IOException
     */
    public Resource purchaseDealsList(String purchaseId) throws IOException {
        URL url = new URL(PublicURLS.Purchase.purchaseDealsList().set("purchaseId", purchaseId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }

}
