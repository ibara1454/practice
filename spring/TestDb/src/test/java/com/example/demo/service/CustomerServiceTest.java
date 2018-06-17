package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class) アノテーション
// Spring BootでJUnitテストするときつける
// つけないとSpring Bootの機能が正しく動作しない(DI, DB等)
// @SpringBootTest アノテーション
// SpringのJava/XML Based Configurationなどの設定を読み込む
// つけないとBeanが正しく設定されない
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @MockBean
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() {
    }

    @Test
    public void findOneExpectNullTest() {
        Optional<Customer> actual = customerService.findOne(null);
        assertThat(actual, is(Optional.empty()));
    }

    @Test
    public void findOneTest() {
        Integer id = 1;
        Optional<Customer> expect = Optional.of(new Customer(1, "Taro Yafu"));
        given(customerRepository.findById(id))
            .willReturn(Optional.of(new Customer(1, "Taro Yafu")));
        Optional<Customer> actual = customerService.findOne(id);

        assertThat(actual, is(expect));
    }

    @Test
    public void findOneExpectEmptyResultTest() {
        Integer id = 5;
        Optional<Customer> expect = Optional.empty();
        given(customerRepository.findById(id))
            .willReturn(expect);
        Optional<Customer> actual = customerService.findOne(id);

        assertThat(actual, is(expect));
    }
}
