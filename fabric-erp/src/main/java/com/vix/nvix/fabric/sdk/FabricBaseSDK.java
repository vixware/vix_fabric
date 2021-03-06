package com.vix.nvix.fabric.sdk;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hyperledger.fabric.sdk.BlockInfo.EnvelopeType.TRANSACTION_ENVELOPE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.binary.Hex;
import org.hyperledger.fabric.protos.ledger.rwset.kvrwset.KvRwset;
import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.ChaincodeEndorsementPolicy;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.ChannelConfiguration;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.EventHub;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.InstallProposalRequest;
import org.hyperledger.fabric.sdk.InstantiateProposalRequest;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.SDKUtils;
import org.hyperledger.fabric.sdk.TxReadWriteSetInfo;
import org.hyperledger.fabric.sdk.exception.ChaincodeEndorsementPolicyParseException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.InvalidProtocolBufferRuntimeException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionEventException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.RegistrationException;
import org.hyperledger.fabric_sdk.fabric.sdk.TestConfigHelper;
import org.hyperledger.fabric_sdk.fabric.sdk.testutils.TestConfig;
import org.hyperledger.fabric_sdk.fabric.sdkintegration.SampleOrg;
import org.hyperledger.fabric_sdk.fabric.sdkintegration.SampleStore;
import org.hyperledger.fabric_sdk.fabric.sdkintegration.SampleUser;
import org.hyperledger.fabric_sdk.fabric.sdkintegration.Util;

/**
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.fabric.sdk.FabricAddSDK
 *
 * @date 2018年4月20日
 */
public class FabricBaseSDK {

	private static final TestConfig testConfig = TestConfig.getConfig();
	// private static final String TEST_ADMIN_NAME = "admin";
	// private static final String TESTUSER_1_NAME = "user_3355";
	// private static final String TEST_FIXTURES_PATH = "D:\\work\\workspace\\fabric\\fabric-erp\\src\\test\\fixture";
	private static final String TEST_FIXTURES_PATH = "C:\\work\\fabric-erp\\src\\test\\fixture";

	private static final String CHAIN_CODE_NAME = "example1";
	private static final String CHAIN_CODE_PATH = "github.com/example_test";
	private static final String CHAIN_CODE_VERSION = "1";

	private static final String FOO_CHANNEL_NAME = "foo";
	// private static final String BAR_CHANNEL_NAME = "bar";

	String testTxID = null; // save the CC invoke TxID and use in queries

	private final TestConfigHelper configHelper = new TestConfigHelper();

	private Collection<SampleOrg> testSampleOrgs;

	public HFClient client;
	public Channel fooChannel;
	public Channel barChannel;
	public SampleOrg sampleOrg1;
	// public SampleOrg sampleOrg2;
	public void clearConfig() {
		try {
			configHelper.clearConfig();
		} catch (Exception e) {
		}
	}

	// public void setup(String adminName, String userName, String key, String value) {
	public void setup(String adminName, String userName) {
		try {

			out("\n\n\nRUNNING: End2endIT.\n");
			configHelper.clearConfig();
			configHelper.customizeConfig();
			testSampleOrgs = testConfig.getIntegrationTestsSampleOrgs();
			// Set up hfca for each sample org
			for (SampleOrg sampleOrg : testSampleOrgs) {
				sampleOrg.setCAClient(HFCAClient.createNewInstance(sampleOrg.getCALocation(), sampleOrg.getCAProperties()));
			}

			// Setup client
			// Create instance of client.
			// HFClient client = HFClient.createNewInstance();
			client = HFClient.createNewInstance();
			client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
			// client.setMemberServices(peerOrg1FabricCA);
			// Set up USERS
			// Persistence is not part of SDK. Sample file store is for demonstration purposes only!
			// MUST be replaced with more robust application implementation (Database, LDAP)
			File sampleStoreFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
			if (sampleStoreFile.exists()) { // For testing start fresh
				sampleStoreFile.delete();
			}

			final SampleStore sampleStore = new SampleStore(sampleStoreFile);
			// sampleStoreFile.deleteOnExit();
			// SampleUser can be any implementation that implements
			// org.hyperledger.fabric.sdk.User Interface
			// get users for all orgs
			for (SampleOrg sampleOrg : testSampleOrgs) {

				getUsersForAllOrgs(adminName, userName, sampleStore, sampleOrg);
			}

			// Construct and run the channels
			// sampleOrg1 = testConfig.getIntegrationTestsSampleOrg("peerOrg1");
			// fooChannel = constructChannel(FOO_CHANNEL_NAME, client, sampleOrg1);

			// runChannel(client, fooChannel, true, sampleOrg1, 0, adminName, userName, key, value);

			// fooChannel.shutdown(true);
			// Force foo channel to shutdown clean up resources.
			// out("\n");

			// sampleOrg2 = testConfig.getIntegrationTestsSampleOrg("peerOrg2");
			// barChannel = constructChannel(BAR_CHANNEL_NAME, client, sampleOrg2);

			// runChannel(client, barChannel, true, sampleOrg2, 100, adminName, userName, key, value);
			// run a newly constructed bar channel with different b value! let bar channel just shutdown so we have both scenarios.

			// out("\nTraverse the blocks for chain %s ", barChannel.getName());
			// 查询通道信息
			// blockWalker(barChannel, key, value);
			// out("That's all folks!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户,组织信息
	 * 
	 * @param adminName
	 * @param userName
	 * @param sampleStore
	 * @param sampleOrg
	 * @throws EnrollmentException
	 * @throws InvalidArgumentException
	 * @throws Exception
	 * @throws RegistrationException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeySpecException
	 */
	private void getUsersForAllOrgs(String adminName, String userName, final SampleStore sampleStore, SampleOrg sampleOrg) throws EnrollmentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, Exception, RegistrationException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		HFCAClient ca = sampleOrg.getCAClient();
		final String orgName = sampleOrg.getName();
		final String mspid = sampleOrg.getMSPID();
		ca.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
		SampleUser admin = sampleStore.getMember(adminName, orgName);
		System.out.println("开始打印用户信息!");
		if (!admin.isEnrolled()) {
			// Preregistered admin only needs to be enrolled with Fabric caClient.
			Enrollment enrollment = ca.enroll(admin.getName(), "adminpw");
			// System.out.println(enrollment.getCert());
			admin.setEnrollment(enrollment);
			admin.setMspId(mspid);
		}

		sampleOrg.setAdmin(admin); // The admin of this org --
		// 获取用户信息
		SampleUser user = sampleStore.getMember(userName, sampleOrg.getName());
		if (!user.isRegistered()) {
			// users need to be registered AND enrolled
			RegistrationRequest rr = new RegistrationRequest(user.getName(), "org1.department1");
			String secret = ca.register(rr, admin);
			System.out.println(secret);
			user.setEnrollmentSecret(secret);
		}
		if (!user.isEnrolled()) {
			Enrollment enrollment = ca.enroll(user.getName(), user.getEnrollmentSecret());
			System.out.println(enrollment.getCert());
			user.setEnrollment(enrollment);
			user.setMspId(mspid);
		}
		System.out.println("结束打印用户信息!");
		sampleOrg.addUser(user);
		// Remember user belongs to this Org

		final String sampleOrgName = sampleOrg.getName();
		final String sampleOrgDomainName = sampleOrg.getDomainName();

		// src/test/fixture/sdkintegration/e2e-2Orgs/channel/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/

		SampleUser peerOrgAdmin = sampleStore.getMember(sampleOrgName + "Admin", sampleOrgName, sampleOrg.getMSPID(), Util.findFileSk(Paths.get(testConfig.getTestChannelPath(), "crypto-config/peerOrganizations/", sampleOrgDomainName, format("/users/Admin@%s/msp/keystore", sampleOrgDomainName)).toFile()), Paths.get(testConfig.getTestChannelPath(), "crypto-config/peerOrganizations/", sampleOrgDomainName, format("/users/Admin@%s/msp/signcerts/Admin@%s-cert.pem", sampleOrgDomainName, sampleOrgDomainName)).toFile());

		sampleOrg.setPeerAdmin(peerOrgAdmin);
		// A special user that can create channels, join peers and install chaincode
	}

	// CHECKSTYLE.OFF: Method length is 320 lines (max allowed is 150).
	public void runChannel(HFClient client, Channel channel, ChaincodeID chaincodeID, boolean installChaincode, SampleOrg sampleOrg, String TEST_ADMIN_NAME, String TESTUSER_1_NAME, String key, String value, String fun) {
		try {
			final String channelName = channel.getName();
			boolean isFooChain = FOO_CHANNEL_NAME.equals(channelName);
			out("Running channel %s", channelName);
			channel.setTransactionWaitTime(testConfig.getTransactionWaitTime());
			channel.setDeployWaitTime(testConfig.getDeployWaitTime());
			// Collection<Peer> channelPeers = channel.getPeers();
			Collection<Orderer> orderers = channel.getOrderers();

			Collection<ProposalResponse> successful = new LinkedList<>();
			Collection<ProposalResponse> failed = new LinkedList<>();

			if (installChaincode) {
				// 安装链码
				installChainCode(client, sampleOrg, isFooChain, chaincodeID, successful, failed);
			}

			// client.setUserContext(sampleOrg.getUser(TEST_ADMIN_NAME));
			// final ChaincodeID chaincodeID = firstInstallProposalResponse.getChaincodeID();
			// Note installing chaincode does not require transaction no need to send to Orderers
			if ("save".equals(fun)) {
				// 实例化链码
				out("我要保存数据了", "");
				initChainCode(client, channel, key, value, isFooChain, chaincodeID, successful, failed);
				out("数据保存完成", "");
			} else if ("query".equals(fun)) {
				out("我要查询数据了", "");
				// Send instantiate transaction to orderer
				channel.sendTransaction(successful, orderers).thenApply(transactionEvent -> {
					try {

						out("Finished transaction with transaction id %s", transactionEvent.getTransactionID());
						testTxID = transactionEvent.getTransactionID();
						// used in the channel queries later

						out("Now query chaincode for the value of " + key + ".");
						queryByChaincode(client, channel, chaincodeID, key);

						return null;
					} catch (Exception e) {
						out("Caught exception while running query");
						e.printStackTrace();
					}

					return null;
				}).exceptionally(e -> {
					if (e instanceof TransactionEventException) {
						BlockEvent.TransactionEvent te = ((TransactionEventException) e).getTransactionEvent();
						if (te != null) {
						}
					}
					return null;
				}).get(testConfig.getTransactionWaitTime(), TimeUnit.SECONDS);
				// Channel queries

				// We can only send channel queries to peers that are in the same
				// org as the SDK user context
				// Get the peers from the current org being used and pick one
				// randomly to send the queries to.
				// Set<Peer> peerSet = sampleOrg.getPeers();
				// Peer queryPeer = peerSet.iterator().next();
				// out("Using peer %s for channel queries", queryPeer.getName());

				// BlockchainInfo channelInfo = channel.queryBlockchainInfo();
				// out("Channel info for : " + channelName);
				// out("Channel height: " + channelInfo.getHeight());
				// String chainCurrentHash = Hex.encodeHexString(channelInfo.getCurrentBlockHash());
				// String chainPreviousHash = Hex.encodeHexString(channelInfo.getPreviousBlockHash());
				// out("Chain current block hash: " + chainCurrentHash);
				// out("Chainl previous block hash: " + chainPreviousHash);
				//
				// // Query by block number. Should return latest block, i.e. block number 2
				// BlockInfo returnedBlock = channel.queryBlockByNumber(channelInfo.getHeight() - 1);
				// String previousHash = Hex.encodeHexString(returnedBlock.getPreviousHash());
				// out("queryBlockByNumber returned correct block with blockNumber " + returnedBlock.getBlockNumber() + " \n previous_hash " + previousHash);
				//
				// // Query by block hash. Using latest block's previous hash so should
				// // return block number 1
				// byte[] hashQuery = returnedBlock.getPreviousHash();
				// returnedBlock = channel.queryBlockByHash(hashQuery);
				// out("queryBlockByHash returned block with blockNumber " + returnedBlock.getBlockNumber());
				//
				// // Query block by TxID. Since it's the last TxID, should be block 2
				// returnedBlock = channel.queryBlockByTransactionID(testTxID);
				// out("queryBlockByTxID returned block with blockNumber " + returnedBlock.getBlockNumber());
				//
				// // query transaction by ID
				// TransactionInfo txInfo = channel.queryTransactionByID(testTxID);
				// out("QueryTransactionByID returned TransactionInfo: txID " + txInfo.getTransactionID() + "\n validation code " + txInfo.getValidationCode().getNumber());
				//
				// out("Running for Channel %s done", channelName);
				out("数据查询结束", "");
			} else if ("delete".equals(fun)) {

			}
		} catch (Exception e) {
			out("Caught an exception running channel %s", channel.getName());
			e.printStackTrace();
		}
	}
	// CHECKSTYLE.ON: Method length is 320 lines (max allowed is 150).

	/**
	 * 安装指定名称,路径,版本的链码到指定的节点中
	 * 
	 * @param client
	 * @param sampleOrg
	 * @param isFooChain
	 * @param chaincodeID
	 * @param successful
	 * @param failed
	 * @throws InvalidArgumentException
	 * @throws IOException
	 * @throws ProposalException
	 */
	private void installChainCode(HFClient client, SampleOrg sampleOrg, boolean isFooChain, final ChaincodeID chaincodeID, Collection<ProposalResponse> successful, Collection<ProposalResponse> failed) throws InvalidArgumentException, IOException, ProposalException {
		Collection<ProposalResponse> responses;
		// Install Proposal Request

		client.setUserContext(sampleOrg.getPeerAdmin());

		out("Creating install proposal");

		InstallProposalRequest installProposalRequest = client.newInstallProposalRequest();
		installProposalRequest.setChaincodeID(chaincodeID);

		if (isFooChain) {
			// on foo chain install from directory. For GO language and serving just a single user,chaincodeSource is mostly likely the users GOPATH
			installProposalRequest.setChaincodeSourceLocation(new File(TEST_FIXTURES_PATH + "/sdkintegration/gocc/sample1"));
		} else {
			// On bar chain install from an input stream.
			installProposalRequest.setChaincodeInputStream(Util.generateTarGzInputStream((Paths.get(TEST_FIXTURES_PATH, "/sdkintegration/gocc/sample1", "src", CHAIN_CODE_PATH).toFile()), Paths.get("src", CHAIN_CODE_PATH).toString()));
		}

		installProposalRequest.setChaincodeVersion(CHAIN_CODE_VERSION);

		out("Sending install proposal");

		// only a client from the same org as the peer can issue an install request
		int numInstallProposal = 0;
		// Set<String> orgs = orgPeers.keySet(); for (SampleOrg org : testSampleOrgs) {

		Set<Peer> peersFromOrg = sampleOrg.getPeers();
		numInstallProposal = numInstallProposal + peersFromOrg.size();
		responses = client.sendInstallProposal(installProposalRequest, peersFromOrg);

		for (ProposalResponse response : responses) {
			if (response.getStatus() == ProposalResponse.Status.SUCCESS) {
				out("Successful install proposal response Txid: %s from peer %s", response.getTransactionID(), response.getPeer().getName());
				successful.add(response);
			} else {
				failed.add(response);
			}
		}

		SDKUtils.getProposalConsistencySets(responses);
		// }
		out("Received %d install proposal responses. Successful+verified: %d . Failed: %d", numInstallProposal, successful.size(), failed.size());

	}

	/**
	 * 链码的实例化
	 * 
	 * @param client
	 * @param channel
	 * @param delta
	 * @param key
	 * @param value
	 * @param isFooChain
	 * @param chaincodeID
	 * @param successful
	 * @param failed
	 * @throws InvalidArgumentException
	 * @throws IOException
	 * @throws ChaincodeEndorsementPolicyParseException
	 * @throws ProposalException
	 * @throws TimeoutException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private void initChainCode(HFClient client, Channel channel, String key, String value, boolean isFooChain, final ChaincodeID chaincodeID, Collection<ProposalResponse> successful, Collection<ProposalResponse> failed) throws InvalidArgumentException, IOException, ChaincodeEndorsementPolicyParseException, ProposalException, InterruptedException, ExecutionException, TimeoutException {
		Collection<ProposalResponse> responses;
		// Instantiate chaincode.
		InstantiateProposalRequest instantiateProposalRequest = client.newInstantiationProposalRequest();
		instantiateProposalRequest.setProposalWaitTime(testConfig.getProposalWaitTime());
		instantiateProposalRequest.setChaincodeID(chaincodeID);
		instantiateProposalRequest.setFcn("init");
		instantiateProposalRequest.setArgs(new String[]{key, value});
		Map<String, byte[]> tm = new HashMap<>();
		tm.put("HyperLedgerFabric", "InstantiateProposalRequest:JavaSDK".getBytes(UTF_8));
		tm.put("method", "InstantiateProposalRequest".getBytes(UTF_8));
		instantiateProposalRequest.setTransientMap(tm);

		/*
		 * policy OR(Org1MSP.member, Org2MSP.member) meaning 1 signature from someone in either Org1 or Org2 See README.md Chaincode endorsement policies section for more details.
		 */
		ChaincodeEndorsementPolicy chaincodeEndorsementPolicy = new ChaincodeEndorsementPolicy();
		chaincodeEndorsementPolicy.fromYamlFile(new File(TEST_FIXTURES_PATH + "/sdkintegration/chaincodeendorsementpolicy.yaml"));
		instantiateProposalRequest.setChaincodeEndorsementPolicy(chaincodeEndorsementPolicy);

		out("Sending instantiateProposalRequest to all peers", "");
		successful.clear();
		failed.clear();

		if (isFooChain) {
			// Send responses both ways with specifying peers
			// and by using those on the channel.
			responses = channel.sendInstantiationProposal(instantiateProposalRequest, channel.getPeers());
		} else {
			responses = channel.sendInstantiationProposal(instantiateProposalRequest);

		}
		for (ProposalResponse response : responses) {
			if (response.isVerified() && response.getStatus() == ProposalResponse.Status.SUCCESS) {
				successful.add(response);
				out("Succesful instantiate proposal response Txid: %s from peer %s", response.getTransactionID(), response.getPeer().getName());
			} else {
				failed.add(response);
			}
		}
		out("Received %d instantiate proposal responses. Successful+verified: %d . Failed: %d", responses.size(), successful.size(), failed.size());

		channel.sendTransaction(successful, channel.getOrderers()).thenApply(transactionEvent -> {
			try {
				out("Finished transaction with transaction id %s", transactionEvent.getTransactionID());
				testTxID = transactionEvent.getTransactionID();
				// used in the channel queries later

				out("Now query chaincode for the value of " + key + ".");
				queryByChaincode(client, channel, chaincodeID, key);

				return null;
			} catch (Exception e) {
				out("Caught exception while running query");
				e.printStackTrace();
			}
			return null;
		}).exceptionally(e -> {
			if (e instanceof TransactionEventException) {
				BlockEvent.TransactionEvent te = ((TransactionEventException) e).getTransactionEvent();
				if (te != null) {
				}
			}
			return null;
		}).get(testConfig.getTransactionWaitTime(), TimeUnit.SECONDS);
	}
	/**
	 * 创建Channel
	 * 
	 * @param name
	 * @param client
	 * @param sampleOrg
	 * @return
	 * @throws Exception
	 */
	public Channel constructChannel(String name, HFClient client, SampleOrg sampleOrg) throws Exception {
		// Construct the channel
		out("Constructing channel %s", name);
		// Only peer Admin org
		client.setUserContext(sampleOrg.getPeerAdmin());
		Collection<Orderer> orderers = new LinkedList<>();
		for (String orderName : sampleOrg.getOrdererNames()) {

			Properties ordererProperties = testConfig.getOrdererProperties(orderName);
			// example of setting keepAlive to avoid timeouts on inactive http2
			// connections.
			// Under 5 minutes would require changes to server side to accept
			// faster ping rates.
			ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
			ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit.SECONDS});

			orderers.add(client.newOrderer(orderName, sampleOrg.getOrdererLocation(orderName), ordererProperties));
		}

		// Just pick the first orderer in the list to create the channel.

		Orderer anOrderer = orderers.iterator().next();
		orderers.remove(anOrderer);

		ChannelConfiguration channelConfiguration = new ChannelConfiguration(new File(TEST_FIXTURES_PATH + "/sdkintegration/e2e-2Orgs/channel/" + name + ".tx"));

		// Create channel that has only one signer that is this orgs peer admin.
		// If channel creation policy needed more signature they would need to
		// be added too.
		Channel newChannel = client.newChannel(name, anOrderer, channelConfiguration, client.getChannelConfigurationSignature(channelConfiguration, sampleOrg.getPeerAdmin()));

		out("Created channel %s", name);

		for (String peerName : sampleOrg.getPeerNames()) {
			String peerLocation = sampleOrg.getPeerLocation(peerName);

			Properties peerProperties = testConfig.getPeerProperties(peerName);
			// test properties for peer.. if any.
			if (peerProperties == null) {
				peerProperties = new Properties();
			}
			// Example of setting specific options on grpc's NettyChannelBuilder
			peerProperties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);

			Peer peer = client.newPeer(peerName, peerLocation, peerProperties);
			newChannel.joinPeer(peer);
			out("Peer %s joined channel %s", peerName, name);
			sampleOrg.addPeer(peer);
		}

		for (Orderer orderer : orderers) { // add remaining orderers if any.
			newChannel.addOrderer(orderer);
		}

		for (String eventHubName : sampleOrg.getEventHubNames()) {

			final Properties eventHubProperties = testConfig.getEventHubProperties(eventHubName);

			eventHubProperties.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
			eventHubProperties.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit.SECONDS});

			EventHub eventHub = client.newEventHub(eventHubName, sampleOrg.getEventHubLocation(eventHubName), eventHubProperties);
			newChannel.addEventHub(eventHub);
		}

		newChannel.initialize();

		out("Finished initialization channel %s", name);

		return newChannel;

	}

	static void out(String format, Object... args) {

		System.err.flush();
		System.out.flush();

		System.out.println(format(format, args));
		System.err.flush();
		System.out.flush();

	}

	private static final Map<String, String> TX_EXPECTED;

	static {
		TX_EXPECTED = new HashMap<>();
		TX_EXPECTED.put("readset1", "Missing readset for channel bar block 1");
		TX_EXPECTED.put("writeset1", "Missing writeset for channel bar block 1");
	}
	// 通过指定节点查询通道
	void blockWalker(Channel channel, String arg0, String arg1) throws InvalidArgumentException, ProposalException, IOException {
		try {
			BlockchainInfo channelInfo = channel.queryBlockchainInfo();

			for (long current = channelInfo.getHeight() - 1; current > -1; --current) {
				BlockInfo returnedBlock = channel.queryBlockByNumber(current);
				final long blockNumber = returnedBlock.getBlockNumber();

				out("current block number %d has data hash: %s", blockNumber, Hex.encodeHexString(returnedBlock.getDataHash()));
				out("current block number %d has previous hash id: %s", blockNumber, Hex.encodeHexString(returnedBlock.getPreviousHash()));
				out("current block number %d has calculated block hash is %s", blockNumber, Hex.encodeHexString(SDKUtils.calculateBlockHash(blockNumber, returnedBlock.getPreviousHash(), returnedBlock.getDataHash())));

				// assertEquals(1, envelopeCount);
				out("current block number %d has %d envelope count:", blockNumber, returnedBlock.getEnvelopeCount());
				int i = 0;
				for (BlockInfo.EnvelopeInfo envelopeInfo : returnedBlock.getEnvelopeInfos()) {
					++i;

					out("  Transaction number %d has transaction id: %s", i, envelopeInfo.getTransactionID());
					final String channelId = envelopeInfo.getChannelId();
					out("  Transaction number %d has channel id: %s", i, channelId);
					out("  Transaction number %d has epoch: %d", i, envelopeInfo.getEpoch());
					out("  Transaction number %d has transaction timestamp: %tB %<te,  %<tY  %<tT %<Tp", i, envelopeInfo.getTimestamp());
					out("  Transaction number %d has type id: %s", i, "" + envelopeInfo.getType());

					if (envelopeInfo.getType() == TRANSACTION_ENVELOPE) {
						BlockInfo.TransactionEnvelopeInfo transactionEnvelopeInfo = (BlockInfo.TransactionEnvelopeInfo) envelopeInfo;

						out("  Transaction number %d has %d actions", i, transactionEnvelopeInfo.getTransactionActionInfoCount());
						out("  Transaction number %d isValid %b", i, transactionEnvelopeInfo.isValid());
						out("  Transaction number %d validation code %d", i, transactionEnvelopeInfo.getValidationCode());

						int j = 0;
						for (BlockInfo.TransactionEnvelopeInfo.TransactionActionInfo transactionActionInfo : transactionEnvelopeInfo.getTransactionActionInfos()) {
							++j;
							out("   Transaction action %d has response status %d", j, transactionActionInfo.getResponseStatus());
							out("   Transaction action %d has response message bytes as string: %s", j, printableString(new String(transactionActionInfo.getResponseMessageBytes(), "UTF-8")));
							out("   Transaction action %d has %d endorsements", j, transactionActionInfo.getEndorsementsCount());
							for (int n = 0; n < transactionActionInfo.getEndorsementsCount(); ++n) {
								BlockInfo.EndorserInfo endorserInfo = transactionActionInfo.getEndorsementInfo(n);
								out("Endorser %d signature: %s", n, Hex.encodeHexString(endorserInfo.getSignature()));
								out("Endorser %d endorser: %s", n, new String(endorserInfo.getEndorser(), "UTF-8"));
							}
							out("   Transaction action %d has %d chaincode input arguments", j, transactionActionInfo.getChaincodeInputArgsCount());
							for (int z = 0; z < transactionActionInfo.getChaincodeInputArgsCount(); ++z) {
								out("     Transaction action %d has chaincode input argument %d is: %s", j, z, printableString(new String(transactionActionInfo.getChaincodeInputArgs(z), "UTF-8")));
							}
							out("   Transaction action %d proposal response status: %d", j, transactionActionInfo.getProposalResponseStatus());
							out("   Transaction action %d proposal response payload: %s", j, printableString(new String(transactionActionInfo.getProposalResponsePayload())));
							TxReadWriteSetInfo rwsetInfo = transactionActionInfo.getTxReadWriteSet();
							if (null != rwsetInfo) {
								out("   Transaction action %d has %d name space read write sets", j, rwsetInfo.getNsRwsetCount());

								for (TxReadWriteSetInfo.NsRwsetInfo nsRwsetInfo : rwsetInfo.getNsRwsetInfos()) {
									final String namespace = nsRwsetInfo.getNamespace();
									KvRwset.KVRWSet rws = nsRwsetInfo.getRwset();

									int rs = -1;
									for (KvRwset.KVRead readList : rws.getReadsList()) {
										rs++;

										out("Namespace %s read set %d key %s  version [%d:%d]", namespace, rs, readList.getKey(), readList.getVersion().getBlockNum(), readList.getVersion().getTxNum());

										if ("bar".equals(channelId) && blockNumber == 2) {
											if ("example_cc_go".equals(namespace)) {
												if (rs == 0) {
												} else if (rs == 1) {
												} else {
												}

												TX_EXPECTED.remove("readset1");
											}
										}
									}

									rs = -1;
									for (KvRwset.KVWrite writeList : rws.getWritesList()) {
										rs++;
										String valAsString = printableString(new String(writeList.getValue().toByteArray(), "UTF-8"));

										out("Namespace %s write set %d key %s has value '%s' ", namespace, rs, writeList.getKey(), valAsString);

										if ("bar".equals(channelId) && blockNumber == 2) {
											if (rs == 0) {
											} else if (rs == 1) {
											} else {
											}

											TX_EXPECTED.remove("writeset1");
										}
									}
								}
							}
						}
					}
				}
			}
			if (!TX_EXPECTED.isEmpty()) {
			}
		} catch (InvalidProtocolBufferRuntimeException e) {
			throw e.getCause();
		}
	}

	static String printableString(final String string) {
		int maxLogStringLength = 64;
		if (string == null || string.length() == 0) {
			return string;
		}

		String ret = string.replaceAll("[^\\p{Print}]", "?");

		ret = ret.substring(0, Math.min(ret.length(), maxLogStringLength)) + (ret.length() > maxLogStringLength ? "..." : "");

		return ret;
	}

	/**
	 * @return the client
	 */
	public HFClient getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(HFClient client) {
		this.client = client;
	}

	/**
	 * @return the fooChannel
	 */
	public Channel getFooChannel() {
		return fooChannel;
	}

	/**
	 * @param fooChannel
	 *            the fooChannel to set
	 */
	public void setFooChannel(Channel fooChannel) {
		this.fooChannel = fooChannel;
	}

	/**
	 * @return the barChannel
	 */
	public Channel getBarChannel() {
		return barChannel;
	}

	/**
	 * @param barChannel
	 *            the barChannel to set
	 */
	public void setBarChannel(Channel barChannel) {
		this.barChannel = barChannel;
	}

	/**
	 * @return the sampleOrg1
	 */
	public SampleOrg getSampleOrg1() {
		return sampleOrg1;
	}

	/**
	 * @param sampleOrg1
	 *            the sampleOrg1 to set
	 */
	public void setSampleOrg1(SampleOrg sampleOrg1) {
		this.sampleOrg1 = sampleOrg1;
	}

	public static void main(String args[]) throws Exception {
		FabricBaseSDK fabricBaseSDK = new FabricBaseSDK();
		String adminName = "admin";
		String userName = "abcd25655";
		String key = "555555";
		String value = "这是我的数据啊";
		fabricBaseSDK.setup(adminName, userName);
		ChaincodeID chaincodeID = ChaincodeID.newBuilder().setName(CHAIN_CODE_NAME).setVersion(CHAIN_CODE_VERSION).setPath(CHAIN_CODE_PATH).build();
		Channel fooChannel = fabricBaseSDK.constructChannel(FOO_CHANNEL_NAME, fabricBaseSDK.client, testConfig.getIntegrationTestsSampleOrg("peerOrg1"));
		fabricBaseSDK.runChannel(fabricBaseSDK.client, fooChannel, chaincodeID, true, testConfig.getIntegrationTestsSampleOrg("peerOrg1"), adminName, userName, key, value, "save");
		// fabricBaseSDK.runChannel(fabricBaseSDK.client, fooChannel, chaincodeID, false, testConfig.getIntegrationTestsSampleOrg("peerOrg1"), adminName, userName, key, value, "query");

		// Channel barChannel = fabricBaseSDK.constructChannel(BAR_CHANNEL_NAME, fabricBaseSDK.client, testConfig.getIntegrationTestsSampleOrg("peerOrg2"));
		// fabricBaseSDK.runChannel(fabricBaseSDK.client, barChannel, chaincodeID, true, testConfig.getIntegrationTestsSampleOrg("peerOrg2"), adminName, userName, key, value, "save");
		// fabricBaseSDK.runChannel(fabricBaseSDK.client, barChannel, chaincodeID, false, testConfig.getIntegrationTestsSampleOrg("peerOrg2"), adminName, userName, key, value, "query");
	}

	/**
	 * 初始化client
	 * 
	 * @param adminName
	 * @param userName
	 */
	public void initClient(String adminName, String userName) {
		try {
			TestConfigHelper configHelper = new TestConfigHelper();
			configHelper.clearConfig();
			configHelper.customizeConfig();
			Collection<SampleOrg> testSampleOrgs = TestConfig.getConfig().getIntegrationTestsSampleOrgs();
			// Set up hfca for each sample org
			for (SampleOrg sampleOrg : testSampleOrgs) {
				sampleOrg.setCAClient(HFCAClient.createNewInstance(sampleOrg.getCALocation(), sampleOrg.getCAProperties()));
			}
			// Setup client
			// Create instance of client.
			HFClient client = HFClient.createNewInstance();
			client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
			// client.setMemberServices(peerOrg1FabricCA);
			// Set up USERS
			// Persistence is not part of SDK. Sample file store is for demonstration purposes only!
			// MUST be replaced with more robust application implementation (Database, LDAP)
			File sampleStoreFile = new File(System.getProperty("java.io.tmpdir") + "/HFCSampletest.properties");
			if (sampleStoreFile.exists()) { // For testing start fresh
				sampleStoreFile.delete();
			}
			final SampleStore sampleStore = new SampleStore(sampleStoreFile);
			// sampleStoreFile.deleteOnExit();
			// SampleUser can be any implementation that implements org.hyperledger.fabric.sdk.User Interface
			// get users for all orgs
			for (SampleOrg sampleOrg : testSampleOrgs) {
				getAllOrgsUsers(adminName, userName, sampleStore, sampleOrg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Construct the channel
	 * 
	 * @param name
	 * @param client
	 * @param sampleOrg
	 * @return
	 * @throws Exception
	 */
	public Channel createChannel(String name, HFClient client, SampleOrg sampleOrg) throws Exception {
		// Construct the channel
		// Only peer Admin org
		client.setUserContext(sampleOrg.getPeerAdmin());
		Collection<Orderer> orderers = new LinkedList<>();
		for (String orderName : sampleOrg.getOrdererNames()) {

			Properties ordererProperties = TestConfig.getConfig().getOrdererProperties(orderName);
			// example of setting keepAlive to avoid timeouts on inactive http2 connections.
			// Under 5 minutes would require changes to server side to accept faster ping rates.
			ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
			ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit.SECONDS});
			orderers.add(client.newOrderer(orderName, sampleOrg.getOrdererLocation(orderName), ordererProperties));
		}
		// Just pick the first orderer in the list to create the channel.
		Orderer anOrderer = orderers.iterator().next();
		orderers.remove(anOrderer);
		ChannelConfiguration channelConfiguration = new ChannelConfiguration(new File(TEST_FIXTURES_PATH + "/sdkintegration/e2e-2Orgs/channel/" + name + ".tx"));
		// Create channel that has only one signer that is this orgs peer admin.
		// If channel creation policy needed more signature they would need to be added too.
		Channel newChannel = client.newChannel(name, anOrderer, channelConfiguration, client.getChannelConfigurationSignature(channelConfiguration, sampleOrg.getPeerAdmin()));
		for (String peerName : sampleOrg.getPeerNames()) {
			String peerLocation = sampleOrg.getPeerLocation(peerName);
			Properties peerProperties = TestConfig.getConfig().getPeerProperties(peerName);
			// test properties for peer.. if any.
			if (peerProperties == null) {
				peerProperties = new Properties();
			}
			// Example of setting specific options on grpc's NettyChannelBuilder
			peerProperties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
			Peer peer = client.newPeer(peerName, peerLocation, peerProperties);
			newChannel.joinPeer(peer);
			out("Peer %s joined channel %s", peerName, name);
			sampleOrg.addPeer(peer);
		}
		for (Orderer orderer : orderers) { // add remaining orderers if any.
			newChannel.addOrderer(orderer);
		}
		for (String eventHubName : sampleOrg.getEventHubNames()) {
			final Properties eventHubProperties = TestConfig.getConfig().getEventHubProperties(eventHubName);
			eventHubProperties.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[]{5L, TimeUnit.MINUTES});
			eventHubProperties.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[]{8L, TimeUnit.SECONDS});
			EventHub eventHub = client.newEventHub(eventHubName, sampleOrg.getEventHubLocation(eventHubName), eventHubProperties);
			newChannel.addEventHub(eventHub);
		}
		newChannel.initialize();
		return newChannel;
	}
	public void getAllOrgsUsers(String adminName, String userName, final SampleStore sampleStore, SampleOrg sampleOrg) throws EnrollmentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, Exception, RegistrationException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		HFCAClient ca = sampleOrg.getCAClient();
		final String orgName = sampleOrg.getName();
		final String mspid = sampleOrg.getMSPID();
		ca.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
		SampleUser admin = sampleStore.getMember(adminName, orgName);
		System.out.println("开始打印用户信息!");
		if (!admin.isEnrolled()) {
			// Preregistered admin only needs to be enrolled with Fabric caClient.
			Enrollment enrollment = ca.enroll(admin.getName(), "adminpw");
			// System.out.println(enrollment.getCert());
			admin.setEnrollment(enrollment);
			admin.setMspId(mspid);
		}

		sampleOrg.setAdmin(admin); // The admin of this org --
		// 获取用户信息
		SampleUser user = sampleStore.getMember(userName, sampleOrg.getName());
		if (!user.isRegistered()) {
			// users need to be registered AND enrolled
			RegistrationRequest rr = new RegistrationRequest(user.getName(), "org1.department1");
			String secret = ca.register(rr, admin);
			System.out.println(secret);
			user.setEnrollmentSecret(secret);
		}
		if (!user.isEnrolled()) {
			Enrollment enrollment = ca.enroll(user.getName(), user.getEnrollmentSecret());
			System.out.println(enrollment.getCert());
			user.setEnrollment(enrollment);
			user.setMspId(mspid);
		}
		System.out.println("结束打印用户信息!");
		sampleOrg.addUser(user);
		// Remember user belongs to this Org

		final String sampleOrgName = sampleOrg.getName();
		final String sampleOrgDomainName = sampleOrg.getDomainName();

		// src/test/fixture/sdkintegration/e2e-2Orgs/channel/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/

		SampleUser peerOrgAdmin = sampleStore.getMember(sampleOrgName + "Admin", sampleOrgName, sampleOrg.getMSPID(), Util.findFileSk(Paths.get(testConfig.getTestChannelPath(), "crypto-config/peerOrganizations/", sampleOrgDomainName, format("/users/Admin@%s/msp/keystore", sampleOrgDomainName)).toFile()), Paths.get(testConfig.getTestChannelPath(), "crypto-config/peerOrganizations/", sampleOrgDomainName, format("/users/Admin@%s/msp/signcerts/Admin@%s-cert.pem", sampleOrgDomainName, sampleOrgDomainName)).toFile());

		sampleOrg.setPeerAdmin(peerOrgAdmin);
		// A special user that can create channels, join peers and install chaincode
	}

	public void runningChannel(HFClient client, Channel channel, ChaincodeID chaincodeID, boolean installChaincode, SampleOrg sampleOrg, String TEST_ADMIN_NAME, String TESTUSER_1_NAME, String key, String value, String fun) {
		try {
			String channelName = channel.getName();
			boolean isFooChain = FOO_CHANNEL_NAME.equals(channelName);
			out("Running channel %s", channelName);
			channel.setTransactionWaitTime(TestConfig.getConfig().getTransactionWaitTime());
			channel.setDeployWaitTime(TestConfig.getConfig().getDeployWaitTime());
			// Collection<Peer> channelPeers = channel.getPeers();
			Collection<Orderer> orderers = channel.getOrderers();
			Collection<ProposalResponse> successful = new LinkedList<>();
			Collection<ProposalResponse> failed = new LinkedList<>();
			if (installChaincode) {
				// 安装链码
				installChainCode(client, sampleOrg, isFooChain, chaincodeID, successful, failed);
			}
			// client.setUserContext(sampleOrg.getUser(TEST_ADMIN_NAME));
			// final ChaincodeID chaincodeID = firstInstallProposalResponse.getChaincodeID();
			// Note installing chaincode does not require transaction no need to send to Orderers
			if ("save".equals(fun)) {
				// 实例化链码
				out("我要保存数据了", "");
				initChainCode(client, channel, key, value, isFooChain, chaincodeID, successful, failed);
				out("数据保存完成", "");
			} else if ("query".equals(fun)) {
				out("我要查询数据了", "");
				// Send instantiate transaction to orderer
				channel.sendTransaction(successful, orderers).thenApply(transactionEvent -> {
					try {
						out("Finished transaction with transaction id %s", transactionEvent.getTransactionID());
						testTxID = transactionEvent.getTransactionID();
						// used in the channel queries later
						out("Now query chaincode for the value of " + key + ".");
						//查询链码
						queryByChaincode(client, channel, chaincodeID, key);
						return null;
					} catch (Exception e) {
						out("Caught exception while running query");
						e.printStackTrace();
					}
					return null;
				}).exceptionally(e -> {
					if (e instanceof TransactionEventException) {
						BlockEvent.TransactionEvent te = ((TransactionEventException) e).getTransactionEvent();
						if (te != null) {
						}
					}
					return null;
				}).get(TestConfig.getConfig().getTransactionWaitTime(), TimeUnit.SECONDS);
				// Channel queries
				out("数据查询结束", "");
			} else if ("delete".equals(fun)) {

				QueryByChaincodeRequest queryByChaincodeRequest = client.newQueryProposalRequest();
				queryByChaincodeRequest.setArgs(new String[]{"delete", key});
				queryByChaincodeRequest.setFcn("invoke");
				queryByChaincodeRequest.setChaincodeID(chaincodeID);
				Map<String, byte[]> tm2 = new HashMap<>();
				tm2.put("HyperLedgerFabric", "QueryByChaincodeRequest:JavaSDK".getBytes(UTF_8));
				tm2.put("method", "QueryByChaincodeRequest".getBytes(UTF_8));
				queryByChaincodeRequest.setTransientMap(tm2);

				Collection<ProposalResponse> queryProposals = channel.queryByChaincode(queryByChaincodeRequest, channel.getPeers());
				for (ProposalResponse proposalResponse : queryProposals) {
					if (!proposalResponse.isVerified() || proposalResponse.getStatus() != ProposalResponse.Status.SUCCESS) {
					} else {
						String payload = proposalResponse.getProposalResponse().getResponse().getPayload().toStringUtf8();
						out("Query payload of " + key + " from peer %s returned %s", proposalResponse.getPeer().getName(), payload);
					}
				}
			}
		} catch (Exception e) {
			out("Caught an exception running channel %s", channel.getName());
			e.printStackTrace();
		}
	}

	/**
	 * @param client
	 * @param channel
	 * @param chaincodeID
	 * @param key
	 * @throws InvalidArgumentException
	 * @throws ProposalException
	 */
	private void queryByChaincode(HFClient client, Channel channel, ChaincodeID chaincodeID, String key) throws InvalidArgumentException, ProposalException {
		QueryByChaincodeRequest queryByChaincodeRequest = client.newQueryProposalRequest();
		queryByChaincodeRequest.setArgs(new String[]{"query", key});
		queryByChaincodeRequest.setFcn("invoke");
		queryByChaincodeRequest.setChaincodeID(chaincodeID);
		Map<String, byte[]> tm2 = new HashMap<>();
		tm2.put("HyperLedgerFabric", "QueryByChaincodeRequest:JavaSDK".getBytes(UTF_8));
		tm2.put("method", "QueryByChaincodeRequest".getBytes(UTF_8));
		queryByChaincodeRequest.setTransientMap(tm2);

		Collection<ProposalResponse> queryProposals = channel.queryByChaincode(queryByChaincodeRequest, channel.getPeers());
		for (ProposalResponse proposalResponse : queryProposals) {
			if (!proposalResponse.isVerified() || proposalResponse.getStatus() != ProposalResponse.Status.SUCCESS) {
			} else {
				String payload = proposalResponse.getProposalResponse().getResponse().getPayload().toStringUtf8();
				out("Query payload of " + key + " from peer %s returned %s", proposalResponse.getPeer().getName(), payload);
			}
		}
	}
}
