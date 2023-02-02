import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class SettingsReader {
	
	static void browseSaveFile() {
		 // JFileChooser-Objekt erstellen
        JFileChooser chooser = new JFileChooser();
        // Dialog zum Oeffnen von Dateien anzeigen
        int rueckgabeWert = chooser.showOpenDialog(null);
        
        /* Abfrage, ob auf "Öffnen" geklickt wurde */
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
             // Ausgabe der ausgewaehlten Datei
            System.out.println("Die zu öffnende Datei ist: " +
                  chooser.getSelectedFile().getName());
        }
        FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(person);
      objectOutputStream.flush();
      objectOutputStream.close();
      
      FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Person p2 = (Person) objectInputStream.readObject();
      objectInputStream.close(); 
	}
	
	
}
