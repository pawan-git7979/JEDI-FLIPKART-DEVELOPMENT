package com.flipkart.constant;

public class SQLQueries {
    public static final String GET_PENDING_GYM_OWNER_REQUESTS = """
        SELECT 
            u.userId, u.name, u.email, u.password, u.address, 
            o.aadhaarNumber, o.panNumber, o.governmentDocument 
        FROM FlipFitGymCenter gc
        JOIN FlipFitGymOwner o ON gc.ownerId = o.ownerId
        JOIN FlipFitUser u ON o.ownerId = u.userId
        WHERE gc.status = 'PENDING'
    """;

    public static final String APPROVE_GYM_OWNER =
            "UPDATE FlipFitGymCenter SET status = 'APPROVED' WHERE ownerId = ?";

    public static final String DELETE_GYMS_BY_OWNER =
            "DELETE FROM FlipFitGymCenter WHERE ownerId = ?";

    public static final String DELETE_GYM_OWNER =
            "DELETE FROM FlipFitGymOwner WHERE ownerId = ?";

    public static final String GET_ALL_CUSTOMERS = """
        SELECT u.name, u.email 
        FROM FlipFitGymCustomer c
        JOIN FlipFitUser u ON c.userId = u.userId
    """;

    public static final String GET_ALL_OWNERS = """
        SELECT DISTINCT u.name, u.email 
        FROM FlipFitGymOwner o
        JOIN FlipFitUser u ON o.ownerId = u.userId
        WHERE EXISTS (
            SELECT 1 FROM FlipFitGymCenter gc
            WHERE gc.ownerId = o.ownerId AND gc.status = 'APPROVED'
        )
    """;

    public static final String GET_GYM_NAMES_BY_OWNER =
            "SELECT name FROM FlipFitGymCenter WHERE ownerId = ?";

    public static final String GET_ALL_GYMS = """
        SELECT name, location 
        FROM FlipFitGymCenter
        WHERE status = 'APPROVED'
    """;

    // FlipFitBooking Queries
    public static final String GET_BOOKING_BY_ID =
            "SELECT * FROM FlipFitBooking WHERE id = ?";

    public static final String GET_BOOKINGS_BY_USER =
            "SELECT * FROM FlipFitBooking WHERE user_id = ?";

    public static final String CANCEL_BOOKING =
            "DELETE FROM FlipFitBooking WHERE id = ?";


    // FlipFitCustomer Queries
    public static final String REGISTER_GYM_CUSTOMER =
            "INSERT INTO FlipFitGymCustomer (userId, governmentDocumentNumber) VALUES (?, ?)";

    public static final String GET_AVAILABLE_CITIES =
            "SELECT DISTINCT location FROM FlipFitGymCenter";

    public static final String GET_GYMS_BY_CITY =
            "SELECT * FROM FlipFitGymCenter WHERE location = ?";

    public static final String GET_AVAILABLE_SLOTS =
            "SELECT * FROM FlipFitGymSlot WHERE gymId = ? AND numOfSeats > numOfSeatsBooked";

    public static final String ADD_TO_WAITLIST =
            "INSERT INTO waitlist (userId, slotId) VALUES (?, ?)";

    public static final String BOOK_SLOT =
            "INSERT INTO FlipFitBooking (userId, centerId, slotId, status) VALUES (?, ?, ?, 'BOOKED')";

    public static final String UPDATE_SLOT_BOOKING =
            "UPDATE FlipFitGymSlot SET numOfSeatsBooked = numOfSeatsBooked + 1 WHERE slotId = ?";

    public static final String GET_USER_BOOKINGS =
            "SELECT * FROM FlipFitBooking WHERE userId = ?";

    public static final String GET_USER_PAYMENTS =
            "SELECT * FROM FlipFitPayment WHERE userId = ?";

    public static final String GET_USER_NOTIFICATIONS =
            "SELECT * FROM FlipFitNotification WHERE userId = ?";

    // FlipFitGymCenter Queries
    public static final String ADD_GYM =
            "INSERT INTO FlipFitGymCenter (name, location, owner_id) VALUES (?, ?, ?)";

    public static final String UPDATE_GYM_DETAILS =
            "UPDATE FlipFitGymCenter SET name = ?, location = ? WHERE id = ?";

    public static final String GET_ALL_GYMS_FOR_GYM_CENTER =
            "SELECT * FROM FlipFitGymCenter";


    // FlipFitGymOwner Queries
    public static final String REGISTER_GYM_OWNER =
            "INSERT INTO FlipFitGymOwner (ownerId, aadhaarNumber, panNumber, governmentDocument) VALUES (?, ?, ?, ?)";

    public static final String GET_OWNER_BY_ID =
            "SELECT * FROM FlipFitGymOwner WHERE user_id = ?";

    public static final String UPDATE_OWNER_DETAILS =
            "UPDATE gym_owners SET gym_names = ?, aadhaar_number = ?, pan_number = ?, government_document = ? WHERE user_id = ?";



    // FlipFitGymCenter Queries
    public static final String ADD_GYM_CENTER =
            "INSERT INTO FlipFitGymCenter (name, location, ownerId, adminId) VALUES (?, ?, ?, ?)";

    public static final String UPDATE_GYM_INFO =
            "UPDATE FlipFitGymCenter SET name = ?, location = ? WHERE gymId = ?";

    // FlipFitGymSlot Queries
    public static final String ADD_OR_UPDATE_SLOT =
            "INSERT INTO FlipFitGymSlot (gymId, startTime, endTime, numOfSeats) VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE startTime = ?, endTime = ?, numOfSeats = ?";

    // FlipFitBooking Queries
    public static final String VIEW_BOOKINGS =
            "SELECT * FROM FlipFitBooking WHERE centerId IN (SELECT gymId FROM FlipFitGymCenter WHERE ownerId = ?)";

    // FlipFitPayment Queries
    public static final String PROCESS_PAYMENT =
            "INSERT INTO FlipFitPayment (userId, amount, status) VALUES (?, ?, ?)";

    public static final String GET_PAYMENT_DETAILS =
            "SELECT * FROM FlipFitPayment WHERE id = ?";


    // FlipFitUser Queries
    public static final String ADD_USER =
            "INSERT INTO FlipFitUser (name, email, password, role, address) VALUES (?, ?, ?, ?, ?)";

    public static final String GET_USER_BY_ID =
            "SELECT * FROM FlipFitUser WHERE userId = ?";

    public static final String GET_USER_BY_EMAIL =
            "SELECT * FROM FlipFitUser WHERE email = ?";

    public static final String GET_ALL_USERS =
            "SELECT * FROM FlipFitUser";

    public static final String UPDATE_USER =
            "UPDATE FlipFitUser SET name = ?, email = ?, password = ?, role = ?, address = ? WHERE userId = ?";

    public static final String DELETE_USER =
            "DELETE FROM FlipFitUser WHERE userId = ?";

}
