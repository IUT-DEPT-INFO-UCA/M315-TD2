package fr.uca.iut.info.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Based on http://www.jmdoudoux.fr/java/dej/chap-serialisation.htm
 * 
 * @author Mireille Blay
 *
 */

public class Memory {

	static Logger monLog = Logger.getLogger(Memory.class.getName());
	
	private Memory() {
		super();
	}

	
    /**
     * @param Object to save in a file, it must be serializable
     * @param file Name where to write the "object"
     * @throws IOException
     */
    public  static void save (Object o, String fileName) throws IOException{
        ObjectOutputStream oos = null;
        try (FileOutputStream fichier = new FileOutputStream(fileName)){
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(o);
            oos.flush();
        } 
    }

	/**
     * @param fileName
     * @return an object corresponding to the serialization written in the file.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public  static Object read (String fileName) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        Object o = null;
        File f = new File(fileName);
        if(!(f.isFile()))
        { 
         return null;
        }
        try (FileInputStream fichier = new FileInputStream(fileName)){
            ois = new ObjectInputStream(fichier);
            o = ois.readObject();
        } 
        return o;
     }
}
