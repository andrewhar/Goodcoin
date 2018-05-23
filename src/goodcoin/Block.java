package goodcoin;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	

	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0','0');
		System.out.println("target:" + target);
		while(!hash.substring(0, difficulty).equals(target)) {
			System.out.println(nonce + "hash.substring:" + hash.substring(0, difficulty));
			nonce ++;
			hash = calculateHash();
			System.out.println("hash is" + hash);
		}
		System.out.println("Block Mined!!! its hash is: " + hash);
		
		
		
	}
	
	
}