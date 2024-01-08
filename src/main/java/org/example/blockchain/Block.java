package org.example.blockchain;

import lombok.Getter;
import lombok.Setter;
import org.example.util.StringUtil;

import java.util.Date;

@Getter
@Setter
public class Block {
    private static int number = 0;

    private final int id;
    private final String prevHash;
    private final long createdAt;
    private final int difficulty;
    private String hash;

    public Block(String prevHash, int difficulty) {
        this.id = ++number;
        this.hash = StringUtil.applySha256(this);
        this.prevHash = prevHash;
        this.createdAt = new Date().getTime();
        this.difficulty = difficulty;
    }

    public Block(int difficulty) {
        this.id = ++number;
        this.hash = StringUtil.applySha256(this);
        this.prevHash = String.valueOf(0);
        this.createdAt = new Date().getTime();
        this.difficulty = difficulty;
    }


    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
