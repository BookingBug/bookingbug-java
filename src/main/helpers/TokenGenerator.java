package helpers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.simple.JSONObject;


/**
 * Singleton class for creating a BookingBug SSO Token. See [main(String[])] for usage example.
 * Requires commons-codec library [commons.apache.org/codec].
 */
public class TokenGenerator {

    private static final String COMPANY_ID = "{Your Company ID or Affiliate ID}";
    private static final String SECURE_KEY = "{Your Secure Key}";
    private static final byte[] INIT_VECTOR = "OpenSSL for Ruby".getBytes();

    private SecretKeySpec secretKeySpec;
    private IvParameterSpec ivSpec;
    private URLCodec urlCodec = new URLCodec("ASCII");
    private Base64 base64 = new Base64();

    private static TokenGenerator INSTANCE = new TokenGenerator();


    public static TokenGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenGenerator();
        }
        return INSTANCE;
    }


    private TokenGenerator() {
        String salted = SECURE_KEY + COMPANY_ID;
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


    public String create(JSONObject json) throws Exception {
        Date expires = DateUtils.addHours(new Date(), 1);
        json.put("expires", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(expires));
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
            JSONObject jsonObj = new JSONObject();
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