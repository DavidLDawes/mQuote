package com.virtualsoundnw.quotes.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Quote entity.
 */
public class QuoteDTO implements Serializable {

    private String id;

    private Double last;

    private Double changeamount;

    private Double changepercent;

    private Double sharesoutstanding;

    private Double ask;

    private Double bid;

    private Double volume;

    private Double tendayaveragevolume;

    private ZonedDateTime lasttradetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }
    public Double getChangeamount() {
        return changeamount;
    }

    public void setChangeamount(Double changeamount) {
        this.changeamount = changeamount;
    }
    public Double getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(Double changepercent) {
        this.changepercent = changepercent;
    }
    public Double getSharesoutstanding() {
        return sharesoutstanding;
    }

    public void setSharesoutstanding(Double sharesoutstanding) {
        this.sharesoutstanding = sharesoutstanding;
    }
    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }
    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }
    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
    public Double getTendayaveragevolume() {
        return tendayaveragevolume;
    }

    public void setTendayaveragevolume(Double tendayaveragevolume) {
        this.tendayaveragevolume = tendayaveragevolume;
    }
    public ZonedDateTime getLasttradetime() {
        return lasttradetime;
    }

    public void setLasttradetime(ZonedDateTime lasttradetime) {
        this.lasttradetime = lasttradetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteDTO quoteDTO = (QuoteDTO) o;

        if ( ! Objects.equals(id, quoteDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
            "id=" + id +
            ", last='" + last + "'" +
            ", changeamount='" + changeamount + "'" +
            ", changepercent='" + changepercent + "'" +
            ", sharesoutstanding='" + sharesoutstanding + "'" +
            ", ask='" + ask + "'" +
            ", bid='" + bid + "'" +
            ", volume='" + volume + "'" +
            ", tendayaveragevolume='" + tendayaveragevolume + "'" +
            ", lasttradetime='" + lasttradetime + "'" +
            '}';
    }
}
