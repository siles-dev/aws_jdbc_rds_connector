package org.aws_rds_connector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordListTest {
    private DataSourceProperties db;
    private WordList dbWords;
    @BeforeEach
    void setup() {
        dbWords = new WordList();
    }
    @Test
    void listAllWords() {
        Assertions.assertFalse(dbWords.handleRequest().isEmpty());
    }
}