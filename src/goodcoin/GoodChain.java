package goodcoin;

import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import com.google.gson.GsonBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class GoodChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 	
	public static int difficulty = 2;
	public static Wallet walletA;
	public static Wallet walletB;
	
	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		
		walletA = new Wallet();
		walletB = new Wallet();
		
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		
		System.out.println("Is signature verified");
		System.out.println(transaction.verifySignature());
		
//		//add our blocks to the blockchain ArrayList:
//		blockchain.add(new Block("Hi im the first block", "0"));
//		System.out.println("Trying to Mine block 1... ");
//		blockchain.get(0).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash)); 
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
//		blockchain.get(2).mineBlock(difficulty);
//		
//		System.out.println("\nBlockchain is Valid: " + isChainValid());
//		
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
//		System.out.println(blockchainJson);
	
	}
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	

	
	
	
}