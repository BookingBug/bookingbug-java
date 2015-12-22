package bookingbugAPI.models;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import helpers.HttpServiceResponse;


public class BBCollection<T extends BBRoot> extends BBRoot {

    private String collectionNameSpace = null;


    public BBCollection(HttpServiceResponse httpServiceResponse, String auth_token) {
       super(httpServiceResponse, auth_token);
    }


    /**
     * getNext
     * @return T extends BBRoot
     */
    public T getNext(){
        Object obj = new Object();
        return (T)obj;
    }


    /**
     * getObjectAtIndex
     * @param idx
     * @return T extends BBRoot
     */
    public T getObjectAtIndex(int idx){
        return (T) new BBRoot(new HttpServiceResponse((ContentRepresentation)getRep().getResourcesByRel(collectionNameSpace).get(idx)));
    }


    /**
     * setCollectionNameSpace
     * @param newNameSpace
     */
    public void setCollectionNameSpace (String newNameSpace) {
        collectionNameSpace = newNameSpace;
    }


    /**
     * getCollectionNameSpace
     * @return String
     */
    public String getCollectionNameSpace() {
        return collectionNameSpace;
    }


    /**
     * size
     * @return int
     */
    public int size() {
        return getRep().getResourcesByRel(collectionNameSpace).size();
    }
}
