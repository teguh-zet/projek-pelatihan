package com.example.hotel.Controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hotel.Model.Booking;
import com.example.hotel.Model.Room;
import com.example.hotel.Model.User;
import com.example.hotel.Model.UserLogin;
import com.example.hotel.Repository.BookingRepository;
// import com.example.hotel.Repository.CustomerRepository;
import com.example.hotel.Repository.RoomRepository;
import com.example.hotel.Repository.UserLoginRepository;
import com.example.hotel.Repository.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    // @Autowired
    // CustomerRepository customerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/Booking")
    public String allBooking(Model model) {
        // List<Booking> booking = bookingRepository.findAll();
        // model.addAttribute("booking", booking);
        List<UserLogin> userLogins = userLoginRepository.findAll();
        User user = userRepository.getReferenceById(userLogins.get(0).getId());
        List<Booking> bookings = bookingRepository.findAll();

        // for (Booking b : bookings) {
        //     if (b.getUser().getId() == user.getId()) {
        //         model.addAttribute("booking", bookingRepository.getReferenceById(b.getIdBooking()));
        //         break;
        //     }
        // }
        model.addAttribute("booking", bookingRepository.findTopByOrderByIdBookingDesc());

        // Booking booking = bookingRepository.getReferenceById()
        return "payment";
    }

    @GetMapping("/add-booking/{id}")
    public String addBooking(@PathVariable Long id, Model model) {
        Booking booking = new Booking();
        booking.setIdRoom(roomRepository.getReferenceById(id));
        model.addAttribute("booking", booking);
        model.addAttribute("room", roomRepository.getReferenceById(id));
        return "addBooking";
    }

    @PostMapping("/save-booking")
    public String saveBoking(@ModelAttribute Booking booking) {

        Room pesanRoom = roomRepository.getReferenceById(RoomController.tampungId);
        long daysBetween = calculateDaysBetween(booking.getCheckInDate(), booking.getCheckOutDate());
        pesanRoom.setStatus("Tidak Tersedia");
        roomRepository.save(pesanRoom);
        booking.setDuration((int) daysBetween);
        booking.setTotalPemesanan(
                booking.getDuration() * roomRepository.getReferenceById(booking.getIdRoom().getId()).getPrice());
        List<UserLogin> userLogins = userLoginRepository.findAll();
        User user = userRepository.getReferenceById(userLogins.get(0).getId());
        booking.setUser(user);
        bookingRepository.save(booking);

        return "redirect:/Booking";
    }

    private long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    @GetMapping("/update-customer/{id}")
    public String updateCus(@PathVariable(value = "id") Integer id, Model model) {
        Booking customers = bookingRepository.getReferenceById(id);
        model.addAttribute("customer", customers);
        return "updateCustomer";
    }
       @GetMapping("/update-booking/{id}")
    public String updatBok(@PathVariable(value = "id") Integer id, Model model) {
        Booking kings = bookingRepository.getReferenceById(id);
        model.addAttribute("booking", kings);
        return "updateBooking";
    }

    // @GetMapping("/riwayat")
    // public String Riwayat(Model model) {
    // List<UserLogin> userLogins = userLoginRepository.findAll();
    // User user = userRepository.getReferenceById(userLogins.get(0).getId());
    // model.addAttribute("data", bookingRepository.findByUser(user));
    // return "myBookings";
    // }

}