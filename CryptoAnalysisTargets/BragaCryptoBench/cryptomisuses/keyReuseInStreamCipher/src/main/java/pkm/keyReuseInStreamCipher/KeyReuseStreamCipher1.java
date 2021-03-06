package pkm.keyReuseInStreamCipher;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

//
public final class KeyReuseStreamCipher1 {

	public static void main(String args[]) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			byte[][] M = { ("key1").getBytes(), ("key2").getBytes() };
			byte[][] iv = { "0123456789ABCDEF0123456789ABCDEF".getBytes(),
					"0123456789ABCDEF0123456789ABCDEF".getBytes() };
			byte[] k = "00112233445566778899AABBCCDDEEFF".getBytes();
			byte[][] C = new byte[2][];

			SecretKeySpec ks = new SecretKeySpec(k, "AES");
			Cipher enc = Cipher.getInstance("AES/OFB/NoPadding", "BC");

			enc.init(Cipher.ENCRYPT_MODE, ks, new IvParameterSpec(iv[0]));
			C[0] = enc.doFinal(M[0]);
			enc.init(Cipher.ENCRYPT_MODE, ks, new IvParameterSpec(iv[1]));
			C[1] = enc.doFinal(M[1]);

		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
		}
	}
}
