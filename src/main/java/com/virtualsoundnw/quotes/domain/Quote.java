package com.virtualsoundnw.quotes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Quote.
 */

@Document(collection = "quote")
public class Quote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("last")
    private Double last;

    @Field("changeamount")
    private Double changeamount;

    @Field("changepercent")
    private Double changepercent;

    @Field("sharesoutstanding")
    private Double sharesoutstanding;

    @Field("ask")
    private Double ask;

    @Field("bid")
    private Double bid;

    @Field("volume")
    private Double volume;

    @Field("tendayaveragevolume")
    private Double tendayaveragevolume;

    @Field("lasttradetime")
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

    public Quote last(Double last) {
        this.last = last;
        return this;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getChangeamount() {
        return changeamount;
    }

    public Quote changeamount(Double changeamount) {
        this.changeamount = changeamount;
        return this;
    }

    public void setChangeamount(Double changeamount) {
        this.changeamount = changeamount;
    }

    public Double getChangepercent() {
        return changepercent;
    }

    public Quote changepercent(Double changepercent) {
        this.changepercent = changepercent;
        return this;
    }

    public void setChangepercent(Double changepercent) {
        this.changepercent = changepercent;
    }

    public Double getSharesoutstanding() {
        return sharesoutstanding;
    }

    public Quote sharesoutstanding(Double sharesoutstanding) {
        this.sharesoutstanding = sharesoutstanding;
        return this;
    }

    public void setSharesoutstanding(Double sharesoutstanding) {
        this.sharesoutstanding = sharesoutstanding;
    }

    public Double getAsk() {
        return ask;
    }

    public Quote ask(Double ask) {
        this.ask = ask;
        return this;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public Quote bid(Double bid) {
        this.bid = bid;
        return this;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getVolume() {
        return volume;
    }

    public Quote volume(Double volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getTendayaveragevolume() {
        return tendayaveragevolume;
    }

    public Quote tendayaveragevolume(Double tendayaveragevolume) {
        this.tendayaveragevolume = tendayaveragevolume;
        return this;
    }

    public void setTendayaveragevolume(Double tendayaveragevolume) {
        this.tendayaveragevolume = tendayaveragevolume;
    }

    public ZonedDateTime getLasttradetime() {
        return lasttradetime;
    }

    public Quote lasttradetime(ZonedDateTime lasttradetime) {
        this.lasttradetime = lasttradetime;
        return this;
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
        Quote quote = (Quote) o;
        if(quote.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, quote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Quote{" +
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
