package com.position.book.positionbook.service;

import com.position.book.positionbook.exceptions.PositionException;
import com.position.book.positionbook.model.Events;
import com.position.book.positionbook.model.Positions;

import java.util.concurrent.BrokenBarrierException;

public interface EventProcessingService {
    Positions process(Events events) throws BrokenBarrierException, InterruptedException, PositionException;

    Positions getPositions(String AccountName);
}
