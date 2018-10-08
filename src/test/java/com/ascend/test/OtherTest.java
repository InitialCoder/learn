package com.ascend.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class OtherTest {

	public static void main(String[] args) {
		String hashAlgorithmName="MD5",credentials="635241";
		int hashIterations=1024;
		Object salt=ByteSource.Util.bytes("user2");
		Object result= new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}
