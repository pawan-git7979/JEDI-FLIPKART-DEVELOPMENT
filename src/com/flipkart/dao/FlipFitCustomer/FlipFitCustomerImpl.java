package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.*;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlipFitCustomerImpl implements FlipFitCustomerInterface {

    /**
     * Registers a new gym customer in the system.
     *
     * @param customer The FlipFitGymCustomer object containing customer details.
     * @return true if the registration was successful, false otherwise.
     */
    public boolean registerGymCustomer(FlipFitGymCustomer customer) {
        try (Connection conn = FlipFitDBUtil.getConnection()) {
            conn.setAutoCommit(false);

            String query = SQLQueries.REGISTER_GYM_CUSTOMER;
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, customer.getUserId());
                stmt.setString(2, customer.getGovernmentDocumentNumber());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("❌ ERROR: Customer insertion failed!");
                    conn.rollback();  // Rollback transaction on failure
                    return false;
                }
                conn.commit(); // Commit the transaction
                return true;
            } catch (SQLException e) {
                conn.rollback();  // Ensure rollback on any exception
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the list of available cities where gyms are located.
     *
     * @return A list of cities where gyms are available.
     */
    @Override
    public List<String> getAvailableCities() {
        List<String> cities = new ArrayList<>();
        String query = SQLQueries.GET_AVAILABLE_CITIES;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cities.add(rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities.stream()
                .sorted() // Sort by city name
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of gyms located in a specific city.
     *
     * @param city The name of the city to search for gyms.
     * @return A list of FlipFitGymCenter objects located in the specified city.
     */
    @Override
    public List<FlipFitGymCenter> getGymsByCity(String city) {
        List<FlipFitGymCenter> gyms = new ArrayList<>();
        String query = SQLQueries.GET_GYMS_BY_CITY;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                gyms.add(new FlipFitGymCenter(
                        rs.getInt("gymId"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("adminId"),
                        rs.getInt("ownerId"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gyms.stream()
                .sorted(Comparator.comparing(FlipFitGymCenter::getName)) // Sort by gym name
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of available gym slots for a given gym.
     *
     * @param gymId The ID of the gym to retrieve slots for.
     * @return A list of available gym slots.
     */
    @Override
    public List<FlipFitGymSlot> getAvailableSlots(int gymId) {
        List<FlipFitGymSlot> slots = new ArrayList<>();
        String query = SQLQueries.GET_AVAILABLE_SLOTS;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                slots.add(new FlipFitGymSlot(
                        rs.getInt("slotId"),
                        rs.getInt("gymId"),
                        rs.getString("startTime"),
                        rs.getString("endTime"),
                        rs.getInt("numOfSeats"),
                        rs.getInt("numOfSeatsBooked"),
                        rs.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots.stream()
                .sorted(Comparator.comparing(FlipFitGymSlot::getStartTime)) // Sort by start time
                .collect(Collectors.toList());
    }

    /**
     * Adds a user to the waitlist for a specific gym slot.
     *
     * @param userId The ID of the user to add to the waitlist.
     * @param slotId The ID of the gym slot to add the user to.
     */
    @Override
    public void addToWaitlist(int userId, int slotId) {
        String query = SQLQueries.ADD_TO_WAITLIST;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, slotId);
            stmt.executeUpdate();
            System.out.println("User " + userId + " added to waitlist for slot " + slotId);
        } catch (SQLException e) {
            e.printStackTrace();  // Log exception if any
        }
    }

    /**
     * Books a gym slot for a user.
     *
     * @param userId The ID of the user making the booking.
     * @param gymId The ID of the gym where the booking is being made.
     * @param slotId The ID of the slot to be booked (null if waitlisted).
     * @param booked 1 if the slot is successfully booked, 0 if waitlisted.
     * @return A FlipFitBooking object representing the booking.
     */
    @Override
    public FlipFitBooking bookSlot(int userId, int gymId, Integer slotId, int booked) {
        String query = SQLQueries.BOOK_SLOT;
        String updateSlotQuery = SQLQueries.UPDATE_SLOT_BOOKING;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement updateSlotStmt = conn.prepareStatement(updateSlotQuery)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, gymId);

            // Assign slotId = 0 if null (for waitlist)
            if (slotId == null) {
                slotId = 0;
            }
            stmt.setInt(3, slotId);

            String status = (booked == 1) ? "BOOKED" : "WAITLISTED";
            stmt.setString(4, status);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                if (slotId > 0 && booked == 1) {
                    updateSlotStmt.setInt(1, slotId);
                    updateSlotStmt.executeUpdate();
                }

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new FlipFitBooking(
                            generatedKeys.getInt(1),
                            userId,
                            gymId,
                            slotId,
                            (booked == 1) ? BookingStatus.BOOKED : BookingStatus.WAITLISTED,
                            0,
                            null,
                            null
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves the list of bookings for a particular user.
     *
     * @param userId The ID of the user whose bookings are to be retrieved.
     * @return A list of FlipFitBooking objects representing the user's bookings.
     */
    @Override
    public List<FlipFitBooking> getUserBookings(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        String query = SQLQueries.GET_USER_BOOKINGS;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(new FlipFitBooking(
                        rs.getInt("bookingId"),
                        rs.getInt("userId"),
                        rs.getInt("centerId"),
                        rs.getInt("slotId"),
                        BookingStatus.valueOf(rs.getString("status")), // Convert String to ENUM
                        rs.getInt("paymentId"),
                        rs.getTimestamp("bookingTime").toLocalDateTime(), // Convert Timestamp to LocalDateTime
                        rs.getTimestamp("updatedTime") != null ? rs.getTimestamp("updatedTime").toLocalDateTime() : null // Handle NULL case
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings.stream()
                .sorted(Comparator.comparing(FlipFitBooking::getBookingTime)) // Sort by booking time
                .collect(Collectors.toList());
    }




    @Override
    public boolean deleteBooking(int bookingId) {
        String query = SQLQueries.CANCEL_BOOKING;
        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a list of payments made by a specific user.
     *
     * @param userId The ID of the user whose payments are to be retrieved.
     * @return A list of FlipFitPayment objects representing the user's payments.
     */
    @Override
    public List<FlipFitPayment> getUserPayments(int userId) {
        List<FlipFitPayment> payments = new ArrayList<>();
        String query = SQLQueries.GET_USER_PAYMENTS;  // Ensure table name is correct

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitPayment payment = new FlipFitPayment(
                        rs.getInt("paymentId"),
                        rs.getInt("userId"),
                        rs.getDouble("amount"),
                        rs.getString("status")
                );
                payments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    /**
     * Retrieves a list of notifications for a specific user.
     *
     * @param userId The ID of the user whose notifications are to be retrieved.
     * @return A list of FlipFitNotification objects representing the user's notifications.
     */
    @Override
    public List<FlipFitNotification> getUserNotifications(int userId) {
        List<FlipFitNotification> notifications = new ArrayList<>();
        String query = SQLQueries.GET_USER_NOTIFICATIONS;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitNotification notification = new FlipFitNotification(
                        rs.getInt("notificationId"),
                        rs.getInt("userId"),
                        rs.getString("message"),
                        rs.getString("status")
                );
                notifications.add(notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
