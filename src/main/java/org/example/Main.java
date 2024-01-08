package org.example;

import org.example.blockchain.Block;
import org.example.blockchain.Blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        Block b1 = blockchain.generateBlock(2);
        Block b2 = blockchain.generateBlock(2);
        Block b3 = blockchain.generateBlock(2);
        blockchain.printBlocks();

        System.out.println(blockchain.isValid());
    }
}