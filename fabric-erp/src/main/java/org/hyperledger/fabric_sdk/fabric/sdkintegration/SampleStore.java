/*
 *  Copyright 2016, 2017 DTCC, Fujitsu Australia Software Technology, IBM - All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.hyperledger.fabric_sdk.fabric.sdkintegration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.Enrollment;

/**
 * 基于本地文件的密钥值存储
 * 
 * A local file-based key value store.
 */
public class SampleStore {

	private String file;
	private Log logger = LogFactory.getLog(SampleStore.class);

	public SampleStore(File file) {

		this.file = file.getAbsolutePath();
	}

	/**
	 * Get the value associated with name.
	 *
	 * @param name
	 * @return value associated with the name
	 */
	public String getValue(String name) {
		Properties properties = loadProperties();
		return properties.getProperty(name);
	}

	private Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream input = new FileInputStream(file)) {
			properties.load(input);
			input.close();
		} catch (FileNotFoundException e) {
			logger.warn(String.format("Could not find the file \"%s\"", file));
		} catch (IOException e) {
			logger.warn(String.format("Could not load keyvalue store from file \"%s\", reason:%s", file, e.getMessage()));
		}

		return properties;
	}

	/**
	 * Set the value associated with name.
	 *
	 * @param name
	 *            The name of the parameter
	 * @param value
	 *            Value for the parameter
	 */
	public void setValue(String name, String value) {
		Properties properties = loadProperties();
		try (OutputStream output = new FileOutputStream(file)) {
			properties.setProperty(name, value);
			properties.store(output, "");
			output.close();

		} catch (IOException e) {
			logger.warn(String.format("Could not save the keyvalue store, reason:%s", e.getMessage()));
		}
	}

	private final Map<String, SampleUser> members = new HashMap<>();

	/**
	 * Get the user with a given name
	 * 
	 * @param name
	 * @param org
	 * @return user
	 */
	public SampleUser getMember(String name, String org) {

		// Try to get the SampleUser state from the cache
		SampleUser sampleUser = members.get(SampleUser.toKeyValStoreName(name, org));
		if (null != sampleUser) {
			return sampleUser;
		}

		// Create the SampleUser and try to restore it's state from the key
		// value store (if found).
		sampleUser = new SampleUser(name, org, this);

		return sampleUser;

	}

	/**
	 * Get the user with a given name
	 * 
	 * @param name
	 * @param org
	 * @param mspId
	 * @param privateKeyFile
	 * @param certificateFile
	 * @return user
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	public SampleUser getMember(String name, String org, String mspId, File privateKeyFile, File certificateFile) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		try {
			// Try to get the SampleUser state from the cache
			SampleUser sampleUser = members.get(SampleUser.toKeyValStoreName(name, org));
			if (null != sampleUser) {
				return sampleUser;
			}

			// Create the SampleUser and try to restore it's state from the key
			// value store (if found).
			sampleUser = new SampleUser(name, org, this);
			sampleUser.setMspId(mspId);

			String certificate = new String(IOUtils.toByteArray(new FileInputStream(certificateFile)), "UTF-8");
			// String certificate = new String(IOUtils.toByteArray(new
			// FileInputStream(new
			// File("src\\test\\fixture\\sdkintegration\\e2e-2Orgs\\channel\\crypto-config\\peerOrganizations\\org1.example.com\\users\\Admin@org1.example.com\\msp\\signcerts\\Admin@example.com-cert.pem"))),
			// "UTF-8");
			// String certificate="-----BEGIN CERTIFICATE-----\n" +
			// "MIICCTCCAbCgAwIBAgIQY3gmcII386QCK7pUCcYc/DAKBggqhkjOPQQDAjBpMQsw\n"
			// +
			// "CQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNU2FuIEZy\n"
			// +
			// "YW5jaXNjbzEUMBIGA1UEChMLZXhhbXBsZS5jb20xFzAVBgNVBAMTDmNhLmV4YW1w\n"
			// +
			// "bGUuY29tMB4XDTE3MDYyMjEyMDg0MloXDTI3MDYyMDEyMDg0MlowVjELMAkGA1UE\n"
			// +
			// "BhMCVVMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcTDVNhbiBGcmFuY2lz\n"
			// +
			// "Y28xGjAYBgNVBAMMEUFkbWluQGV4YW1wbGUuY29tMFkwEwYHKoZIzj0CAQYIKoZI\n"
			// +
			// "zj0DAQcDQgAE+DMXipPmoQV9vMisD26YjciJuIVkklwttku2lPmDlHpM/SNyBwqt\n"
			// +
			// "o8bojtQlzzK3VmvhGWA7rDpaaYue7bUj/aNNMEswDgYDVR0PAQH/BAQDAgeAMAwG\n"
			// +
			// "A1UdEwEB/wQCMAAwKwYDVR0jBCQwIoAg5XU5jq8vDUdbqJ9WjEn1cBOvk4wVd1bA\n"
			// +
			// "ZUuU5ZJAuP4wCgYIKoZIzj0EAwIDRwAwRAIgWhUq9jdFWEOZSrWKXtJUPUAxJKSx\n"
			// +
			// "p+jrzHg8fU89u6UCIGTOGPOk2c3YM8H8KAPE26OzHqjSobPYyICtaMY34I5m\n"
			// +
			// "-----END CERTIFICATE-----";
			PrivateKey privateKey = getPrivateKeyFromBytes(IOUtils.toByteArray(new FileInputStream(privateKeyFile)));
			// PrivateKey privateKey = getPrivateKeyFromBytes();

			sampleUser.setEnrollment(new SampleStoreEnrollement(privateKey, certificate));

			sampleUser.saveState();

			return sampleUser;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassCastException e) {
			e.printStackTrace();
			throw e;
		}

	}

	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	static PrivateKey getPrivateKeyFromBytes(byte[] data) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
		// final Reader pemReader = new StringReader(
		// "-----BEGIN PRIVATE KEY-----\n" +
		// "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgsJTX+YkYEm8Q9Dhu\n"
		// +
		// "BKVa25ubFSMt3Hu7ZKZrQEL8D7+hRANCAAT4MxeKk+ahBX28yKwPbpiNyIm4hWSS\n"
		// +
		// "XC22S7aU+YOUekz9I3IHCq2jxuiO1CXPMrdWa+EZYDusOlppi57ttSP9\n" +
		// "-----END PRIVATE KEY-----");
		final Reader pemReader = new StringReader(new String(data));

		final PrivateKeyInfo pemPair;
		try (PEMParser pemParser = new PEMParser(pemReader)) {
			pemPair = (PrivateKeyInfo) pemParser.readObject();
		}

		PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getPrivateKey(pemPair);

		return privateKey;
	}
	static PrivateKey getPrivateKeyFromBytes() throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
		final Reader pemReader = new StringReader("-----BEGIN PRIVATE KEY-----\n" + "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgsJTX+YkYEm8Q9Dhu\n" + "BKVa25ubFSMt3Hu7ZKZrQEL8D7+hRANCAAT4MxeKk+ahBX28yKwPbpiNyIm4hWSS\n" + "XC22S7aU+YOUekz9I3IHCq2jxuiO1CXPMrdWa+EZYDusOlppi57ttSP9\n" + "-----END PRIVATE KEY-----");
		// final Reader pemReader = new StringReader(new String(data));

		final PrivateKeyInfo pemPair;
		try (PEMParser pemParser = new PEMParser(pemReader)) {
			pemPair = (PrivateKeyInfo) pemParser.readObject();
		}

		PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getPrivateKey(pemPair);

		return privateKey;
	}

	static final class SampleStoreEnrollement implements Enrollment, Serializable {

		private static final long serialVersionUID = -2784835212445309006L;
		private final PrivateKey privateKey;
		private final String certificate;

		SampleStoreEnrollement(PrivateKey privateKey, String certificate) {

			this.certificate = certificate;

			this.privateKey = privateKey;
		}

		@Override
		public PrivateKey getKey() {

			return privateKey;
		}

		@Override
		public String getCert() {
			return certificate;
		}

	}

}