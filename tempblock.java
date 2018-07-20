//block.java
package core;


import util.Util;


public class Block {


  private int blockID;
  private String previousBlockHash;
  private int nonce;
  private String data;


  public int getBlockID() {
    return blockID;
  }
  public void setBlockID(int blockID) {
    this.blockID = blockID;
  }
  public String getPreviousBlockHash() {
    return previousBlockHash;
  }
  public void setPreviousBlockHash(String previousBlockHash) {
    this.previousBlockHash = previousBlockHash;
  }
  public int getNonce() {
    return nonce;
  }
  public void setNonce(int nonce) {
    this.nonce = nonce;
  }
  public String getData() {
    return data;
  }
  public void setData(String data) {
    this.data = data;
  }
  public Block(int blockID, String previousBlockHash, int nonce, String data) {
    this.blockID = blockID;
    this.previousBlockHash = previousBlockHash;
    this.nonce = nonce;
    this.data = data;
  }
  public void getInformation() {
    System.out.println("--------------------------------------");
    System.out.println("블록 번호: " + getBlockID());
    System.out.println("이전 해시: " + getPreviousBlockHash());
    System.out.println("채굴 변수 값: " + getNonce());
    System.out.println("블록 데이터: " + getData());
    System.out.println("블록 해시: " + getBlockHash());
    System.out.println("--------------------------------------");
  }
  public String getBlockHash() {
    return Util.getHash(nonce + data + previousBlockHash);
  }
  public void mine() {
    while(true) {
      if(getBlockHash().substring(0, 4).equals("0000")) {
        System.out.println(blockID + "번째 블록의 채굴에 성공했습니다.");
        break;
      }
      nonce++;
    }
  }
}
﻿
﻿
//blockchainstarter.java
package core;


import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;


import org.bouncycastle.jce.provider.BouncyCastleProvider;


import util.EC;


public class BlockChainStarter {
 
  public static void main(String[] args) throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    EC ec = new EC();
    
    // 총 두 쌍의 키를 생성해 파일 형태로 저장합니다.
    ec.generate("private1.pem", "public1.pem");
    ec.generate("private2.pem", "public2.pem");
 
    // 파일 형태로 저장한 키 데이터를 프로그램으로 불러옵니다.
    PrivateKey privateKey1 = ec.readPrivateKeyFromPemFile("private1.pem");
    PublicKey publicKey1 = ec.readPublicKeyFromPemFile("public1.pem");
    PrivateKey privateKey2 = ec.readPrivateKeyFromPemFile("private2.pem");
    PublicKey publicKey2 = ec.readPublicKeyFromPemFile("public2.pem");
 
    Signature ecdsa;
    ecdsa = Signature.getInstance("SHA1withECDSA");
    // 개인키 1을 이용해 암호화(서명)합니다.
    ecdsa.initSign(privateKey1);


    String text = "평문입니다.";
    System.out.println("평문 정보: " + text);
    byte[] baText = text.getBytes("UTF-8");


    // 평문 데이터를 암호화하여 서명한 데이터를 출력합니다.
    ecdsa.update(baText);
    byte[] baSignature = ecdsa.sign();
    System.out.println("서명된 값: 0x" + (new BigInteger(1, baSignature).toString(16)).toUpperCase());


    Signature signature;
    signature = Signature.getInstance("SHA1withECDSA");
    
    // 검증할 때는 공개키 2를 이용해 복호화를 수행합니다.
    signature.initVerify(publicKey2);
    signature.update(baText);
    boolean result = signature.verify(baSignature);
    
    // 개인키와 매칭되는 공개키가 아니므로 복호화에 실패합니다.
    System.out.println("신원 검증: " + result);
  }
}
﻿
//EC.java
package util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import org.bouncycastle.util.encoders.Base64;


public class EC {


  // 세부 알고리즘으로 sect163k1을 사용합니다.
  private final String ALGORITHM = "sect163k1";
  
  public void generate(String privateKeyName, String publicKeyName) throws Exception {
    // 바운시 캐슬의 타원 곡선 표준 알고리즘(ECDSA)을 사용합니다.
    KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA", "BC");
    
    // 타원 곡선의 세부 알고리즘으로 sect163k1을 사용합니다.
    ECGenParameterSpec ecsp;
    ecsp = new ECGenParameterSpec(ALGORITHM);
    generator.initialize(ecsp, new SecureRandom());
    
    // 해당 알고리즘으로 랜덤의 키 한 쌍을 생성합니다.
    KeyPair keyPair = generator.generateKeyPair();
    System.out.println("타원곡선 암호키 한 쌍을 생성했습니다.");
    
    // 생성한 키 한 쌍에서 개인키와 공개키를 추출합니다.
    PrivateKey priv = keyPair.getPrivate();
    PublicKey pub = keyPair.getPublic();
    
    // 개인키와 공개키를 특정한 파일 이름으로 저장합니다.
    writePemFile(priv, "EC PRIVATE KEY", privateKeyName);
    writePemFile(pub, "EC PUBLIC KEY", publicKeyName);
  }
  
  // Pem 클래스를 이용해 생성된 암호키를 파일로 저장하는 함수입니다.
  private void writePemFile(Key key, String description, String filename)
      throws FileNotFoundException, IOException {
    Pem pemFile = new Pem(key, description);
    pemFile.write(filename);
    System.out.println(String.format("EC 암호키 %s을(를) %s 파일로 내보냈습니다.", description, filename));
  }
  
  // 문자열 형태의 인증서에서 개인키를 추출하는 함수입니다.
  public PrivateKey readPrivateKeyFromPemFile(String privateKeyName)
      throws FileNotFoundException, IOException, NoSuchAlgorithmException, 
      InvalidKeySpecException {
    String data = readString(privateKeyName);
    System.out.println("EC 개인키를 " + privateKeyName + "로부터 불러왔습니다.");
    System.out.print(data);


    // 불필요한 설명 구문을 제거합니다.
    data = data.replaceAll("-----BEGIN EC PRIVATE KEY-----", "");
    data = data.replaceAll("-----END EC PRIVATE KEY-----", "");


    // PEM 파일은 Base64로 인코딩 되어있으므로 디코딩해서 읽을 수 있도록 합니다.
    byte[] decoded = Base64.decode(data);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
    KeyFactory factory = KeyFactory.getInstance("ECDSA");
    PrivateKey privateKey = factory.generatePrivate(spec);
    return privateKey;
  }


  // 문자열 형태의 인증서에서 공개키를 추출하는 함수입니다.
  public PublicKey readPublicKeyFromPemFile(String publicKeyName)
      throws FileNotFoundException, IOException, NoSuchAlgorithmException, 
      InvalidKeySpecException {
    String data = readString(publicKeyName);
    System.out.println("EC 개인키를 " + publicKeyName + "로부터 불러왔습니다.");
    System.out.print(data);


    // 불필요한 설명 구문을 제거합니다.
    data = data.replaceAll("-----BEGIN EC PUBLIC KEY-----", "");
    data = data.replaceAll("-----END EC PUBLIC KEY-----", "");


    // PEM 파일은 Base64로 인코딩 되어있으므로 디코딩해서 읽을 수 있도록 합니다.
    byte[] decoded = Base64.decode(data);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    KeyFactory factory = KeyFactory.getInstance("ECDSA");
    PublicKey publicKey = factory.generatePublic(spec);
    return publicKey;
  }


  // 특정한 파일에 작성되어 있는 문자열을 그대로 읽어오는 함수입니다.
  private String readString(String filename) throws FileNotFoundException, IOException {
    String pem = "";
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = br.readLine()) != null)
      pem += line + "\n";
    br.close();
    return pem;
  }  
}
//util.java
package util;
import java.security.MessageDigest;
public class Util {
 public static String getHash(String input) {
   StringBuffer result = new StringBuffer();
      try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());
        byte bytes[] = md.digest();
        for(int i = 0; i < bytes.length; i++) {
          result.append(
     Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)
          );
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return result.toString();
    }
 
}﻿
