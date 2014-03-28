import java.util.ArrayList;


public class DUnit {

	private int SOURCE_1;
	private int SOURCE_2;
	private int DESTINATION;
	public int MULTIPLICATION_TIME;
	public int ADDITION_TIME;
	private int STEP;
	private int ID;
	private int INST_ID;
	private Register[] registers;
	
	private Instruction I = null;
	
	private ArrayList<Instruction> instructions;
	private ArrayList<ArrayList<String>> rows;
	
	private String currentOperation;
	
	public DUnit(int id, int instId, int mt, int at, String op, Register[] registers, ArrayList<Instruction> instructions, ArrayList<ArrayList<String>> rows) {
		MULTIPLICATION_TIME = mt;
		ADDITION_TIME = at;
		setID(id);
		setINST_ID(instId);
		STEP = 0;
		currentOperation = op;
		this.registers = registers;
		this.instructions = instructions;
		this.rows = rows;
	}
	
	public boolean tick()	{
		STEP++;
		if(currentOperation == null)	{
			STEP = 0;
			return true;
		}
		if(currentOperation.equalsIgnoreCase("m"))	{
			if(STEP == this.MULTIPLICATION_TIME)	{
				registers[DESTINATION].load(registers[SOURCE_1].get()*registers[SOURCE_2].get()); 
				I = null;
				instructions.set(INST_ID, null);
				STEP = 0;
				return true;
			}
		}
		if(currentOperation.equalsIgnoreCase("a"))	{
			if(STEP == this.ADDITION_TIME)	{
				registers[DESTINATION].load(registers[SOURCE_1].get()+registers[SOURCE_2].get());
				I = null;
				instructions.set(INST_ID, null);
				STEP = 0;
				return true;
			}
		}
		return false;
	}
	
	public boolean setOperation(String op)	{
		if(currentOperation == null)	{
			currentOperation = op;
			return true;
		}
		return false;
	}
	
	public String toString()	{
		return "Current Operation: " + currentOperation + "\n"
			 + "Multiplication Duration: " + MULTIPLICATION_TIME + "step(s)\n"
			 + "Addition Duration: " + ADDITION_TIME + "step(s)\n";
	}

	public boolean setInstruction(Instruction i)	{
		if(I == null)	{
			setOperation(i.getOP());
			setINST_ID(i.getID());
			setDESTINATION(i.getDESTINATION());
			setSOURCE_1(i.getSOURCE_1());
			setSOURCE_2(i.getSOURCE_2());
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * @return the sOURCE_1
	 */
	public int getSOURCE_1() {
		return SOURCE_1;
	}

	/**
	 * @param sOURCE_1 the sOURCE_1 to set
	 */
	public void setSOURCE_1(int sOURCE_1) {
		SOURCE_1 = sOURCE_1;
	}

	/**
	 * @return the sOURCE_2
	 */
	public int getSOURCE_2() {
		return SOURCE_2;
	}

	/**
	 * @param sOURCE_2 the sOURCE_2 to set
	 */
	public void setSOURCE_2(int sOURCE_2) {
		SOURCE_2 = sOURCE_2;
	}

	/**
	 * @return the dESTINATION
	 */
	public int getDESTINATION() {
		return DESTINATION;
	}

	/**
	 * @param dESTINATION the dESTINATION to set
	 */
	public void setDESTINATION(int dESTINATION) {
		DESTINATION = dESTINATION;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the iNST_ID
	 */
	public int getINST_ID() {
		return INST_ID;
	}

	/**
	 * @param iNST_ID the iNST_ID to set
	 */
	public void setINST_ID(int iNST_ID) {
		INST_ID = iNST_ID;
		if(INST_ID != -1)
		rows.get(INST_ID).add("s" +  INST_ID + "|");
	}
}
