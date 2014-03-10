import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MachineSimulator {

	private File input;
	DUnit[] dUnits;
	Register[] registers;
	
	public MachineSimulator(String file)	{
		try {
			this.input = new File(file);
			String inputs = null;
			Scanner sc = new Scanner(this.input);
			if(sc.hasNextLine())
				inputs = sc.nextLine();
			String[] s = inputs.split(",");
			if(s.length == 2)	{
				dUnits = new DUnit[Integer.parseInt(s[0])];
				registers = new Register[Integer.parseInt(s[1])];
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void main(String[] args) {

	}

}
