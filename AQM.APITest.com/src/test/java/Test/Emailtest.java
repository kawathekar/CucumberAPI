package Test;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.Properties;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipOutputStream;

	import javax.activation.DataHandler;
	import javax.activation.FileDataSource;
	import javax.mail.BodyPart;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;

	import org.dom4j.DocumentException;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	public class Emailtest implements ITestListener {
		
	String fileName = "Execution_Result.zip";

	@Override
	public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
	// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
	try {
	File fin = new File(fileName);
	if (fin.exists())
	fin.delete();
	String file1= System.getProperty("user.dir") + "\\Execution_Result\\ExecutionResultsSummarycucumber\\"+"";
//		String file1 = "D:\\Execution_Result"+"";
	pack(file1, fileName);
	String to[] = { "abhishek.kawathekar@aqmtechnologies.com","faizan.khan@aqmtechnologies.com" };
	String cc[] = { "sagar.dhande@aqmtechnologies.com" };
	try {
	send("abhishek.kawathekar@aqmtechnologies.com", to, "Cucumber Report", "Check the zip attachment.", cc);
	} catch (MessagingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (DocumentException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}

	public void send(String from, String tos[], String subject, String text, String ccs[])
	throws MessagingException, IOException, DocumentException {
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.rediffmailpro.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication("abhishek.kawathekar@aqmtechnologies.com", "Nive@2401");
	}
	});
	try {
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(from));// change accordingly
	for (String to : tos) {
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	}
	for (String cc : ccs) {
	message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
	}
	message.setSubject(subject);
	BodyPart objMessageBodyPart = new MimeBodyPart();
	// Option 3: Send text along with attachment
	objMessageBodyPart.setContent("Mail from Selenium Project!" + text, "text/html");
	Multipart multipart = new MimeMultipart();
	multipart.addBodyPart(objMessageBodyPart);
	objMessageBodyPart = new MimeBodyPart();
	FileDataSource source = new FileDataSource(fileName);
	objMessageBodyPart.setDataHandler(new DataHandler(source));
	objMessageBodyPart.setFileName(fileName);
	multipart.addBodyPart(objMessageBodyPart);
	message.setContent(multipart);
	Transport.send(message);
	System.out.println("message sent successfully");
	} catch (MessagingException e) {
	throw new RuntimeException(e);
	}
	}

	public void pack(String sourceDirPath, String zipFilePath) throws IOException {
	Path p = Files.createFile(Paths.get(zipFilePath));
	try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
	Path pp = Paths.get(sourceDirPath);
	Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
	ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
	try {
	zs.putNextEntry(zipEntry);
	Files.copy(path, zs);
	zs.closeEntry();
	} catch (IOException e) {
	System.err.println(e);
	}
	});
	zs.flush();
	zs.close();
	}
	}

	}
