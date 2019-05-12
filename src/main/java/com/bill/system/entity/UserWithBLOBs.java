package com.bill.system.entity;

public class UserWithBLOBs extends User {
    private byte[] address;

    private byte[] signature;

    public byte[] getAddress() {
        return address;
    }

    public void setAddress(byte[] address) {
        this.address = address;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}