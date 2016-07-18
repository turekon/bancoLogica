package co.edu.usbcali.demo.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SqsOperaciones {
	private static final Logger log = LoggerFactory.getLogger(SqsOperaciones.class);
	
	private String qurl="https://sqs.us-west-2.amazonaws.com/821283354285/colaReportesTransaccionesBanco";
	
	private AWSCredentials credentials = null;
	
	private AmazonSQS sqs = null;
	
	public SqsOperaciones() {
	}

	public void enviarMensajeALaCola(String message) throws Exception {
		credentials = null;
        try {
//            credentials = new ProfileCredentialsProvider("default").getCredentials();
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/Users/actual_user/.aws/credentials), and is in valid format.",
                    e);
        }
        
		try {
			sqs = new AmazonSQSClient(credentials);
			
			sqs.sendMessage(new SendMessageRequest(qurl, message));
			
			sqs.shutdown();
		} catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon SQS, but was rejected with an error response for some reason.");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with SQS, such as not " +
                    "being able to access the network.");
            log.info("Error Message: " + ace.getMessage());
        }
	}
}
