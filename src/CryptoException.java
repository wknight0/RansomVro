public class CryptoException extends Exception {
    public CryptoException() {
    }
    //Used to throw exceptions relevant to encryption/decryption status
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
