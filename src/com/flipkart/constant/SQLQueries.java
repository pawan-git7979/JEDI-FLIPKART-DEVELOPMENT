package com.flipkart.constant;

/**
 * This class contains all the SQL queries used throughout the FlipFit application.
 * These queries are organized by their respective entity categories, such as FlipFitUser, FlipFitGymOwner, FlipFitGymCenter, etc.
 * The SQL queries are used for CRUD operations (Create, Read, Update, Delete) and other interactions with the database.
 */
public class SQLQueries {

    // FlipFitGymOwner Queries
    /**
     * SQL query to fetch pending gym owner requests.
     */
    public static final String GET_PENDING_GYM_OWNER_REQUESTS = """
        SELECT 
            u.userId, u.name, u.email, u.password, u.address, 
            o.aadhaarNumber, o.panNumber, o.governmentDocument 
        FROM FlipFitGymCenter gc
        JOIN FlipFitGymOwner o ON gc.ownerId = o.ownerId
        JOIN FlipFitUser u ON o.ownerId = u.userId
        WHERE gc.status = 'PENDING'
    """;

 // Updated SQL query to get pending gym center requests
    public static final String GET_PENDING_GYM_CENTER_REQUESTS = """
        SELECT * FROM FlipFitGymCenter gc
        
        WHERE gc.status = 'PENDING'
    """;

    // Updated SQL query to approve a gym center
    public static final String APPROVE_GYM_CENTER =
            "UPDATE FlipFitGymCenter SET status = 'APPROVED' WHERE gymId = ?";

    // Updated SQL query to reject a gym center
    public static final String DELETE_GYM_CENTER =
            "DELETE FROM FlipFitGymCenter WHERE gymId = ?";

    /**
     * SQL query to approve a gym owner's request.
     */

    public static final String APPROVE_GYM_OWNER =
            "UPDATE FlipFitGymCenter SET status = 'APPROVED' WHERE ownerId = ?";

    /**
     * SQL query to delete gyms owned by a specific owner.
     */
    public static final String DELETE_GYMS_BY_OWNER =
            "DELETE FROM FlipFitGymCenter WHERE ownerId = ?";

    /**
     * SQL query to delete a gym owner.
     */
    public static final String DELETE_GYM_OWNER =
            "DELETE FROM FlipFitGymOwner WHERE ownerId = ?";

    // FlipFitCustomer Queries
    /**
     * SQL query to fetch all gym customers.
     */
    public static final String GET_ALL_CUSTOMERS = """
        SELECT u.name, u.email 
        FROM FlipFitGymCustomer c
        JOIN FlipFitUser u ON c.userId = u.userId
    """;

    /**
     * SQL query to fetch all gym owners who have an approved gym.
     */
    public static final String GET_ALL_OWNERS = """
        SELECT DISTINCT u.name, u.email 
        FROM FlipFitGymOwner o
        JOIN FlipFitUser u ON o.ownerId = u.userId
        WHERE EXISTS (
            SELECT 1 FROM FlipFitGymCenter gc
            WHERE gc.ownerId = o.ownerId AND gc.status = 'APPROVED'
        )
    """;

    // FlipFitBooking Queries
    public static final String VIEW_BOOKINGS =
            "SELECT * FROM FlipFitBooking WHERE centerId IN (SELECT gymId FROM FlipFitGymCenter WHERE ownerId = ?)";

    /**
     * SQL query to fetch gym names by owner ID.
     */
    public static final String GET_GYM_NAMES_BY_OWNER =
            "SELECT name FROM FlipFitGymCenter WHERE ownerId = ?";
    public static final String GET_GYM_ID_BY_OWNER =
            "SELECT * FROM FlipFitGymCenter WHERE ownerId = ?";
    
    

    /**
     * SQL query to fetch all approved gyms.
     */
    public static final String GET_ALL_GYMS = """
        SELECT name, location 
        FROM FlipFitGymCenter
        WHERE status = 'APPROVED'
    """;
    public static final String ADD_GYM_CENTER =
            "INSERT INTO FlipFitGymCenter (name, location, ownerId, adminId) VALUES (?, ?, ?, ?)";

    // FlipFitBooking Queries
    /**
     * SQL query to get booking details by booking ID.
     */
    public static final String GET_BOOKING_BY_ID =
            "SELECT * FROM FlipFitBooking WHERE bookingId = ?";

    /**
     * SQL query to get all bookings made by a user.
     */
    public static final String GET_BOOKINGS_BY_USER =
            "SELECT * FROM FlipFitBooking WHERE user_id = ?";

    /**
     * SQL query to cancel a booking by booking ID.
     */
    public static final String CANCEL_BOOKING =
            "DELETE FROM FlipFitBooking WHERE bookingId = ?";

    // FlipFitCustomer Queries
    /**
     * SQL query to register a gym customer.
     */
    public static final String REGISTER_GYM_CUSTOMER =
            "INSERT INTO FlipFitGymCustomer (userId, governmentDocumentNumber) VALUES (?, ?)";

    /**
     * SQL query to fetch available cities for gyms.
     */
    public static final String GET_AVAILABLE_CITIES =
            "SELECT DISTINCT location FROM FlipFitGymCenter WHERE status = 'APPROVED'";

    public static final String GET_GYMS_BY_CITY =
            "SELECT * FROM FlipFitGymCenter WHERE location = ? AND status = 'APPROVED'";

    /**
     * SQL query to fetch available gym slots for a specific gym.
     */
    public static final String GET_AVAILABLE_SLOTS =
            "SELECT * FROM FlipFitGymSlot WHERE gymId = ? AND numOfSeats > numOfSeatsBooked";

    /**
     * SQL query to add a user to the waitlist for a gym slot.
     */
    public static final String ADD_TO_WAITLIST =
            "INSERT INTO FlipFitGymWaitlist (userId, slotId, status) VALUES (?, ?, 'WAITING')";

    /**
     * SQL query to book a gym slot for a user.
     */
    public static final String BOOK_SLOT =
            "INSERT INTO FlipFitBooking (userId, centerId, slotId, status) VALUES (?, ?, ?, ?)";

    /**
     * SQL query to update the number of seats booked for a gym slot.
     */
    public static final String UPDATE_SLOT_BOOKING =
            "UPDATE FlipFitGymSlot SET numOfSeatsBooked = numOfSeatsBooked + 1 WHERE slotId = ?";

    /**
     * SQL query to get all bookings for a user.
     */
    public static final String GET_USER_BOOKINGS =
            "SELECT * FROM FlipFitBooking WHERE userId = ?";

    /**
     * SQL query to get all payments for a user.
     */
    public static final String GET_USER_PAYMENTS =
            "SELECT * FROM FlipFitPayment WHERE userId = ?";

    /**
     * SQL query to get all notifications for a user.
     */
    public static final String GET_USER_NOTIFICATIONS =
            "SELECT * FROM FlipFitNotification WHERE userId = ?";

    /**
     * SQL query to send a notification to a user.
     */
    public static final String NOTIFY_USER =
            "INSERT INTO FlipFitNotification (userId, message) VALUES (?, ?)";

    // FlipFitGymCenter Queries
    /**
     * SQL query to add a new gym.
     */
    public static final String ADD_GYM =
            "INSERT INTO FlipFitGymCenter (name, location, owner_id) VALUES (?, ?, ?)";

    /**
     * SQL query to update gym details.
     */
    public static final String UPDATE_GYM_DETAILS =
            "UPDATE FlipFitGymCenter SET name = ?, location = ? WHERE id = ?";

    /**
     * SQL query to get all gym centers.
     */
    public static final String GET_ALL_GYMS_FOR_GYM_CENTER =
            "SELECT * FROM FlipFitGymCenter";

    // FlipFitGymOwner Queries
    /**
     * SQL query to register a new gym owner.
     */
    public static final String REGISTER_GYM_OWNER =
            "INSERT INTO FlipFitGymOwner (ownerId, aadhaarNumber, panNumber, governmentDocument) VALUES (?, ?, ?, ?)";

    /**
     * SQL query to fetch gym owner details by user ID.
     */
    public static final String GET_OWNER_BY_ID =
            "SELECT * FROM FlipFitGymOwner WHERE user_id = ?";

    /**
     * SQL query to update gym owner details.
     */
    public static final String UPDATE_OWNER_DETAILS =
            "UPDATE gym_owners SET gym_names = ?, aadhaar_number = ?, pan_number = ?, government_document = ? WHERE user_id = ?";

    // FlipFitGymSlot Queries
    /**
     * SQL query to add or update a gym slot.
     */
    public static final String ADD_OR_UPDATE_SLOT =
            "INSERT INTO FlipFitGymSlot (gymId, startTime, endTime, numOfSeats, price) VALUES (?, ?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE startTime = ?, endTime = ?, numOfSeats = ?";

    // FlipFitPayment Queries
    /**
     * SQL query to process a payment.
     */
    public static final String PROCESS_PAYMENT =
            "INSERT INTO FlipFitPayment (userId, amount, status) VALUES (?, ?, ?)";

    /**
     * SQL query to get payment details by payment ID.
     */
    public static final String GET_PAYMENT_DETAILS =
            "SELECT * FROM FlipFitPayment WHERE id = ?";

    // FlipFitUser Queries
    /**
     * SQL query to add a new user.
     */
    public static final String ADD_USER =
            "INSERT INTO FlipFitUser (name, email, password, role, address) VALUES (?, ?, ?, ?, ?)";

    /**
     * SQL query to get a user by user ID.
     */
    public static final String GET_USER_BY_ID =
            "SELECT * FROM FlipFitUser WHERE userId = ?";

    /**
     * SQL query to get a user by email address.
     */
    public static final String GET_USER_BY_EMAIL =
            "SELECT * FROM FlipFitUser WHERE email = ?";

    /**
     * SQL query to get all users.
     */
    public static final String GET_ALL_USERS =
            "SELECT * FROM FlipFitUser";

    /**
     * SQL query to update user details.
     */
    public static final String UPDATE_USER =
            "UPDATE FlipFitUser SET name = ?, email = ?, password = ?, role = ?, address = ? WHERE userId = ?";

    /**
     * SQL query to delete a user by user ID.
     */
    public static final String DELETE_USER =
            "DELETE FROM FlipFitUser WHERE userId = ?";
}
