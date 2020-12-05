package com.mary.kiragu.domain;

/**
 *
 * @author Lenovo
 */
public class AccountStatement {

    private String transactionDate;
    private int tenantId;
    private String transactionType;
    private int amount;
    private String Details;
    private String user;
    private int balanceBefore;
    private int balanceAfter;
    private int statementId;

    public int getStatementId() {
        return statementId;
    }

    public void setStatementId(int statementId) {
        this.statementId = statementId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(int balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(int balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        return "AccountStatement{" + "transactionDate=" + transactionDate + ", tenantId=" + tenantId + ","
                + " transactionType=" + transactionType + ", amount=" + amount + ","
                + " Details=" + Details + ", user=" + user + ", balanceBefore=" + balanceBefore + ", BalanceAfter=" + balanceAfter + '}';
    }

}
