import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MachineSimulator {

	private File input;
	
	private DUnit[] dUnits;
	private Register[] registers;
	private ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	
	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
	
	private int MULTIPLICATION_TIME;
	private int ADDITION_TIME;
	
	
	public MachineSimulator(String file)	{
		try {
			this.input = new File(file);
			String inputs = null;
			Scanner sc = new Scanner(this.input);
			if(sc.hasNextLine())
				inputs = sc.nextLine();
			String[] s = inputs.split(",");
			machineSetup(s);
			System.out.println(this);
			loadProgram(sc);
			runProgram();
			sc.close();
			for(ArrayList<String> al: rows)	{
				for(String str : al)	{
					System.out.print(str);
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public void machineSetup(String[] params)	{
		if(params.length == 4)	{
			dUnits = new DUnit[Integer.parseInt(params[0])];
			registers = new Register[Integer.parseInt(params[1])];
			MULTIPLICATION_TIME = Integer.parseInt(params[2]);
			ADDITION_TIME = Integer.parseInt(params[3]);
		}
		else	{
			System.out.println("Input Parameters Error: Format Incorrect.\n"
							+ "Please Use <# of DUnits>, <# of Registers>, <Duration of Muliplication, <Duration of Addition>");
		}
		for(int i=0; i<dUnits.length; i++)	{
			dUnits[i] = new DUnit(i, -1, MULTIPLICATION_TIME, ADDITION_TIME, null, registers, instructions, rows);
		}
		for(int j=0; j<registers.length; j++)	{
			registers[j] = new Register(j,j*100);
		}
	}
	
	
	public void loadProgram(Scanner sc)	{
		int index = 0;
		while(sc.hasNextLine())	{
			Instruction i = new Instruction(index++, sc.nextLine());
			for(Instruction inst : instructions)	{
				if(inst != null)	{
					while(i.getDESTINATION() == inst.getDESTINATION())	{
						System.out.println("Conflict Detected For S" + i.getID());
						if(instructions.get(i.getDESTINATION()+1) == null)	{
							System.out.println("Renaming... " + i.getDESTINATION()+1);
							i.setDESTINATION(i.getDESTINATION()+1);
						}
					}
				}
			}
			instructions.add(i.getID(), i);
			rows.add(new ArrayList<String>());
		}
	}
	
	
	public void runProgram()	{
		int current = 0;
		boolean instructionAccepted = true;
		boolean dUnitsIdle = false;
		while(true)	{
			dUnitsIdle = true;
			for(DUnit d : dUnits)	{
				if(!d.tick())	{
					dUnitsIdle = false;
				}
			}
			instructionAccepted = false;
			for(Instruction I : instructions)	{
				for(DUnit du : dUnits)	{
					if(du.setInstruction(I))	{
					}
				}
			}
		}
	}
	
	
	public String toString()	{
		return "Machine Settings: \n"
			 + "Number Of DUnits: " + dUnits.length + "\n"
			 + "Number Of Registers: " + registers.length + "\n";
	}
	
	public static void main(String[] args) {
		new MachineSimulator("MachineSource.txt");
	}

}
