package com.example.decluttered_dhaka_metro_rail;

public class Ticket {
    private int id;
    private String ticketType;
    private int ticketQuantity;
    private String startingStation;
    private String endingStation;
    private String user;

    public Ticket(int id, String ticketType, int ticketQuantity, String startingStation, String endingStation, String user) {
        this.id = id;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.startingStation = startingStation;
        this.endingStation = endingStation;
        this.user = user;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public String getStartingStation() {
        return startingStation;
    }

    public String getEndingStation() {
        return endingStation;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType='" + ticketType + '\'' +
                ", ticketQuantity=" + ticketQuantity +
                ", startingStation='" + startingStation + '\'' +
                ", endingStation='" + endingStation + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

