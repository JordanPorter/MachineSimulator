
public class Instruction {

	private int ID;
	
	private int DESTINATION;
	private int SOURCE_1;
	private int SOURCE_2;
	private String OP;
	
	public Instruction(int id, String s)	{
		setID(id);
		parseInstruction(s);
	}
	
	public void parseInstruction(String s)	{
		try {
			String[] str = s.split("\\D+");
			setDESTINATION(Integer.parseInt(str[0]));
			setSOURCE_1(Integer.parseInt(str[1]));
			setSOURCE_2(Integer.parseInt(str[2]));
			if(s.contains("*"))	{
				setOP("m");
			}
			else if(s.contains("+")){
				setOP("a");
			}
			else 	{
				throw new NullPointerException();
			}
		} catch(NullPointerException ex)	{
			System.err.println("Instruction Format Error");
		}
	}

	public String toString()	{
		return DESTINATION + "<-" + OP + "(" + SOURCE_1 + "," + SOURCE_2 + ")"; 
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
	 * @return the oP
	 */
	public String getOP() {
		return OP;
	}

	/**
	 * @param oP the oP to set
	 */
	public void setOP(String oP) {
		OP = oP;
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
}
