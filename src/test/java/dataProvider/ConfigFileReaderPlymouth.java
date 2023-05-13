package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReaderPlymouth {
	
	private Properties propertiesPlymouth;
	private final String propertyFilePath = "configs//ConfigurationPlymouth.properties";
	public ConfigFileReaderPlymouth() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			propertiesPlymouth = new Properties();
			try {
				propertiesPlymouth.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			  }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("propertiesPlymouth.properties not found at " + propertyFilePath);
		}
	}
	
	//Global Get Functions
			public String getBrowserId() {
				String browserId = propertiesPlymouth.getProperty("browserId");
				if(browserId != null) return browserId;
				else throw new RuntimeException("browserId not specified in the Configuration.properties file.");
			}
			
			public long getImplicitlyWait() { 
				String ImplicitlyWait = propertiesPlymouth.getProperty("ImplicitlyWait");
				if(ImplicitlyWait != null) return Long.parseLong(ImplicitlyWait);
				else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
			}
		
			public String getApplicationUrl() {
				String applicationUrl = propertiesPlymouth.getProperty("applicationUrl");
				if(applicationUrl != null) return applicationUrl;
				else throw new RuntimeException("applicationUrl not specified in the Configuration.properties file.");
			}
			
			public long getSleepTime() { 
				String sleepTime = propertiesPlymouth.getProperty("sleepTime");
				if(sleepTime != null) return Long.parseLong(sleepTime);
				else throw new RuntimeException("sleepTime not specified in the Configuration.properties file."); 
			}
			
	//Login local functions
	
			public String getLoginTitleText() {
				String loginTitleText = propertiesPlymouth.getProperty("loginTitleText");
				if(loginTitleText != null) return loginTitleText;
				else throw new RuntimeException("loginTitleText not specified in the Configuration.properties file.");
			}
			
			public String getFieldRequiredText() {
				String fieldRequiredText = propertiesPlymouth.getProperty("fieldRequiredText");
				if(fieldRequiredText != null) return fieldRequiredText;
				else throw new RuntimeException("fieldRequiredText not specified in the Configuration.properties file.");
			}
			
			public String getWrongUserText() {
				String wrongUserText = propertiesPlymouth.getProperty("wrongUserText");
				if(wrongUserText != null) return wrongUserText;
				else throw new RuntimeException("wrongUserText not specified in the Configuration.properties file.");
			}
			
			public String getWrongPassText() {
				String wrongPassText = propertiesPlymouth.getProperty("wrongPassText");
				if(wrongPassText != null) return wrongPassText;
				else throw new RuntimeException("wrongPassText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectUserPassMsgText() {
				String incorrectUserPassMsgText = propertiesPlymouth.getProperty("incorrectUserPassMsgText");
				if(incorrectUserPassMsgText != null) return incorrectUserPassMsgText;
				else throw new RuntimeException("incorrectUserPassMsgText not specified in the Configuration.properties file.");
			}
			
			public String getCorrectUserText() {
				String correctUserText = propertiesPlymouth.getProperty("correctUserText");
				if(correctUserText != null) return correctUserText;
				else throw new RuntimeException("correctUserText not specified in the Configuration.properties file.");
			}
			
			public String getCorrectPassText() {
				String correctPassText = propertiesPlymouth.getProperty("correctPassText");
				if(correctPassText != null) return correctPassText;
				else throw new RuntimeException("correctPassText not specified in the Configuration.properties file.");
			}
			
			public String getLogoutText() {
				String logoutText = propertiesPlymouth.getProperty("logoutText");
				if(logoutText != null) return logoutText;
				else throw new RuntimeException("logoutText not specified in the Configuration.properties file.");
			}
			
			public String getBadFormatedMailText() {
				String badFormatedMailText = propertiesPlymouth.getProperty("badFormatedMailText");
				if(badFormatedMailText != null) return badFormatedMailText;
				else throw new RuntimeException("badFormatedMailText not specified in the Configuration.properties file.");
			}
			
			public String getNotValidMailText() {
				String notValidMailText = propertiesPlymouth.getProperty("notValidMailText");
				if(notValidMailText != null) return notValidMailText;
				else throw new RuntimeException("notValidMailText not specified in the Configuration.properties file.");
			}
			
			public String getNotExistedMailText() {
				String notExistedMailText = propertiesPlymouth.getProperty("notExistedMailText");
				if(notExistedMailText != null) return notExistedMailText;
				else throw new RuntimeException("notExistedMailText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectMailText() {
				String incorrectMailText = propertiesPlymouth.getProperty("incorrectMailText");
				if(incorrectMailText != null) return incorrectMailText;
				else throw new RuntimeException("incorrectMailText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectMailMsgText() {
				String incorrectMailMsgText = propertiesPlymouth.getProperty("incorrectMailMsgText");
				if(incorrectMailMsgText != null) return incorrectMailMsgText;
				else throw new RuntimeException("incorrectMailMsgText not specified in the Configuration.properties file.");
			}
		
			public String getGoodMail1() {
				String goodMail1 = propertiesPlymouth.getProperty("goodMail1");
				if(goodMail1 != null) return goodMail1;
				else throw new RuntimeException("goodMail1 not specified in the Configuration.properties file.");
			}
			
			public String getGoodMail2() {
				String goodMail2 = propertiesPlymouth.getProperty("goodMail2");
				if(goodMail2 != null) return goodMail2;
				else throw new RuntimeException("goodMail2 not specified in the Configuration.properties file.");
			}
			
			public String getNotMatchedMailText() {
				String notMatchedMailText = propertiesPlymouth.getProperty("notMatchedMailText");
				if(notMatchedMailText != null) return notMatchedMailText;
				else throw new RuntimeException("notMatchedMailText not specified in the Configuration.properties file.");
			}
			
			public String getShortPass() {
				String shortPass = propertiesPlymouth.getProperty("shortPass");
				if(shortPass != null) return shortPass;
				else throw new RuntimeException("shortPass not specified in the Configuration.properties file.");
			}
			
			public String getShortPassMsg() {
				String shortPassMsg = propertiesPlymouth.getProperty("shortPassMsg");
				if(shortPassMsg != null) return shortPassMsg;
				else throw new RuntimeException("shortPassMsg not specified in the Configuration.properties file.");
			}
			
			public String getPassNotMatchedMsg() {
				String passNotMatchedMsg = propertiesPlymouth.getProperty("passNotMatchedMsg");
				if(passNotMatchedMsg != null) return passNotMatchedMsg;
				else throw new RuntimeException("passNotMatchedMsg not specified in the Configuration.properties file.");
			}
			
			public String getExistedUserMsg() {
				String existedUserMsg = propertiesPlymouth.getProperty("existedUserMsg");
				if(existedUserMsg != null) return existedUserMsg;
				else throw new RuntimeException("existedUserMsg not specified in the Configuration.properties file.");
			}
			
			public String getAutoMailExtText() {
				String autoMailExtText = propertiesPlymouth.getProperty("autoMailExtText");
				if(autoMailExtText != null) return autoMailExtText;
				else throw new RuntimeException("autoMailExtText not specified in the Configuration.properties file.");
			}
			
			public String getUserFB() {
				String userFB = propertiesPlymouth.getProperty("userFB");
				if(userFB != null) return userFB;
				else throw new RuntimeException("userFB not specified in the Configuration.properties file.");
			}
			
			public String getPasswordFB() {
				String passwordFB = propertiesPlymouth.getProperty("passwordFB");
				if(passwordFB != null) return passwordFB;
				else throw new RuntimeException("passwordFB not specified in the Configuration.properties file.");
			}
			
	////////New properties
			
				public String getFieldCheckBoxText() {
					String fieldRequiredCheckBox = propertiesPlymouth.getProperty("fieldRequiredCheckBox");
					if (fieldRequiredCheckBox != null)
						return fieldRequiredCheckBox;
					else
						throw new RuntimeException("fieldRequiredCheckBox not specified in the Configuration.properties file.");
				}
			
				public String getFirstName() {
					String firstName = propertiesPlymouth.getProperty("firstName");
					if (firstName != null)
						return firstName;
					else
						throw new RuntimeException("firstName not specified in the Configuration.properties file.");
				}
			
				public String getLastName() {
					String lastName = propertiesPlymouth.getProperty("lastName");
					if (lastName != null)
						return lastName;
					else
						throw new RuntimeException("lastName not specified in the Configuration.properties file.");
				}
			
				public String getCorrectDate() {
					String correctDate = propertiesPlymouth.getProperty("correctDate");
					if (correctDate != null)
						return correctDate;
					else
						throw new RuntimeException("correctDate not specified in the Configuration.properties file.");
				}
			
				public String getFutureDate() {
					String futureDate = propertiesPlymouth.getProperty("futureDate");
					if (futureDate != null)
						return futureDate;
					else
						throw new RuntimeException("futureDate not specified in the Configuration.properties file.");
				}
			
				public String getDateNotValid() {
					String dateNotValid = propertiesPlymouth.getProperty("dateNotValid");
					if (dateNotValid != null)
						return dateNotValid;
					else
						throw new RuntimeException("dateNotValid not specified in the Configuration.properties file.");
				}
				
}