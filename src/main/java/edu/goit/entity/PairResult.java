package edu.goit.entity;

import lombok.Value;

import java.util.Map;

@Value
public class PairResult {
    String symbol;
    Double price;

    public PairResult(Map.Entry<String, Double> entry) {
        this.symbol = entry.getKey();
        this.price = entry.getValue();
    }
}
