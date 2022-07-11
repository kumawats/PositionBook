package com.position.book.positionbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class is used to return the list of the positions on various accounts
 */
public class Positions {

    @JsonProperty("Positions")
    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
