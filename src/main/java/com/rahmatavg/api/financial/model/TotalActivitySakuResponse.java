package com.rahmatavg.api.financial.model;

public class TotalActivitySakuResponse {
    private Double totalIncome;
    private Double totalExpenditure;
    private Double balance;

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(Double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public TotalActivitySakuResponse() {
    }

    public TotalActivitySakuResponse(Double totalIncome, Double totalExpenditure, Double balance) {
        this.totalIncome = totalIncome;
        this.totalExpenditure = totalExpenditure;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TotalActivitySakuResponse{" +
                "totalIncome=" + totalIncome +
                ", totalExpenditure=" + totalExpenditure +
                ", balance=" + balance +
                '}';
    }
}
