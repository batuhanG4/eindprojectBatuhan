package com.batuhan.eindprojectBatuhan.service;

import com.batuhan.eindprojectBatuhan.model.Cart;
import com.batuhan.eindprojectBatuhan.model.User;
import com.batuhan.eindprojectBatuhan.repository.CartRepository;
import com.batuhan.eindprojectBatuhan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor-based dependency injection
    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Methode voor het registreren van een gebruiker
    public void registerUser(User user) {
        // Controleer of het emailadres al bestaat in de database
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        // Versleutel het wachtwoord voor veiligheid
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sla de gebruiker op in de database
        userRepository.save(user);

        // Maak een winkelmandje aan voor de gebruiker
        createCartForUser(user);
    }

    // Zoek een gebruiker op basis van de gebruikersnaam (email in dit geval)
    public User findByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Maak een winkelmandje aan voor de gebruiker
    public void createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);  // Koppel het winkelmandje aan de gebruiker
        cartRepository.save(cart);  // Sla het winkelmandje op in de database
    }
}
