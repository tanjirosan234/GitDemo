package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReaderWTI {
	
	private Properties propertiesWTI;
	private final String propertyFilePath = "configs//ConfigurationWTI.properties";
	public ConfigFileReaderWTI() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			propertiesWTI = new Properties();
			try {
				propertiesWTI.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			  }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("propertiesWTI.properties not found at " + propertyFilePath);
		}
	}
	
	//Global Get Functions
			public String getBrowserId() {
				String browserId = propertiesWTI.getProperty("browserId");
				if(browserId != null) return browserId;
				else throw new RuntimeException("browserId not specified in the Configuration.properties file.");
			}
			
			public long getImplicitlyWait() { 
				String ImplicitlyWait = propertiesWTI.getProperty("ImplicitlyWait");
				if(ImplicitlyWait != null) return Long.parseLong(ImplicitlyWait);
				else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
			}
		
			public String getApplicationUrl() {
				String applicationUrl = propertiesWTI.getProperty("applicationUrl");
				if(applicationUrl != null) return applicationUrl;
				else throw new RuntimeException("applicationUrl not specified in the Configuration.properties file.");
			}
			
			public long getSleepTime() { 
				String sleepTime = propertiesWTI.getProperty("sleepTime");
				if(sleepTime != null) return Long.parseLong(sleepTime);
				else throw new RuntimeException("sleepTime not specified in the Configuration.properties file."); 
			}
			
	//Login local functions
	
			public String getLoginTitleText() {
				String loginTitleText = propertiesWTI.getProperty("loginTitleText");
				if(loginTitleText != null) return loginTitleText;
				else throw new RuntimeException("loginTitleText not specified in the Configuration.properties file.");
			}
			
			public String getFieldRequiredText() {
				String fieldRequiredText = propertiesWTI.getProperty("fieldRequiredText");
				if(fieldRequiredText != null) return fieldRequiredText;
				else throw new RuntimeException("fieldRequiredText not specified in the Configuration.properties file.");
			}
			
			public String getWrongUserText() {
				String wrongUserText = propertiesWTI.getProperty("wrongUserText");
				if(wrongUserText != null) return wrongUserText;
				else throw new RuntimeException("wrongUserText not specified in the Configuration.properties file.");
			}
			
			public String getWrongPassText() {
				String wrongPassText = propertiesWTI.getProperty("wrongPassText");
				if(wrongPassText != null) return wrongPassText;
				else throw new RuntimeException("wrongPassText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectUserPassMsgText() {
				String incorrectUserPassMsgText = propertiesWTI.getProperty("incorrectUserPassMsgText");
				if(incorrectUserPassMsgText != null) return incorrectUserPassMsgText;
				else throw new RuntimeException("incorrectUserPassMsgText not specified in the Configuration.properties file.");
			}
			
			public String getCorrectUserText() {
				String correctUserText = propertiesWTI.getProperty("correctUserText");
				if(correctUserText != null) return correctUserText;
				else throw new RuntimeException("correctUserText not specified in the Configuration.properties file.");
			}
			
			public String getCorrectPassText() {
				String correctPassText = propertiesWTI.getProperty("correctPassText");
				if(correctPassText != null) return correctPassText;
				else throw new RuntimeException("correctPassText not specified in the Configuration.properties file.");
			}
			
			public String getLogoutText() {
				String logoutText = propertiesWTI.getProperty("logoutText");
				if(logoutText != null) return logoutText;
				else throw new RuntimeException("logoutText not specified in the Configuration.properties file.");
			}
			
			public String getBadFormatedMailText() {
				String badFormatedMailText = propertiesWTI.getProperty("badFormatedMailText");
				if(badFormatedMailText != null) return badFormatedMailText;
				else throw new RuntimeException("badFormatedMailText not specified in the Configuration.properties file.");
			}
			
			public String getNotValidMailText() {
				String notValidMailText = propertiesWTI.getProperty("notValidMailText");
				if(notValidMailText != null) return notValidMailText;
				else throw new RuntimeException("notValidMailText not specified in the Configuration.properties file.");
			}
			
			public String getNotExistedMailText() {
				String notExistedMailText = propertiesWTI.getProperty("notExistedMailText");
				if(notExistedMailText != null) return notExistedMailText;
				else throw new RuntimeException("notExistedMailText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectMailText() {
				String incorrectMailText = propertiesWTI.getProperty("incorrectMailText");
				if(incorrectMailText != null) return incorrectMailText;
				else throw new RuntimeException("incorrectMailText not specified in the Configuration.properties file.");
			}
			
			public String getIncorrectMailMsgText() {
				String incorrectMailMsgText = propertiesWTI.getProperty("incorrectMailMsgText");
				if(incorrectMailMsgText != null) return incorrectMailMsgText;
				else throw new RuntimeException("incorrectMailMsgText not specified in the Configuration.properties file.");
			}
		
			public String getGoodMail1() {
				String goodMail1 = propertiesWTI.getProperty("goodMail1");
				if(goodMail1 != null) return goodMail1;
				else throw new RuntimeException("goodMail1 not specified in the Configuration.properties file.");
			}
			
			public String getGoodMail2() {
				String goodMail2 = propertiesWTI.getProperty("goodMail2");
				if(goodMail2 != null) return goodMail2;
				else throw new RuntimeException("goodMail2 not specified in the Configuration.properties file.");
			}
			
			public String getNotMatchedMailText() {
				String notMatchedMailText = propertiesWTI.getProperty("notMatchedMailText");
				if(notMatchedMailText != null) return notMatchedMailText;
				else throw new RuntimeException("notMatchedMailText not specified in the Configuration.properties file.");
			}
			
			public String getShortPass() {
				String shortPass = propertiesWTI.getProperty("shortPass");
				if(shortPass != null) return shortPass;
				else throw new RuntimeException("shortPass not specified in the Configuration.properties file.");
			}
			
			public String getShortPassMsg() {
				String shortPassMsg = propertiesWTI.getProperty("shortPassMsg");
				if(shortPassMsg != null) return shortPassMsg;
				else throw new RuntimeException("shortPassMsg not specified in the Configuration.properties file.");
			}
			
			public String getPassNotMatchedMsg() {
				String passNotMatchedMsg = propertiesWTI.getProperty("passNotMatchedMsg");
				if(passNotMatchedMsg != null) return passNotMatchedMsg;
				else throw new RuntimeException("passNotMatchedMsg not specified in the Configuration.properties file.");
			}
			
			public String getExistedUserMsg() {
				String existedUserMsg = propertiesWTI.getProperty("existedUserMsg");
				if(existedUserMsg != null) return existedUserMsg;
				else throw new RuntimeException("existedUserMsg not specified in the Configuration.properties file.");
			}
			
			public String getAutoMailExtText() {
				String autoMailExtText = propertiesWTI.getProperty("autoMailExtText");
				if(autoMailExtText != null) return autoMailExtText;
				else throw new RuntimeException("autoMailExtText not specified in the Configuration.properties file.");
			}
			
			public String getUserFB() {
				String userFB = propertiesWTI.getProperty("userFB");
				if(userFB != null) return userFB;
				else throw new RuntimeException("userFB not specified in the Configuration.properties file.");
			}
			
			public String getPasswordFB() {
				String passwordFB = propertiesWTI.getProperty("passwordFB");
				if(passwordFB != null) return passwordFB;
				else throw new RuntimeException("passwordFB not specified in the Configuration.properties file.");
			}
			
	////////New properties
			
				public String getFieldCheckBoxText() {
					String fieldRequiredCheckBox = propertiesWTI.getProperty("fieldRequiredCheckBox");
					if (fieldRequiredCheckBox != null)
						return fieldRequiredCheckBox;
					else
						throw new RuntimeException("fieldRequiredCheckBox not specified in the Configuration.properties file.");
				}
			
				public String getFirstName() {
					String firstName = propertiesWTI.getProperty("firstName");
					if (firstName != null)
						return firstName;
					else
						throw new RuntimeException("firstName not specified in the Configuration.properties file.");
				}
			
				public String getLastName() {
					String lastName = propertiesWTI.getProperty("lastName");
					if (lastName != null)
						return lastName;
					else
						throw new RuntimeException("lastName not specified in the Configuration.properties file.");
				}
			
				public String getCorrectDate() {
					String correctDate = propertiesWTI.getProperty("correctDate");
					if (correctDate != null)
						return correctDate;
					else
						throw new RuntimeException("correctDate not specified in the Configuration.properties file.");
				}
			
				public String getFutureDate() {
					String futureDate = propertiesWTI.getProperty("futureDate");
					if (futureDate != null)
						return futureDate;
					else
						throw new RuntimeException("futureDate not specified in the Configuration.properties file.");
				}
			
				public String getDateNotValid() {
					String dateNotValid = propertiesWTI.getProperty("dateNotValid");
					if (dateNotValid != null)
						return dateNotValid;
					else
						throw new RuntimeException("dateNotValid not specified in the Configuration.properties file.");
				}
				
}