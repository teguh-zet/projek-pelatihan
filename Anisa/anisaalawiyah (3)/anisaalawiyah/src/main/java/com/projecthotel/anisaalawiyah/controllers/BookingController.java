// package com.projecthotel.anisaalawiyah.controllers;

// import java.time.LocalDate;
// import java.time.temporal.ChronoUnit;
// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.projecthotel.anisaalawiyah.models.Booking;
// import com.projecthotel.anisaalawiyah.models.Room;
// import com.projecthotel.anisaalawiyah.reporitories.BookingRepository;
// import com.projecthotel.anisaalawiyah.reporitories.CustomerRepository;
// import com.projecthotel.anisaalawiyah.reporitories.RoomRepository;

// @Controller
// public class BookingController {
//     @Autowired
//     BookingRepository bookingRepository;

//     @Autowired
//     CustomerRepository customerRepository;

//     @Autowired
//     RoomRepository roomRepository;


//     @GetMapping("/Booking")
//     public String allBooking(Model model){
//       Booking b= new Booking();

//       for (Booking a :bookingRepository.findAll()) {
//         b=a;
//       }
//         model.addAttribute("booking",b);
//         return"show-booking";
//     }
    
//      @GetMapping("/admin-booking")
//     public String adminBooking(Model model){
//         model.addAttribute("booking",bookingRepository.findAll());
//         return"admin-booking";
//     }

    
//     // @GetMapping("/add-booking")
//     // public String addBooking(Model model){
//     //     Booking booking = new Booking();
//     //     Room room=roomRepository.getReferenceById(RoomController.tampungId);
//     //     System.out.println(RoomController.tampungId);
//     //     System.out.println(room);
//     //     model.addAttribute("booking",booking);
//     //     model.addAttribute("customer", customerRepository.findAll());
//     //     model.addAttribute("roomType", room.getType());
//     //     return"add-booking";
//     // }
   
//     @GetMapping("/add-booking")
//     public String addBooking(Model model){
//         Booking booking = new Booking();
//         Room room=roomRepository.getReferenceById(RoomController.tampungId);
//         // System.out.println(RoomController.tampungId);
//         model.addAttribute("booking",booking);
//         model.addAttribute("customer", customerRepository.findAll());
//         model.addAttribute("room", room);
//         return"add-booking";
//     }
 
//     @PostMapping("/save-booking")
//     public String saveBoking(Booking booking) {
//         Room pesanRoom=roomRepository.getReferenceById(RoomController.tampungId);
//         long daysBetween = calculateDaysBetween(booking.getCheckInDate(),booking.getCheckOutDate());
//         ArrayList<Room> room = new ArrayList<>();
//         Room room2 = booking.getIdRoom();
//         int idx = -1;
//         room.addAll(roomRepository.findAll());
//         for(int i = 0; i < room.size(); i++){
//           if(room.get(i).getIdRoom().equals(room2.getIdRoom())){
//               idx = i;
//           }
//         }
//         pesanRoom.setStatus("Tidak Tersedia");
//         roomRepository.save(pesanRoom);
//         booking.setDuration((int) daysBetween);
//         booking.setTotalPemesanan(booking.getDuration()* room.get(idx).getPrice());
//         bookingRepository.save(booking);

//         return "redirect:/Booking";
//     }
    
      
//       // Metode untuk menghitung durasi antara dua tanggal
//       private long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
//         return ChronoUnit.DAYS.between(startDate, endDate);
//       }
//     // @PostMapping("/save-booking")
//     // public String saveBoking(@ModelAttribute("booking") Booking booking) {
//     //     Room pesanRoom=roomRepository.getReferenceById(RoomController.tampungId);
//     //     long daysBetween = calculateDaysBetween(booking.getCheckInDate(),booking.getCheckOutDate());
//     //     ArrayList<Room> room = new ArrayList<>();
//     //     Room room2 = booking.getIdRoom();
//     //     if (booking.getIdRoom() == null) {
//     //       System.out.println("Data Null");
//     //     }else {
//     //       System.out.println("Data tidak null");
//     //     }
       
//     //     int idx = -1;
//     //     room.addAll(roomRepository.findAll());
//     //     for(int i = 0; i < room.size(); i++){
//     //       if(room.get(i).getIdRoom().equals(room2.getIdRoom())){
//     //           idx = i;
//     //       }
//     //     }
//     //     pesanRoom.setStatus("Tidak Tersedia");
//     //     roomRepository.save(pesanRoom);
//     //     booking.setDuration((int) daysBetween);
//     //     booking.setTotalPemesanan(booking.getDuration()* room.get(idx).getPrice());
//     //     bookingRepository.save(booking);

//     //     return "redirect:/Booking";
//     // }
    
      
//     //   // Metode untuk menghitung durasi antara dua tanggal
//     //   private long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
//     //     return ChronoUnit.DAYS.between(startDate, endDate);
//     //   }

//       @GetMapping("/update-Booking/{idBooking}")
//       public String updateBooking(@PathVariable Integer idBooking,Model model){
//         Booking booking =new Booking();
//         model.addAttribute("booking", booking);
//         model.addAttribute("customer",customerRepository.findAll());
//         model.addAttribute("room", roomRepository.findAll());
//         return"update-booking";
//       }

//       @PostMapping("/update-booking")
//       public String updateBooking(@ModelAttribute("booking") Booking booking){
//         bookingRepository.save(booking);
//         return"redirect:/admin-booking";
//       }

      
//       @GetMapping("delete-booking/{idBooking}")
//       public String deleteAdmin(@PathVariable Integer idBooking){
//         bookingRepository.deleteById(idBooking);
//         return"redirect:/admin-booking";
//       }

//        @GetMapping("delete/{idBooking}")
//         public String delete(@PathVariable Integer idBooking){
//         bookingRepository.deleteById(idBooking);
//         return"redirect:/home";
//       }

//     // // Metode untuk memeriksa ketersediaan rentang tanggal
//     // private boolean isDateRangeAvailable(Booking booking) {
//     //     // Implementasikan logika 
//     // List<Booking> existingReservations = bookingRepository.findAll();

//     //     for (Booking existingReservation : existingReservations) {
//     //         // Periksa apakah ada overlap dengan tanggal yang sudah ada
//     //         if (existingReservation.isDateRangeOverlap(booking)) {
//     //             return false; // Rentang tanggal tidak tersedia
//     //         }
//     //     }

//     //     return true; // Rentang tanggal tersedia
//     // }


//     //  @PostMapping("/update-customer{idCustomer}")
//     // public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
//     //     // validasi di tanggal
//     //     if (isDateRangeAvailable(customer)) {
//     //         // Hitung durasi antara check-in dan check-out
//     //         long daysBetween = calculateDaysBetween(customer.getCheckInDate(), customer.getCheckOutDate());

//     //         // Set durasi pada objek customer
//     //         customer.setDuration((int) daysBetween);

//     //         customerRepository.save(customer);
//     //         return "redirect:/customer";
//     //     } else {
//     //         // ini kalau Tanggal tidak tersedia
//     //         model.addAttribute("error", "Tanggal yang dipilih tidak tersedia.");
//     //         return "update-customer";
//     //     }
//     // }

// }
package com.projecthotel.anisaalawiyah.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projecthotel.anisaalawiyah.models.Booking;
import com.projecthotel.anisaalawiyah.models.Room;
import com.projecthotel.anisaalawiyah.reporitories.BookingRepository;
import com.projecthotel.anisaalawiyah.reporitories.CustomerRepository;
import com.projecthotel.anisaalawiyah.reporitories.RoomRepository;

@Controller
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoomRepository roomRepository;


    @GetMapping("/Booking")
    public String allBooking(Model model){
      Booking b= new Booking();

      for (Booking a :bookingRepository.findAll()) {
        b=a;
      }
        model.addAttribute("booking",b);
        return"show-booking";
    }
     @GetMapping("/booking-admin")
    public String adminBooking(Model model){
        model.addAttribute("booking",bookingRepository.findAll());
        return"admin-booking";
    }

    
    @GetMapping("/add-booking")
    public String addBooking(Model model){
        Booking booking = new Booking();
        Room room=roomRepository.getReferenceById(RoomController.tampungId);
        // System.out.println(RoomController.tampungId);
        model.addAttribute("booking",booking);
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("room", room);
        return"add-booking";
    }
   
 
    @PostMapping("/save-booking")
    public String saveBoking(Booking booking) {
        Room pesanRoom=roomRepository.getReferenceById(RoomController.tampungId);
        long daysBetween = calculateDaysBetween(booking.getCheckInDate(),booking.getCheckOutDate());
        ArrayList<Room> room = new ArrayList<>();
        Room room2 = booking.getIdRoom();
        int idx = -1;
        room.addAll(roomRepository.findAll());
        for(int i = 0; i < room.size(); i++){
          if(room.get(i).getIdRoom().equals(room2.getIdRoom())){
              idx = i;
          }
        }
        pesanRoom.setStatus("Tidak Tersedia");
        roomRepository.save(pesanRoom);
        booking.setDuration((int) daysBetween);
        booking.setTotalPemesanan(booking.getDuration()* room.get(idx).getPrice());
        bookingRepository.save(booking);

        return "redirect:/Booking";
    }
    
      
      // Metode untuk menghitung durasi antara dua tanggal
      private long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
      }

      @GetMapping("/update-Booking/{idBooking}")
      public String updateBooking(@PathVariable Integer idBooking,Model model){
        Booking booking =new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("customer",customerRepository.findAll());
        model.addAttribute("room", roomRepository.findAll());
        return"update-booking";
      }

      @PostMapping("/update-booking")
      public String updateBooking(@ModelAttribute("booking") Booking booking){
        bookingRepository.save(booking);
        return"redirect:/Booking";
      }

      
      @GetMapping("delete-booking/{idBooking}")
      public String delete(@PathVariable Integer idBooking){
        bookingRepository.deleteById(idBooking);
        return"redirect:/Booking";
      }


}
