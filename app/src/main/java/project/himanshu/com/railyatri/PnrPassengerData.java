package project.himanshu.com.railyatri;

import java.io.Serializable;

public class PnrPassengerData implements Serializable {

    String no;
    String current_status;
    String booking_status;

    public PnrPassengerData(String no, String current_status, String booking_status) {
        this.no = no;
        this.current_status = current_status;
        this.booking_status = booking_status;
    }
}
