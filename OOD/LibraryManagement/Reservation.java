package com.laioffer.OOD.LibraryManagement;

public class Reservation {
    private Long reservationID;
    private BookItem bookItem;
    private ReservationStatus brs;
    private Member member;

    public Reservation(Long reservationID, BookItem bookItem, ReservationStatus brs, Member member) {
        this.reservationID = reservationID;
        this.bookItem = bookItem;
        this.brs = brs;
        this.member = member;
    }
    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public ReservationStatus getBrs() {
        return brs;
    }

    public void setBrs(ReservationStatus brs) {
        this.brs = brs;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getMemberId() {
        return member.getID();
    }

    public void updateStatus (ReservationStatus brs) {
        this.brs = brs;
    }
}
