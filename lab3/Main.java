import java.util.*;

class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Hall {
    private String name;
    private int rows;
    private int seatsPerRow;
    private boolean[][] seats;

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new boolean[rows][seatsPerRow];
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public boolean isSeatAvailable(int row, int seat) {
        return !seats[row][seat];
    }

    public void bookSeat(int row, int seat) {
        seats[row][seat] = true;
    }

    public void printSeatLayout() {
        System.out.println("Seat Layout for Hall: " + name);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (seats[i][j]) {
                    System.out.print("X "); // Занятое место обозначается X
                } else {
                    System.out.print("O "); // Свободное место обозначается O
                }
            }
            System.out.println();
        }
    }
}

class Showtime {
    private Movie movie;
    private Hall hall;
    private Date time;

    public Showtime(Movie movie, Hall hall, Date time) {
        this.movie = movie;
        this.hall = hall;
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public Hall getHall() {
        return hall;
    }

    public Date getTime() {
        return time;
    }
}

class Movie {
    private String title;
    private int duration;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}

class TicketSystem {
    private List<Cinema> cinemas;
    private List<Showtime> showtimes;

    public TicketSystem() {
        this.cinemas = new ArrayList<>();
        this.showtimes = new ArrayList<>();
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public Showtime findNearestShowtime(String movieTitle) {
        Showtime nearestShowtime = null;
        Date currentTime = new Date();

        for (Showtime showtime : showtimes) {
            if (showtime.getMovie().getTitle().equals(movieTitle) && showtime.getTime().after(currentTime)) {
                if (nearestShowtime == null || showtime.getTime().before(nearestShowtime.getTime())) {
                    nearestShowtime = showtime;
                }
            }
        }

        return nearestShowtime;
    }

    public void bookTicket(Showtime showtime, int row, int seat) {
        Hall hall = showtime.getHall();
        hall.bookSeat(row, seat);
    }

    public void printSeatLayout(Showtime showtime) {
        Hall hall = showtime.getHall();
        hall.printSeatLayout();
    }
}

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();

        // Добавление кинотеатра
        Cinema cinema = new Cinema("Cinema City");
        ticketSystem.addCinema(cinema);

        // Добавление зала в кинотеатр
        Hall hall = new Hall("Hall 1", 5, 10);
        cinema.addHall(hall);

        // Создание фильма
        Movie movie = new Movie("Avengers: Endgame", 180);

        // Создание сеанса для фильма в определенном зале и времени
        Date showtime1 = new Date(); // Время начала сеанса - текущее время
        Showtime showtime = new Showtime(movie, hall, showtime1);
        ticketSystem.addShowtime(showtime);

        // Продажа билета
        ticketSystem.bookTicket(showtime, 2, 3);

        // Печать плана зала с указанием занятых и свободных мест
        ticketSystem.printSeatLayout(showtime);

        // Поиск ближайшего сеанса выбранного фильма
        Showtime nearestShowtime = ticketSystem.findNearestShowtime("Avengers: Endgame");
        if (nearestShowtime != null) {
            System.out.println("Nearest Showtime for 'Avengers: Endgame': " + nearestShowtime.getTime());
        } else {
            System.out.println("No upcoming showtimes for 'Avengers: Endgame'");
        }
    }
}
