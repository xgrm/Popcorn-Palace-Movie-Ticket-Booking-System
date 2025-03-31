package com.att.tdp.popcorn_palace;

import com.att.tdp.popcorn_palace.dto.BookingDTO;
import com.att.tdp.popcorn_palace.dto.MovieDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeDTO;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;



import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PopcornPalaceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ShowtimesRepository showtimesRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private MovieDTO getMovieByTitle(String movieTitle){
		return new MovieDTO(movieRepository.getByTitle(movieTitle).get());
	}
	private ShowtimeDTO getShowtimeById(Integer id){
		return new ShowtimeDTO(showtimesRepository.findById(id).get());
	}
	private List<ShowtimeDTO> getShowtimeList(){
		return showtimesRepository.findAll().stream().map(showtime -> new ShowtimeDTO(showtime)).collect(Collectors.toList());
	}
	private List<MovieDTO> getMovieList(){
		return movieRepository.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	}
	@Test
	@Order(1)
	void testAddMovie() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MovieDTO movieRequest = new MovieDTO();
		movieRequest.setTitle("Sample Movie Title");
		movieRequest.setGenre("Action");
		movieRequest.setDuration(120);
		movieRequest.setRating(8.7);
		movieRequest.setReleaseYear(2025);
		HttpEntity<MovieDTO> requestEntity = new HttpEntity<>(movieRequest, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/movies", requestEntity, String.class);
		try {
		MovieDTO movieResponse = objectMapper.readValue(response.getBody(), MovieDTO.class);
		movieRequest.setId(movieResponse.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(movieResponse, movieRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.err.println("Error parsing JSON response: " + e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testUpdateMovie() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MovieDTO movieRequest = getMovieList().get(0);
		movieRequest.setDuration(125);

		HttpEntity<MovieDTO> requestEntity = new HttpEntity<>(movieRequest, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"/movies/update/" + movieRequest.getTitle(),
				HttpMethod.PUT,
				requestEntity,
				String.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(getMovieByTitle(movieRequest.getTitle()), movieRequest);
	}

	@Test
	@Order(3)
	void testGetAllRestaurants() {
		ResponseEntity<String> response = restTemplate.getForEntity( "/movies/all", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			List<MovieDTO> movieResponse = objectMapper.readValue(response.getBody(), new TypeReference<List<MovieDTO>>() {});
			assertEquals(movieResponse, movieRepository.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.err.println("Error parsing JSON response: " + e.getMessage());
		}

	}

	@Test
	@Order(9)
	void testDeleteMovie() {
		List<MovieDTO> movieDTOList = getMovieList();


		ResponseEntity<String> response = restTemplate.exchange(
				"/movies/" + movieDTOList.get(0).getTitle(),
				HttpMethod.DELETE,
				null,
				String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}


	@Test
	@Order(4)
	void testAddShowtime() {
//		{ "movieId": 1, "price":20.2, "theater": "Sample Theater", "startTime": "2025-02-14T11:47:46.125405Z", "endTime": "2025-02-14T14:47:46.125405Z" }

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ShowtimeDTO showtimeRequest = new ShowtimeDTO();
		showtimeRequest.setMovieId(getMovieList().get(0).getId());
		showtimeRequest.setPrice(20.2);
		showtimeRequest.setTheater("Sample Theater");
		showtimeRequest.setStartTime(Date.from(Instant.parse("2025-02-14T11:47:46.125405Z")));
		showtimeRequest.setEndTime(Date.from(Instant.parse("2025-02-14T14:47:46.125405Z")));

		HttpEntity<ShowtimeDTO> requestEntity = new HttpEntity<>(showtimeRequest, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/showtimes", requestEntity, String.class);
		try {
			ShowtimeDTO showtimeResponse = objectMapper.readValue(response.getBody(), ShowtimeDTO.class);
			showtimeRequest.setId(showtimeResponse.getId());
			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(showtimeResponse, showtimeRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.err.println("Error parsing JSON response: " + e.getMessage());
		}
	}

	@Test
	@Order(5)
	void testUpdateShowtime() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ShowtimeDTO showtimeRequest = getShowtimeList().get(0);
		showtimeRequest.setPrice(123.3);

		HttpEntity<ShowtimeDTO> requestEntity = new HttpEntity<>(showtimeRequest, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"/showtimes/update/" + showtimeRequest.getId(),
				HttpMethod.PUT,
				requestEntity,
				String.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(getShowtimeById(showtimeRequest.getId()), showtimeRequest);
	}

	@Test
	@Order(6)
	void testGetShowtimeById() {
		ShowtimeDTO showtimeRequest = getShowtimeList().get(0);
		ResponseEntity<String> response = restTemplate.getForEntity( "/showtimes/"+showtimeRequest.getId().toString(), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			ShowtimeDTO showtimeResponse = objectMapper.readValue(response.getBody(), ShowtimeDTO.class);
			assertEquals(showtimeRequest.getId(),showtimeResponse.getId());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.err.println("Error parsing JSON response: " + e.getMessage());
		}

	}

	@Test
	@Order(8)
	void testDeleteShowtime() {
		List<ShowtimeDTO> showtimeDTOList = getShowtimeList();


		ResponseEntity<String> response = restTemplate.exchange(
				"/showtimes/" + showtimeDTOList.get(0).getId(),
				HttpMethod.DELETE,
				null,
				String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}


	@Test
	@Order(7)
	void testBooking() {
//		{ "showtimeId": 1, "seatNumber": 15 , userId:"84438967-f68f-4fa0-b620-0f08217e76af"}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		BookingDTO bookingRequest = new BookingDTO();
		bookingRequest.setShowtimeId(getShowtimeList().get(0).getId());
		bookingRequest.setSeatNumber(15);
		bookingRequest.setUserId(UUID.fromString("84438967-f68f-4fa0-b620-0f08217e76af"));


		HttpEntity<BookingDTO> requestEntity = new HttpEntity<>(bookingRequest, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/bookings", requestEntity, String.class);
		try {
			BookingDTO bookingResponse = objectMapper.readValue(response.getBody(), BookingDTO.class);
			bookingRequest.setBookingId(bookingResponse.getBookingId());
			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(bookingResponse, bookingRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.err.println("Error parsing JSON response: " + e.getMessage());
		}
	}

}
