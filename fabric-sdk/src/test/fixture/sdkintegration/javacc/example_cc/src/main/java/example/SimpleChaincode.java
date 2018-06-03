/*
Copyright DTCC, IBM 2016, 2017 All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package sdkintegration.javacc.example_cc.src.main.java.example;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;

/**
 * @author Sergey Pomytkin spomytkin@gmail.com
 */
public class SimpleChaincode extends ChaincodeBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperledger.java.shim.ChaincodeBase#getChaincodeID()
	 */
	@Override
	public String getChaincodeID() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperledger.java.shim.ChaincodeBase#query(org.hyperledger.java.shim.
	 * ChaincodeStub, java.lang.String, java.lang.String[])
	 */
	@Override
	public String query(ChaincodeStub arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperledger.java.shim.ChaincodeBase#run(org.hyperledger.java.shim.
	 * ChaincodeStub, java.lang.String, java.lang.String[])
	 */
	@Override
	public String run(ChaincodeStub stub, String function, String[] arg2) {
		// TODO Auto-generated method stub
		String key;
		String value;
		switch (function) {

			case "get" :
				key = arg2[0];
				return stub.getState(key);
			case "put" :
				key = arg2[0];
				value = arg2[1];
				stub.putState(key, value);
		}
		return null;
	}
	
	
}
