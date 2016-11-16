package helpers2.hal_addon;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import com.theoryinpractise.halbuilder.json.JsonRepresentationWriter;

/**
 * Created by sebi on 04.02.2016.
 */
public class CustomJsonRepresentationFactory extends JsonRepresentationFactory {

    public CustomJsonRepresentationFactory() {
        this.withRenderer("application/hal+json", JsonRepresentationWriter.class);
        this.withReader("application/hal+json", CustomJsonRepresentationReader.class);
    }
}
