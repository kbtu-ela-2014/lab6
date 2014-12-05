import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Oper  implements Serializable{
	double lastNumber;
	double numberInDisplay;
	String lastOperator;
	Oper(double nid, double ln, String opera){
		lastNumber = ln;
		numberInDisplay = nid;
		lastOperator = opera;
	}
	
	public byte[] getBytes() {
        byte[]bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            oos.reset();
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch(IOException e){
            bytes = new byte[] {};
            Logger.getLogger("bsdlog").log(Level.ALL, "unable to write to output stream" + e);
        }
        return bytes;
    }
	
	public static Oper fromBytes(byte[] body) {
		Oper obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = (Oper) ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
