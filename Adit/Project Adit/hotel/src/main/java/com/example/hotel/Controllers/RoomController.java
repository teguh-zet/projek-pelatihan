package com.example.hotel.Controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotel.Model.Booking;
import com.example.hotel.Model.Room;
import com.example.hotel.Repository.BookingRepository;
import com.example.hotel.Repository.RoomRepository;

@Controller
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    public static Long tampungId = 0L;

    @GetMapping("add-room")
    public String addNewShirt(Model model) {
        Room kamar = new Room();
        model.addAttribute("room", kamar);
        return "rooms";
    }

    @PostMapping("/save-room")
    public String saveRoom(
            @RequestParam("name") String name,
            @RequestParam("capacity") String capacity,
            @RequestParam("size") String size,
            @RequestParam("status") String status,
            @RequestParam("price") Integer price,
            @RequestParam("typeRoom") String typeRoom,
            @RequestParam("detail") String detail,
            @RequestPart("image") MultipartFile multipartFile,
            Model model) {
        try {
            Room kamar = new Room();
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    multipartFile.getOriginalFilename());
            multipartFile.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + multipartFile.getOriginalFilename();
            kamar.setName(name);
            kamar.setCapacity(capacity);
            kamar.setSize(size);
            kamar.setStatus(status);
            kamar.setPrice(price);
            kamar.setDetail(detail);
            kamar.setTypeRoom(typeRoom);
            kamar.setImage(url);
            roomRepository.save(kamar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/update-room/{id}")
    public String updateRoom(@PathVariable(value = "id") Long id, Model model) {
        // Use findById to get a managed entity
        Room room = roomRepository.findById(id).orElse(null);

        if (room != null) {
            model.addAttribute("room", room);
            return "update-room";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/save-update")
    public String saveUpdatedRoom(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("capacity") String capacity,
            @RequestParam("size") String size,
            @RequestParam("status") String status,
            @RequestParam("typeRoom") String typeRoom,
            @RequestParam("price") Integer price,
            @RequestParam("detail") String detail,
            @RequestPart("image") MultipartFile multipartFile) {
        Room existingRoom = roomRepository.findById(id).orElse(null);

        if (existingRoom != null) {
            existingRoom.setName(name);
            existingRoom.setCapacity(capacity);
            existingRoom.setSize(size);
            existingRoom.setStatus(status);
            existingRoom.setPrice(price);
            existingRoom.setDetail(detail);
            existingRoom.setTypeRoom(typeRoom);

            try {
                if (!multipartFile.isEmpty()) {
                    // If a new image is provided, update the image
                    Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                            multipartFile.getOriginalFilename());
                    multipartFile.transferTo(targetPath.toFile());
                    String url = "http://localhost:8080/" + multipartFile.getOriginalFilename();
                    existingRoom.setImage(url);
                }

                roomRepository.save(existingRoom);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/error";
            }
        }

        return "redirect:/";
    }

    @GetMapping("delete-room/{id}")
    public String deleteRoom(@PathVariable(value = "id") Long id) {
        roomRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/room")
    public String Daftar(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", roomRepository.findAll());
        return "roomSuite";
    }
  
    // @PostMapping("/save")
    // public String Customer(Booking booking) {

    // bookingRepository.save(booking);
    // for (Booking e : bookingRepository.findAll()) {
    // if (e.getEmail().equals(booking.getEmail())) {
    // tampBookingId = booking.getId();
    // break;
    // }
    // }

    // return "redirect:/payment";
    // }

    // @GetMapping("/payment")
    // public String Payment(Long id, Model model) {
    // System.out.println(tampRoomId + "," + tampBookingId);
    // model.addAttribute("room", roomRepository.getReferenceById(tampRoomId));
    // model.addAttribute("booking",
    // bookingRepository.getReferenceById(tampBookingId));
    // model.addAttribute("add",new Payment());
    // return "payment";
    // }

    @GetMapping("/booking/{id}")
    // @GetMapping("/pesan/{id}")
    public String pesan(@PathVariable Long id) {
        tampungId = id;
        return "redirect:/add-booking/{id}";
    }

}
