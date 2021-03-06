package pdf.insecureDefault;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class InsecureDefaultRSA {

	public static void main(String args[]) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			byte[] msg1 = ("Insecure default RSA.").getBytes();
			KeyPairGenerator g = KeyPairGenerator.getInstance("RSA", "BC");
			g.initialize(2048);
			KeyPair kp = g.generateKeyPair();

			Cipher enc = Cipher.getInstance("RSA", "BC");
			enc.init(Cipher.ENCRYPT_MODE, kp.getPublic());
			Cipher dec = Cipher.getInstance("RSA", "BC");
			dec.init(Cipher.DECRYPT_MODE, kp.getPrivate());

			byte[][] ct = new byte[2][];
			for (int i = 0; i < 2; i++) {
				ct[i] = enc.doFinal(msg1);
				byte[] pt2 = dec.doFinal(ct[i]);
			}

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e) {
		}
	}
}
