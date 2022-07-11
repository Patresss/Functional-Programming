package com.globallogic.javaacademy.functional.optional;

import com.globallogic.javaacademy.functional.optional.model.Concert;
import com.globallogic.javaacademy.functional.optional.model.MusicBand;
import com.globallogic.javaacademy.functional.optional.model.MusicStar;
import com.globallogic.javaacademy.model.Address;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class OptionalApp {

    private static final List<Concert> concerts = List.of(
            new Concert("Woodstock", new MusicBand("Kombi", new MusicStar("Jan", "Nowak", new Address("Kraków", "Długa")))),
            new Concert("Festyn", new MusicBand("Ich troje", new MusicStar("Jan", "Nowak", null))),
            new Concert("Global", new MusicBand("Abba", null)),
            new Concert("Unknown", null)
    );


    @Test
    public void withoutIf() {
        concerts.forEach(concert -> System.out.println(concert.getMusicBand().getMusicStar().getAddress().getCity()));
    }

    @Test
    public void usingIf() {
        // concerts.forEach(concert -> System.out.println(concert.getMusicBand().getMusicStar().getAddress().getCity()));
        concerts.forEach(concert -> {
            if (concert != null) {
                if (concert.getMusicBand() != null) {
                    if (concert.getMusicBand().getMusicStar() != null) {
                        if (concert.getMusicBand().getMusicStar().getAddress() != null) {
                            if (concert.getMusicBand().getMusicStar().getAddress().getCity() != null) {
                                System.out.println(concert.getMusicBand().getMusicStar().getAddress().getCity());
                            }
                        }
                    }
                }
            }
        });
    }

    @Test
    public void optional() {
        final String name = "abc";
        Optional.of(name);
        Optional.ofNullable(name);
        Optional.empty();
    }

    @Test
    public void usingOptional() {
        concerts.forEach(concert -> Optional.ofNullable(concert)
                .map(Concert::getMusicBand)
                .map(MusicBand::getMusicStar)
                .map(MusicStar::getAddress)
//                .flatMap(MusicStar::getAddress)
                .map(Address::getCity)
                .ifPresent(System.out::println)
        );
    }

    @Test
    public void orElse() {
        String name = Optional.ofNullable(getNameFromApi())
                .orElse(calculateDefaultName());

        System.out.println("Result: \n");
        System.out.println(name);
    }

    @Test
    @SuppressWarnings("all")
    public void orElseVsOrElseGet() {
        System.out.println("orElse:");
        String name = Optional.ofNullable("VALUE")
                .orElse(calculateDefaultName());
        String name2 = Optional.ofNullable(getNameFromApi())
                .orElse(calculateDefaultName());

        System.out.println("orElseGet:");
        String name3 = Optional.ofNullable("VALUE")
                .orElseGet(() -> calculateDefaultName());
        String name4 = Optional.ofNullable(getNameFromApi())
                .orElseGet(() -> calculateDefaultName());
    }

    @Test
    public void filter() {
        concerts.forEach(concert -> Optional.ofNullable(concert)
                .map(Concert::getMusicBand)
                .filter(musicBand -> musicBand.getName().length() >= 5)
                .ifPresent(System.out::println)
        );
    }

    public Optional<Address> findAddress(Concert concert) {
        return Optional.ofNullable(concert)
                .map(Concert::getMusicBand)
                .map(MusicBand::getMusicStar)
                .map(MusicStar::getAddress);
    }

    // ================================================================
    //  Things to avoid
    // ================================================================

    // ===================================
    // Optional as an argument
    public Optional<Address> findAddress(Optional<Concert> concert) {
        return concert
                .map(Concert::getMusicBand)
                .map(MusicBand::getMusicStar)
                .map(MusicStar::getAddress);
    }

    // ===================================
    // Optional isPresent and get
    public void optionalIf() {
        final Optional<String> name = Optional.ofNullable(calculateDefaultName());
        if (name.isPresent()) {
            System.out.println(name.get());
        }

//        final String name = calculateDefaultName();
//        if (name != null) {
//            System.out.println(name);
//        }
    }

    // ===================================
    // Return Optional if list
    public Optional<List<String>> calculateNames() {
        return Optional.empty(); // List.of();
    }

    // ===================================
    // Assign null to Optional
    public void nullOptional() {
        Optional<String> name = null;
    }



    private String getNameFromApi() {
        return null;
    }

    private String calculateDefaultName() {
        System.out.println("... calculate default name");
        return "DEFAULT";
    }


}
