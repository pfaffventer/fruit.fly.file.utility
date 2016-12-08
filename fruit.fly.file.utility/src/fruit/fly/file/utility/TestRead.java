package fruit.fly.file.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class TestRead {

	public TestRead() throws Exception {

		File file = null;
		file = pick();
		if(file==null) return;

		File[] files = file.getParentFile().listFiles();
		for(int i=0;i<files.length;i++) {
			readFile(files[i]);
		}

	}

	private void readFile(File f) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader(f));;
		String line;
		while ((line=r.readLine())!=null) {
			if(line.startsWith("BH")||line.startsWith("BT")) {
				continue;
			}
			readLine(line,f.getName());
		}
		r.close();
	}

	private void readLine(String line,String file) throws Exception {
		
		
		System.out.println(":) " + file + " :: " + line);;
		
		
		
	}

	private File pick() throws Exception {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:/load"));
		int r = fc.showOpenDialog(new JFrame());
        if (r==JFileChooser.APPROVE_OPTION) {
        	return fc.getSelectedFile();
        }
        return null;
	}

	public static void main(String[] args) {
		try {
			new TestRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}
