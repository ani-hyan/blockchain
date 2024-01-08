package org.example.blockchain;

import lombok.Getter;
import org.example.exception.InvalidMiningException;
import org.example.exception.PreviousHashMismatchException;

import java.util.LinkedList;
import java.util.List;

import static org.example.util.StringUtil.mineBlock;

@Getter
public class Blockchain {
    private final List<Block> blocks = new LinkedList<>();
    private Block head;

    public Block generateBlock(int difficulty) {
        Block newBlock;
        if (blocks.isEmpty()) {
            newBlock = new Block(difficulty);
        } else {
            newBlock = new Block(head.getHash(), difficulty);
        }
        mineBlock(newBlock, difficulty);

        head = newBlock;
        blocks.add(newBlock);

        return newBlock;
    }

    public void printBlocks() {
        for (Block block : blocks) {
            System.out.println(block);
        }
    }

    public boolean isValid() {
        if (blocks.size() == 1) {
            return head.getPrevHash().equals("0");
        }
        for (int i = 1; i < blocks.size(); i++) {
            Block current = blocks.get(i);
            Block prev = blocks.get(i - 1);

            if (!prev.getHash().equals(current.getPrevHash())) {
                throw new PreviousHashMismatchException("Block " + current.getId() + ": Hash values do not match.");
            }

            if (!current.getHash().substring(0, current.getDifficulty()).equals("0".repeat(current.getDifficulty()))
                    || current.getDifficulty() != prev.getDifficulty()) {
                throw new InvalidMiningException("Block " + current.getId() + ": Invalid mining.");
            }
        }

        return true;
    }

}
