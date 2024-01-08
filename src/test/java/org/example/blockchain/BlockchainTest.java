package org.example.blockchain;

import org.example.exception.InvalidMiningException;
import org.example.exception.PreviousHashMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockchainTest {
    private static Blockchain blockchain;

    @BeforeAll
    static void setup(){
        blockchain = new Blockchain();
    }

    @BeforeEach
    void clean(){
        blockchain.getBlocks().clear();
    }

    @Test
    void generateBlock() {
        Block block1 = blockchain.generateBlock(3);
        Block block2 = blockchain.generateBlock(3);


        assertEquals("0", block1.getPrevHash());
        assertEquals(block1.getHash(), block2.getPrevHash());

        assertEquals(2, blockchain.getBlocks().size());
    }

    @Test
    void isValid(){
        blockchain.generateBlock(2);
        blockchain.generateBlock(2);
        blockchain.generateBlock(2);

        assertTrue(blockchain.isValid());
    }

    @Test
    void isValidWithInvalidMining() {
        blockchain.generateBlock(2);
        Block block2 = blockchain.generateBlock(3);


        InvalidMiningException exception = Assertions.assertThrows(InvalidMiningException.class,
                () -> blockchain.isValid());

        Assertions.assertEquals("Invalid block: Block " + block2.getId() + ": Invalid mining.", exception.getMessage());
    }

    @Test
    void isValidWithInvalidHashing() {
        blockchain.generateBlock(2);
        Block block2 = new Block(2);
        blockchain.getBlocks().add(block2);

        PreviousHashMismatchException exception = Assertions.assertThrows(PreviousHashMismatchException.class,
                () -> blockchain.isValid());

        Assertions.assertEquals("Previous hash mismatch: Block " + block2.getId() + ": Hash values do not match.", exception.getMessage());
    }
}