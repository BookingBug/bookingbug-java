package bookingbugAPI.models;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import helpers.HttpServiceResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class BBCollection<T extends BBRoot> extends BBRoot implements Iterable<T>{

    private Class<T> class_type;
    private String collectionNameSpace = null;


    public BBCollection(HttpServiceResponse httpServiceResponse, String authToken, Class<T> classType) {
        super(httpServiceResponse, authToken);
        class_type = classType;
    }


    public BBCollection(HttpServiceResponse httpServiceResponse, String authToken, String nameSpace, Class<T> classType) {
        super(httpServiceResponse, authToken);
        auth_token = authToken;
        collectionNameSpace = nameSpace;
        class_type = classType;
    }


    /**
     * getNext
     * @return T extends BBRoot
     */
//    public T getNext(){
//        Object obj = new Object();
//        return (T)obj;
//    }


    /**
     * getObjectAtIndex
     * @param idx
     * @return T extends BBRoot
     */
    public T getObjectAtIndex(int idx){
        //return (T) new BBRoot(new HttpServiceResponse((ContentRepresentation)getRep().getResourcesByRel(collectionNameSpace).get(idx)));

        try {
            T obj = class_type.getConstructor(HttpServiceResponse.class).newInstance(new HttpServiceResponse((ContentRepresentation)getRep().getResourcesByRel(collectionNameSpace).get(idx)));
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
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


    public Iterator<T> iterator() {
        return new BBCollectionIterator(this);
    }


    final class BBCollectionIterator implements Iterator<T> {

        BBCollection bb_collection;

        private int cursor;
        private final int end;


        public BBCollectionIterator(BBCollection bbCollection) {
            bb_collection = bbCollection;

            this.cursor = 0;
            this.end = bbCollection.size();
        }


        public boolean hasNext() {
            return this.cursor < end;
        }


        public T next() {
            if(this.hasNext()) {
                int current = cursor;
                cursor ++;
                return (T)bb_collection.getObjectAtIndex(current);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
