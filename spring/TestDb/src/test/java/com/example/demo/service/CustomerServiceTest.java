package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import com.example.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

// @RunWith(SpringRunner.class) アノテーション
// Spring BootでJUnitテストするときつける
// つけないとSpring Bootの機能が正しく動作しない(DI, DB等)
// @SpringBootTest アノテーション
// SpringのJava/XML Based Configurationなどの設定を読み込む
// つけないとBeanが正しく設定されない

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() {
    }

    @Test(expected = NullPointerException.class)
    public void findOneExceptNullPointerExceptionTest() {
        customerService.findOne(null);
    }

    @Test
    public void findOneTest() {
        Integer id = 1;
        Customer customer = customerService.findOne(id).get();
        assertThat(customer.getId(), is(id));
        assertThat(customer.getName(), is("Taro Yafu"));
    }

    @Test
    public void findOneExceptEmptyResultTest() {
        Integer id = 5;
        Optional<Customer> maybeCustomer = customerService.findOne(id);
        assertThat(maybeCustomer, is(Optional.empty()));
    }
}
