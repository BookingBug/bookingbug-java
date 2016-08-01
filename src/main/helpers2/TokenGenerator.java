package helpers2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Singleton class for creating a BookingBug SSO Token. See [main(String[])] for usage example.
 * Requires commons-codec library [commons.apache.org/codec].
 */
public class TokenGenerator {

    private static TokenGenerator INSTANCE;

    //private static String companyId;
    //private static String secureKey;//ukE2Sjx48dgFlyb27QkHhU
    private static final byte[] INIT_VECTOR = "OpenSSL for Ruby".getBytes();

    private SecretKeySpec secretKeySpec;
    private IvParameterSpec ivSpec;
    private URLCodec urlCodec = new URLCodec("ASCII");
    private Base64 base64 = new Base64();


    public static TokenGenerator getInstance(String company_id, String secure_key) {
        //no need for singleton pattern since this will block future login attempts
        //if (INSTANCE == null) {
        INSTANCE = new TokenGenerator(company_id, secure_key);
        //}

        return INSTANCE;
    }


    private TokenGenerator(String company_id, String secure_key) {
        String salted = secure_key + company_id;
        byte[] hash = DigestUtils.sha(salted);
        byte[] saltedHash = new byte[16];
        System.arraycopy(hash, 0, saltedHash, 0, 16);

        secretKeySpec = new SecretKeySpec(saltedHash, "AES");
        ivSpec = new IvParameterSpec(INIT_VECTOR);
    }


    private void encrypt(InputStream in, OutputStream out) throws Exception {
        try {
            byte[] buf = new byte[1024];

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            out = new CipherOutputStream(out, cipher);

            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public String create(JsonNode json) throws Exception {
        Date expires = DateUtils.addHours(new Date(), 1);
        ((ObjectNode)json).put("expires", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(expires));
        
        byte[] data = json.toString().getBytes();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < 16; i++) {
            data[i] ^= INIT_VECTOR[i];
        }
        encrypt(new ByteArrayInputStream(data), out);

        String token = new String(urlCodec.encode(base64.encode(out.toByteArray())));
        return token;
    }


/*
    public static void main(String[] args) {
        try {
            JsonNode jsonObj = new JsonNode();
            jsonObj.put("first_name", "John");
            jsonObj.put("last_name", "Smith");
            jsonObj.put("email", "smith@example.com");
            jsonObj.put("mobile", "0123456789");

            System.out.println(TokenGenerator.getInstance().create(jsonObj));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}