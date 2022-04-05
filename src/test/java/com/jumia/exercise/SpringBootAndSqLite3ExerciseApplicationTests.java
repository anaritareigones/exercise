package com.jumia.exercise;

import com.jumia.exercise.entity.Customer;
import com.jumia.exercise.repository.CustomerRepository;
import com.jumia.exercise.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpringBootAndSqLite3ExerciseApplicationTests {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerService customerService;

	static List<Customer> list = new ArrayList<>();

	static Customer customer1 = new Customer(60L, "Michael Scott", "(251) 988200000");
	static Customer customer2 = new Customer(70L, "Dwight Schrute", "(258) 847602609");
	static Customer customer3 = new Customer(80L, "Stanley Hudson", "(256) 99404");
	static Customer customer4 = new Customer(90L, "Kevin Malone", "(256) 7503O6263");
	static Customer customer5 = new Customer(99L, "Pam Beesly", "(256) 7734127498");

	@BeforeAll
	static void setUp() {
		list.add(customer1);
		list.add(customer2);
		list.add(customer3);
		list.add(customer4);
		list.add(customer5);
	}


	@Test
	void contextLoads() {
	}

	@Test
	public void testEmptyCountryAndState()
	{
		Mockito.when(customerRepository.findAll())
				.thenReturn(list);
			List<Customer> listResponse= customerService.findWithFilters("", "");

		assertThat(listResponse.equals(list)).isTrue();
	}

	@Test
	public void testFilledCountryEmptyState()
	{
		List<Customer> list2 = new ArrayList<>();
		list2.add(customer3);
		list2.add(customer4);
		list2.add(customer5);

		Mockito.when(customerRepository.findAll())
				.thenReturn(list);
		List<Customer> listResponse= customerService.findWithFilters("Uganda", "");

		assertThat(listResponse.equals(list2)).isTrue();
	}

	@Test
	public void testWithUnlistedCountry()
	{
		Mockito.when(customerRepository.findAll())
				.thenReturn(list);
		List<Customer> listResponse= customerService.findWithFilters("Portugal", "");

		assertThat(listResponse.isEmpty()).isTrue();
	}

	@Test
	public void testWithAllCampsFilled()
	{
		List<Customer> list2 = new ArrayList<>();
		list2.add(customer3);
		list2.add(customer4);
		list2.add(customer5);

		Mockito.when(customerRepository.findAll())
				.thenReturn(list);
		List<Customer> listResponse= customerService.findWithFilters("Uganda", "invalid");

		assertThat(listResponse.equals(list2)).isTrue();
	}

	@Test
	public void testWithStateOnly()
	{
		List<Customer> list2 = new ArrayList<>();
		list2.add(customer1);
		list2.add(customer2);

		Mockito.when(customerRepository.findAll())
				.thenReturn(list);
		List<Customer> listResponse= customerService.findWithFilters("", "valid");

		assertThat(listResponse.equals(list2)).isTrue();
	}
}
