package com.alnt.platform.application.security.hashing;

import java.util.function.Function;

public class HashUtils {
	
	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
	
	public static String hash(String password) {
	    return bcrypt.hash(password);
	}
	
	public static boolean verify(String password, String hash) {
	    return bcrypt.verifyHash(password, hash);
	}
	
	public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
	    return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
	}
	
	public static void main(String[] args) {
		String[] mutableHash = new String[1];
		Function<String, Boolean> update = hash -> { mutableHash[0] = hash; return true; };

		String hashPw1 = HashUtils.hash("Alert1234");
		System.out.println("hash of pw1: {} "+hashPw1);
		System.out.println("verifying pw1: {} "+HashUtils.verifyAndUpdateHash("password", hashPw1, update));
		System.out.println("verifying pw1 fails: {} "+HashUtils.verifyAndUpdateHash("password1", hashPw1, update));
		String hashPw2 = HashUtils.hash("password");
		System.out.println("hash of pw2: {} "+hashPw2);
		System.out.println("verifying pw2: {} "+HashUtils.verifyAndUpdateHash("password", hashPw2, update));
		System.out.println("verifying pw2 fails: {} "+ HashUtils.verifyAndUpdateHash("password2", hashPw2, update));
		UpdatableBCrypt oldHasher = new UpdatableBCrypt(7);
		String oldHash = oldHasher.hash("password");
		System.out.println("hash of oldHash: {} "+ oldHash);
		System.out.println("verifying oldHash: {}, hash upgraded to: {} "+
		HashUtils.verifyAndUpdateHash("password", oldHash, update)+" "+
        mutableHash[0]);
	}

}
