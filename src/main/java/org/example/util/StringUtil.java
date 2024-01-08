package org.example.util;

import org.example.blockchain.Block;

import java.security.MessageDigest;

public class StringUtil {

    public static String applySha256(Block block) {
        return applySha256(
                block.getHash() +
                        block.getPrevHash() +
                        block.getCreatedAt() +
                        block.getId() +
                        block.getDifficulty()
        );
    }

    private static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mineBlock(Block block, int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!block.getHash().substring(0, difficulty).equals(target)) {
            block.setHash(applySha256(block));
        }
    }

}
