package sp.utils.encdec;

import java.util.Base64;

public class Base64Util {
        public static void main(String[] args) throws Exception {
            CaseByte();
            //CaseString();
        }

        public static void CaseByte() throws Exception
        {
            String TestString = "Base64 test";
            byte[] TestBytes = TestString.getBytes("UTF-8");

            System.out.println(new String(TestBytes));

            // Base64 인코딩 ///////////////////////////////////////////////////
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode(TestBytes);

            System.out.println(new String(encodedBytes));

            // Base64 디코딩 ///////////////////////////////////////////////////
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(encodedBytes);

            System.out.println(new String(decodedBytes, "UTF-8"));
        }

        public static void CaseString() throws Exception
        {
            String TestString = "Base64 test";

            System.out.println(TestString);

            // Base64 인코딩 ///////////////////////////////////////////////////
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedString = encoder.encodeToString(TestString.getBytes("UTF-8"));

            System.out.println(encodedString);

            // Base64 디코딩 ///////////////////////////////////////////////////
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(encodedString);
            String decodedString = new String(decodedBytes, "UTF-8");

            System.out.println(decodedString);
        }

}
