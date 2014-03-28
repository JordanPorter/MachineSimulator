
public class Register {

	private int ADDRESS;
	private int CONTENTS;
	
	public Register(int address, int contents)	{
		ADDRESS = address;
		CONTENTS = contents;
	}
	
	public void load(int i)	{
		CONTENTS = i;
	}
	
	public int getAddress()	{
		return ADDRESS;
	}
	
	public int get()	{
		return CONTENTS;
	}
	
	public String toString()	{
		return "Address: " + ADDRESS + "\n"
			 + "Contents: " + CONTENTS + "\n";
	}
}
