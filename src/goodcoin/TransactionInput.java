package goodcoin;

public class TransactionInput {
	public String transactionOutputId;
	public TransactionOutput UtXO;
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}

}
