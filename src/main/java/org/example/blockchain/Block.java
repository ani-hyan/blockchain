package org.example.blockchain;

import lombok.Getter;
import lombok.Setter;
import org.example.util.StringUtil;

import java.time.LocalDateTime;

@Getter
@Setter
public class Block {
    private static int number = 0;

    private final int id;
    private final String prevHash;
    private final LocalDateTime createdAt;
    private final int difficulty;
    private String hash;

    public Block(String prevHash, int difficulty) {
        this.id = ++number;
        this.hash = StringUtil.applySha256(this);
        this.prevHash = prevHash;
        this.createdAt = LocalDateTime.now();
        this.difficulty = difficulty;
    }

    public Block(int difficulty) {
        this.id = ++number;
        this.hash = StringUtil.applySha256(this);
        this.prevHash = "0";
        this.createdAt = LocalDateTime.now();
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
