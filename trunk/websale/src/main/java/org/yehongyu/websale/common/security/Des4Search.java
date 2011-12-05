package org.yehongyu.websale.common.security;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 
 * @author Guo Gang
 * 
 */
public class Des4Search {
	private static final byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0 };

	private static final String PASSWORD_CRYPT_KEY = "@345*123";

	private final static String DES = "DES/ECB/NOPadding";

	// private final static String DES = "DES/CFB8/NoPadding";
	// private final static String DES = "DESede/CBC/PKCS5Padding";
	private final static String DES_KEY = "DES";

	/**
	 * ����
	 * 
	 * @param src
	 *            ����Դ
	 * @param key
	 *            ��Կ�����ȱ�����8�ı���
	 * @return ���ؼ��ܺ������
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		// SecureRandom sr = new SecureRandom();
		AlgorithmParameterSpec sr = new IvParameterSpec(IV);

		// ��ԭʼ�ܳ����ݴ���DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
		// һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_KEY);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɼ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		// cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		// ���ڣ���ȡ���ݲ�����
		// ��ʽִ�м��ܲ���
		return cipher.doFinal(src);
	}

	/**
	 * ����
	 * 
	 * @param src
	 *            ����Դ
	 * @param key
	 *            ��Կ�����ȱ�����8�ı���
	 * @return ���ؽ��ܺ��ԭʼ����
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		// SecureRandom sr = new SecureRandom();
		AlgorithmParameterSpec sr = new IvParameterSpec(IV);
		// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
		// һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_KEY);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		// cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		// ���ڣ���ȡ���ݲ�����
		// ��ʽִ�н��ܲ���
		return cipher.doFinal(src);
	}

	/**
	 * �������
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �������
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {
		if (password == null || "".equals(password))
			return null;
		byte o[] = null;
		StringBuilder builder = new StringBuilder();
		try {

			byte b[] = password.getBytes();
			byte ooo[] = new byte[8];
			for (int i = 0; i < b.length; i += 8) {
				if (i + 8 > b.length) {
					for (int j = 0; j < 8; j++) {
						ooo[j] = 0;
					}
					System.arraycopy(b, i, ooo, 0, b.length - i);

				} else {
					System.arraycopy(b, i, ooo, 0, 8);
				}
				o = encrypt(ooo, PASSWORD_CRYPT_KEY.getBytes());
				builder.append(byte2hex(o));
			}

			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * ������ת�ַ���
	 * 
	 * @param b
	 * 
	 * @return
	 * 
	 */

	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			// System.out.print(b[n]);
			// System.out.print("@");
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			// System.out.print(stmp);
			// System.out.print(" ");
			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)

			throw new IllegalArgumentException("���Ȳ���ż��");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {

			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);

		}

		return b2;
	}

	public static void main(String[] args) {
//		System.out
//				.println("pass="
//						+ encrypt(":mmtlhy4:mmtlhy7:mmtlhy8:mmtlhy10:baby02:baby01:rose2007:mmter2:biziyun:"));
		/*
		 * String es = "12345678ii"; DesDE des=new DesDE(PASSWORD_CRYPT_KEY);
		 * String ds1 = ""; String ds2 = ""; int end = 0; for(int i = 0; i <
		 * es.length(); i += 4) { end = i + 4; if (end > es.length()) { end =
		 * es.length(); } String o = es.substring(i, end); ds1 += des.enc(o,
		 * o.length()); } //String ds1 = des.enc(es, es.length());
		 * System.out.println("encrpt:" + ds1); for(int i = 0; i < ds1.length();
		 * i += 4) { end = i + 4; if (end > ds1.length()) { end = ds1.length(); }
		 * String o = ds1.substring(i, end); ds2 += des.dec(o, o.length()); }
		 * //String ds2 = des.dec(ds1, ds1.length());
		 * System.out.println("encrpt:" + ds2);
		 */
		try {
			// String s = encrypt("��Ҫ������Ҫ��ѧ!33");
			// System.out.println("encrypt===" + s);
			// System.out.println("encrypt===" +
			// decrypt("1084A085DC6BD2D755D4D6A7726"));
			// String es = "��Ҫ������Ҫ��ѧ!";
			// //String ds1 = encrypt(es);
			// String ds2 = "";
			// byte b[] = es.getBytes();
			// byte ooo[] = new byte[8];
			// for (int i = 0; i < b.length; i += 8) {
			// if (i + 8 > b.length) {
			// for(int j = 0; j < 8; j++) {
			// ooo[j] = 0;
			// }
			// System.arraycopy(b, i, ooo, 0, b.length - i);
			//
			// } else {
			// System.arraycopy(b, i, ooo, 0, 8);
			// }
			// byte o[] = encrypt(ooo, PASSWORD_CRYPT_KEY.getBytes());
			// System.out.println("arr=" +byte2hex(o));
			// ds2 += byte2hex(o);
			// System.out.println("ds2=" +ds2);
			// }
			//			
			// System.out.println("de==" +decrypt(ds2) + " len=" +
			// decrypt(ds2).length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { Des my = new Des(); my.run(); }
	 * 
	 * public void run() { // ����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
	 * 
	 * Security.addProvider(new com.sun.crypto.provider.SunJCE()); String key =
	 * "07075be55b37e5cda11afdbf37e901349b83f4f78cec1a70"; //String myinfo =
	 * "Ҫ���ܵ���Ϣ"; String myinfo = "12453535464646"; try { byte[] keyBytes =
	 * Hex.decodeHex(key.toCharArray()); String Algorithm =
	 * "DESede/CBC/PKCS5Padding"; // ���� �����㷨,���� // DES,DESede,Blowfish // ������Կ
	 * //KeyGenerator keygen = KeyGenerator.getInstance(Algorithm); //SecretKey
	 * deskey = keygen.generateKey(); // ���� System.out.println("����ǰ�Ķ�����:" +
	 * byte2hex(myinfo.getBytes())); System.out.println("����ǰ����Ϣ:" + myinfo);
	 * Cipher c1 = Cipher.getInstance(Algorithm); SecretKeySpec dks = new
	 * SecretKeySpec(keyBytes, "DESede"); IvParameterSpec ips = new
	 * IvParameterSpec(IV); c1.init(Cipher.ENCRYPT_MODE, dks, ips);
	 * 
	 * byte[] cipherByte = c1.doFinal(myinfo.getBytes());
	 * System.out.println("���ܺ�Ķ�����:" + byte2hex(cipherByte)); // ���� c1 =
	 * Cipher.getInstance(Algorithm); DESKeySpec dks1 = new
	 * DESKeySpec(key.getBytes()); SecretKeyFactory keyFactory =
	 * SecretKeyFactory.getInstance("DES"); SecretKey securekey =
	 * keyFactory.generateSecret(dks1);
	 * 
	 * c1.init(Cipher.ENCRYPT_MODE, securekey, ips); byte[] clearByte =
	 * c1.doFinal(cipherByte); System.out.println("���ܺ�Ķ�����:" +
	 * byte2hex(clearByte)); System.out.println("���ܺ����Ϣ:" + (new
	 * String(clearByte))); } catch (java.security.NoSuchAlgorithmException e1) {
	 * e1.printStackTrace(); } catch (javax.crypto.NoSuchPaddingException e2) {
	 * e2.printStackTrace(); } catch (java.lang.Exception e3) {
	 * e3.printStackTrace(); } }
	 * 
	 * 
	 * public String byte2hex(byte[] b) // ������ת�ַ��� { String hs = ""; String stmp =
	 * ""; for (int n = 0; n < b.length; n++) { stmp =
	 * (java.lang.Integer.toHexString(b[n] & 0XFF)); if (stmp.length() == 1) hs =
	 * hs + "0" + stmp; else hs = hs + stmp; if (n < b.length - 1) hs = hs +
	 * ":"; } return hs.toUpperCase(); }
	 * 
	 * public static String decryptHex(String src, String key) throws Exception {
	 * if (src == null || key == null) { throw new NullPointerException( "source
	 * or key string isn't nullable"); } String result = null; try { byte[]
	 * keyBytes = Hex.decodeHex(key.toCharArray()); byte[] srcBytes =
	 * Hex.decodeHex(src.toCharArray()); SecretKeySpec dks = new
	 * SecretKeySpec(keyBytes, "DESede"); IvParameterSpec ips = new
	 * IvParameterSpec(IV); Cipher cipher =
	 * Cipher.getInstance("DESede/CBC/PKCS5Padding");
	 * cipher.init(Cipher.DECRYPT_MODE, dks, ips); result = new
	 * String(cipher.doFinal(srcBytes), "UTF-8"); } catch (IOException ex) {
	 * throw new Exception(ex); } catch (DecoderException ex) { throw new
	 * Exception(ex); } return result; }
	 */
}
