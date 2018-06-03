/*
 Copyright IBM Corp. All Rights Reserved.

 SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric_sdk.fabric.sdkintegration;

import org.hyperledger.fabric_sdk.fabricca.sdkintegration.HFCAClientIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({End2endIT.class, End2endAndBackAgainIT.class, UpdateChannelIT.class, HFCAClientIT.class})
public class IntegrationSuite {

}
