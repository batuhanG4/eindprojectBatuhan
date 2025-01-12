# Eindejaarsproject Huurplatform

---

## Projectomschrijving
Het doel van dit project is om een gebruiksvriendelijk huurplatform te creëren waar gebruikers verschillende elektrische producten kunnen huren. Denk aan lampjes, kabels en huisapparatuur.

---

## Functionaliteiten
1. **Login/Registersysteem**:
    - Gebruikers kunnen een account aanmaken en inloggen.
    - Wachtwoorden worden beveiligd opgeslagen met **BCrypt-encryptie** voor extra veiligheid.

2. **Productcatalogus**:
    - Overzicht van alle beschikbare producten.
    - Mogelijkheid om producten te filteren op categorie en prijs.

3. **Winkelmandje**:
    - Gebruikers kunnen producten toevoegen aan hun winkelmandje.
    - Het winkelmandje toont een totaalprijs.

4. **Bevestigingspagina**:
    - Gebruikers kunnen hun huur bevestigen.
    - Na de bevestiging krijgt de user een een bevestigingspagina.

---

## Technologieën
- **Backend**: Java Spring Boot
- **Database**: MySQL (via XAMPP)
- **Frontend**: HTML (met Thymeleaf templates) en CSS
- **Beveiliging**: BCrypt-wachtwoordencryptie

---

## Installatiegids

### Vereisten
- **Java JDK 17**
- **Maven** (voor dependency management)
- **XAMPP** (voor MySQL-database)
- **IntelliJ IDEA** (of een andere Java IDE)

### Installeren
1. Clone de repository:
   ```bash
   git clone <repository-url>
1. Open het project in IntelliJ IDEA.
2. Start **XAMPP** en zet **Apache** en **MySQL** aan.
3. Maak een nieuwe database aan met de naam: `huurapplicatie`.
4. Tabellen worden automatisch gegenereerd door Hibernate bij het opstarten van de applicatie.
5. Start de applicatie via IntelliJ (Start-knop rechtsboven).
6. Bezoek de applicatie op [http://localhost:9000](http://localhost:9000).

---

## Databaseoverzicht

De database `huurapplicatie` bevat de volgende tabellen:

- **`user`**: Opslag van gebruikersinformatie zoals e-mail, gebruikersnaam en geëncrypteerd wachtwoord.
- **`product`**: Lijst met beschikbare producten voor verhuur, inclusief naam, prijs en categorie.
- **`category`**: Beschikbare productcategorieën (bijv. lampjes, kabels).
- **`cart`**: Een winkelmandje gekoppeld aan een gebruiker.
- **`cart_item`**: Relatie tussen een product en een winkelmandje, inclusief de hoeveelheid.
- **`cart_products`**: Koppelingstabel tussen producten en winkelmandjes.

---

## Projectstructuur

### Codecomponenten

- **Controllers**:  
  Behandelen inkomende HTTP-verzoeken en sturen gegevens naar de juiste Thymeleaf-templates.

- **Modellen**:  
  Vertegenwoordigen de database-entiteiten, zoals `User`, `Product`, en `Cart`.

- **Repositories**:  
  Verantwoordelijk voor het communiceren met de database, zoals het ophalen en opslaan van gegevens.

- **Services**:  
  Bevatten de businesslogica, zoals het berekenen van de totaalprijs of het ophalen van orders.

- **SecurityConfig**:  
  Regelt authenticatie en autorisatie, inclusief login- en beveiligingsregels.

---

## Frontend (HTML & CSS)

- **Thymeleaf**:  
  Templates worden gebruikt voor dynamische weergave van gegevens op pagina's zoals `catalog.html` en `cart.html`.

- **CSS**:  
  De stijl van de applicatie wordt verzorgd door `styles.css`.

---

## Bronnen

Hier is een lijst van nuttige bronnen die tijdens de ontwikkeling van het project zijn gebruikt:

1. [Spring Initializr](https://start.spring.io/) - Voor het genereren van een Spring Boot-project.
2. [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/) - Officiële documentatie van Spring Boot.
3. [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html) - Voor hulp bij het werken met Thymeleaf.
4. [Baeldung](https://www.baeldung.com/) - Uitgebreide tutorials over Spring Boot, JPA, en beveiliging.
5. [Java Guides](https://www.javaguides.net/) - Praktische gidsen over Spring Boot en Java.
6. [W3Schools](https://www.w3schools.com/) - Voor HTML, CSS, en andere webontwikkeling gerelateerde onderwerpen.
7. [Stack Overflow](https://stackoverflow.com/) - Voor het oplossen van programmeervragen.
8. [Mkyong](https://mkyong.com/) - Duidelijke en eenvoudige voorbeelden van Spring Boot en Java.
9. [Spring Academy](https://spring.academy/) - Officiële cursussen van Spring voor beginners en gevorderden.
10. [FreeCodeCamp](https://www.freecodecamp.org/) - Gratis tutorials en blogs over Spring Boot en webontwikkeling.
11. [GeeksforGeeks](https://www.geeksforgeeks.org/) - Artikelen en tutorials over Java en Spring Boot.
12. [DZone](https://dzone.com/) - Artikelen en nieuws over Spring Boot en andere technologieën.
13. [Java Brains](https://javabrains.io/) - Video-tutorials over Java, Spring Boot, en andere programmeertalen.
14. [TutorialsPoint](https://www.tutorialspoint.com/) - Tutorials en handleidingen over Java en Spring Boot.
15. [ChatGPT](https://chat.openai.com) - Voor hulp met uitleg en codevoorbeelden.
16. [Video Leerkracht](https://ehb.instructuremedia.com/embed/b70df4a0-c46f-4b86-a845-e04124d60a31) - Demo Orm video van David Van Steertegem voor een goede basis.

---

## Chathistories

1. [Nieuwe producten & Categorieën](https://chatgpt.com/share/67832b5f-8044-8001-a245-b2a02667642d)
2. [Errors over CartRepository](https://chatgpt.com/share/67832c00-1fc8-8001-8a45-e89d9ab603de)
3. [Login & registratie opbouw & errors](https://chatgpt.com/share/67832c7c-ae40-8001-9ebf-01207810c72d)

## Bijdrage van ChatGPT
- Voor dit project is ChatGPT gebruikt bij het oplossen van fouten, het vinden van problemen in de code, en het leggen van een basis voor sommige codecomponenten. ChatGPT bood waardevolle ondersteuning door het snel genereren van voorbeelden en uitleg, wat het ontwikkelingsproces aanzienlijk heeft versneld.
---
