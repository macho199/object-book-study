package com.oop;
import java.time.LocalDateTime;

/**
 * Screening
 */
public class Screening {

    private Movie movie;
    private int sequence; // 순번
    private LocalDateTime whenScreening; // 상영 시작 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreening) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreening = whenScreening;
    }

    public LocalDateTime getStartTime() {
        return whenScreening;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money clcualateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}