package com.flipkart.dao.FlipFitCustomer;

import com.flipkart.bean.*;
import com.flipkart.constant.SQLQueries;
import com.flipkart.utils.FlipFitDBUtil;
import com.flipkart.exception.SlotNotAvailableException;
import com.flipkart.exception.PaymentFailureException;  // Import the custom exception for PaymentFailureException

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FlipFitCustomerImpl implements FlipFitCustomerInterface {

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

    @Override
    public List<FlipFitGymSlot> getAvailableSlots(int gymId) throws SlotNotAvailableException {
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

            if (slots.isEmpty()) {
                throw new SlotNotAvailableException("No available slots found for gym with ID: " + gymId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SlotNotAvailableException("Database error while fetching available slots.", e);
        }
        return slots;
    }

    @Override
    public FlipFitBooking bookSlot(int userId, int gymId, Integer slotId, int booked) throws SlotNotAvailableException {
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
            } else {
                throw new SlotNotAvailableException("Failed to book slot for user ID: " + userId + " at gym ID: " + gymId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SlotNotAvailableException("Database error while booking slot.", e);
        }
    }

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
        return cities;
    }

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
        return gyms;
    }

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
            e.printStackTrace();  // Keeping this as it was
        }
    }

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
                        rs.getInt("gymId"),
                        rs.getInt("slotId"),
                        BookingStatus.valueOf(rs.getString("status")),
                        rs.getInt("amountPaid"),
                        rs.getDate("bookingDate") != null ? rs.getDate("bookingDate").toLocalDate().atStartOfDay() : null, // Convert to LocalDateTime
                        rs.getTimestamp("paymentDate") != null ? rs.getTimestamp("paymentDate").toLocalDateTime() : null // LocalDateTime for paymentDate
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    @Override
    public List<FlipFitPayment> getUserPayments(int userId) throws PaymentFailureException {
        List<FlipFitPayment> payments = new ArrayList<>();
        String query = SQLQueries.GET_USER_PAYMENTS;

        try (Connection conn = FlipFitDBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitPayment payment = new FlipFitPayment();
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setUserId(rs.getInt("userId"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setStatus(rs.getString("status"));
                payment.setPaymentDate(rs.getDate("paymentDate") != null ? rs.getDate("paymentDate").toLocalDate() : null);

                payments.add(payment);
            }
        } catch (SQLException e) {
            throw new PaymentFailureException("Failed to fetch payments for user ID: " + userId, e);
        }

        return payments;
    }
}
